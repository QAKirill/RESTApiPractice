package in.reqres.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModelResponse {
    Map<String, String> data;

    public UserModelResponse checkElement(String element, String value) {
        assertEquals(value, getData().get(element));

        return this;
    }
}
