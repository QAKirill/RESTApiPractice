package in.reqres.models;

import in.reqres.helpers.BookConfig;
import in.reqres.helpers.ConfigReader;
import lombok.Data;

import static java.lang.String.format;

@Data
public class BookDataModel {
    private String title, isbn;
    private final BookConfig bookConfig = ConfigReader.INSTANCE.read();

    public BookDataModel(){
        setIsbn(bookConfig.isbn());
        setTitle(bookConfig.title());
    }

    public String getAddBookData(String userId){
        return format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, bookConfig.isbn());
    }

    public String getDeleteBookData(String userId){
        return format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                userId, bookConfig.isbn());
    }
}