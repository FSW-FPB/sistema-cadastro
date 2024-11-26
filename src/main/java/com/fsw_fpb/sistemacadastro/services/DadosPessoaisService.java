package com.fsw_fpb.sistemacadastro.services;

import com.fsw_fpb.sistemacadastro.dto.DadosPessoaisDTO;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.repositories.DadosPessoaisRepository;
import com.fsw_fpb.sistemacadastro.services.exception.CPFExistsException;
import com.fsw_fpb.sistemacadastro.services.exception.DatabaseException;
import com.fsw_fpb.sistemacadastro.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DadosPessoaisService {

    @Autowired
    private DadosPessoaisRepository repository;

    @Transactional(readOnly = true)
    public List<DadosPessoaisDTO> findAll() {
        List<DadosPessoais> list = repository.findAll();
        return list.stream().map(x -> new DadosPessoaisDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DadosPessoaisDTO findByCpf(String cpf) {
        Optional<DadosPessoais> obj = repository.findByCpf(cpf);
        return obj.map(x -> new DadosPessoaisDTO(x))
                .orElseThrow(() -> new ResourceNotFoundException("CPF não encontrado"));
    }


    @Transactional(readOnly = true)
    public DadosPessoaisDTO findById(Long id){
        DadosPessoais dados = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new DadosPessoaisDTO(dados);
    }

    @Transactional
    public DadosPessoaisDTO insert(DadosPessoaisDTO dto){
        DadosPessoais dados = new DadosPessoais();
        copyDtoToEntity(dto, dados);

        if(repository.existsByCpf(dto.getCpf())){
            throw new CPFExistsException("O CPF enviado ja existe no banco de dados!");
        }
        dados = repository.save(dados);
        return new DadosPessoaisDTO(dados);
    }

    @Transactional
    public DadosPessoaisDTO update(Long id, DadosPessoaisDTO dto){
        try{
            DadosPessoais dados = repository.getReferenceById(id);
            copyDtoToEntity(dto, dados);
            dados = repository.save(dados);
            return new DadosPessoaisDTO(dados);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
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

    private void copyDtoToEntity(DadosPessoaisDTO dto, DadosPessoais entity) {
        entity.setNome(dto.getNome());
        entity.setCep(dto.getCep());
        entity.setCpf(dto.getCpf());
        entity.setStatus(dto.getStatus());
        entity.setData_nascimento(dto.getData_nascimento());
        entity.setTelefone(dto.getTelefone());
        entity.setGenero(dto.getGenero());
        entity.setImgUrl(dto.getImgUrl());
    }
}
