package vip.hyzt.core.security.handle;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.common.constant.HttpStatus;
import vip.hyzt.common.utils.ServletUtils;
import vip.hyzt.core.domain.LoginUser;
import vip.hyzt.core.domain.Result;
import vip.hyzt.core.manager.AsyncManager;
import vip.hyzt.core.manager.factory.AsyncFactory;
import vip.hyzt.core.web.service.TokenService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom exit processing class Return success
 * @author hy
 * @since 2021/10/18
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    /**
     * Exit processing
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        LoginUser loginUser = tokenService.getLoginUser(request);
        if (!ObjectUtils.isEmpty(loginUser)) {
            String username = loginUser.getUsername();
            tokenService.deleteLoginUser(loginUser.getToken());
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGOUT, "exit successfully"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(Result.error(HttpStatus.SUCCESS, "exit successfully")));
    }

}
