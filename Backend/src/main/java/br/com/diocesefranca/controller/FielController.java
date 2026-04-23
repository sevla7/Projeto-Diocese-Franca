package br.com.diocesefranca.controller;

import br.com.diocesefranca.repository.IFiel;
import br.com.diocesefranca.dto.LoginDTO;
import br.com.diocesefranca.model.Fiel;
import br.com.diocesefranca.service.FielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cadastro") /* para todos os endpoints esse endereço será usado */
@CrossOrigin("*")
@RestController
public class FielController {

    @Autowired
    private IFiel dao;

    private final FielService fielService;

    public FielController(FielService fielService) {
        this.fielService = fielService;
    }
    @Autowired
    private PasswordEncoder encoder;

    public Fiel cadastrar(Fiel f) {
        f.setSenha(encoder.encode(f.getSenha()));
        return dao.save(f);
    }

    public boolean verificarSenha(String senhaDigitada, String senhaCriptografada) {
        return encoder.matches(senhaDigitada, senhaCriptografada);
    }

    @GetMapping /* lista os fiéis */
    public ResponseEntity<List<Fiel>> listaFieis () {
        return ResponseEntity.status(200).body(fielService.listarTodos());
    }

    @PostMapping  /* cria usuarios */
    public ResponseEntity <Fiel> criarUsuario (@RequestBody Fiel fiel) {
        return ResponseEntity.status(201).body(fielService.cadastrar(fiel));
    }

    @PutMapping  /* Edita o fiel */
    public ResponseEntity<Fiel> editarUsuario (@RequestBody Fiel fiel) {
        Fiel fielNovo = dao.save(fiel);
        return  ResponseEntity.status(201).body(fielNovo);
    }

    @DeleteMapping("/{id}") /* Edita usuarios / usa um novo endereço */
    public ResponseEntity<?> excluirUsuario (@PathVariable Integer id) {
        dao.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("cadastro/login")
    public ResponseEntity<String> logarFiel(@RequestBody LoginDTO credenciais) {

        // 1. CHAMA O SERVICE PARA VERIFICAR A SENHA HASHED NO POSTGRESQL
        boolean autenticado = fielService.autenticar(
                credenciais.getEmail(),
                credenciais.getSenha()
        );

        // 2. INICIA A SESSÃO: Armazena o ID do usuário na sessão do servidor.
        // O Spring Security ou HttpSession faria isso:
        // httpSession.setAttribute("fielId", idFiel);
        if (autenticado) {
            // 3. RETORNA SUCESSO. O Front-end será redirecionado.
            return ResponseEntity.ok("Login bem-sucedido.");
        } else {
            // 4. RETORNA ERRO DE CREDENCIAL
            return ResponseEntity.status(401).body("Credenciais inválidas. Verifique seu e-mail e senha.");
        }
        // LÓGICA DE AUTENTICAÇÃO --
    }

}