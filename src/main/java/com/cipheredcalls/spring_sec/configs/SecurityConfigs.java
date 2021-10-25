package com.cipheredcalls.spring_sec.configs;

import com.cipheredcalls.spring_sec.entities.SecurityUser;
import com.cipheredcalls.spring_sec.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

    DataSource dataSource ;

    public SecurityConfigs(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User user = User.builder().username("Mohammed").password("123456")
                .enabled(true).phoneNumber("056232323").build();
        User user2 = User.builder().username("Mohammed2").password("12345")
                .enabled(true).phoneNumber("056343434").build();

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withUser(new SecurityUser(user))
                .withUser(new SecurityUser(user2))
                .withDefaultSchema()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable();
    }

}
