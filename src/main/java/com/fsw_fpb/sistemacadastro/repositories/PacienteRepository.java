package com.fsw_fpb.sistemacadastro.repositories;

import com.fsw_fpb.sistemacadastro.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
