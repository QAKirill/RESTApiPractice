package in.reqres.models;

import in.reqres.helpers.ConfigReader;
import in.reqres.helpers.LoginConfig;
import lombok.Data;

@Data
public class LoginBodyModel {
    String login, password, loginPage;
    private final LoginConfig loginConfig = ConfigReader.INSTANCE.getCredentials();

    public LoginBodyModel() {
        this.login = loginConfig.login();
        this.password = loginConfig.password();
        this.loginPage = loginConfig.loginPage();
    }

    public String getAuthData() {
        return String.format("{\"userName\":\"%s\",\"password\":\"%s\"}", login, password);
    }
}