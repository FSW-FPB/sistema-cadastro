package com.fsw_fpb.sistemacadastro.services;

import com.fsw_fpb.sistemacadastro.dto.LoginDTO;
import com.fsw_fpb.sistemacadastro.dto.LoginResponseDTO;
import com.fsw_fpb.sistemacadastro.entity.Medico;
import com.fsw_fpb.sistemacadastro.entity.Paciente;
import com.fsw_fpb.sistemacadastro.entity.Atendente;
import com.fsw_fpb.sistemacadastro.repositories.MedicoRepository;
import com.fsw_fpb.sistemacadastro.repositories.PacienteRepository;
import com.fsw_fpb.sistemacadastro.repositories.AtendenteRepository;
import com.fsw_fpb.sistemacadastro.services.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<LoginResponseDTO> authenticatePaciente(LoginDTO loginDTO) {
        Paciente paciente = pacienteRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AuthException("Email não existe na nossa base de dados!!"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), paciente.getSenha())) {
            throw new AuthException("Senha incorreta");
        }
        String token = jwtService.generateToken(paciente.getEmail());
        LoginResponseDTO response = new LoginResponseDTO(token, 1);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<LoginResponseDTO> authenticateMedico(LoginDTO loginDTO) {
        Medico medico = medicoRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AuthException("Email não existe na nossa base de dados!!"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), medico.getSenha())) {
            throw new AuthException("Senha incorreta");
        }
        String token = jwtService.generateToken(medico.getEmail());
        LoginResponseDTO response = new LoginResponseDTO(token, 2);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<LoginResponseDTO> authenticateAtendente(LoginDTO loginDTO) {
        Atendente atendente = atendenteRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AuthException("Email não existe na nossa base de dados!!"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), atendente.getSenha())) {
            throw new AuthException("Senha incorreta");
        }
        String token = jwtService.generateToken(atendente.getEmail());
        LoginResponseDTO response = new LoginResponseDTO(token, 3);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
