package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object representing the payment page.
 * Provides methods to interact with and retrieve information from the payment page.
 */
public class PaymentPage extends BasePage {

    @FindBy(css = "button[title='Place Order']")
    private WebElement placeOrderBtn;

    /**
     * Clicks the 'Place Order' button and navigates to the success page.
     *
     * @return an instance of SuccessPage for further interactions with the success page.
     */
    public SuccessPage clickAndNavigateToSuccessPage(){
        clickAndNavigate(this.placeOrderBtn, "placeOrderBtn", "Success Page", "SuccessPage");
        return new SuccessPage();
    }
}
