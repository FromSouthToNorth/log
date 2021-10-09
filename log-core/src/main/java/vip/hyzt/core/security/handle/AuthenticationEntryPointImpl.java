package vip.hyzt.core.security.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import vip.hyzt.common.constant.HttpStatus;
import vip.hyzt.common.utils.ServletUtils;
import vip.hyzt.core.domain.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Authentication failure processing class Return unauthorized
 * @author hy
 * @since 2021/10/9
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, AuthenticationException e) {

        int code = HttpStatus.UNAUTHORIZED;
        String message = String.format("Requested access: %s, authentication failed, unable to access system resources", httpServletRequest.getRequestURI());
        ServletUtils.renderString(httpServletResponse, JSON.toJSONString(Result.error(code, message)));
    }
}
