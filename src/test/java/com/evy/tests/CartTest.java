package com.evy.tests;

import com.evy.framework.pages.home.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.*;
import org.testng.annotations.Test;

/**
 * Test class for verifying cart functionalities such as adding and deleting products from the cart.
 */
@Feature("Cart Operations")
public class CartTest extends BaseTest {

    /**
     * Test to verify adding a product to the cart.
     * Steps:
     * 1. Navigate to the product dropdown.
     * 2. Select categories from the product dropdown.
     * 3. Select a product by its name.
     * 4. Set the product color, quantity, and size.
     * 5. Click the "Add to Cart" button.
     * 6. Retrieve the success message for adding the product to the cart.
     * <p>
     * This test checks if the success message is displayed correctly after adding the product.
     */
    @Test
    @Story("Add Product to Cart")
    @Description("Verify if the product can be added to the cart and the success message is displayed correctly.")
    public void testAddProductToCart() {
        AssertionUtils.assertEquality(
                addProductToCartSuccessMsg(),
                "You added Proteus Fitness Jackshirt to your shopping cart.",
                "Verify if actualMessage is equals to expectedMsg"
        );
    }

    /**
     * Test to verify deleting a product from the cart.
     * Steps:
     * 1. Navigate to the product dropdown.
     * 2. Select categories from the product dropdown.
     * 3. Select a product by its name.
     * 4. Set the product color, quantity, and size.
     * 5. Click the "Add to Cart" button.
     * 6. Navigate to the cart page.
     * 7. Delete the product from the cart.
     * 8. Retrieve the success message for deleting the product from the cart.
     *
     * This test checks if the success message is displayed correctly after deleting the product.
     */
    @Test
    @Story("Delete Product from Cart")
    @Description("Verify if the product can be deleted from the cart and the success message is displayed correctly.")
    public void testUserDeleteProductFromCart() {
        AssertionUtils.assertContains(
                deleteProductFromCartAndGetSuccessMsg(),
                "You have no items in your shopping cart.",
                "Verify delete product from cart response message"
        );
    }

    /**
     * Adds a product to the cart and retrieves the success message.
     *
     * @return the success message after adding the product to the cart
     */
    private String addProductToCartSuccessMsg() {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategoriesFromProductDropdown("Men", "Tops", "Jackets")
                .selectProductByName("Proteus Fitness Jackshirt")
                .setProductColor("Black").setProductQuantity("1").setProductSize("M")
                .clickAddToCartBtn()
                .getSuccessAddToCartMsg();
    }

    /**
     * Deletes a product from the cart and retrieves the success message.
     *
     * @return the success message after deleting the product from the cart
     */
    private String deleteProductFromCartAndGetSuccessMsg() {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategoriesFromProductDropdown("Men", "Tops", "Jackets")
                .selectProductByName("Proteus Fitness Jackshirt")
                .setProductColor("Black").setProductQuantity("1").setProductSize("M")
                .clickAddToCartBtn()
                .navigateToCartPage()
                .deleteProductFromCart()
                .getDeleteProductFromCartMsg();
    }
}
