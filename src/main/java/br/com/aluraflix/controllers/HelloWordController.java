package br.com.aluraflix.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    

    @GetMapping("/hello")
    public String hello(){
        return "Hello, World!";
    }
}