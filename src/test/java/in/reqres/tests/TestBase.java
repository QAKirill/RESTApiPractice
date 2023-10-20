package in.reqres.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demoqa.com";
        RestAssured.defaultParser = Parser.JSON;

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
