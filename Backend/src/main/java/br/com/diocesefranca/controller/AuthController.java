package br.com.diocesefranca.controller;

import br.com.diocesefranca.repository.IFiel;
import br.com.diocesefranca.model.Fiel;
import br.com.diocesefranca.service.FielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap; // <--- Importante para criar o JSON
import java.util.Map;     // <--- Importante
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private IFiel repository;

    @Autowired
    private PasswordEncoder encoder;

    Fiel fiel = new Fiel();

    // Mudamos de ResponseEntity<String> para ResponseEntity<Object>
    // Isso permite retornar um JSON (Map)
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Fiel login) {

        // Busca o fiel pelo email
        Optional<Fiel> fielOpt = repository.findByEmail(login.getEmail());

        // CORREÇÃO: Optional nunca é null. Devemos usar .isEmpty()
        if (fielOpt.isEmpty()) {
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "❌ Fiel não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }

        Fiel fielEncontrado = fielOpt.get();

        // Compara senha digitada com o hash
        if (encoder.matches(login.getSenha(), fielEncontrado.getSenha())) {
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "✅ Login realizado com sucesso!");
            resposta.put("nome", fiel.getNome() );
            resposta.put("email", fiel.getEmail());
            resposta.put("id", fiel.getId()); // <--- ESSENCIAL

            return ResponseEntity.ok(resposta);

        } else {

            // ERRO SENHA: Criando JSON de erro
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "❌ Senha incorreta.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
        }
    }
}