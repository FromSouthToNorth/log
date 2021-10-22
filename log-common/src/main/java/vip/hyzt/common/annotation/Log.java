package vip.hyzt.common.annotation;

import vip.hyzt.common.enums.BusinessType;
import vip.hyzt.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * Custom operation log record annotation
 * @author hy
 * @since 2021/10/18
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * Module
     */
    public String title() default "";

    /**
     * Function
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * Operator category
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * Whether to save the requested parameters
     */
    public boolean isSaveRequestData() default true;

    /**
     * Whether to save the response parameters
     */
    public boolean isSaveResponseData() default true;

}
