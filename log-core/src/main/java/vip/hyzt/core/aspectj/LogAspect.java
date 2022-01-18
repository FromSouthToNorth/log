package vip.hyzt.core.aspectj;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import vip.hyzt.common.annotation.Log;
import vip.hyzt.common.enums.BusinessStatus;
import vip.hyzt.common.utils.ServletUtils;
import vip.hyzt.common.utils.ip.IpUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.core.domain.LoginUser;
import vip.hyzt.core.manager.AsyncManager;
import vip.hyzt.core.manager.factory.AsyncFactory;
import vip.hyzt.core.security.SecurityService;
import vip.hyzt.system.domain.SysOperLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * Operation log record Aop
 * @author hy
 * @since 2021/10/18
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Execute after processing the request
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * Intercept abnormal operations
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    private void handleLog(final  JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        try {
            LoginUser loginUser = SecurityService.getLoginUser();
            System.out.println("loginUser = " + loginUser);
            SysOperLog operLog = new SysOperLog();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            operLog.setOperIp(ip);
            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            if (!ObjectUtils.isEmpty(loginUser)) {
                operLog.setOperName(loginUser.getUsername());
            }

            if (!ObjectUtils.isEmpty(e)) {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setOperId(IdUtils.simpleUUID());
            operLog.setMethod(className + "." + methodName + "()");
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        }
        catch (Exception ex) {
            logger.error("==前置通知异常==");
            logger.error("异常信息: {}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void getControllerMethodDescription(JoinPoint joinPoint, Log log,
                  SysOperLog operLog, Object jsonResult) throws Exception {
        operLog.setBusinessType(log.businessType().ordinal());
        operLog.setTitle(log.title());
        operLog.setOperatorType(log.operatorType().ordinal());
        if (log.isSaveRequestData()) {
            setRequestValue(joinPoint, operLog);
        }
        if (log.isSaveResponseData() && !ObjectUtils.isEmpty(jsonResult)) {
            operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }

    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog) {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
        }
        else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (!ObjectUtils.isEmpty(paramsArray)) {
            for (Object value : paramsArray) {
                if (!ObjectUtils.isEmpty(value) && !isFilterObject(value)) {
                    try {
                        Object json = JSON.toJSON(value);
                        params.append(json.toString()).append(" ");
                    }
                    catch (Exception e) {
                        //
                    }
                }
            }
        }
        return params.toString();
    }

    /**
     * Determine whether to filter the object
     * @param o Object information
     * @return If it is an object that needs to be filtered, it returns true; otherwise, it returns false.
     */
    @SuppressWarnings("rawtypes")
    private boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof  MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof  MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }

}
