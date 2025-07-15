package com.bvn.auth_service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		// generate valid secret key once when running
		/* String base64Key = Encoders.BASE64.encode(Jwts.SIG.HS256.key().build().getEncoded());
		System.out.println("Generated Base64 Secret Key: " + base64Key); */
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
