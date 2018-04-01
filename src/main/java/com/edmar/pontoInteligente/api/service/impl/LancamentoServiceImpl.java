package com.edmar.pontoInteligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.edmar.pontoInteligente.api.entities.Lancamento;
import com.edmar.pontoInteligente.api.repository.LancamentoRepository;
import com.edmar.pontoInteligente.api.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService{
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando um lancamento para funcionario ID {}", funcionarioId );
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("buscando um lancamento pelo ID {}", id);
		return Optional.ofNullable(this.lancamentoRepository.findOne(id));
	}

	@Override
	public Lancamento Persistir(Lancamento lancamento) {
		log.info("persistindo o lancamento: {} ",lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("removendo o lançamento ID : {}", id);
		this.lancamentoRepository.delete(id);
		
	}
	

}
