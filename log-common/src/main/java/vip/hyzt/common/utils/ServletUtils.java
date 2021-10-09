package vip.hyzt.common.utils;

import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.hyzt.common.constant.HttpStatus;

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

    private final static String CONTENT_TYPE = "application/json";

    private final static String CHARACTER_ENCODING = "utf-8";

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
    private static Integer getParameterToInt(String name) {
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
            response.setStatus(HttpStatus.SUCCESS);
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(CHARACTER_ENCODING);
            response.getWriter().print(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
