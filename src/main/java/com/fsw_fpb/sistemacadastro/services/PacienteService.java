package com.fsw_fpb.sistemacadastro.services;

import com.fsw_fpb.sistemacadastro.dto.PacienteDTO;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.entity.Paciente;
import com.fsw_fpb.sistemacadastro.repositories.DadosPessoaisRepository;
import com.fsw_fpb.sistemacadastro.repositories.PacienteRepository;
import com.fsw_fpb.sistemacadastro.services.exception.DatabaseException;
import com.fsw_fpb.sistemacadastro.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private DadosPessoaisRepository dadosPessoaisRepository;

    @Transactional (readOnly = true)
    public Page<PacienteDTO> findAll(Pageable pageable){
        Page<Paciente> entities = repository.findAll(pageable);
        return entities.map(x -> new PacienteDTO(x));
    }

    @Transactional (readOnly = true)
    public PacienteDTO findById(Long id){
        Paciente entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        return new PacienteDTO(entity);
    }

    @Transactional
    public PacienteDTO create(PacienteDTO dto) {
        if (dto.getDadosPessoais().getId() == null) {
            throw new IllegalArgumentException("ID de Dados Pessoais não pode ser nulo");
        }

        DadosPessoais dadosPessoais = dadosPessoaisRepository.findById(dto.getDadosPessoais().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Dados Pessoais não encontrado"));

        Paciente entity = new Paciente();
        copyDtoToEntity(dto, entity);
        entity.setDadosPessoais(dadosPessoais);

        entity = repository.save(entity);
        return new PacienteDTO(entity);
    }

    @Transactional
    public PacienteDTO update(Long id, PacienteDTO dto){
        try{
            Paciente entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PacienteDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não foi encontrado!");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial!");
        }
    }

    private void copyDtoToEntity(PacienteDTO pacienteDTO, Paciente paciente){
        paciente.setTipoSanguineo(pacienteDTO.getTipoSanguineo());
        paciente.setAlergia(pacienteDTO.getAlergia());
        paciente.setDoencasCronicas(pacienteDTO.getDoencasCronicas());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setSenha(pacienteDTO.getSenha());
    }
}
