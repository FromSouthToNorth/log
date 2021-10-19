package vip.hyzt.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;
import vip.hyzt.core.security.filter.JwtAuthenticationTokenFilter;
import vip.hyzt.core.security.handle.AuthenticationEntryPointImpl;
import vip.hyzt.core.security.handle.LogoutSuccessHandlerImpl;

/**
 * Spring security configuration
 * @author hy
 * @since 2021/10/9
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private CorsFilter corsFilter;

    /**
     * Solution Cannot inject AuthenticationManager directly
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * anyRequest | match all request paths
     * access | Can be accessed when the result of SpringEl expression is true
     * anonymous | anonymously accessible
     * denyAll | User cannot access
     * fullyAuthenticated | The user is fully authenticated and can access (automatically log in under non-remember-me)
     * hasAnyAuthority | If there are parameters, the parameters indicate permissions, and any one of them can access
     * hasAnyRole | If there is a parameter, the parameter represents a role, any one of them can access
     * hasAuthority | If there is a parameter, the parameter indicates the permission, then the permission can be accessed
     * hasIpAddress | If there is a parameter, the parameter represents the IP address, if the user IP matches the parameter, you can access
     * hasRole | If there is a parameter, the parameter indicates a role, then its role can be accessed
     * permitAll | Users can access at will
     * rememberMe | Allow users who log in through remember-me to access
     * authenticated | The user can access after logging in
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login", "/register", "/captchaImage", "/home/**").anonymous()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/**/*/css",
                        "/**/*.js",
                        "/profile/**").permitAll()
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/*/api-docs").anonymous()
                .antMatchers("/druid/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        http.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    /**
     * Strong Hash Hash Encryption Implementation
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Identity authentication interface
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
