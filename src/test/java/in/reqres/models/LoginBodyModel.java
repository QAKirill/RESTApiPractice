package in.reqres.models;

import in.reqres.helpers.Credentials;
import lombok.Data;

@Data
public class LoginBodyModel {
    String login, password;

    public LoginBodyModel() {
        this.login = Credentials.login;
        this.password = Credentials.password;
    }

    public String getAuthData() {
        return String.format("{\"userName\":\"%s\",\"password\":\"%s\"}", login, password);
    }
}
