package com.example.aulas.aula5;

import com.example.aulas.aula4.model.Mensagem;
import com.example.aulas.aula5.model.Pet;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    /*1. Crie a rota GET /pets, que responde:
    Bem-vinda ao Pet Shop Elas Tech!*/
    @GetMapping
    public String pets() {
        return "Bem-vinda ao Pet Shop Elas Tech!";
    }

    /*2. Crie a rota GET /pets/horario, que responde:
    Abrimos de segunda a sábado, das 8h às 18h.*/
    @GetMapping("/horario")
    public String horario() {
        return "Abrimos de segunda a sábado, das 8h às 18h.";
    }

    /*3. Crie no pacote model a classe Pet, com os atributos nome (String), especie (String) e idade (int),
    um construtor que recebe os três e os getters. Depois crie a rota GET /pets/destaque, que responde com o JSON:

    { "nome": "Thor", "especie": "Cachorro", "idade": 3 }*/

    @GetMapping("/destaque")
    public Pet destaque() {
        return new Pet("Thor", "Cachorro", 3);
    }

    /*4. Crie a rota GET /pets/adotar/{nome}, que usa o nome que vem no caminho:
            /pets/adotar/Luna → Parabéns! Você adotou Luna!
            /pets/adotar/Thor → Parabéns! Você adotou Thor!*/

    @GetMapping("/adotar/{nome}")
    public String adotar(@PathVariable String nome) {
        return "Parabéns! Você adotou " + nome + "!";
    }

    /*5. Crie a rota GET /pets/banho, que recebe o nome do pet depois do ?. Se ninguém mandar o nome, usa “Seu pet”:
            /pets/banho?pet=Mel → Mel está tomando banho!
            /pets/banho → Seu pet está tomando banho!*/

    @GetMapping("/banho")
    public String banho(@RequestParam(defaultValue = "Seu pet") String nome) {
        return nome + " está tomando banho!";
    }

    /*6. Crie a rota GET /pets/novo/{nome}, que devolve um JSON de um gato filhote com o nome que veio no caminho:
            /pets/novo/Frajola → { "nome": "Frajola", "especie": "Gato", "idade": 1 }*/

    @GetMapping("/novo/{nome}")
    public Pet novoPet(@PathVariable String nome) {
        return new Pet(nome,"Gato",1);
    }

    /*7. Crie a rota GET /pets/idade-humana, que recebe a idade do pet depois do ? e calcula a idade humana (multiplica por 7):
            /pets/idade-humana?anos=3 → 3 anos de pet equivalem a 21 anos humanos!

    Depois teste /pets/idade-humana sem o ?anos=. O que acontece? Por quê?*/

    @GetMapping("/idade-humana")
    public String calculaIdade(@RequestParam("anos") int idadePet) {
        return idadePet + " anos de pet equivalem a " + (idadePet * 7) + " anos humanos!";
    }
    //Se não informar o valor, dá erro 400, porque o @RequestParam espera um parâmetro e
    // não foi informado um valor default. Para corrigir, poderíamos usar @RequestParam(value = "anos",
    // defaultValue = "1"), por exemplo.

    /*Desafio. Crie a rota GET /pets/consulta, que recebe dois valores depois do ?, o nome do pet e
    o nome da veterinária:
            /pets/consulta?pet=Luna&vet=Carla → Luna tem consulta com a Dra. Carla!

    Dica: na URL, os valores são separados por &.*/

    @GetMapping("/consulta")
    public String petConsulta(
            @RequestParam("pet") String pet,
            @RequestParam("vet") String vet) {
        return pet + " tem consulta com a Dra. " + vet;
    }






}
