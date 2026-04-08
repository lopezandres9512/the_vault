package com.hotel.app;


import com.hotel.app.model.Room;
import com.hotel.app.model.Hotel;
import com.hotel.app.model.Reservation;
import com.hotel.app.model.User;

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


    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

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
    void testRoomsReturnsEmptyList() {
        ResponseEntity<Room[]> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/rooms",
                Room[].class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Room[] body = response.getBody();
        assertThat(body).isEmpty();
    }


    @Test
    void createAndDeleteRoom() {

        ResponseEntity<Room[]> getResponse = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/rooms",
                Room[].class
        );
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isEmpty();


        Room room = new Room();
        room.setCode("101");
        room.setSize("Double");
        room.setPersonQuantity(3);
        room.setState(Room.RoomState.AVAILABLE);

        HttpEntity<Room> roomRequest = new HttpEntity<>(room);
        ResponseEntity<Room> createResponse = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/rooms",
                roomRequest,
                Room.class
        );


        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Room createdRoom = createResponse.getBody();
        assertThat(createdRoom).isNotNull();
        assertThat(createdRoom.getId()).isNotNull();
        assertThat(createdRoom.getCode()).isEqualTo("101");
        assertThat(createdRoom.getPersonQuantity()).isEqualTo(3);
        assertThat(createdRoom.getSize()).isEqualTo("Double");
        assertThat(createdRoom.getState()).isEqualTo(Room.RoomState.AVAILABLE);


        ResponseEntity<Room> getByidResponse = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/rooms/{id}",
                Room.class,
                createdRoom.getId()
        );
        assertThat(getByidResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Room fetchedRoom = getByidResponse.getBody();
        assertThat(fetchedRoom).isNotNull();
        assertThat(fetchedRoom.getId()).isEqualTo(createdRoom.getId());
        assertThat(fetchedRoom.getCode()).isEqualTo("101");


        testRestTemplate.delete(
                "http://localhost:" + port + "/api/v1/rooms/{id}",
                createdRoom.getId()
        );


        ResponseEntity<Room> afterDeleteResponse = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/rooms/{id}",
                Room.class,
                createdRoom.getId()
        );
        assertThat(afterDeleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}