package com.hotel.app;

import com.hotel.app.config.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;

@SpringBootTest
@ContextConfiguration(classes = TestcontainersConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppApplicationTests {

	@DynamicPropertySource
	static void configureTestDatabase(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", () ->
				"jdbc:mysql://" + TestcontainersConfiguration.MYSQL_CONTAINER.getHost() +
						":" + TestcontainersConfiguration.MYSQL_CONTAINER.getMappedPort(3306) +
						"/testdb"
		);
		registry.add("spring.datasource.username", () -> "testuser");
		registry.add("spring.datasource.password", () -> "testpassword");
	}

	@Test
	void contextLoads() {
	}
}
