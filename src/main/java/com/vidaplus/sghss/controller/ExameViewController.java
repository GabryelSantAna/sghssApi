package com.vidaplus.sghss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exames")
public class ExameViewController {

    @GetMapping
    public String exibirPaginaExames() {
        return "exames"; // Nome do arquivo HTML na pasta templates
    }
}
