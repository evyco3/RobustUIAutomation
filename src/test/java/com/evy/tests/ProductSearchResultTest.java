package com.evy.tests;


import com.evy.framework.data.ProductSearchResultData;
import com.evy.framework.pages.home.HomePage;
import com.evy.framework.utils.AssertionUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

/**
 * Test scenarios for selecting products from search results.
 * <p>
 * This class contains tests for selecting products based on different categories
 * and verifying the expected page title after selection.
 * <p>
 * Feature: Product Search Results
 * Story: Verify user interaction with product search results.
 * Description: Test different scenarios for selecting products from search results
 * and verify the expected page title after selection.
 * <p>
 * Steps:
 * 1. User lands on the HomePage.
 * 2. User navigates to the product dropdown and selects specified categories.
 * 3. User selects a product by name from the search results.
 */
@Feature("Product Search Results")
public class ProductSearchResultTest extends BaseTest {

    /**
     * Test case to verify selecting a product from search results.
     *
     * @param mainCategory      The main category of the product.
     * @param subCategory       The sub-category of the product.
     * @param subSubCategory    The sub-sub-category of the product.
     * @param productName       The name of the product to select.
     * @param expectedPageTitle The expected page title after selecting the product.
     */
    @Test(dataProviderClass = ProductSearchResultData.class, dataProvider = "productSearchResultData")
    @Parameters({"mainCategory", "subCategory", "subSubCategory", "productName", "expectedPageTitle"})
    @Story("Select Product from Search Results")
    @Description("Verify selecting a product from search results and checking the expected page title.")
    public void testUserSelectProduct(String mainCategory, String subCategory, String subSubCategory, String productName, String expectedPageTitle) {
        String actualPageTitle = selectProductByNameFromSearchResultAndGetPageTitle(mainCategory, subCategory, subSubCategory, productName);
        AssertionUtils.assertEquality(actualPageTitle, expectedPageTitle, "Verify actualPageTitle Equals to expectedUrl");
    }

    /**
     * Selects a product by name from the search results and retrieves the page title.
     *
     * @param mainCategory    The main category of the product.
     * @param subCategory     The sub-category of the product.
     * @param subSubCategory  The sub-sub-category of the product.
     * @param productName     The name of the product to select.
     * @return The page title after selecting the product.
     */
    private String selectProductByNameFromSearchResultAndGetPageTitle(String mainCategory, String subCategory, String subSubCategory, String productName) {
        return HomePage.getInstance()
                .navigateToProductDropdown()
                .selectCategoriesFromProductDropdown(mainCategory, subCategory, subSubCategory)
                .selectProductByName(productName)
                .getPageTitle();
    }
}
