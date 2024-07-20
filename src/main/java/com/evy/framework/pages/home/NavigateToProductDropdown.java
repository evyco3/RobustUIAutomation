package com.evy.framework.pages.home;

import com.evy.framework.pages.product.ProductSearchResultPage;
import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * NavigateToProductDropdown class representing actions related to product dropdown navigation.
 * Extends from BasePage to inherit common page functionalities.
 */
public class NavigateToProductDropdown extends BasePage {

    /**
     * Selects categories from the product dropdown and navigates to the corresponding product search result page.
     *
     * @param mainCategory   the main category to select
     * @param subCategory    the sub-category to select
     * @param subSubCategory the sub-sub-category to select
     * @return an instance of ProductSearchResultPage
     */
    public ProductSearchResultPage selectCategoriesFromProductDropdown(String mainCategory, String subCategory, String subSubCategory){
        try {
            String mainElementStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']", mainCategory);
            WebElement mainElement = driver.findElement(By.xpath(mainElementStringValue));

            if(subCategory.isEmpty() && subSubCategory.isEmpty()){
                click(mainElement, mainCategory);
                waitForPageTitleToBeEquals(mainCategory);
            } else if(subSubCategory.isEmpty()){
                String subElementStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']/ancestor::li/ul/li/a/span[normalize-space()='%s']", mainCategory, subCategory);
                WebElement subElement = driver.findElement(By.xpath(subElementStringValue));
                moveTo(mainElement, mainCategory);
                click(subElement, subCategory);
                waitForPageTitleToBeEquals(subCategory + " - " + mainCategory);
            } else {
                String subSubElementStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']/ancestor::li/ul/li/a/span[normalize-space()='%s']/ancestor::li//span[normalize-space()='%s']", mainCategory, subCategory, subSubCategory);
                WebElement subSubElement = driver.findElement(By.xpath(subSubElementStringValue));
                String subElementStringValue = String.format("//ul[@id='ui-id-2']/li/a/span[normalize-space()='%s']/ancestor::li/ul/li/a/span[normalize-space()='%s']", mainCategory, subCategory);
                WebElement subElement = driver.findElement(By.xpath(subElementStringValue));

                moveTo(mainElement, mainCategory);
                moveTo(subElement, subCategory);
                click(subSubElement, subSubCategory);
                waitForPageTitleToBeEquals(subSubCategory + " - " + subCategory + " - " + mainCategory);
            }
            return new ProductSearchResultPage();
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during select categories {" + mainCategory + "," + subCategory + "," + subSubCategory + "} from dropdown", e);
            throw new RuntimeException("Failed to select categories " + mainCategory + "," + subCategory + "," + subSubCategory + " from dropdown", e);
        }
    }
}
