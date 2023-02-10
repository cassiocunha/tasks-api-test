package com.monkeyhand;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://host.docker.internal:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTarefas() {
        RestAssured.given()
                .when()
                .get("/todo")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefas() {
        RestAssured.given()
                .body("{ \"task\": \"Teste via API\", \"dueDate\":\"2023-12-30\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(201);
    }

    @Test
    public void naoDeveAdicionarTarefasInvalidas() {
        RestAssured.given()
                .body("{ \"task\": \"Teste via API\", \"dueDate\":\"2020-12-30\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(400);
    }
}
