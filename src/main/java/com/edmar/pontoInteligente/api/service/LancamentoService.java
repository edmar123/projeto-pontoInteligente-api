package com.edmar.pontoInteligente.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.edmar.pontoInteligente.api.entities.Lancamento;

public interface LancamentoService {
	
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);
	
	Optional<Lancamento> buscarPorId(Long id);
	
	Lancamento Persistir(Lancamento lancamento);
	
	void remover(Long id);

}
