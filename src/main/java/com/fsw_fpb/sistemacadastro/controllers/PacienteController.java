package com.fsw_fpb.sistemacadastro.controllers;

import com.fsw_fpb.sistemacadastro.dto.PacienteDTO;
import com.fsw_fpb.sistemacadastro.dto.UpdateEmailPasswordDTO;
import com.fsw_fpb.sistemacadastro.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> findAll(Pageable pageable){
        Page<PacienteDTO> dtos = service.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping()
    public ResponseEntity<PacienteDTO> create(@RequestBody @Valid PacienteDTO dto) {
        dto = service.create(dto);
        URI uri;
        try {
            uri = new URI("/pacientes/" + dto.getId());
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody @Valid PacienteDTO dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/updateEmailOrPassword/{id}")
    public ResponseEntity<PacienteDTO> updateEmailOrPassword(@PathVariable Long id, @RequestBody UpdateEmailPasswordDTO dto) {
        PacienteDTO updatedPaciente = service.updateEmailOrPassword(id, dto);
        return ResponseEntity.ok(updatedPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
