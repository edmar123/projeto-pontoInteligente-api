package com.edmar.pontoInteligente.api.repository;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edmar.pontoInteligente.api.entities.Empresa;
import com.edmar.pontoInteligente.api.entities.Funcionario;
import com.edmar.pontoInteligente.api.entities.Lancamento;
import com.edmar.pontoInteligente.api.enums.PerfilEnum;
import com.edmar.pontoInteligente.api.enums.TipoEnum;
import com.edmar.pontoInteligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	private Long funcionarioId;
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		Funcionario funcionario = this.funcionarioRepository.save(ObterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();
		
		this.lancamentoRepository.save(obterDadosLancamento(funcionario));
		this.lancamentoRepository.save(obterDadosLancamento(funcionario));
		
	}
	@After
	public void tearDown() throws Exception{
		this.empresaRepository.deleteAll();
	}
	@Test
	public void testBuscarFuncionarioPorId() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarFuncionarioPorIdPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	private Lancamento obterDadosLancamento(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setFuncionario(funcionario);
		return lancamento;
		
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("empresa x");
		empresa.setCnpj("12345678912");
		return empresa;
	}
	
	private Funcionario ObterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("edmar");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBcript("123456"));
		funcionario.setCpf("1234567");
		funcionario.setEmail("@gmail.com");
		funcionario.setEmpresa(empresa);
		
		return funcionario;
	}
}
