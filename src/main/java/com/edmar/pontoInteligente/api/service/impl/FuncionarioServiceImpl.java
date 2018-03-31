package com.edmar.pontoInteligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.pontoInteligente.api.entities.Funcionario;
import com.edmar.pontoInteligente.api.repository.FuncionarioRepository;
import com.edmar.pontoInteligente.api.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	@Autowired
	private FuncionarioRepository funcionariorepository;
	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("persistindo funcionario: {}", funcionario);
		return this.funcionariorepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando funcionário pelo cpf: {}", cpf);
		return Optional.ofNullable(this.funcionariorepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionario por email: {}", email);
		return Optional.ofNullable(this.funcionariorepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionario pelo id:{}", id);
		return Optional.ofNullable(this.funcionariorepository.findOne(id));
	}

}
