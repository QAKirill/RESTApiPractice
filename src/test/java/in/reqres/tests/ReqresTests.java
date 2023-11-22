package in.reqres.tests;

import in.reqres.endpoints.Endpoints;
import in.reqres.models.*;
import in.reqres.pages.UserResponseData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.endpoints.Endpoints.usersEndpoint;
import static in.reqres.specs.Specs.requestSpec;
import static in.reqres.specs.Specs.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Reqres")
@Feature("Work with users")
@Tag("Reqres")
public class ReqresTests extends TestBaseReqres {

    Endpoints endpoints = new Endpoints();

    @Test
    @DisplayName("Проверка списка пользователей")
    void getUsersAndCheckTotal() {
        UsersResponseModel response = step("Make users request", () ->
                given(requestSpec)
                        .when()
                        .get(usersEndpoint)
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(UsersResponseModel.class));

        step("Verify response", () -> {
            assertAll("Grouped Assertions of response params",
                    () -> assertEquals(1, response.getPage()),
                    () -> assertEquals(6, response.getPerPage()),
                    () -> assertEquals(12, response.getTotal()),
                    () -> assertEquals(2, response.getTotalPages()));
        });
    }

    @Test
    @DisplayName("Проверка полей конкретного пользователя")
    void getUserById() {
        UserModelResponse response = step("Make user request", () ->
                given(requestSpec)
                        .when()
                        .get(endpoints.usersEndpointId, 3)
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .body("data.id", is(3))
                        .extract().as(UserModelResponse.class));

        step("Verify response", () -> {
            assertEquals(response.getData(), new UserResponseData().getData());
        });
    }

    @Test
    @DisplayName("Невозможно получить данные пользователя не заполнив обязательные атрибуты")
    void getUserByWrongId() {
        UserModelResponse response = step("Make user request without attribute", () ->
                given(requestSpec)
                        .when()
                        .get(endpoints.usersEndpointId, 13)
                        .then()
                        .spec(responseSpec.expect().statusCode(404))
                        .extract().as(UserModelResponse.class));

        step("Verify response", () -> {
            assertNull(response.getData());
            assertNull(response.getSupport());
        });
    }

    @Test
    @DisplayName("Возможно создать нового пользователя")
    void createUser() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("Alexander");
        userData.setJob("author");

        CreateUserResponseModel response = step("Make create user request", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .post(usersEndpoint)
                        .then()
                        .spec(responseSpec.expect().statusCode(201))
                        .extract().as(CreateUserResponseModel.class));

        step("Verify response", () -> {
            assertAll("Grouped Assertions of response params",
                    () -> assertEquals("Alexander", response.getName()),
                    () -> assertEquals("author", response.getJob()),
                    () -> assertNotNull(response.getId()),
                    () -> assertNotNull(response.getCreatedAt()));
        });
    }

    @Test
    @DisplayName("Возможно обновить пользователя")
    void updateUserById() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("Katya");
        userData.setJob("housewife");

        UpdateUserResponseModel response = step("Make update user request", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .put(endpoints.usersEndpointId, 3)
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(UpdateUserResponseModel.class));

        step("Verify response", () -> {
            assertAll("Grouped Assertions of response params",
                    () -> assertEquals("Katya", response.getName()),
                    () -> assertEquals("housewife", response.getJob()),
                    () -> assertNotNull(response.getUpdatedAt()));
        });
    }
}