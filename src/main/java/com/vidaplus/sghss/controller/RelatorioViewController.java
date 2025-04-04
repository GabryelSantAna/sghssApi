package com.vidaplus.sghss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/relatorios")
public class RelatorioViewController {

    @GetMapping
    public String exibirPaginaRelatorios() {
        return "relatorios"; // Nome do arquivo HTML na pasta templates
    }
}
