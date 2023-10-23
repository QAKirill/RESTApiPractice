package in.reqres.helpers;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:book.properties"})

public interface BookConfig extends Config {
    @Key("title")
    @DefaultValue("Speaking JavaScript")
    String title();

    @Key("isbn")
    @DefaultValue("9781449365035")
    String isbn();
}