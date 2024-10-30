package com.fsw_fpb.sistemacadastro.dto;


import com.fsw_fpb.sistemacadastro.entity.Atendente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtendenteDTO {
    private Long id;
    @Email(message = "Este é um campo de email!!")
    @NotBlank(message = "Não deixe EMAIL vazio.")
    private String email;
    @NotNull(message = "Campo SENHA precisa ser preenchido com algo")
    @Size(min = 5, message = "Mínimo de 5 caracteres")
    private String senha;
    private DadosPessoaisDTO dadosPessoais;

    public AtendenteDTO(Long id, String email, String senha, DadosPessoaisDTO dadosPessoais) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.dadosPessoais = dadosPessoais;
    }

    public AtendenteDTO(Atendente entity){
        id = entity.getId();
        email = entity.getEmail();
        senha = entity.getSenha();
        dadosPessoais = new DadosPessoaisDTO(entity.getDadosPessoais());
    }
    //Getters

    public Long getId() { return id; }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    public DadosPessoaisDTO getDadosPessoais() { return dadosPessoais; }
}
