package com.raven.framework.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * SpringSecurity配置
 * @author : raven
 * @since : 2024-01-05 15:26
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    @Resource
    AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    AccessDeniedHandler accessDeniedHandler;

    @Resource
    JwtTokenFilter jwtTokenFilter;

//    @Bean
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                // 不走springsecurity的过滤器，开放的api
//                .antMatchers("/login")
//                .antMatchers("/swagger-ui.html")
//                .antMatchers("/webjars/**")
//                .antMatchers("/v2/**")
//                .antMatchers("/swagger-resources/**");
//    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 因为我们的token是无状态的，不需要跨站保护
                .cors(withDefaults());

        //最后，我们定义 filter，用来替换原来的UsernamePasswordAuthenticationFilter

        httpSecurity.addFilterAt(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}

