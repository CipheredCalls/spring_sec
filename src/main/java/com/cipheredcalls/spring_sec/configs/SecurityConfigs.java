package com.cipheredcalls.spring_sec.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfigs {
    @Bean
    UserDetailsService userDetailsService(){
        var userDetailsService = new InMemoryUserDetailsManager();

        userDetailsService.createUser(
                User.withUsername("Mohammed")
                        .password("123456")
                        .authorities("read")
                        .build()
        );

        return userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
