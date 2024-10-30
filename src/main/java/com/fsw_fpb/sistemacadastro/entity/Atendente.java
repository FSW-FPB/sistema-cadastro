package com.fsw_fpb.sistemacadastro.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_atendente", uniqueConstraints = { @UniqueConstraint(columnNames = "id_dados_pessoais") })
public class Atendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
    private DadosPessoais dadosPessoais;

    public Atendente() {
    }

    public Atendente(Long id, String email, String senha, DadosPessoais dadosPessoais) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.dadosPessoais = dadosPessoais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public DadosPessoais getDadosPessoais() { return dadosPessoais; }

    public void setDadosPessoais(DadosPessoais dadosPessoais) { this.dadosPessoais = dadosPessoais; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atendente atendente = (Atendente) o;
        return Objects.equals(getId(), atendente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
