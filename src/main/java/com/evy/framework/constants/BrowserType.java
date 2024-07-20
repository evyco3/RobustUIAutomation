/**
 * Package con.evy.framework.constants
 *
 * This package contains classes and enums that define various constants used
 * throughout the automation framework.
 */

package com.evy.framework.constants;

/**
 * Enum representing the different types of web browsers supported by the automation framework.
 * <p>
 * This enum is used to specify the browser type when creating WebDriver instances.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>{@code
 * BrowserType browser = BrowserType.CHROME;
 * }</pre>
 *
 * @see org.openqa.selenium.WebDriver
 */
public enum BrowserType {
    /**
     * Google Chrome browser.
     */
    CHROME,

    /**
     * Mozilla Firefox browser.
     */
    FIREFOX,

    /**
     * Microsoft Edge browser.
     */
    EDGE,

    /**
     * Opera browser.
     */
    OPERA,

    /**
     * Apple Safari browser.
     */
    SAFARI,

    /**
     * Microsoft Internet Explorer browser.
     */
    EXPLORER
}
