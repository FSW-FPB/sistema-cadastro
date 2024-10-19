package com.fsw_fpb.sistemacadastro.dto;

import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

public class DadosPessoaisDTO {
    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nome;
    @Size(min = 10, message = "CPF precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String cpf;
    @Size(min = 6, message = "TELEFONE precisa ter no mínimo 6 caracteres")
    @NotBlank(message = "Campo requerido")
    private String telefone;
    @Size(min = 5, message = "CEP precisa ter no mínimo 5 caracteres")
    @NotBlank(message = "Campo requerido")
    private String cep;
    @NotNull(message = "Campo requerido")
    private LocalDate data_nascimento;
    @NotBlank(message = "Campo requerido -> Retorne 'ATIVO' ou 'INATIVO'")
    private String status;

    public DadosPessoaisDTO(Long id, String nome, String cpf, String telefone, String cep, LocalDate data_nascimento, String status) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cep = cep;
        this.data_nascimento = data_nascimento;
        this.status = status;
    }

    public DadosPessoaisDTO(DadosPessoais entity){
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();
        telefone = entity.getTelefone();
        cep = entity.getCep();
        data_nascimento = entity.getData_nascimento();
        status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public String getStatus() {
        return status;
    }
}
