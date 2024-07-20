package com.evy.framework.pages.authentication;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The LoginPage class represents the login page of the application.
 * It provides methods to perform login operations and check for login success or failure messages.
 */
public class LoginPage extends BasePage {

    @FindBy(css = "#email")
    private WebElement email;
    @FindBy(css = "#pass")
    private WebElement password;
    @FindBy(css = "#send2")
    private WebElement loginBtn;
    @FindBy(css = ".page-header .logged-in")
    private WebElement successLoginMsg;
    @FindBy(css = "div[data-bind*='message.text']")
    private WebElement failLoginMsg;
    @FindBy(css = "div[generated=true]")
    private WebElement emptyLoginMsg;

    /**
     * Logs in with the specified email and password, and navigates to the specified next page class on success.
     *
     * @param email        The email to use for login.
     * @param password     The password to use for login.
     * @param criteria     A boolean indicating whether the login attempt is expected to succeed.
     * @param nextPageClass The class of the next page to navigate to on successful login.
     * @param <T>          The type of the next page class.
     * @return An instance of the next page class.
     * @throws RuntimeException If the login operation fails.
     */
    public <T> T login(String email, String password, boolean criteria, Class<T> nextPageClass) {
        try {
            sendKeys(this.email, email, "email");
            sendKeys(this.password, password, "password");

            if (criteria) {
                clickAndNavigate(this.loginBtn, "loginBtn", "Home Page", "HomePage");
                LoggerUtils.info(getClass(), "Login Success, moving to HomePage");
            } else {
                click(this.loginBtn, "loginBtn");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during login operation", e);
            throw new RuntimeException("Failed to complete login operation", e);
        }
    }


    /**
     * Checks if the appropriate login message is displayed based on the operation type.
     *
     * @param operation The type of login operation ("valid login data", "invalid login data", or "invalid missing data").
     * @return True if the corresponding login message is displayed, otherwise false.
     */
    public boolean isSuccessLoginMsgDisplay(String operation) {
        return switch (operation.toLowerCase()) {
            case "valid login data" -> {
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.textToBePresentInElement(this.successLoginMsg, "Welcome"));
                yield isDisplayed(this.successLoginMsg, "success login message display");
            }
            case "invalid login data" -> isDisplayed(this.failLoginMsg, "fail login message");
            case "invalid missing data" -> isDisplayed(this.emptyLoginMsg, "invalid missing data message");
            default -> {
                LoggerUtils.error(getClass(), "Unknown operation type: " + operation, null);
                throw new IllegalArgumentException("Invalid operation type: " + operation);
            }
        };
    }
}
