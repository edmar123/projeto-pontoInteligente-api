package com.edmar.pontoInteligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.pontoInteligente.api.entities.Empresa;
import com.edmar.pontoInteligente.api.repository.EmpresaRepository;
import com.edmar.pontoInteligente.api.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("buscando empresa por cnpj {}", cnpj);
		return Optional.ofNullable(this.empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("persistindo empresa: {}", empresa);
		return this.empresaRepository.save(empresa);
	}
	
}
