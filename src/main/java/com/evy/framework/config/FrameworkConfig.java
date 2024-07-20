package com.evy.framework.config;

import com.evy.framework.constants.BrowserType;
import org.aeonbits.owner.Config;

/**
 * FrameworkConfig is an interface for configuration properties
 * related to the framework. It uses the Owner library for property management.
 */

@Config.Sources("file:${user.dir}/src/main/resources/config.properties")
public interface FrameworkConfig extends Config {

    @DefaultValue("CHROME")
    @Key("browserType")
    @ConverterClass(BrowserTypeConverter.class)
    BrowserType browserType();

    @Key("url")
    String url();

    @Key("pageLoadTime")
    int pageLoadTime();

    @Key("implicitTime")
    int implicitTime();

    @Key("email")
    String email();

    @Key("password")
    String password();


}
