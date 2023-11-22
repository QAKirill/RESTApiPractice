package in.reqres.models;

import lombok.Data;

import java.util.Map;

@Data
public class UserModelResponse {
    Map<String, String> data;
    Map<String, String> support;
}
