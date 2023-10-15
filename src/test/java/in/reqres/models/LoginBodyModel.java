package in.reqres.models;

import in.reqres.helpers.Credentials;
import lombok.Data;

@Data
public class LoginBodyModel {
    String email, password;

    public LoginBodyModel gatAuthData(){
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(Credentials.email);
        authData.setPassword(Credentials.password);

        return authData;
    }
}
