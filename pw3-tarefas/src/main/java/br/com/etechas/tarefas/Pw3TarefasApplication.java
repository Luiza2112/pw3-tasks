package br.com.etechas.tarefas;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Pw3TarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pw3TarefasApplication.class, args);
	}

}
