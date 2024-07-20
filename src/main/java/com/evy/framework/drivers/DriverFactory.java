package com.evy.framework.drivers;

import com.evy.framework.constants.BrowserType;
import com.evy.framework.utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Factory class to create WebDriver instances based on browser types.
 * This class provides a centralized way to obtain WebDriver instances configured for different browsers.
 * It supports browsers like Chrome, Firefox, Edge, Safari, Opera, and Internet Explorer.
 */
public final class DriverFactory {

    private static final Map<BrowserType, Supplier<DriverSupplier>> DRIVERS_MAP = new EnumMap<>(BrowserType.class);

    /**
     * Private constructor to prevent instantiation of the factory class.
     */
    private DriverFactory() {}

    static {
        DRIVERS_MAP.put(BrowserType.CHROME, ChromeDriverSupplier::new);
        DRIVERS_MAP.put(BrowserType.FIREFOX, FirefoxDriverSupplier::new);
        DRIVERS_MAP.put(BrowserType.EDGE, EdgeDriverSupplier::new);
        DRIVERS_MAP.put(BrowserType.SAFARI, SafariDriverSupplier::new);
        DRIVERS_MAP.put(BrowserType.OPERA, OperaDriverSupplier::new);
        DRIVERS_MAP.put(BrowserType.EXPLORER, InternetExplorerDriverSupplier::new);
    }

    /**
     * Returns a WebDriver instance for the specified browser type.
     *
     * @param browserType the type of browser for which the WebDriver instance is needed
     * @return a configured WebDriver instance
     * @throws RuntimeException if the WebDriver instance cannot be created
     */
    static WebDriver getDriver(BrowserType browserType) {
        try {
            WebDriver driver = DRIVERS_MAP.get(browserType).get().getDriver();
            Objects.requireNonNull(driver, "Driver instance is null for browser: " + browserType);
            LoggerUtils.info(DriverFactory.class, "Launch Browser: " + browserType);
            return driver;
        } catch (Exception e) {
            LoggerUtils.error(DriverFactory.class, "Error during browser launch: " + browserType, e);
            throw new RuntimeException("Failed to launch browser: " + browserType, e);
        }
    }

    /**
     * Supplier for Chrome WebDriver instances.
     */
    public static final class ChromeDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
           WebDriverManager.chromedriver().setup();
           ChromeOptions options=new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-debugging-port=9222");
            return new ChromeDriver(options);

        }
    }

    /**
     * Supplier for Firefox WebDriver instances.
     */
    public static final class FirefoxDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.firefoxdriver().create();
        }
    }

    /**
     * Supplier for Edge WebDriver instances.
     */
    public static final class EdgeDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.edgedriver().create();
        }
    }

    /**
     * Supplier for Internet Explorer WebDriver instances.
     * Note: Internet Explorer does not support headless mode.
     */
    public static final class InternetExplorerDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.iedriver().create(); // not support headless mode
        }
    }

    /**
     * Supplier for Safari WebDriver instances.
     * Note: Safari does not support headless mode.
     */
    public static final class SafariDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.safaridriver().create(); // not support headless browser mode
        }
    }

    /**
     * Supplier for Opera WebDriver instances.
     * Note: Opera uses ChromeOptions since it is based on Chromium.
     */
    public static final class OperaDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.operadriver().create();
        }
    }
}
