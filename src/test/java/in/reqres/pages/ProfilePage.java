package in.reqres.pages;

import com.codeborne.selenide.SelenideElement;
import in.reqres.models.LoginResponseModel;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProfilePage {
    @Getter
    private final SelenideElement reactTable = $(".ReactTable");

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