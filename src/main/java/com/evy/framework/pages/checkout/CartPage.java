package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object representing the shopping cart page.
 * Provides methods to interact with and retrieve information from the cart page.
 */
public class CartPage extends BasePage {

    @FindBy(css = "#product-addtocart-button")
    private WebElement addToCartBtn;

    @FindBy(css = "div[data-ui-id='message-success']>div")
    private WebElement successAddProductToCartMsg;

    @FindBy(css = ".action.action-delete")
    private WebElement deleteProductFromCart;

    @FindBy(css = ".cart-empty>p")
    private WebElement deleteProductFromCartMsg;

    @FindBy(css = "ul button[class='action primary checkout']")
    private WebElement navigateToCheckoutPageBtn;

    /**
     * Adds a product to the cart by clicking the 'Add to Cart' button.
     *
     * @param productName The name of the product to be added.
     * @return the current instance of CartPage for method chaining.
     */
    public CartPage addProductToCart(String productName){
        click(this.addToCartBtn, "addToCartBtn");
        return this;
    }

    /**
     * Retrieves the success message displayed after adding a product to the cart.
     *
     * @return the success message text.
     */
    public String getSuccessAddToCartMsg(){
        return getText(this.successAddProductToCartMsg, "success add product to cart message");
    }

    /**
     * Deletes a product from the cart by clicking the delete button.
     *
     * @return the current instance of CartPage for method chaining.
     */
    public CartPage deleteProductFromCart(){
        click(this.deleteProductFromCart, "delete product from cart");
        return this;
    }

    /**
     * Retrieves the message displayed after deleting a product from the cart.
     *
     * @return the delete product from cart message text.
     */
    public String getDeleteProductFromCartMsg(){
        return getText(this.deleteProductFromCartMsg, "delete product from cart message");
    }

    /**
     * Navigates to the checkout page by clicking the 'Checkout' button.
     *
     * @return an instance of CheckoutPage for further interactions with the checkout page.
     */
    public CheckoutPage navigateToCheckoutPage(){
        click(this.navigateToCheckoutPageBtn, "navigate to checkout btn");
        waitForPageTitleToBeEquals("Checkout");
        return new CheckoutPage();
    }
}
