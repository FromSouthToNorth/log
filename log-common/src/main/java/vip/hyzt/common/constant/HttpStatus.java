package vip.hyzt.common.constant;

/**
 * Result status code
 * @author hy
 * @since 2021/10/9
 */
public final class HttpStatus {

    /**
     * Successful operation
     */
    public static final int SUCCESS = 200;

    /**
     * Object created successfully
     */
    public static final int CREATED = 201;

    /**
     * Request has been accepted
     */
    public static final int ACCEPTED = 202;

    /**
     * The operation was executed successfully, but no data was returned
     */
    public static final int NO_CONTENT = 204;

    /**
     * Resource has been removed
     */
    public static final int MOVED_PERM = 301;

    /**
     * Redirect
     */
    public static final int SEE_OTHER = 303;

    /**
     * The resource has not been modified
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * Parameter list error (missing, format mismatch)
     */
    public static final int BAD_REQUEST = 400;

    /**
     * unauthorized
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * Access restricted, authorization expired
     */
    public static final int FORBIDDEN = 403;

    /**
     * Resource, service not found
     */
    public static final int NOT_FOUND = 404;

    /**
     * Http method not allowed
     */
    public static final int BAD_METHOD = 405;

    /**
     * Resource conflict, or resource is locked
     */
    public static final int CONFLICT = 409;

    /**
     * Unsupported data, media type
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * Internal System Error
     */
    public static final int ERROR = 500;

    /**
     * Interface not implemented
     */
    public static final int NOT_IMPLEMENTED = 501;

}
