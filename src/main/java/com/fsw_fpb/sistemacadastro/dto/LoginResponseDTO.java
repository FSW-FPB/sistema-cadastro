package com.fsw_fpb.sistemacadastro.dto;

public class LoginResponseDTO {
    private String token;
    private Integer tipoUsuario;

    public LoginResponseDTO(String token, Integer tipoUsuario) {
        this.token = token;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
