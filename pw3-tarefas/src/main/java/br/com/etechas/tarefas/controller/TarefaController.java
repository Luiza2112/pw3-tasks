package br.com.etechas.tarefas.controller;
import br.com.etechas.tarefas.dto.TarefaRegisterDTO;
import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
//@CrossOrigin(origins = "*")
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaResponseDTO> listar(){
        return tarefaService.findAll();
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> cadastrar(@RequestBody TarefaRegisterDTO novaTarefa){
        // @RequestBody converte o JSON vindo do Angular para um TarefaRegisterDTO
        // ResponseEntity retorna Status + Corpo
        TarefaResponseDTO tarefaCriada = tarefaService.cadastrar(novaTarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        //@PathVariable pega o valor da rota da URL (no caso daqui, o id) e passa para o parâmetro do método

        if(tarefaService.excluirPorId(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try{
            tarefaService.excluirPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT)
        } catch(RuntimeExcepion e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
    }*/
}
