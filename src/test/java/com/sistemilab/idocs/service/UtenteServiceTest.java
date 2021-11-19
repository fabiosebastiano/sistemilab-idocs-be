package com.sistemilab.idocs.service;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class UtenteServiceTest {

    @Test
    void getAllUsers() {
        /*
        given()
                .pathParam("userId", 1)
                .when().get("/users/")
                .then()
                .statusCode(200)
                .body(is("hello"));

         */
    }

    @Test
    void authenticateUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateClientiAssociati() {
    }
}