package in.reqres.models;

import lombok.Data;

import static java.lang.String.format;

@Data
public class BookDataModel {
    private String name,isbn,userId;

    public String getAddBookData(){
        return format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, isbn);
    }

    public String getDeleteBookData(){
        return format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                userId, isbn);
    }
}
