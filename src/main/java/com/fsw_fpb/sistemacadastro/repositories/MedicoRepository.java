package com.fsw_fpb.sistemacadastro.repositories;

import com.fsw_fpb.sistemacadastro.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByEmail(String email);
}
