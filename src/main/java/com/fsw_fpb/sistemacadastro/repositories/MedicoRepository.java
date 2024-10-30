package com.fsw_fpb.sistemacadastro.repositories;

import com.fsw_fpb.sistemacadastro.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
