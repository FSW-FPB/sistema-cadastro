package com.fsw_fpb.sistemacadastro.dto;

public class LoginResponseDTO {
    private String token;
    private Integer tipoUsuario;
    private Long id_usuario;

    public LoginResponseDTO(String token, Integer tipoUsuario, Long id_usuario) {
        this.token = token;
        this.tipoUsuario = tipoUsuario;
        this.id_usuario = id_usuario;
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

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
