package com.evy.tests;

import com.evy.framework.data.ProductDropdownData;
import com.evy.framework.pages.home.HomePage;
import com.evy.framework.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

/**
 * Test scenarios for selecting categories from the product dropdown.
 * <p>
 * Feature: Product Dropdown Selection
 * Story: Verify user interaction with product category dropdown.
 * Description: Test different scenarios for selecting categories from the product dropdown and verify the resulting URL.
 * <p>
 * Steps:
 * 1. User lands on the HomePage.
 * 2. User navigates to the product dropdown.
 * 3. User selects categories from the product dropdown.
 * 4. Verify that the actual URL contains the expected URL after selection.
 */
@Feature("Product Dropdown Selection")
public class ProductDropdownTest extends BaseTest {

    /**
     * Test case to verify selecting categories from the product dropdown.
     */

    @Test(dataProviderClass = ProductDropdownData.class, dataProvider = "productDropdownData")
    @Parameters({"mainCategory", "subCategory", "subSubCategory", "expectedUrl"})
    @Story("Verify selection of categories from product dropdown")
    @Description("Test different scenarios for selecting categories from the product dropdown and verify the resulting URL.")
    public void testUserSelectCategoriesFromProductDropdown(String mainCategory, String subCategory, String subSubCategory, String expectedUrl) {
        String actualUrl = selectCategoriesFromDropdownAndGetUrl(mainCategory, subCategory, subSubCategory);
        AssertionUtils.assertContains(actualUrl, expectedUrl, "Verify that actualUrl contains expectedUrl");
    }

    /**
     * Selects categories from the product dropdown and retrieves the current URL.
     */
    private String selectCategoriesFromDropdownAndGetUrl(String mainCategory, String subCategory, String subSubCategory) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategoriesFromProductDropdown(mainCategory, subCategory, subSubCategory)
                .getCurrentUrl();
    }
}
