import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class TestClass {

    @Test
    void getUsersAndCheckTotal() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void getUserById() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(3));
    }

    @Test
    void getUserByWrongId() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/users/13")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void createUser() {
        String userData = "{ \"name\": \"Alexander\", \"job\": \"author\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Alexander"))
                .body("job", is("author"));
    }

    @Test
    void createUserBadRequest() {
        String userData = "{ \"name\":, \"job\": \"author\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body(containsString("Bad Request"));
    }

    @Test
    void updateUserById() {
        String userData = "{ \"name\": \"Katya\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .put("https://reqres.in/api/users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("Katya"));
    }
}
