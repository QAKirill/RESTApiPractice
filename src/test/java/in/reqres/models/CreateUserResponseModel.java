package in.reqres.models;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    int id;
    String name, job, createdAt;
}
