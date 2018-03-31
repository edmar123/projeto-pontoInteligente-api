package com.edmar.pontoInteligente.api.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edmar.pontoInteligente.api.entities.Empresa;
import com.edmar.pontoInteligente.api.entities.Funcionario;
import com.edmar.pontoInteligente.api.enums.PerfilEnum;
import com.edmar.pontoInteligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String email = "@gmail.com";
	private static final String cpf = "12345678901";

	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioRepository.save(ObterDadosFuncionario(empresa));
	}

	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}
	@Test
	public void testBuscarFuncionarioPoEmail() {
		Funcionario funcionario = this.funcionarioRepository.findByEmail(email);
		assertEquals(email, funcionario.getEmail());
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpf(cpf);
		assertEquals(cpf, funcionario.getCpf());
	}
	@Test
	public void testBuscarFuncionarioPorCpfeEmail() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(cpf, email);
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncionarioPorCpfeEmailParaEmailInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(cpf, "@invalido.com");
		assertNotNull(funcionario);
	}
	@Test
	public void testBuscarFuncionarioPorCpfeEmailParaCpfInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("66767676", email);
		assertNotNull(funcionario);
	}

	private Funcionario ObterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("edmar");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBcript("123456"));
		funcionario.setCpf(cpf);
		funcionario.setEmail(email);
		funcionario.setEmpresa(empresa);
		return funcionario;
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("empresa x");
		empresa.setCnpj("12345678912");
		return empresa;
	}
}
