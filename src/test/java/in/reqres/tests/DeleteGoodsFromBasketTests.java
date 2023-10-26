package in.reqres.tests;

import in.reqres.methods.StepsApi;
import in.reqres.methods.StepsUi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteGoodsFromBasketTests extends TestBase {

    @Test
    void addBookToCollectionTest() {
        StepsApi authResponse = new StepsApi();

        authResponse
                .login()
                .deleteAllBooks()
                .addBook()
                .deleteBook();

        assertFalse(new StepsUi().isBookPresent(authResponse.getAuthResponse(), authResponse.getBook().getTitle()));

        String checkBookResult = authResponse.isBookPresentByAPI();
        assertFalse(checkBookResult.contains(authResponse.getBook().getTitle()));
        assertFalse(checkBookResult.contains(authResponse.getBook().getIsbn()));
    }
}