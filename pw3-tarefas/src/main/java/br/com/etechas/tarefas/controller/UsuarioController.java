package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioCadastroDTO cadastro){
        UsuarioResponseDTO response = usuarioService.registrar(cadastro);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<UsuarioResponseDTO> findAll(){
        return usuarioService.findAll();
    }
}
