package com.edmar.pontoInteligente.api.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);

	public PasswordUtils() {
		
	}
	
	public static String gerarBcript(String senha) {
		if(senha == null) {
			return  senha;
		}
		
		log.info("Gerando hash com o bcrip");
		BCryptPasswordEncoder bcrypEncoder = new BCryptPasswordEncoder();
		return bcrypEncoder.encode(senha);
	}
}
