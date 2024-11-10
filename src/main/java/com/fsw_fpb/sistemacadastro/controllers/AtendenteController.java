package com.fsw_fpb.sistemacadastro.controllers;

import com.fsw_fpb.sistemacadastro.dto.AtendenteDTO;
import com.fsw_fpb.sistemacadastro.dto.LoginDTO;
import com.fsw_fpb.sistemacadastro.dto.LoginResponseDTO;
import com.fsw_fpb.sistemacadastro.services.AtendenteService;
import com.fsw_fpb.sistemacadastro.services.AuthService;
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
@RequestMapping(value = "/atendentes")
public class AtendenteController {
    @Autowired
    private AtendenteService service;

    @Autowired
    private AuthService authService;

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
            @RequestBody @Valid LoginDTO dto) {
        AtendenteDTO updatedDto = service.updateEmailOrPassword(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginAtendente(@RequestBody LoginDTO loginDTO) {
        try {
            LoginResponseDTO response = authService.authenticateAtendente(loginDTO).getBody();
            return ResponseEntity.ok(response);
        } catch (AuthException e) {
            LoginResponseDTO errorResponse = new LoginResponseDTO(null, null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
