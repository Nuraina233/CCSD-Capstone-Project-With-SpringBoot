package com.example.ecommerce;

import Database.CreateDbTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) throws SQLException {

		//CreateDbTable create = new CreateDbTable();

		SpringApplication.run(EcommerceApplication.class, args);
	}
}
