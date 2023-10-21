package in.reqres.models;

import in.reqres.helpers.TestConfig;
import lombok.Data;

import static java.lang.String.format;

@Data
public class BookDataModel {
    private String userId;
    private final TestConfig testConfig;

    public BookDataModel(TestConfig testConfig){
        this.testConfig = testConfig;
    }

    public String getAddBookData(){
        return format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                userId, testConfig.isbn());
    }

    public String getDeleteBookData(){
        return format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                userId, testConfig.isbn());
    }

    public String getTitle(){
        return testConfig.title();
    }

    public String getIsbn(){
        return testConfig.isbn();
    }
}