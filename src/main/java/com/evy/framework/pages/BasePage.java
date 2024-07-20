package com.evy.framework.pages;

import com.evy.framework.drivers.Driver;
import com.evy.framework.utils.ActionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;


    public BasePage(){
        this.driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver,this);
    }

    protected WebElement waitForElementToBeVisible(WebElement element){
        Wait<WebDriver>wait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void sendKeys(WebElement element,String value,String elementName){
        ActionUtils.execVoid(getClass(),()->{
            WebElement webElement=waitForElementToBeVisible(element);
            webElement.clear();
            webElement.sendKeys(value);}
                ,"Send keys to "+elementName+":"+value,"Failed to send keys to "+elementName);
    }


    protected void click(WebElement element,String elementName){
        ActionUtils.execVoid(getClass(),()->{
            JavascriptExecutor js=(JavascriptExecutor)driver;
            WebElement webElement=waitForElementToBeVisible(element);
            js.executeScript("arguments[0].click();",webElement);}
                ,"Clicked on "+elementName,"Failed to click on "+elementName);
    }

    protected String getText(WebElement element,String elementName){
         return ActionUtils.execString(getClass(),()->{
            WebElement webElement=waitForElementToBeVisible(element);
           return webElement.getText();}
                 ,"Retrieve text from "+elementName+":"+element.getText(),"Failed to retrieve text from "+elementName);
    }


    protected boolean isDisplayed(WebElement element, String elementName) {
        return ActionUtils.execBoolean(getClass(), element::isDisplayed,
                elementName + " Display status: " + true, elementName + " Displayed status: " + false);
    }

    protected void clickAndNavigate(WebElement element,String elementName,String pageTitle,String nextPageClass){
        ActionUtils.execVoid(getClass(),()->{
            click(element,elementName);
            waitForPageTitleToBeEquals(pageTitle);}
                ,"Clicked on "+elementName+","+"Navigate to+"+nextPageClass,"Failed to navigate to "+nextPageClass );
    }

    protected void waitForPageTitleToBeEquals(String pageTitle){
        ActionUtils.execVoid(getClass(),()->
                new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.titleIs(pageTitle)),
                "Page Title :"+pageTitle,"Failed to wait for pageTitle to be :"+pageTitle);
    }

    protected void moveTo(WebElement element,String elementName){
        ActionUtils.execVoid(getClass(),()->{
            WebElement webElement=waitForElementToBeVisible(element);
            Actions actions=new Actions(driver);
            actions.moveToElement(webElement).perform();
        },"Moved to "+elementName,"Failed to move to "+elementName);
    }

    protected void selectByVisibleText(WebElement element,String value,String elementName){
        ActionUtils.execVoid(getClass(),()->{
            Select select=new Select(element);
            select.selectByVisibleText(value);
        },"Selected "+value+" from "+elementName+" dropdown","Failed to select "+value+" from dropdown");
    }

    public String getCurrentUrl(){
        return ActionUtils.execString(getClass(), driver::getCurrentUrl,"Retrieve current url: "+driver.getCurrentUrl(),"Failed to retrieve current url");
    }

    public String getPageTitle(){
        return ActionUtils.execString(getClass(), driver::getTitle,"Retrieve PageTitle: "+driver.getTitle(),"Failed to retrieve PageTitle");
    }


}
