package com.fsw_fpb.sistemacadastro.services;

import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.repositories.DadosPessoaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DadosPessoaisService {

    @Autowired
    private DadosPessoaisRepository repository;

    @Transactional(readOnly = true)
    public List<DadosPessoais> findAll() {
        return repository.findAll();
    }
}
