package com.edmar.pontoInteligente.api.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edmar.pontoInteligente.api.entities.Empresa;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {
	@Autowired
	private EmpresaRepository empresaRepository;
	private static final String cnpj = "12345678912";
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa =  new Empresa();
		empresa.setRazaoSocial("empresa x");
		empresa.setCnpj(cnpj);
		this.empresaRepository.save(empresa);
	}
	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}
	@Test
	public void testBuscarPorCnpj() {
		Empresa empresa = this.empresaRepository.findByCnpj(cnpj);
		assertEquals(cnpj, empresa.getCnpj());
	}
	
}
