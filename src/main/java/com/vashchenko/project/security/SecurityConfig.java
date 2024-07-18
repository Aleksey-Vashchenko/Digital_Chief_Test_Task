package com.vashchenko.project.security;

import com.vashchenko.project.logging.LoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
public class SecurityConfig {


    private final UserDetailsService userDetailsService;
    private final LoggingFilter loggingFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          LoggingFilter filter) {
        this.userDetailsService = userDetailsService;
        this.loggingFilter=filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/registration").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/users").permitAll();
                    auth.requestMatchers(HttpMethod.POST,"/users/posts").authenticated();
                    auth.requestMatchers("/users/posts/*").authenticated();
                    auth.requestMatchers(HttpMethod.GET,"/users/me").authenticated();
                    auth.requestMatchers(HttpMethod.GET,"/users/*").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/users/me/posts").authenticated();
                    auth.requestMatchers(HttpMethod.GET,"/users/*/posts").permitAll();
                    auth.requestMatchers(HttpMethod.DELETE,"/users").authenticated();
                    auth.anyRequest().permitAll();
                })
                .addFilterBefore(loggingFilter, WebAsyncManagerIntegrationFilter.class)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}
