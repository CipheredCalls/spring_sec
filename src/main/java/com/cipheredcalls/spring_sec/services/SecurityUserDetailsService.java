package com.cipheredcalls.spring_sec.services;

import com.cipheredcalls.spring_sec.entities.SecurityUser;
import com.cipheredcalls.spring_sec.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    UserRepo userRepo ;

    public SecurityUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new SecurityUser(
                userRepo.findOneByUsername(s)
                        .orElseThrow(() -> new UsernameNotFoundException(s + " does not exist"))
        );
    }
}
