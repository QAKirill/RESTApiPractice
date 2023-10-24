package in.reqres.helpers;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:login.properties"})

public interface LoginConfig extends Config{
    @Config.Key("login")
    @Config.DefaultValue("Petrov1415")
    String login();

    @Config.Key("password")
    @Config.DefaultValue("Petrov1415!")
    String password();

    @Config.Key("loginPage")
    @Config.DefaultValue("/Account/v1/Login")
    String loginPage();
}