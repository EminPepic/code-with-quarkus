package org.biblioteka;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
            .contentType("application/json")
            .body("{\"ime\":\"Test\",\"prezime\":\"User\"}")
            .when().post("/student/addStudent")
            .then()
            .statusCode(200)
            .body("ime", is("Test"))
            .body("prezime", is("User"));
    }

}
