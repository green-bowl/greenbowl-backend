package com.greenbowl.greenbowlserver.user.adapter.in.web.configuration;

import com.greenbowl.greenbowlserver.user.adapter.in.web.security.CustomAuthenticationEntryPoint;
import com.greenbowl.greenbowlserver.user.adapter.in.web.security.JwtAuthenticationFilter;
import com.greenbowl.greenbowlserver.user.port.out.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final AuthenticationPort authenticationPort;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint) // 인증되지 않았을 시 custom entry point 사용
                .and()
                .httpBasic().disable() // JWT 사용
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/", "/swagger-ui/**", "/v2/api-docs", "/swagger-resources/**",
                        "/**/login", "/**/token")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(authenticationPort, customAuthenticationEntryPoint),
                        UsernamePasswordAuthenticationFilter.class)
                .logout().permitAll();

        return http.build();
    }
}
