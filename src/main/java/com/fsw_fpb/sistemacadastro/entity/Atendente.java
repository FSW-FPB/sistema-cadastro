package com.fsw_fpb.sistemacadastro.entity;

public class Atendente {
    private Long id;
    private String cpf;
    private String email;
    private String senha;

    public Atendente() {
    }

    public Atendente(Long id, String cpf, String email, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String crm) {
        this.cpf = cpf;
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
}
