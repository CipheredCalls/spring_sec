package com.cipheredcalls.spring_sec.configs;

import com.cipheredcalls.spring_sec.entities.User;
import com.cipheredcalls.spring_sec.repos.UserRepo;
import com.cipheredcalls.spring_sec.services.SecurityUserDetailsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigs extends WebSecurityConfigurerAdapter implements InitializingBean {

    final UserRepo userRepo;
    final SecurityUserDetailsService userDetailsService ;

    public SecurityConfigs(UserRepo userRepo, SecurityUserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                    .authorizeRequests().anyRequest().authenticated()
                .and()
                    .csrf().disable()
                    .headers().frameOptions().disable();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = User.builder().username("Mohammed").password("123456")
                .enabled(true).phoneNumber("056232323").build();
        User user2 = User.builder().username("Mohammed2").password("12345")
                .enabled(true).phoneNumber("056343434").build();
        userRepo.save(user);
        userRepo.save(user2);
    }
}
