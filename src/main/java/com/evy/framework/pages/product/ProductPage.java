package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.checkout.CartPage;
import com.evy.framework.utils.ActionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page object representing the product page.
 * Provides methods to interact with and retrieve information from the product page.
 */
public class ProductPage extends BasePage {

    @FindBy(css = "#qty")
    private WebElement productQuantity;

    @FindBy(css = "#product-addtocart-button")
    private WebElement addToCartBtn;

    @FindBy(css = "div[data-bind*='message.text']")
    private WebElement successAddToCartMsg;

    @FindBy(css = "#qty-error")
    private WebElement quantityErrorMsg;

    /**
     * Sets the product size by selecting the specified size from the available options.
     *
     * @param productSize The size to be selected.
     * @return the current instance of ProductPage for method chaining.
     */
    public ProductPage setProductSize(String productSize){
        String productSizeStringValue = String.format("div[id*='option-label-size'][option-tooltip-value='%s']", productSize);
        WebElement element = driver.findElement(By.cssSelector(productSizeStringValue));
        click(element, productSize);
        return this;
    }

    /**
     * Sets the product color by selecting the specified color from the available options.
     *
     * @param productColor The color to be selected.
     * @return the current instance of ProductPage for method chaining.
     */
    public ProductPage setProductColor(String productColor){
        String productColorStringValue = String.format("div[id*='option-label-color'][aria-label='%s']", productColor);
        WebElement element = driver.findElement(By.cssSelector(productColorStringValue));
        click(element, productColor);
        return this;
    }

    /**
     * Sets the product quantity by entering the specified quantity into the quantity field.
     *
     * @param productQuantity The quantity to be set.
     * @return the current instance of ProductPage for method chaining.
     */
    public ProductPage setProductQuantity(String productQuantity){
        sendKeys(this.productQuantity, productQuantity, "productQuantity");
        return this;
    }

    /**
     * Clicks the 'Add to Cart' button to add the product to the shopping cart.
     *
     * @return the current instance of ProductPage for method chaining.
     */
    public ProductPage clickAddToCartBtn(){
        click(this.addToCartBtn, "addToCartBtn");
        return this;
    }

    /**
     * Retrieves the success message displayed after adding a product to the cart.
     *
     * @return the success message text.
     */
    public String getSuccessAddToCartMsg(){
        return getText(this.successAddToCartMsg, "add to cart success message");
    }

    /**
     * Retrieves the response message based on the operation type.
     *
     * @param operation The type of operation (e.g., "valid quantity" or "invalid quantity").
     * @return the response message text.
     * @throws IllegalArgumentException if the operation type is not recognized.
     */
    public String getQuantityResponseMsg(String operation){
        if(operation.equalsIgnoreCase("valid quantity")){
            return getSuccessAddToCartMsg();
        }
        else if(operation.equalsIgnoreCase("invalid quantity")){
            return getText(this.quantityErrorMsg, "quantity error message");
        }
        throw new IllegalArgumentException("Illegal operation " + operation);
    }

    /**
     * Navigates to the cart page if the success message is verified.
     *
     * @return an instance of CartPage if the message is verified; null otherwise.
     */
    public CartPage navigateToCartPage(){
        if(verifySuccessAddToCartMessage()) {
            click(driver.findElement(By.cssSelector(".action.showcart")), "cart button");
            click(driver.findElement(By.cssSelector(".action.viewcart")), "cart view btn");
            waitForPageTitleToBeEquals("Shopping Cart");
            return new CartPage();
        }
        return null;
    }

    /**
     * Verifies if the success message is displayed after adding an item to the cart.
     *
     * @return true if the message is displayed, false otherwise.
     */
    public boolean verifySuccessAddToCartMessage() {
        return ActionUtils.execBoolean(getClass(), () -> {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.textToBePresentInElement(
                            successAddToCartMsg,
                            "You added"
                    ));
            return true;
        }, "Verify success message is displayed after adding to cart", "Error verifying success message");
    }
}
