package br.edu.iftm.autenticaautoriza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {
    
    @GetMapping(value="/model-attributes")
    public String getMethodName() {
        return "imprime-model-attributes";
    }
    
}
