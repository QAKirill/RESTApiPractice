package in.reqres.tests;

import com.codeborne.selenide.Driver;
import in.reqres.models.LoginResponseModel;
import in.reqres.pages.ProfilePage;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static in.reqres.specs.Specs.requestSpec;
import static in.reqres.specs.Specs.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

public class DeleteGoodsFromBasketTests extends TestBase {
    @Test
    void addBookToCollectionTest() throws InterruptedException {
        String authData = "{\"userName\":\"Petrov1415\",\"password\":\"Petrov1415!\"}";

        LoginResponseModel authResponse = step("Логинимся", () ->
                given(requestSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec.expect().statusCode(200))
                .extract().as(LoginResponseModel.class));

        step("Очистка коллекции", () ->
                given(requestSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())//path("token"))
                .queryParams("UserId", authResponse.getUserId())//path("userId"))
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec.expect().statusCode(204)));

        String isbn = "9781449365035";
        String bookAddData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.getUserId() , isbn);

        step("Добавляем одну книгу", () ->
                given(requestSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(bookAddData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec.expect().statusCode(201)));

        String bookDeleteData = format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                authResponse.getUserId() , isbn);

        step("Удаляем книгу добавленную на предыдущем шаге", () ->
                given(requestSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(bookDeleteData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec.expect().statusCode(204)));

        ProfilePage profilePage = new ProfilePage();
        profilePage.addCookies(authResponse);

        step("Проверяем, что книга удалена (UI)", () -> {
            profilePage.openProfilePage();
            $(".ReactTable").shouldNotHave(text("Speaking JavaScript"));
        });

        step("Проверяем, что книга удалена (API)", () -> {
            given(requestSpec)
                    .header("Authorization", "Bearer " + authResponse.getToken())
                    .body(bookDeleteData)
                    .when()
                    .post("/Account/v1/User/"+authResponse.getUserId())
                    .then()
                    .spec(responseSpec.expect().statusCode(200));
        });
    }

    @Test
    void addBookToCollection_withDelete1Book_Test() {
        String authData = "{\"userName\":\"testtestov31\",\"password\":\"Testtestov31_%\"}";

        Response authResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        String isbn = "9781449365035";
        String deleteBookData = format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                authResponse.path("userId") , isbn);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(deleteBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.path("userId") , isbn);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userId", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));

        open("/profile");
        $(".ReactTable").shouldHave(text("Speaking JavaScript"));
    }
}
