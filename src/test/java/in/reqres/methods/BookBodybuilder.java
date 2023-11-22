package in.reqres.methods;

import static java.lang.String.format;

public class BookBodybuilder {

    public String getAddBookData(String userId, String isbn){
        return format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, isbn);
    }

    public String getDeleteBookData(String userId, String isbn) {
        return format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                userId, isbn);
    }
}
