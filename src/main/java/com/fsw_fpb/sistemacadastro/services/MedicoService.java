package com.fsw_fpb.sistemacadastro.services;

import com.fsw_fpb.sistemacadastro.dto.*;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.entity.Medico;
import com.fsw_fpb.sistemacadastro.repositories.DadosPessoaisRepository;
import com.fsw_fpb.sistemacadastro.repositories.MedicoRepository;
import com.fsw_fpb.sistemacadastro.services.exception.DatabaseException;
import com.fsw_fpb.sistemacadastro.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private DadosPessoaisRepository dadosPessoaisRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Page<MedicoDTO> findAll(Pageable pageable){
        Page<Medico> entities = repository.findAll(pageable);
        return entities.map(x -> new MedicoDTO(x));
    }

    @Transactional (readOnly = true)
    public MedicoDTO findById(Long id){
        Medico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        return new MedicoDTO(entity);
    }

    @Transactional
    public MedicoDTO create(MedicoDTO dto) {
        if (dto.getDadosPessoais().getId() == null) {
            throw new IllegalArgumentException("ID de Dados Pessoais não pode ser nulo");
        }

        DadosPessoais dadosPessoais = dadosPessoaisRepository.findById(dto.getDadosPessoais().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Dados Pessoais não encontrado"));

        Medico entity = new Medico();
        copyDtoToEntity(dto, entity);
        entity.setDadosPessoais(dadosPessoais);
        entity.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        entity = repository.save(entity);
        return new MedicoDTO(entity);
    }

    @Transactional
    public MedicoDTO update(Long id, UpdateMedicoDTO dto) {
        Medico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + id));

        if (dto.getCrm() != null) {
            entity.setCrm(dto.getCrm());
        }
        if (dto.getEspecialidade() != null) {
            entity.setEspecialidade(dto.getEspecialidade());
        }
        entity = repository.save(entity);
        return new MedicoDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial!");
        }
    }

    @Transactional
    public MedicoDTO updateEmailOrPassword(Long id, LoginDTO dto) {
        try {
            Medico entity = repository.getReferenceById(id);

            if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
                entity.setEmail(dto.getEmail());
            }
            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                entity.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));
            }

            entity = repository.save(entity);
            return new MedicoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não foi encontrado!");
        }
    }

    private void copyDtoToEntity(MedicoDTO dto, Medico entity){
        entity.setCrm(dto.getCrm());
        entity.setEspecialidade(dto.getEspecialidade());
        entity.setEmail(dto.getEmail());
    }
}
