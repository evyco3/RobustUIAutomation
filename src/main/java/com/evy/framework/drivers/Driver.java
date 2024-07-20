package com.evy.framework.drivers;

import com.evy.framework.config.ConfigManager;
import com.evy.framework.constants.BrowserType;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Objects;

/**
 * Singleton class to manage WebDriver instances using ThreadLocal for thread safety.
 * It provides methods to initialize, quit, retrieve, and configure WebDriver.
 */
public final class Driver {

    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final Driver INSTANCE = new Driver();

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private Driver() {
    }

    /**
     * Returns the singleton instance of Driver class.
     *
     * @return the singleton instance of Driver
     */
    public static Driver getInstance() {
        return INSTANCE;
    }

    /**
     * Initializes the WebDriver instance based on the browser type configured in ConfigManager.
     * Configures timeouts, deletes cookies, and navigates to the configured URL.
     *
     * @throws IllegalArgumentException if an unsupported BrowserType is configured
     * @throws RuntimeException         if WebDriver initialization fails
     */
    public void initDriver() {
        try {
            BrowserType browserType = ConfigManager.get().browserType();
            WebDriver driver = DriverFactory.getDriver(browserType);
            if (driver == null) {
                throw new IllegalArgumentException("Unsupported BrowserType: " + browserType);
            }
            WEB_DRIVER_THREAD_LOCAL.set(driver);
            configureDriver(driver);
            LoggerUtils.info(Driver.class,"Driver initialization successful");
        } catch (Exception e) {
            LoggerUtils.error(Driver.class, "Error during driver initialization", e);
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    /**
     * Quits the current WebDriver instance, removes it from ThreadLocal, and logs the action.
     *
     * @throws RuntimeException if WebDriver quit fails or WebDriver instance is null
     */
    public void quitDriver() {
        try {
            WEB_DRIVER_THREAD_LOCAL.get().quit();
            WEB_DRIVER_THREAD_LOCAL.remove();
            LoggerUtils.info(Driver.class, "Quit Driver");
        } catch (Exception e) {
            LoggerUtils.error(Driver.class, "Error during driver quit", e);
            throw new RuntimeException("Failed to quit driver", e);
        }
    }

    /**
     * Retrieves the current WebDriver instance from ThreadLocal.
     *
     * @return the WebDriver instance
     * @throws NullPointerException if WebDriver instance is null
     */
    public WebDriver getDriver() {
        WebDriver driver = WEB_DRIVER_THREAD_LOCAL.get();
        Objects.requireNonNull(driver, "WebDriver instance is null");
        return driver;
    }

    /**
     * Configures the provided WebDriver instance with page load timeout, implicit wait time,
     * deletes all cookies, and navigates to the URL configured in ConfigManager.
     *
     * @param driver the WebDriver instance to configure
     */
    private void configureDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.get().pageLoadTime()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.get().implicitTime()));
        driver.manage().deleteAllCookies();
        driver.get(ConfigManager.get().url());
    }
}
