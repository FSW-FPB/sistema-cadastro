package com.fsw_fpb.sistemacadastro.repositories;

import com.fsw_fpb.sistemacadastro.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    Optional<Atendente> findByEmail(String email);
}
