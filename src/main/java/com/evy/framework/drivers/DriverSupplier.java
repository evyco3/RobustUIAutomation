/**
 * Package con.evy.framework.drivers
 * <p>
 * This package contains classes and interfaces related to the creation and management
 * of WebDriver instances for browser automation using Selenium WebDriver.
 */

package com.evy.framework.drivers;

import org.openqa.selenium.WebDriver;

/**
 * The DriverSupplier interface provides a method to supply WebDriver instances.
 * <p>
 * Implementations of this interface are responsible for creating and returning
 * instances of WebDriver for different browsers.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * DriverSupplier chromeDriverSupplier = new ChromeDriverSupplier();
 * WebDriver driver = ChromeDriverSupplier.getDriver();
 * }</pre>
 *
 * @see org.openqa.selenium.WebDriver
 */
public interface DriverSupplier {

    /**
     * Gets a WebDriver instance.
     *
     * @return a WebDriver instance
     */
    WebDriver getDriver();
}
