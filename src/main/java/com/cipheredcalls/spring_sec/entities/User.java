package com.cipheredcalls.spring_sec.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String username ;
    private String password ;
    private boolean enabled ;
    private String phoneNumber ;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities ;
}
