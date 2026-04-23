package br.com.diocesefranca.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chamado")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chamado")
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "id_categoria_fk", nullable = false)
    private Integer idCategoria;

    @Column(name = "id_dev_fk")
    private Integer idDev;

    @Column(name = "id_fiel_fk", nullable = false)
    private Integer idFiel;

    @Column(name = "data_abertura", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "descricao",nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(length = 50)
    private String status = "Aberto";

    @Column(length = 50)
    private String prioridade = "Baixa";


    // Getters e setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdDev() {
        return idDev;
    }

    public void setIdDev(Integer idDev) {
        this.idDev = idDev;
    }

    public Integer getIdFiel() {
        return idFiel;
    }

    public void setIdFiel(Integer idFiel) {
        this.idFiel = idFiel;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
}
