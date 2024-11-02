package com.fsw_fpb.sistemacadastro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UpdateEmailPasswordDTO {
    @Email(message = "Este é um campo de email válido!")
    private String email;

    @Size(min = 5, message = "Senha deve ter no mínimo 5 caracteres")
    private String senha;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
