package br.com.diocesefranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DioceseFrancaApplication {

	public static void main(String[] args) {

        SpringApplication.run(DioceseFrancaApplication.class, args);
        System.out.println("✅ Servidor iniciado em http://localhost:8080");
	}

}
