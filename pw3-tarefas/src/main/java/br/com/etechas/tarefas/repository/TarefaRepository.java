package br.com.etechas.tarefas.repository;

import br.com.etechas.tarefas.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    /*@Query("select t from Tabela t where t.id = :id and " +
    "t.status = br.com.etechas.tarefas.enums.StatusEnum.PENDING") -> A consulta é na entidade, não no Banco
    Optional<Tarefa> findByIdAndPending*/
}








