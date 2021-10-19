package vip.hyzt.common.annotation;

import java.lang.annotation.*;

/**
 * Custom annotations to prevent repeated submission of forms
 * @author hy
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * Interval time (ms), less than this time is regarded as repeated submission
     */
    public int interval() default 5000;

    /**
     * Prompt message
     */
    public String message() default "Duplicate submission is not allowed, please try again later";

}
