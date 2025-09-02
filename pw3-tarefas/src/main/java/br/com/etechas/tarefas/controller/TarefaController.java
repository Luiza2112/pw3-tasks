package br.com.etechas.tarefas.controller;
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
@CrossOrigin(origins = "*")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaResponseDTO> listar(){
        return tarefaService.findAll();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(tarefaService.excluirPorId(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
