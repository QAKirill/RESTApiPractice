package in.reqres.Interactions;

import in.reqres.models.BookDataModel;
import in.reqres.models.LoginBodyModel;
import in.reqres.models.LoginResponseModel;
import in.reqres.pages.ProfilePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static in.reqres.pages.BookStoreApi.*;
import static in.reqres.specs.Specs.requestSpec;
import static in.reqres.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class Api {
    private LoginResponseModel authResponse;

    public LoginResponseModel getAuthResponse() {
        return authResponse;
    }

    BookDataModel book = new BookDataModel();
    LoginBodyModel loginBodyModel = new LoginBodyModel();

    public BookDataModel getBook() {
        return book;
    }

    @Step("Логинимся")
    public Api login(){
        authResponse = given(requestSpec)
                .body(loginBodyModel.getAuthData())
                .when()
                .post(loginBodyModel.getLoginPage())
                .then()
                .spec(responseSpec.expect().statusCode(200))
                .extract().as(LoginResponseModel.class);

        authResponse.setHeaderValue("Bearer " + authResponse.getToken());
        return this;
    }

    @Step("Очистка коллекции книг")
    public Api deleteAllBooks(){
        given(requestSpec)
                .header(authResponse.getHeader(), authResponse.getHeaderValue())
                .queryParams("UserId", authResponse.getUserId())
                .when()
                .delete(booksEndpoint)
                .then()
                .spec(responseSpec.expect().statusCode(204));

        return this;
    }

    @Step("Удаляем книгу")
    public Api deleteBook(){
        given(requestSpec)
                .header(authResponse.getHeader(), authResponse.getHeaderValue())
                .body(book.getDeleteBookData(authResponse.getUserId()))
                .when()
                .delete(bookEndpoint)
                .then()
                .spec(responseSpec.expect().statusCode(204));

        return this;
    }

    @Step("Добавляем книгу")
    public Api addBook(){
        given(requestSpec)
                .header(authResponse.getHeader(), authResponse.getHeaderValue())
                .body(book.getAddBookData(authResponse.getUserId()))
                .when()
                .post(booksEndpoint)
                .then()
                .spec(responseSpec.expect().statusCode(201));

        return this;
    }

    @Step("Проверяем, что книга удалена (API)")
    public String isBookPresentByAPI(){
        return given(requestSpec)
                .header(authResponse.getHeader(), authResponse.getHeaderValue())
                .when()
                .get(loginBodyModel.getLoginPage() + authResponse.getUserId())
                .then()
                .spec(responseSpec.expect().statusCode(200))
                .extract().asString();
    }
}