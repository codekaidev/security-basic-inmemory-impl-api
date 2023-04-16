package com.dhcaicedoe.securitybasicinmemoryimplapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class to configure the encryption
 *
 * @author Daniel Caicedo
 * @version 1.0, 07/04/2023
 */
@Configuration
public class PasswordConfig {

    /**
     * Bean that allows configuring password encryption
     *
     * @return configured instance of a PasswordEncoder implementation
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}
