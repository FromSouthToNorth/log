package vip.hyzt.common.utils;

import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.hyzt.common.constant.HttpStatus;
import vip.hyzt.common.core.text.Convert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet utility class
 * @author hy
 * @since 2021/10/9
 */
public abstract class ServletUtils {

    /**
     * Get String parameter
     * @param name Parameter name
     * @return parameter
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * Get Integer parameter
     * @param name Parameter name
     * @return parameter
     */
    @Nullable
    public static Integer getParameterToInt(String name) {
        String parameter = getRequest().getParameter(name);
        try {
            return Integer.parseInt(parameter);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Get request
     * @return Request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * Ger response
     * @return Response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * Get Session
     * @return Session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * Render the string to the client
     * @param response Response
     * @param message message
     */
    public static void renderString(HttpServletResponse response, String message) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name)
    {
        return Convert.toBool(getRequest().getParameter(name));
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        return Convert.toBool(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 是否是Ajax异步请求
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json")) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return StringUtils.inStringIgnoreCase(ajax, "json", "xml");
    }
}
