package com.cipheredcalls.spring_sec.configs;

import com.cipheredcalls.spring_sec.encoders.FakePasswordEncoder;
import com.cipheredcalls.spring_sec.entities.Authority;
import com.cipheredcalls.spring_sec.entities.User;
import com.cipheredcalls.spring_sec.repos.AuthorityRepo;
import com.cipheredcalls.spring_sec.repos.UserRepo;
import com.cipheredcalls.spring_sec.services.SecurityUserDetailsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.util.List;

@Configuration
public class SecurityConfigs extends WebSecurityConfigurerAdapter implements InitializingBean {

    final UserRepo userRepo;
    final AuthorityRepo authorityRepo;
    final SecurityUserDetailsService userDetailsService ;
    FakePasswordEncoder fake = new FakePasswordEncoder();

    public SecurityConfigs(UserRepo userRepo, AuthorityRepo authorityRepo, SecurityUserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(fake);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                    .authorizeRequests()
                .mvcMatchers("/admin").hasAnyAuthority("ADMIN")
                .mvcMatchers("/user").hasAnyAuthority("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                    .headers().frameOptions().disable();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Authority auth1 = authorityRepo.save(new Authority("ADMIN"));
        Authority auth2 = authorityRepo.save(new Authority("USER"));

        User user = User.builder().username("Mohammed").password(fake.encode("123456"))
                .enabled(true).phoneNumber("056232323").authorities(List.of(auth1)).build(); // admin
        User user2 = User.builder().username("Mohammed2").password(fake.encode("12345"))
                .enabled(true).phoneNumber("056343434").authorities(List.of(auth2)).build();// user

        userRepo.save(user);
        userRepo.save(user2);
    }
}
