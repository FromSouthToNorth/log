package vip.hyzt.core.web.service;

import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vip.hyzt.common.constant.Constants;
import vip.hyzt.common.utils.ServletUtils;
import vip.hyzt.common.utils.ip.AddressUtils;
import vip.hyzt.common.utils.ip.IpUtils;
import vip.hyzt.common.utils.uuid.IdUtils;
import vip.hyzt.core.domain.LoginUser;
import vip.hyzt.common.utils.redis.RedisCache;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Token verification processing
 * @author hy
 * @since 2021/10/
 */
@Component
public class TokenService {

    /** Token custom ID */
    @Value("${token.header}")
    private String header;

    /** Token key */
    @Value("${token.secret}")
    private String secret;

    /** Token validity period (default 60 minutes) */
    @Value("${token.expireTime}")
    private int expireTime;

    private static final long MILLIS_SECOND = 1000L;

    private static final long MILLIS_MINUTE = 60L * MILLIS_SECOND;

    private static final long MILLIS_MINUTE_TEN = 20L * 60L * 1000L;

    @Autowired
    private RedisCache redisCache;

    /**
     * Return Obtain user identity information
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (!ObjectUtils.isEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                return redisCache.getCacheObject(userKey);
            }
            catch (Exception e) {
                //
            }
        }
        return null;
    }

    /**
     * Delete cache token
     */
    public void deleteLoginUser(String token) {
        if (!ObjectUtils.isEmpty(token)) {
            String tokenKey = getTokenKey(token);
            redisCache.deleteObject(tokenKey);
        }
    }

    /**
     * Set user identity information
     * @param loginUser - login user info
     */
    public void setLoginUser(LoginUser loginUser) {
        if (ObjectUtils.isEmpty(loginUser) && !ObjectUtils.isEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * Return creation token
     * @param loginUser - login user info
     */
    public String createToken(LoginUser loginUser) {
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * Verify that the token validity period is less than 20 minutes away, and the cache is automatically refreshed
     * @param loginUser - login user info
     */
    public void verifyToken(LoginUser loginUser) {
        Long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * Refresh token validity period
     * @param loginUser - login user info
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // Cache loginUser according to uuid
        String userTokenKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userTokenKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * Set user agent information
     * @param loginUser - login user info
     */
    public void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setLoginIpAddress(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowserType(userAgent.getBrowser().getName());
        loginUser.setOperatingSystem(userAgent.getOperatingSystem().getName());
    }

    /**
     * Return generate token from data claim
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Return get the data claim from the token
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Return request token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (!ObjectUtils.isEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

}
