package com.example.aulas.aula4.controller;

import com.example.aulas.aula4.model.Mensagem;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula4")
public class OiController {

    @GetMapping("/ola")
    public String ola() {
        return "Olá, Elas Tech!";
    }

    @GetMapping("/mensagem")
    public Mensagem mensagem() {
        return new Mensagem("Bem-vindas ao Spring!", "Flora");
    }

    // GET /aula4/ola/Ana -> Olá, Ana!
    @GetMapping("/ola/{nome}")
    public String olaPorNome(@PathVariable String nome) {
        return "Olá, " + nome + "!";
    }
    // GET /aula4/saudacao?nome=Bia -> Seja bem-vinda, Bia!
// GET /aula4/saudacao -> Seja bem-vinda, visitante!
    @GetMapping("/saudacao")
    public String saudacao(@RequestParam(defaultValue = "visitante") String nome) {
        return "Seja bem-vinda, " + nome + "!";
    }

}
