package br.com.etechas.tarefas.mapper;

import br.com.etechas.tarefas.dto.TarefaRegisterDTO;
import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    TarefaMapper INSTACE = Mappers.getMapper(TarefaMapper.class);

    List<TarefaResponseDTO> toResponseDTOList(List<Tarefa> tarefa);

    Tarefa toEntity(TarefaRegisterDTO dto); // Transforma o DTO em entidade

    TarefaResponseDTO toResponseDTO(Tarefa tarefa); // Tranforma a entidade em DTO novamente
}
