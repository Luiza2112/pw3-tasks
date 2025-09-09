//Thabata e Luiza

package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.dto.TarefaRegisterDTO;
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

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public List<TarefaResponseDTO> findAll(){return tarefaMapper.toResponseDTOList(tarefaRepository.findAll());
    }

    public TarefaResponseDTO cadastrar(TarefaRegisterDTO novaTarefa){
        Tarefa tarefa = tarefaMapper.toEntity(novaTarefa);
        tarefa.setStatus(StatusEnum.PENDING); // Sempre pendente ?

        LocalDate data = tarefa.getDataLimite();
        LocalDate dataAtual = LocalDate.now();

        if (data.isBefore(dataAtual)){
            throw new RuntimeException("A data da tarefa não pode ser menor que a data atual.");
        }else{
            tarefaRepository.save(tarefa);
        }

        return tarefaMapper.toResponseDTO(tarefa); // Retorna a vizualização da tarefa, se for necessário
    }

    public boolean excluirPorId(Long id){
        var tarefa = tarefaRepository.findById(id);
        /*tarefa.isPresent() && tarefa.get().isPending()*/
        /*&& tarefa.get().getStatus().equals(StatusEnum.PENDING*/
        if(tarefa.isEmpty()){
            return false;
        }
        if(tarefa.get().isPending()){
            tarefaRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Tarefa em progresso ou concluída");
        /*Tarefa tarefa = tarefaRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Não existe uma tarefa com o id  " + id));

            if (tarefa.getStatus() == StatusEnum.PENDING){
                tarefaRepository.delete(tarefa);
            }else{
                throw new RuntimeException("Tarefa em andamento ou concluida.");
            }
            throw new RuntimeException("Erro na busca da tarefa pelo id:  "); */
    }
}
