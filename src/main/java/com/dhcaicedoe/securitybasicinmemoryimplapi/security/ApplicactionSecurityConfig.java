package com.dhcaicedoe.securitybasicinmemoryimplapi.security;

import com.dhcaicedoe.securitybasicinmemoryimplapi.shared.advice.exception.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;

import static com.dhcaicedoe.securitybasicinmemoryimplapi.security.ApplicationUserRole.ADMIN;
import static com.dhcaicedoe.securitybasicinmemoryimplapi.security.ApplicationUserRole.USER;

/**
 * Allows you to configure the security of the application
 *
 * @author Daniel Caicedo
 * @version 1.0, 06/04/2023
 */

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ApplicactionSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    /**
     * <p>
     * Configure request security
     * </p>
     * <ul>
     *     <li>Disable crf.</li>
     *     <li>Allow unrestricted requests to authenticating endpoints.</li>
     *     <li>The authentication type is Basic.</li>
     * </ul>
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/auth/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/**/demo/user").hasAnyRole(USER.name())
                .antMatchers(HttpMethod.GET, "/api/**/demo/admin").hasAnyAuthority("admin:get")
                .antMatchers(HttpMethod.GET, "/api/**/demo/admin").hasAnyRole(USER.name(), ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     * Bean that allows to store preconfigured users in memory
     *
     * @return instance of one of the implementations of UserDetailSservice which is the storage of users in memory
     * InMemoryUserDetailsManager that contains the created users
     */
    @Bean
    @Override protected UserDetailsService userDetailsService() {
        UserDetails userDetails1 =
                User.builder()
                        .username("juan")
                        .password(passwordEncoder.encode("password"))
                        .roles(USER.name())
                        .build();
        UserDetails userDetails2 =
                User.builder().
                        username("genesis")
                        .password(passwordEncoder.encode("password"))
                        .roles(ADMIN.name())
                        .authorities(ADMIN.getAuthorities())
                        .build();
        UserDetails userDetails3 =
                User.builder()
                        .username("monica")
                        .password(passwordEncoder.encode("password"))
                        .roles(USER.name(), ADMIN.name())
                        .build();
        return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
    }

    /**
     * Bean for handling exceptions for access denied
     *
     * @return custom implementation of AccessDeniedHandler for handling access denied errors
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

}
