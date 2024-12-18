package com.fsw_fpb.sistemacadastro.services;

import com.fsw_fpb.sistemacadastro.dto.AtendenteDTO;
import com.fsw_fpb.sistemacadastro.dto.LoginDTO;
import com.fsw_fpb.sistemacadastro.entity.Atendente;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.repositories.AtendenteRepository;
import com.fsw_fpb.sistemacadastro.repositories.DadosPessoaisRepository;
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
public class AtendenteService {
    @Autowired
    private AtendenteRepository repository;
    @Autowired
    private DadosPessoaisRepository dadosPessoaisRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Page<AtendenteDTO> findAll(Pageable pageable){
        Page<Atendente> entities = repository.findAll(pageable);
        return entities.map(x -> new AtendenteDTO(x));
    }

    @Transactional (readOnly = true)
    public AtendenteDTO findById(Long id){
        Atendente entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        return new AtendenteDTO(entity);
    }

    @Transactional
    public AtendenteDTO create(AtendenteDTO dto) {
        if (dto.getDadosPessoais().getId() == null) {
            throw new IllegalArgumentException("ID de Dados Pessoais não pode ser nulo");
        }

        DadosPessoais dadosPessoais = dadosPessoaisRepository.findById(dto.getDadosPessoais().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Dados Pessoais não encontrado"));

        Atendente entity = new Atendente();
        copyDtoToEntity(dto, entity);
        entity.setDadosPessoais(dadosPessoais);
        entity.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        entity = repository.save(entity);
        return new AtendenteDTO(entity);
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

    @Transactional
    public AtendenteDTO updateEmailOrPassword(Long id, LoginDTO dto) {
        try {
            Atendente entity = repository.getReferenceById(id);

            // Atualiza apenas os campos que foram fornecidos
            if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
                entity.setEmail(dto.getEmail());
            }
            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                entity.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));
            }

            entity = repository.save(entity);
            return new AtendenteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não foi encontrado!");
        }
    }


    private void copyDtoToEntity(AtendenteDTO dto, Atendente entity){
        entity.setEmail(dto.getEmail());
    }
}
