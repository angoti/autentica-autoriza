package br.edu.iftm.autenticaautoriza.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String loginNameDuplicado(java.sql.SQLIntegrityConstraintViolationException ex, Model model) {
        System.out.println(">>>>>>>>>>>>>>>> passei aqui");
        String errorMessage = ex.getMessage();
        if (errorMessage.contains("Duplicate entry")) {
            model.addAttribute("mensagem", "E-mail já cadastrado. Tente cadastrar outro e-mail");
        } else {
            model.addAttribute("mensagem", errorMessage);
        }
        return "/error";
    }

}
