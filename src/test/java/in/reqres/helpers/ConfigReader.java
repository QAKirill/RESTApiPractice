package in.reqres.helpers;

import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {
    INSTANCE;

    private static final TestConfig testConfig =
            ConfigFactory.create(
                    TestConfig.class,
                    System.getProperties()
            );

    public TestConfig read() {
        return testConfig;
    }
}