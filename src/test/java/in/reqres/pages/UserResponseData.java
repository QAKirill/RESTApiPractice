package in.reqres.pages;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class UserResponseData {
    @Getter
    Map<String, String> data = new HashMap<>();

    public UserResponseData() {
        data.put("id","3");
        data.put("email", "emma.wong@reqres.in");
        data.put("first_name", "Emma");
        data.put("last_name", "Wong");
        data.put("avatar", "https://reqres.in/img/faces/3-image.jpg");
    }
}
