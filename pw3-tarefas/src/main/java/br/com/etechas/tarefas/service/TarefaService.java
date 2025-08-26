//Thabata e Luiza

package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public List<TarefaResponseDTO> findAll(){return tarefaMapper.toResponseDTOList(tarefaRepository.findAll());
    }

    public ResponseEntity<Void> excluirPorId(Long id){
        try {
             Tarefa tarefa = tarefaRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Não existe uma tarefa com o id  " + id));

             if (tarefa.getStatus() == StatusEnum.PENDING){
                 tarefaRepository.delete(tarefa);
             }else{
                 throw new RuntimeException("Tarefa em andamento ou concluida.");
             }
        }catch (Exception e){
            throw new RuntimeException("Erro na busca da tarefa pelo id:  " + HttpStatus.NO_CONTENT);
        }
        return null;
    }
}
