package in.reqres.methods;

import in.reqres.models.LoginResponseModel;
import in.reqres.pages.ProfilePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StepsUi {

    @Step("Проверяем, что книга удалена (UI)")
    public boolean isBookPresent(LoginResponseModel response, String bookAttribute){
        ProfilePage profilePage = new ProfilePage();
        profilePage.addCookies(response);
        profilePage.openProfilePage();
        return $(profilePage.getReactTable()).$(byText(bookAttribute)).exists();
    }
}