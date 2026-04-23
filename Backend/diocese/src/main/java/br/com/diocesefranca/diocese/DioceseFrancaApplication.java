package br.com.diocesefranca.diocese;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação.
 * Responsável por iniciar o servidor embutido (Tomcat) e carregar os controladores REST.
 */
@SpringBootApplication
public class DioceseFrancaApplication {

	public static void main(String[] args) {

        SpringApplication.run(DioceseFrancaApplication.class, args);

	}
}
