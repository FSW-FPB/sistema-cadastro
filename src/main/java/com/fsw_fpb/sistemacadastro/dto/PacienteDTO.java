package com.fsw_fpb.sistemacadastro.dto;

import com.fsw_fpb.sistemacadastro.entity.Paciente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PacienteDTO {
    private Long id;
    private String tipoSanguineo;
    private String alergia;
    private String doencasCronicas;
    @Email(message = "Este é um campo de email!!")
    @NotBlank(message = "Não deixe EMAIL vazio.")
    private String email;
    @NotNull(message = "Campo SENHA precisa ser preenchido com algo")
    private String senha;
    private DadosPessoaisDTO dadosPessoais;

    public PacienteDTO(Long id, String tipoSanguineo, String alergia, String doencasCronicas, String email, String senha, DadosPessoaisDTO dadosPessoais) {
        this.id = id;
        this.tipoSanguineo = tipoSanguineo;
        this.alergia = alergia;
        this.doencasCronicas = doencasCronicas;
        this.email = email;
        this.senha = senha;
        this.dadosPessoais = dadosPessoais;
    }

    public PacienteDTO(Paciente entity) {
        id = entity.getId();
        tipoSanguineo = entity.getTipoSanguineo();
        alergia = entity.getAlergia();
        doencasCronicas = entity.getDoencasCronicas();
        email = entity.getEmail();
        senha = entity.getSenha();
        dadosPessoais = new DadosPessoaisDTO(entity.getDadosPessoais());
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public String getAlergia() {
        return alergia;
    }

    public String getDoencasCronicas() {
        return doencasCronicas;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public DadosPessoaisDTO getDadosPessoais() {
        return dadosPessoais;
    }
}
