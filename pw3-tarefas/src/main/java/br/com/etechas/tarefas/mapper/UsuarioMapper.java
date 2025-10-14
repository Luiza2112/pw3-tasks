package br.com.etechas.tarefas.mapper;

import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toEntity(UsuarioCadastroDTO cadastro);

    UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario);

    List<UsuarioResponseDTO> toUsuarioResponseDTOList(List<Usuario> usuarios);
}
