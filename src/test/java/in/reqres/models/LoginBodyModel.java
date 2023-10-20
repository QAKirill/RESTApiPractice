package in.reqres.models;

import in.reqres.helpers.Credentials;
import lombok.Data;

@Data
public class LoginBodyModel {
    String login, password;

    public LoginBodyModel getAuthData(){
        LoginBodyModel authData = new LoginBodyModel();
        authData.setLogin(Credentials.login);
        authData.setPassword(Credentials.password);

        return authData;
    }
}
