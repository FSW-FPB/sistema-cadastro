package com.fsw_fpb.sistemacadastro.controllers;

import com.fsw_fpb.sistemacadastro.dto.AtendenteDTO;
import com.fsw_fpb.sistemacadastro.dto.UpdateEmailPasswordDTO;
import com.fsw_fpb.sistemacadastro.services.AtendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/atendentes")
public class AtendenteController {
    @Autowired
    private AtendenteService service;

    @GetMapping
    public ResponseEntity<Page<AtendenteDTO>> findAll(Pageable pageable){
        Page<AtendenteDTO> dtos = service.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendenteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping()
    public ResponseEntity<AtendenteDTO> create(@RequestBody @Valid AtendenteDTO dto) {
        dto = service.create(dto);
        URI uri;
        try {
            uri = new URI("/atendentes/" + dto.getId());
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping("/updateEmailOrPassword/{id}")
    public ResponseEntity<AtendenteDTO> updateEmailOrPassword(
            @PathVariable Long id,
            @RequestBody @Valid UpdateEmailPasswordDTO dto) {
        AtendenteDTO updatedDto = service.updateEmailOrPassword(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
