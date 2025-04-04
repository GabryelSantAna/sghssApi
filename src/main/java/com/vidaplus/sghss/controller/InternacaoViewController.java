package com.vidaplus.sghss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/internacoes")
public class InternacaoViewController {

    @GetMapping
    public String exibirPaginaInternacoes() {
        return "internacoes"; // Nome do arquivo HTML na pasta templates
    }
}
