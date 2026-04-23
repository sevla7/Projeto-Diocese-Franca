package br.com.diocesefranca.controller;

import br.com.diocesefranca.repository.IContato; // Renomeado de IContato
import br.com.diocesefranca.repository.IFiel;
import br.com.diocesefranca.dto.ContatoDTO; // DTO para receber dados do formulário
import br.com.diocesefranca.model.Contato; // Renomeado de Contato
import br.com.diocesefranca.model.Fiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contato")
@CrossOrigin(origins = "*")
public class ContatoController {

    @Autowired
    private IFiel daoFiel; // DAO para a tabela FIEL

    @Autowired
    private IContato daoChamado; // DAO para a tabela CHAMADO (anteriormente 'dao')

    // ⚠️ ATENÇÃO: Se Contato/Chamado não possui um DTO, use ContatoDTO, pois o body do formulário
    // tem campos que não estão no Model (como o email do fiel).

    @GetMapping
    public List<Contato> listarChamados() {
        return (List<Contato>) daoChamado.findAll();
    }

    @PostMapping // Endpoint: POST /api/chamados
    // Usa ContatoDTO para receber os dados do Front-end (Nome, Email, Mensagem, Categoria)
    public ResponseEntity<String> criarChamado(@RequestBody ContatoDTO dadosContato) {

        // --- 1. VERIFICAÇÃO E CRIAÇÃO/VINCULAÇÃO DO FIEL PELO EMAIL ---

        // Tenta buscar um fiel existente pelo e-mail
        Optional<Fiel> fielExistente = daoFiel.findByEmail(dadosContato.getEmail());

        Fiel fiel;
        if (fielExistente.isPresent()) {
            // Fiel encontrado, usa o existente
            fiel = fielExistente.get();
        } else {
            // Fiel NÃO encontrado, cria um novo perfil básico
            fiel = new Fiel();
            fiel.setNome(dadosContato.getNome());
            fiel.setEmail(dadosContato.getEmail());
            fiel.setParoquia("Não Informada");
            fiel.setSenha("Sem Senha");

            // Salva o novo fiel no banco para obter o ID_Fiel gerado
            fiel = daoFiel.save(fiel); // Retorna o objeto Fiel com o ID gerado
        }

        // --- 2. CRIAÇÃO DO CHAMADO (USANDO O ID_Fiel OBTIDO) ---
        Contato novoChamado = new Contato();


        try {
            // Atributos base do Chamado (Mapeamento do DTO para o Model)
            novoChamado.setTitulo(dadosContato.getTitulo());
            novoChamado.setMensagem(dadosContato.getMensagem());
            novoChamado.setDataAbertura(LocalDateTime.now());
            System.out.println("Data Abertura: " + dadosContato.getDataAbertura());// Mensagem do DTO -> Descricao do Model
            novoChamado.setStatus("Aberto");
            novoChamado.setPrioridade("Baixa");
            // novoChamado.setDataAbertura(LocalDateTime.now()); // Adicione a data de abertura aqui

            // Chaves Estrangeiras (FKs)
            novoChamado.setIdFiel(fiel.getId()); // ID_Fiel obtido
            novoChamado.setIdCategoria(dadosContato.getIdCategoria()); // Categoria selecionada


            novoChamado.setIdDev(1); // Placeholder

            // Salva o Chamado
            daoChamado.save(novoChamado);

            return ResponseEntity.status(HttpStatus.CREATED).body("Chamado registrado com sucesso! ID de Rastreio: " + novoChamado.getId());

        } catch (Exception e) {
            // Logar o erro detalhadamente aqui
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao registrar o chamado: " + e.getMessage());
        }

    }
}