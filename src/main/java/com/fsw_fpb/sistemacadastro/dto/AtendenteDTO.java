package com.fsw_fpb.sistemacadastro.dto;


import com.fsw_fpb.sistemacadastro.entity.Atendente;

import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AtendenteDTO {
    private Long id;
    private String cpf;
    @Email(message = "Este é um campo de email!!")
    @NotBlank(message = "Não deixe EMAIL vazio.")
    private String email;
    @NotNull(message = "Campo SENHA precisa ser preenchido com algo")
    private String senha;
    private DadosPessoais dadosPessoais;

    public AtendenteDTO(Long id, String cpf, String email, String senha, DadosPessoais dadosPessoais) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.dadosPessoais = dadosPessoais;
    }

    public AtendenteDTO(Atendente entity){

    }
    //Getters

    public Long getId() { return id; }

    public String getCpf() { return cpf; }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    public DadosPessoais getDadosPessoais() { return dadosPessoais; }
}
