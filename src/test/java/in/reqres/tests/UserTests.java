package in.reqres.tests;

import in.reqres.models.*;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class UserTests extends TestBase {

    @Test
    void getUsersAndCheckTotal() {
        UsersResponseModel response = step("Make users request", () ->
                given(requestSpec)
                        .when()
                        .get("/users")
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(UsersResponseModel.class));

        step("Verify response", () -> {
            assertEquals(1, response.getPage());
            assertEquals(6, response.getPerPage());
            assertEquals(12, response.getTotal());
            assertEquals(2, response.getTotalPages());
        });
    }


    @Test
    void getUserById() {
        UserModelResponse response = step("Make user request", () ->
                given(requestSpec)
                        .when()
                        .get("/users/3")
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .body("data.id", is(3))
                        .extract().as(UserModelResponse.class));

        step("Verify response", () -> {
            response
                    .checkElement("id", "3")
                    .checkElement("email", "emma.wong@reqres.in")
                    .checkElement("first_name", "Emma")
                    .checkElement("last_name", "Wong")
                    .checkElement("avatar", "https://reqres.in/img/faces/3-image.jpg");
        });
    }

    @Test
    void getUserByWrongId() {
        UserModelResponse response = step("Make user request without attribute", () ->
                given(requestSpec)
                        .when()
                        .get("/users/13")
                        .then()
                        .spec(responseSpec.expect().statusCode(404))
                        .extract().as(UserModelResponse.class));

        step("Verify response", () -> {
            assertNull(response.getData());
            //assertNull(response.getSupport());
        });
    }

    @Test
    void createUser() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("Alexander");
        userData.setJob("author");

        CreateUserResponseModel response = step("Make create user request", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec.expect().statusCode(201))
                        .extract().as(CreateUserResponseModel.class));

        step("Verify response", () -> {
            assertEquals("Alexander", response.getName());
            assertEquals("author", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    void updateUserById() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("Katya");
        userData.setJob("housewife");

        UpdateUserResponseModel response = step("Make update user request", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .put("/users/3")
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(UpdateUserResponseModel.class));

        step("Verify response", () -> {
            assertEquals("Katya", response.getName());
            assertEquals("housewife", response.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }
}
