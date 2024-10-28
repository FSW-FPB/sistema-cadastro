package com.fsw_fpb.sistemacadastro.dto;

import com.fsw_fpb.sistemacadastro.entity.Medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicoDTO {
    private Long id;
    private String cpf;
    private String crm;
    private String especialidade;
    @Email(message = "Este é um campo de email!!")
    @NotBlank(message = "Não deixe EMAIL vazio.")
    private String email;
    @NotNull(message = "Campo SENHA precisa ser preenchido com algo")
    private String senha;
    private DadosPessoaisDTO dadosPessoais;

    public MedicoDTO(Long id, String cpf, String crm, String especialidade, String email, String senha, DadosPessoaisDTO dadosPessoais) {
        this.id = id;
        this.cpf = cpf;
        this.crm = crm;
        this.especialidade = especialidade;
        this.email = email;
        this.senha = senha;
        this.dadosPessoais = dadosPessoais;
    }

    public MedicoDTO(Medico entity){
        id = entity.getId();
        cpf = entity.getCpf();
        crm = entity.getCrm();
        especialidade = entity.getEspecialidade();
        email = entity.getEmail();
        senha = entity.getSenha();
        dadosPessoais = new DadosPessoaisDTO(entity.getDadosPessoais());
    }
    //Getters

    public Long getId() { return id; }

    public String getCpf() { return cpf; }

    public String getCrm() { return crm; }

    public String getEspecialidade() { return especialidade; }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    public DadosPessoaisDTO getDadosPessoais() { return dadosPessoais; }
}
