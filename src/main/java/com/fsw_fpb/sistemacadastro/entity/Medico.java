package com.fsw_fpb.sistemacadastro.entity;

public class Medico {
    private Long id;
    private String cpf;
    private String crm;
    private String especialidade;
    private String email;
    private String senha;
    private DadosPessoais dadosPessoais;
    public Medico() {
    }

    public Medico(Long id, String cpf, String crm, String especialidade, String email, String senha, DadosPessoais dadosPessoais) {
        this.id = id;
        this.cpf = cpf;
        this.crm = crm;
        this.especialidade = especialidade;
        this.email = email;
        this.senha = senha;
        this.dadosPessoais = dadosPessoais;
    }

    public Medico(Long id, String cpf, String crm, String especialidade, String email, String senha) {
        this.id = id;
        this.cpf = cpf;
        this.crm = crm;
        this.especialidade = especialidade;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }
}
