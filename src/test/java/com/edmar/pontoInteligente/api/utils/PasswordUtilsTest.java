package com.edmar.pontoInteligente.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Test
	public void testsenhaNula() throws Exception{
		assertNull(PasswordUtils.gerarBcript(null));
	}
	@Test
	public void testGerarHashSenha() throws Exception{
		String hash = PasswordUtils.gerarBcript(SENHA);
		assertTrue(bcrypt.matches(SENHA, hash));
	}
}
