package ru.artdy.RestTestTask.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say")
    public Map<String, String> hello() {
        return Collections.singletonMap("message", "hello!");
    }
}
