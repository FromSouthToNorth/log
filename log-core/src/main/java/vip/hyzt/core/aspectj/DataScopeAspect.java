package vip.hyzt.core.aspectj;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.annotation.DataScope;
import vip.hyzt.core.domain.LoginUser;
import vip.hyzt.core.security.SecurityService;
import vip.hyzt.system.domain.BaseEntity;
import vip.hyzt.system.domain.SysRole;
import vip.hyzt.system.domain.SysUser;

/**
 * Data filtering processing
 * @author hy
 * @since 2021/10/20
 */
@Aspect
@Component
public class DataScopeAspect {

    /**
     * 全部数据权限
     */
    public static final int DATA_SCOPE_ALL = 1;

    /**
     * 自定数据权限
     */
    public static final int DATA_SCOPE_CUSTOM = 2;

    /**
     * 仅本人数据权限
     */
    public static final int DATA_SCOPE_SELF = 3;

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, DataScope controllerDataScope) {
        clearDataScope(point);
        handleDataScope(point, controllerDataScope);
    }


    private void handleDataScope(final JoinPoint joinPoint, DataScope controllerDataScope) {
        // Get the current old iron
        LoginUser loginUser = SecurityService.getLoginUser();
        if (!ObjectUtils.isEmpty(loginUser)) {
            SysUser userInfo = loginUser.getUserInfo();
            // If you are a super administrator, do not filter data
            if (!ObjectUtils.isEmpty(userInfo) && !userInfo.isAdmin()) {
                dataScopeFilter(joinPoint, userInfo, controllerDataScope.userAlias());
            }
        }
    }

    /**
     * Data range filtering
     * @param joinPoint - Cut-off point
     * @param user - user info
     * @param userAlias - Alias
     */
    private static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String userAlias) {
        StringBuilder builder = new StringBuilder();
        for (SysRole role : user.getRoles()) {
            int dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL == dataScope) {
                builder = new StringBuilder();
                break;
            }
            else if (DATA_SCOPE_SELF == dataScope) {
                if (StringUtils.isNotBlank(userAlias)) {
                    builder.append(String.format(" or %s.user_id = %s", userAlias, user.getUserId()));
                }
                else {
                    // The data permission is only for me and there is no userAlias ​​alias, do not query any data
                    builder.append(" or 1=0 ");
                }
            }
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            Object arg = joinPoint.getArgs()[0];
            if (!ObjectUtils.isEmpty(arg) && arg instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) arg;
                baseEntity.getParams().put(DATA_SCOPE, " and (" + builder.substring(4) + ")");
            }
        }
    }

    /**
     * Clear the params.dataScope parameter before splicing permission sql to prevent injection
     * @param joinPoint - Cut-off point
     */
    private void clearDataScope(final JoinPoint joinPoint) {
        Object arg = joinPoint.getArgs()[0];
        if (!ObjectUtils.isEmpty(arg) && arg instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) arg;
            baseEntity.getParams().put(DATA_SCOPE, "");
        }
    }

}
