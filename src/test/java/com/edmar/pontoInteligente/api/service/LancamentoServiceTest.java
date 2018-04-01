package com.edmar.pontoInteligente.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edmar.pontoInteligente.api.entities.Lancamento;
import com.edmar.pontoInteligente.api.repository.LancamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {
	
	@MockBean
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private LancamentoService lancamentoservice;
	
	@Before
	public void setup() throws Exception{
		BDDMockito
			.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
			.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito
			.given(this.lancamentoRepository.findOne(Mockito.anyLong()))
			.willReturn(new Lancamento());
		BDDMockito
			.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class)))
			.willReturn(new Lancamento());
		
			
	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		Page<Lancamento> lancamento = this.lancamentoservice.buscarPorFuncionarioId(1L, new PageRequest(0, 10));
		
		assertNotNull(lancamento);
	}
	@Test
	public void buscarLancamentoPorId() {
		Optional<Lancamento> lancamento = this.lancamentoservice.buscarPorId(1L);
		
		assertTrue(lancamento.isPresent());
	}
	@Test
	public void persistirLancamento() {
		Lancamento lancamento = this.lancamentoservice.Persistir(new Lancamento());
		
		assertNotNull(lancamento);
	}
	
}
