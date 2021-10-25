package com.cipheredcalls.spring_sec.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private String username ;
    private String password ;
    private boolean enabled ;
    private String phoneNumber ;
}
