package com.example.aulas.aula5;

import com.example.aulas.aula5.model.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aula5/funcionarios")
public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    // Listar - GET
    @GetMapping
    public List<Funcionario> listar() {
        return funcionarios;
    }

    //Buscar por Id - GET
    @GetMapping("/{id}")
    public Funcionario buscar(@PathVariable int id) {
        for (Funcionario f : funcionarios) {    //Procura o Id
            if (f.getId() == id) {
                return f;
            }
        }   //Se não achar, lança erro
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Funcionário não encontrado");
    }

    //Cadastrar - PUT
    @PostMapping
    //Devolve o funcionário já com o id, pra quem cadastrou saber qual número ele ganhou.
    @ResponseStatus(HttpStatus.CREATED)
    //pega o JSON do corpo e transforma num objeto Funcionario.
    public Funcionario cadastrar(@RequestBody Funcionario funcionario) {
        funcionario.setId(proximoId);
        proximoId++;    //Id gerado pelo servidor
        funcionarios.add(funcionario);
        return funcionario;
    }

    //Atualizar - PUT
    //Recebe as duas coisas: o id pela URL e os dados novos pelo corpo.
    @PutMapping("/{id}")
    public Funcionario atualizar(@PathVariable int id,
                                 @RequestBody Funcionario dados) {
        //se o id não existe, o 404 já sai de lá. Sem repetir código!
        Funcionario existente = buscar(id); // reaproveita!
        existente.setNome(dados.getNome());
        existente.setCargo(dados.getCargo());
        existente.setSalario(dados.getSalario());
        return existente;
    }   //Copia os dados novos pro objeto que já está na lista. O id continua o mesmo.

    //Remover - DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)      //204 "deu certo, e a resposta vem vazia de propósito".
    //O método é void: depois de apagar, não tem nada pra devolver.
    public void remover(@PathVariable int id) {
        Funcionario existente = buscar(id);     //id que não existe já volta 404.
        funcionarios.remove(existente);
    }

}
