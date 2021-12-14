package com.cipheredcalls.spring_sec.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {


    @GetMapping("/admin")
    public ResponseEntity<?> admin(){
        return ResponseEntity.ok("admin page");
    }

    @GetMapping("/user")
    public ResponseEntity<?> user(){
        return ResponseEntity.ok("user page");
    }

    @GetMapping("/")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("Hi , I am a get");
    }

    @PostMapping("/")
    public ResponseEntity<?> post(){
        return ResponseEntity.ok("Hi , I am a post");
    }
}
