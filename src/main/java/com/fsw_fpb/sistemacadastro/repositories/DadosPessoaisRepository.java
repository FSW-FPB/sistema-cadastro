package com.fsw_fpb.sistemacadastro.repositories;

import com.fsw_fpb.sistemacadastro.entity.DadosPessoais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Long> {
    Optional<DadosPessoais> findByCpf(String cpf);
}
