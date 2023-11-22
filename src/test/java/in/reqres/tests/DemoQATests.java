package in.reqres.tests;

import in.reqres.methods.StepsApi;
import in.reqres.methods.StepsUi;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DemoQATests extends TestBase {

    @Test
    @Epic("DemoQA")
    @Feature("Work with books")
    @Tag("DemoQA")
    @DisplayName("Книга удаляется из коллекции")
    void deleteBookFromCollectionTest() {
        StepsApi authResponse = new StepsApi();

        authResponse
                .login()
                .deleteAllBooks()
                .addBook()
                .deleteBook();

        assertFalse(new StepsUi().isBookPresent(authResponse.getAuthResponse(), authResponse.getBook().getTitle()));

        String checkBookResult = authResponse.isBookPresentByAPI();
        assertAll("Grouped Assertions of response params",
                () -> assertFalse(checkBookResult.contains(authResponse.getBook().getTitle())),
                () -> assertFalse(checkBookResult.contains(authResponse.getBook().getIsbn())));
    }
}