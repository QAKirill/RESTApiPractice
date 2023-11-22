package in.reqres.methods;

import in.reqres.models.BookDataModel;
import in.reqres.models.LoginBodyModel;
import in.reqres.models.LoginResponseModel;
import io.qameta.allure.Step;

import static in.reqres.endpoints.Endpoints.bookEndpoint;
import static in.reqres.endpoints.Endpoints.booksEndpoint;
import static in.reqres.specs.Specs.requestSpec;
import static in.reqres.specs.Specs.responseSpec;
import static io.restassured.RestAssured.given;

public class StepsApi {
    private LoginResponseModel authResponse;

    public LoginResponseModel getAuthResponse() {
        return authResponse;
    }

    BookDataModel book = new BookDataModel();
    BookBodybuilder bodybuilder = new BookBodybuilder();
    LoginBodyModel loginBodyModel = new LoginBodyModel();

    public BookDataModel getBook() {
        return book;
    }

    @Step("Логинимся")
    public StepsApi login(){
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
    public StepsApi deleteAllBooks(){
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
    public StepsApi deleteBook(){
        given(requestSpec)
                .header(authResponse.getHeader(), authResponse.getHeaderValue())
                .body(bodybuilder.getDeleteBookData(authResponse.getUserId(), book.getIsbn()))
                .when()
                .delete(bookEndpoint)
                .then()
                .spec(responseSpec.expect().statusCode(204));

        return this;
    }

    @Step("Добавляем книгу")
    public StepsApi addBook(){
        given(requestSpec)
                .header(authResponse.getHeader(), authResponse.getHeaderValue())
                .body(bodybuilder.getAddBookData(authResponse.getUserId(), book.getIsbn()))
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