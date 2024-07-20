package com.evy.tests;


import com.evy.framework.data.ProductPageData;
import com.evy.framework.pages.home.HomePage;
import com.evy.framework.pages.product.ProductPage;
import com.evy.framework.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

/**
 * Test scenarios for setting product details and operations.
 * <p>
 * This class contains tests for setting product quantity, size, and color,
 * and verifies the expected responses.
 * <p>
 * Feature: Product Operations
 * Story: Verify user interactions with product details on the product page.
 * Description: Test different scenarios for setting product details (quantity, size, color)
 * and verify the expected responses on the product page.
 * <p>
 * Steps:
 * 1. User lands on the HomePage.
 * 2. User navigates to the product dropdown and selects Men > Tops > Jackets category.
 * 3. User selects the "Proteus Fitness Jackshirt" product from the dropdown.
 */
@Feature("Product Operations")
public class ProductTest extends BaseTest {

    /**
     * Test case to verify setting product quantity.
     *
     * @param productQuantity    The quantity of the product to set.
     * @param operation          The operation to perform on the quantity.
     * @param expectedResponseMsg The expected response message after setting the quantity.
     */
    @Test(dataProviderClass = ProductPageData.class, dataProvider = "productQuantityData")
    @Parameters({"productQuantity", "operation", "expectedMsg"})
    @Story("Set Product Quantity")
    @Description("Verify setting product quantity and checking the response message.")
    public void testUserSetProductQuantity(String productQuantity, String operation, String expectedResponseMsg) {
        String actualResponseMsg = setProductQuantity(productQuantity, operation);
        AssertionUtils.assertContains(actualResponseMsg, expectedResponseMsg, "Verify if actualMsg Contains to ExpectedMsg");
    }

    /**
     * Test case to verify setting product size.
     *
     * @param productSize   The size of the product to set.
     * @param expectedMsg   The expected response message after setting the size.
     */
    @Test(dataProviderClass = ProductPageData.class, dataProvider = "productSizeData")
    @Story("Set Product Size")
    @Description("Verify setting product size and checking the success message.")
    public void testUserSetProductSize(String productSize, String expectedMsg) {
        String actualResponseMsg = setProductSize(productSize);
        AssertionUtils.assertContains(actualResponseMsg, expectedMsg, "Verify if actualResponseMsg Equals to expectedResponseMsg");
    }

    /**
     * Test case to verify setting product color.
     *
     * @param productColor  The color of the product to set.
     * @param expectedMsg   The expected response message after setting the color.
     */
    @Test(dataProviderClass = ProductPageData.class, dataProvider = "productColorData")
    @Story("Set Product Color")
    @Description("Verify setting product color and checking the success message.")
    public void testUserSetProductColor(String productColor, String expectedMsg) {
        String actualResponseMsg = setProductColor(productColor);
        AssertionUtils.assertContains(actualResponseMsg, expectedMsg, "Verify actualMsg contains expectedMsg");
    }

    /**
     * Retrieves the ProductPage instance after navigating and selecting product details.
     *
     * @return ProductPage instance.
     */
    public ProductPage getProductPage() {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategoriesFromProductDropdown("Men", "Tops", "Jackets")
                .selectProductByName("Proteus Fitness Jackshirt");
    }

    /**
     * Sets product quantity and retrieves the response message.
     *
     * @param productQuantity The quantity of the product to set.
     * @param operation       The operation to perform on the quantity.
     * @return Response message after setting the quantity.
     */
    private String setProductQuantity(String productQuantity, String operation) {
        return getProductPage()
                .setProductColor("Black")
                .setProductSize("M")
                .setProductQuantity(productQuantity)
                .clickAddToCartBtn()
                .getQuantityResponseMsg(operation);
    }

    /**
     * Sets product size and retrieves the success message.
     *
     * @param productSize The size of the product to set.
     * @return Success message after setting the size.
     */
    private String setProductSize(String productSize) {
        return getProductPage()
                .setProductQuantity("1")
                .setProductColor("Black")
                .setProductSize(productSize)
                .clickAddToCartBtn()
                .getSuccessAddToCartMsg();
    }

    /**
     * Sets product color and retrieves the success message.
     *
     * @param productColor The color of the product to set.
     * @return Success message after setting the color.
     */
    private String setProductColor(String productColor) {
        return getProductPage()
                .setProductSize("M")
                .setProductQuantity("1")
                .setProductColor(productColor)
                .clickAddToCartBtn()
                .getSuccessAddToCartMsg();
    }
}
