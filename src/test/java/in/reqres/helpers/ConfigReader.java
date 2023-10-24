package in.reqres.helpers;

import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {
    INSTANCE;

    private static final BookConfig BOOK_CONFIG =
            ConfigFactory.create(
                    BookConfig.class,
                    System.getProperties()
            );

    public BookConfig read() {
        return BOOK_CONFIG;
    }

    private static final LoginConfig LOGIN_CONFIG =
            ConfigFactory.create(
                    LoginConfig.class,
                    System.getProperties()
            );

    public LoginConfig getCredentials() {
        return LOGIN_CONFIG;
    }
}