package com.edmar.pontoInteligente.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.pontoInteligente.api.entities.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);
}
