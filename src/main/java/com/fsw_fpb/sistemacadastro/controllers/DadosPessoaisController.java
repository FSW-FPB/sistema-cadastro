package com.fsw_fpb.sistemacadastro.controllers;

import com.fsw_fpb.sistemacadastro.dto.DadosPessoaisDTO;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.services.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/dados-pessoais")
public class DadosPessoaisController {
    @Autowired
    private DadosPessoaisService service;

    @GetMapping
    public ResponseEntity<List<DadosPessoaisDTO>> findAll() {
        List<DadosPessoais> dadosPessoaisList = service.findAll();
        List<DadosPessoaisDTO> dadosPessoaisDTOList = dadosPessoaisList.stream()
                .map(dados -> new DadosPessoaisDTO(dados))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dadosPessoaisDTOList, HttpStatus.OK);
    }
}
