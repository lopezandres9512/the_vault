package com.hotel.app.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;


@TestConfiguration
public class TestcontainersConfiguration {

	private static final MySQLContainer<?> MYSQL_CONTAINER =
			new MySQLContainer<>("mysql:8.0")
					.withDatabaseName("testdb")
					.withUsername("testuser")
					.withPassword("testpassword");

	static {
		MYSQL_CONTAINER.start();
	}

	@Bean
	public MySQLContainer<?> mySQLContainer() {
		return MYSQL_CONTAINER;
	}
}
