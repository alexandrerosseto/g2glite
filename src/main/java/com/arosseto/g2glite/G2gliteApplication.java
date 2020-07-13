package com.arosseto.g2glite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arosseto.g2glite.services.S3Service;

@SpringBootApplication
public class G2gliteApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(G2gliteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("D:\\GDrive\\Trabalho\\FaturasWeb\\Promocional\\Instagram\\Tablet-FaturasWeb-Mulher.jpg.png");
	}
}
