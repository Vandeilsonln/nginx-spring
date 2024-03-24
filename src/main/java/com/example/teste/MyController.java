package com.example.teste;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xablau")
public class MyController {

    @GetMapping("teste")
    public String myMethod() {
        return "Eita preula";
    }

    @GetMapping("mais-teste")
    public String anotherMethod() {
        return "Deu certo de novo";
    }

}
