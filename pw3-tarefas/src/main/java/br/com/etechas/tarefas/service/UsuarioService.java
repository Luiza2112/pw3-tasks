package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.mapper.UsuarioMapper;
import br.com.etechas.tarefas.repository.TarefaRepository;
import br.com.etechas.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO registrar(UsuarioCadastroDTO cadastro){

        var existe =  usuarioRepository.findByUsername(cadastro.username());

        if(existe.isPresent()){
            throw new RuntimeException("Nome de usuário já existente");
        }

        // Cifrar a senha
        var senhaCifrada = passwordEncoder.encode(cadastro.password());
        var entidade = usuarioMapper.toEntity(cadastro);
        entidade.setPassword(senhaCifrada);

        var salvo = usuarioRepository.save(entidade);
        return usuarioMapper.toUsuarioResponseDTO(salvo);

    }

    public List<UsuarioResponseDTO> findAll() {
        return usuarioMapper.toUsuarioResponseDTOList(usuarioRepository.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com username: " + username));
    }

}
