package vip.hyzt.common.annotation;


import java.lang.annotation.*;

/**
 * Data permission filter annotation
 * @author hy
 * @since 2021/10/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * Alias of user table
     */
    public String userAlias() default "";

}
