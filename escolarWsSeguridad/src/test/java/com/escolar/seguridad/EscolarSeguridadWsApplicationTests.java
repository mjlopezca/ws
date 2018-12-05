package com.escolar.seguridad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EscolarSeguridadWsApplicationTests {

	@Test
	public void contextLoads() {
	}
  
	@Test
	public void encriptar() {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();		
		System.err.println(encoder.encode("contraSecreta"));	
		//$2a$10$OK2RWVXtzKmDYDg4NojZs.iQLhfPTBocNaTkyZc4eBw5sLAbZHTG2
		//$2a$10$Y01ciLLi..Rm0U3OvzRMi.P1MACQm1VWhMq62X8OmOps5UjfyUaXG
	}
}
