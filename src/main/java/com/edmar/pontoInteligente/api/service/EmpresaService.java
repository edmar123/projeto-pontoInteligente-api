package com.edmar.pontoInteligente.api.service;

import java.util.Optional;

import com.edmar.pontoInteligente.api.entities.Empresa;

public interface EmpresaService {
	
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	Empresa persistir(Empresa empresa);
}
