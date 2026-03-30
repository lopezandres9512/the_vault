package com.hotel.app;

import com.hotel.app.model.Habitacion;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppApplicationTests {

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8");

	@Test
	void contextLoads() {
	}

    @BeforeAll
    static void beforeAll() {
        mysql.start();
    }

    @AfterAll
    static void afterAll() {
        mysql.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void testHabitacionesRetornaListaVacia() {
        ResponseEntity<Habitacion[]> response = testRestTemplate.getForEntity("http://localhost:" + port + "/habitaciones", Habitacion[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Habitacion[] body = response.getBody();
        assertThat(body).isEmpty();
    }

    @Test
    void crearBorrarHabitciones() {
        // get empty
        ResponseEntity<Habitacion[]> response = testRestTemplate.getForEntity("http://localhost:" + port + "/habitaciones", Habitacion[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Habitacion[] body = response.getBody();
        assertThat(body).isEmpty();

        // create
        Habitacion habitacion = new Habitacion();
        habitacion.setCodigo("101");
        habitacion.setTamanio("??");
        habitacion.setCantidadPersonas(3);
        habitacion.setState(Room.RoomState.DISPONIBLE);
        var habitacionRequest = new HttpEntity<>(habitacion);
        ResponseEntity<Habitacion> habitacionResponseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/habitaciones", habitacionRequest, Habitacion.class);
        assertThat(habitacionResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Habitacion bodyHabitacion = habitacionResponseEntity.getBody();
        assertThat(bodyHabitacion.getId()).isNotNull();
        assertThat(bodyHabitacion.getCodigo()).isEqualTo(habitacion.getCodigo());
        assertThat(bodyHabitacion.getCantidadPersonas()).isEqualTo(habitacion.getCantidadPersonas());
        assertThat(bodyHabitacion.getTamanio()).isEqualTo(habitacion.getTamanio());

        // get
        ResponseEntity<Habitacion> habitacionResponseEntity2 = testRestTemplate.getForEntity("http://localhost:" + port + "/habitaciones/{id}", Habitacion.class, bodyHabitacion.getId());
        assertThat(habitacionResponseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);
        Habitacion bodyHabitacion2 = habitacionResponseEntity2.getBody();
        assertThat(bodyHabitacion2.getId()).isEqualTo(bodyHabitacion.getId());
        assertThat(bodyHabitacion2.getCodigo()).isEqualTo(habitacion.getCodigo());
        assertThat(bodyHabitacion2.getCantidadPersonas()).isEqualTo(habitacion.getCantidadPersonas());
        assertThat(bodyHabitacion2.getTamanio()).isEqualTo(habitacion.getTamanio());

        // delete
        testRestTemplate.delete("http://localhost:" + port + "/habitaciones/{id}", bodyHabitacion.getId());

        // get
        ResponseEntity<Habitacion> habitacionResponseEntity3 = testRestTemplate.getForEntity("http://localhost:" + port + "/habitaciones/{id}", Habitacion.class, bodyHabitacion.getId());
        assertThat(habitacionResponseEntity3.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
