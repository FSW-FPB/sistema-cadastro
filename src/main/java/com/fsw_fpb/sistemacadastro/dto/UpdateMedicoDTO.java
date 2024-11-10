package com.fsw_fpb.sistemacadastro.dto;

import com.fsw_fpb.sistemacadastro.entity.Medico;

public class UpdateMedicoDTO {
    private Long id;
    private String crm;
    private String especialidade;

    public UpdateMedicoDTO(Long id, String crm, String especialidade) {
        this.id = id;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public UpdateMedicoDTO(Medico entity){
        this.id = entity.getId();
        this.crm = entity.getCrm();
        this.especialidade = entity.getEspecialidade();
    }

    public Long getId() {
        return id;
    }

    public String getCrm() {
        return crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

}