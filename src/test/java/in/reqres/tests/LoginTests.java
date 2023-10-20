package in.reqres.tests;

import in.reqres.models.LoginBodyModel;
import in.reqres.models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.Specs.requestSpec;
import static in.reqres.specs.Specs.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests extends TestBase {

    @Test
    void successfulLoginTest() {
        LoginResponseModel response = step("Make login request", () ->
                given(requestSpec)
                        .body(new LoginBodyModel().getAuthData())
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(LoginResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    void successfulLoginTestWithAllure() {
        LoginResponseModel response = step("Make login request", () ->
                given(requestSpec)
                        .body(new LoginBodyModel().getAuthData())
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(LoginResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    void successfulLoginTestWithCustomAllure() {
        LoginResponseModel response = step("Make login request", () ->
                given(requestSpec)
                        .body(new LoginBodyModel().getAuthData())
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(LoginResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    void successfulLoginTestWithSpec() {
        LoginResponseModel response = step("Make login request", () ->
                given(requestSpec)
                        .body(new LoginBodyModel().getAuthData())
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }
}