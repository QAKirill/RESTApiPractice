package in.reqres.tests;

import in.reqres.Interactions.Api;
import in.reqres.Interactions.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteGoodsFromBasketTests extends TestBase {

    @Test
    void addBookToCollectionTest() {
        Api authResponse = new Api();

        authResponse
                .login()
                .deleteAllBooks()
                .addBook()
                .deleteBook();

        assertFalse(new Ui().isBookPresent(authResponse.getAuthResponse(), authResponse.getBook().getTitle()));

        String checkBookResult = authResponse.isBookPresentByAPI();
        assertFalse(checkBookResult.contains(authResponse.getBook().getTitle()));
        assertFalse(checkBookResult.contains(authResponse.getBook().getIsbn()));
    }
}