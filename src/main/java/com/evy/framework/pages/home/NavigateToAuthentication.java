package com.evy.framework.pages.home;

import com.evy.framework.pages.authentication.LoginPage;
import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.authentication.RegisterPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * NavigateToAuthentication class representing navigation actions related to authentication pages.
 * Extends from BasePage to inherit common page functionalities.
 */
public class NavigateToAuthentication extends BasePage {
    @FindBy(css = "header.page-header a[href*='create']")
    private WebElement registerLink;

    @FindBy(css = "header.page-header a[href*='login']")
    private WebElement loginLink;

    /**
     * Navigates to the registration page.
     *
     * @return an instance of RegisterPage
     */
    public RegisterPage navigateToRegisterPage(){
        clickAndNavigate(this.registerLink, "registerLink", "Create New Customer Account", "RegisterPage");
        return new RegisterPage();
    }

    /**
     * Navigates to the login page.
     *
     * @return an instance of LoginPage
     */
    public LoginPage navigateToLoginPage(){
        clickAndNavigate(this.loginLink, "loginLink", "Customer Login", "LoginPage");
        return new LoginPage();
    }
}
