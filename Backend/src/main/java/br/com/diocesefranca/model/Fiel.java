package br.com.diocesefranca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fiel")
public class Fiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fiel")
    private Integer id_fiel;

    @Column(name = "nome", length = 150, nullable = true)
    private String nome;

    @Column(name = "email", length = 150, nullable = true, unique = true)
    private String email;

    @Column(name = "paroquia", length = 100)
    private String paroquia;

    @Column(name = "senha", columnDefinition = "text", nullable = true, unique = true)
    private String senha;


    public Integer getId() {
        return id_fiel;
    }

    public void setId(Integer id) {
        this.id_fiel = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParoquia() {
        return paroquia;
    }

    public void setParoquia(String paroquia) {
        this.paroquia = paroquia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
