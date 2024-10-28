package com.fsw_fpb.sistemacadastro.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_paciente", uniqueConstraints = { @UniqueConstraint(columnNames = "id_dados_pessoais") })
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoSanguineo;
    private String alergia;
    @Column(columnDefinition = "TEXT")
    private String doencasCronicas;
    private String email;
    private String senha;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
    private DadosPessoais dadosPessoais;

    public Paciente() {
    }

    public Paciente(Long id, String tipoSanguineo, String alergia, String doencasCronicas, String email, String senha, DadosPessoais dadosPessoais) {
        this.id = id;
        this.tipoSanguineo = tipoSanguineo;
        this.alergia = alergia;
        this.doencasCronicas = doencasCronicas;
        this.email = email;
        this.senha = senha;
        this.dadosPessoais = dadosPessoais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getDoencasCronicas() {
        return doencasCronicas;
    }

    public void setDoencasCronicas(String doencasCronicas) {
        this.doencasCronicas = doencasCronicas;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paciente paciente = (Paciente) o;
        return Objects.equals(getId(), paciente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
