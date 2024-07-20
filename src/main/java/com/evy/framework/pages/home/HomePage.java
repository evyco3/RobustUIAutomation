package com.evy.framework.pages.home;

import com.evy.framework.pages.BasePage;

/**
 * HomePage class representing the home page of the application.
 * Extends from BasePage to inherit common page functionalities.
 */
public class HomePage extends BasePage {

    /**
     * Gets an instance of the HomePage.
     *
     * @return a new instance of HomePage
     */
    public static HomePage getInstance(){
        return new HomePage();
    }

    /**
     * Navigates to the product dropdown section of the home page.
     *
     * @return an instance of NavigateToProductDropdown
     */
    public NavigateToProductDropdown navigateToProductDropdown(){
        return new NavigateToProductDropdown();
    }

    /**
     * Navigates to the authentication section of the home page.
     *
     * @return an instance of NavigateToAuthentication
     */
    public NavigateToAuthentication navigateToAuthentication(){
        return new NavigateToAuthentication();
    }
}
