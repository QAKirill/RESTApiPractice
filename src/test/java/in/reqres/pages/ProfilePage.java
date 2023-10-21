package in.reqres.pages;

import in.reqres.models.LoginResponseModel;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {

    @Step("Добавляем файлы cookie")
    public ProfilePage addCookies(LoginResponseModel response) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));

        return this;
    }

    public ProfilePage openProfilePage() {
        open("/profile");

        return this;
    }
}