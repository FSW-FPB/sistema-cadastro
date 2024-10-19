package com.fsw_fpb.sistemacadastro.services;

import com.fsw_fpb.sistemacadastro.dto.DadosPessoaisDTO;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.repositories.DadosPessoaisRepository;
import com.fsw_fpb.sistemacadastro.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
