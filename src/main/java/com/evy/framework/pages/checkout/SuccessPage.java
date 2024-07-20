package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object representing the success page.
 * Provides methods to interact with and retrieve information from the success page.
 */
public class SuccessPage extends BasePage {

    @FindBy(css = ".page-title>span")
    private WebElement successOrderMsg;

    /**
     * Retrieves the success message displayed after placing an order.
     *
     * @return the success message text.
     */
    public String getSuccessOrderMsg(){
        return getText(this.successOrderMsg, "success order message");
    }
}
