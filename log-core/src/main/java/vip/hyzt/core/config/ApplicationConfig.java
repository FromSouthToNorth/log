package vip.hyzt.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

/**
 * Program annotation configuration
 *
 * Indicates that the proxy object is exposed through the aop framework,
 * and AopContext can access
 *
 * Specify the path of the package of the Mapper class to be scanned
 * @author hy
 * @since 2021/10/18
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("vip.hyzt.**.mapper")
public class ApplicationConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }

}
