package com.fsw_fpb.sistemacadastro.controllers;

import com.fsw_fpb.sistemacadastro.dto.ApiResponse;
import com.fsw_fpb.sistemacadastro.dto.DadosPessoaisDTO;
import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import com.fsw_fpb.sistemacadastro.services.DadosPessoaisService;
import com.fsw_fpb.sistemacadastro.services.exception.CPFExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<DadosPessoaisDTO> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosPessoaisDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<DadosPessoaisDTO>> insert(@Valid @RequestBody DadosPessoaisDTO dto) {
        try {
            dto = service.insert(dto);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(dto.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(new ApiResponse<>(dto, null));
        } catch (CPFExistsException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(null, e.getMessage()));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DadosPessoaisDTO> update(@PathVariable Long id, @Valid @RequestBody DadosPessoaisDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
