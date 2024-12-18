package com.fsw_fpb.sistemacadastro.controllers;

import com.fsw_fpb.sistemacadastro.dto.LoginResponseDTO;
import com.fsw_fpb.sistemacadastro.dto.MedicoDTO;
import com.fsw_fpb.sistemacadastro.dto.LoginDTO;
import com.fsw_fpb.sistemacadastro.dto.UpdateMedicoDTO;
import com.fsw_fpb.sistemacadastro.services.AuthService;
import com.fsw_fpb.sistemacadastro.services.MedicoService;
import com.fsw_fpb.sistemacadastro.services.exception.AuthException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {
    @Autowired
    private MedicoService service;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> findAll(Pageable pageable){
        Page<MedicoDTO> dtos = service.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id){
        MedicoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> create(@RequestBody @Valid MedicoDTO dto){
        dto = service.create(dto);
        URI uri;
        try {
            uri = new URI("/medicos/" + dto.getId());
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateMedicoDTO dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PatchMapping("/updateEmailOrPassword/{id}")
    public ResponseEntity<MedicoDTO> updateEmailOrPassword(
            @PathVariable Long id,
            @RequestBody @Valid LoginDTO dto){
        MedicoDTO updatedDto = service.updateEmailOrPassword(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginMedico(@RequestBody LoginDTO loginDTO) {
        try {
            LoginResponseDTO response = authService.authenticateMedico(loginDTO).getBody();
            return ResponseEntity.ok(response);
        } catch (AuthException e) {
            LoginResponseDTO errorResponse = new LoginResponseDTO(null, null, null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}
