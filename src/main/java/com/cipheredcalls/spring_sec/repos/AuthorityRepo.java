package com.cipheredcalls.spring_sec.repos;

import com.cipheredcalls.spring_sec.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority,String> { }
