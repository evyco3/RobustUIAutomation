package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the product search result page.
 * Provides methods to interact with and retrieve information from the search result page.
 */
public class ProductSearchResultPage extends BasePage {

    /**
     * Selects a product by its name from the search results and navigates to the ProductPage.
     *
     * @param productName The name of the product to be selected.
     * @return an instance of ProductPage for further interactions with the selected product.
     */
    public ProductPage selectProductByName(String productName){
        try {
            String productStringValue = String.format("//a[@class='product-item-link'][normalize-space()='%s']", productName);
            WebElement webElement = driver.findElement(By.xpath(productStringValue));
            clickAndNavigate(webElement, productName, productName, "ProductPage");
            return new ProductPage();
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during product select by name: " + productName, e);
            throw new RuntimeException("Failed to select " + productName + " from product pool result");
        }
    }
}
