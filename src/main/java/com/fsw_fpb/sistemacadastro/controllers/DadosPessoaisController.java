package com.fsw_fpb.sistemacadastro.controllers;

import com.fsw_fpb.sistemacadastro.dto.DadosPessoaisDTO;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.services.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<DadosPessoaisDTO> findByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosPessoaisDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
}
