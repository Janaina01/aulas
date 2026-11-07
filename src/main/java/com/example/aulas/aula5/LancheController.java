
package com.example.aulas.aula5;

import com.example.aulas.aula5.model.Lanche;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aula05/lanches")
public class LancheController {

    private List<Lanche> lanches = new ArrayList<>();
    private int proximoId = 1;

    // GET - Listar todos os lanches
    @GetMapping
    public List<Lanche> listar() {
        return lanches;
    }

    // GET - Buscar lanche pelo ID
    @GetMapping("/{id}")
    public Lanche buscar(@PathVariable int id) {
        for (Lanche l : lanches) {
            if (l.getId() == id) {
                return l;
            }
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Lanche não encontrado"
        );
    }

    // POST - Cadastrar um lanche
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lanche cadastrar(@RequestBody Lanche lanche) {

        if (lanche.getPreco() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O preço deve ser maior que zero!"
            );
        }

        lanche.setId(proximoId);
        proximoId++;

        lanches.add(lanche);

        return lanche;
    }

    // DELETE - Remover um lanche
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable int id) {
        Lanche existente = buscar(id);
        lanches.remove(existente);
    }
}

