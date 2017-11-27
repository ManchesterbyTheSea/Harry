package com.harry.web;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
//		String hashAlgorithmName = "MD5";
//		String credentials = "123456";
//		int hashIterations = 3;
//		ByteSource credentialsSalt = ByteSource.Util.bytes("manchester");
//		Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
//		System.out.println(obj);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}
}
