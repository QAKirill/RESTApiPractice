package in.reqres.models;

import lombok.Data;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
public class UserModelResponse {
    Map<String, String> data;
    Map<String, String> support;

    public UserModelResponse checkElement(String element, String value) {
        assertEquals(value, getData().get(element));

        return this;
    }
}
