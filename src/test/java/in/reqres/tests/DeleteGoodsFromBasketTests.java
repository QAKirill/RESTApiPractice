package in.reqres.tests;

import in.reqres.models.BookDataModel;
import in.reqres.models.HeaderModel;
import in.reqres.models.LoginBodyModel;
import in.reqres.models.LoginResponseModel;
import in.reqres.pages.ProfilePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static in.reqres.specs.Specs.requestSpec;
import static in.reqres.specs.Specs.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteGoodsFromBasketTests extends TestBase {

    @Test
    void addBookToCollectionTest() {
        HeaderModel headerModel = new HeaderModel();
        BookDataModel book = new BookDataModel(testConfig);

        LoginResponseModel authResponse = step("Логинимся", () ->
                given(requestSpec)
                        .body(new LoginBodyModel().getAuthData())
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(responseSpec.expect().statusCode(200))
                        .extract().as(LoginResponseModel.class));

        headerModel.setHeader("Authorization");
        headerModel.setHeaderValue("Bearer " + authResponse.getToken());
        book.setUserId(authResponse.getUserId());

        step("Очистка коллекции", () ->
                given(requestSpec)
                        .header(headerModel.getHeader(), headerModel.getHeaderValue())
                        .queryParams("UserId", authResponse.getUserId())
                        .when()
                        .delete("/BookStore/v1/Books")
                        .then()
                        .spec(responseSpec.expect().statusCode(204)));

        step("Добавляем одну книгу", () ->
                given(requestSpec)
                        .header(headerModel.getHeader(), headerModel.getHeaderValue())
                        .body(book.getAddBookData())
                        .when()
                        .post("/BookStore/v1/Books")
                        .then()
                        .spec(responseSpec.expect().statusCode(201)));

        step("Удаляем книгу добавленную на предыдущем шаге", () ->
                given(requestSpec)
                        .header(headerModel.getHeader(), headerModel.getHeaderValue())
                        .body(book.getDeleteBookData())
                        .when()
                        .delete("/BookStore/v1/Book")
                        .then()
                        .spec(responseSpec.expect().statusCode(204)));

        ProfilePage profilePage = new ProfilePage();
        profilePage.addCookies(authResponse);

        step("Проверяем, что книга удалена (UI)", () -> {
            profilePage.openProfilePage();
            $(".ReactTable").shouldNotHave(text(book.getTitle()));
        });

        step("Проверяем, что книга удалена (API)", () -> {
            String response = given(requestSpec)
                    .header(headerModel.getHeader(), headerModel.getHeaderValue())
                    .when()
                    .get("/Account/v1/User/" + authResponse.getUserId())
                    .then()
                    .spec(responseSpec.expect().statusCode(200))
                    .extract().asString();

            System.out.println(response);
            assertFalse(response.contains(book.getTitle()));
            assertFalse(response.contains(book.getIsbn()));
        });
    }
}