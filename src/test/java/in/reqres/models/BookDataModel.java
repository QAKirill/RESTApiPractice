package in.reqres.models;

import in.reqres.helpers.BookConfig;
import in.reqres.helpers.ConfigReader;
import lombok.Data;

@Data
public class BookDataModel {
    private String title, isbn;
    private final BookConfig bookConfig = ConfigReader.INSTANCE.read();

    public BookDataModel(){
        setIsbn(bookConfig.isbn());
        setTitle(bookConfig.title());
    }
}