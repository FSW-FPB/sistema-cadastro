package com.fsw_fpb.sistemacadastro.dto;

import com.fsw_fpb.sistemacadastro.entity.Paciente;

public class UpdatePacienteDTO {
    private Long id;
    private String tipoSanguineo;
    private String alergia;
    private String doencasCronicas;

    public UpdatePacienteDTO(Long id, String tipoSanguineo, String alergia, String doencasCronicas) {
        this.id = id;
        this.tipoSanguineo = tipoSanguineo;
        this.alergia = alergia;
        this.doencasCronicas = doencasCronicas;
    }

    public UpdatePacienteDTO(Paciente entity){
        this.id = entity.getId();
        this.alergia = entity.getAlergia();
        this.doencasCronicas = entity.getDoencasCronicas();
        this.tipoSanguineo = entity.getTipoSanguineo();
    }

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
}
