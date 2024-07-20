package com.evy.framework.pages.authentication;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(css = "#firstname")
    private WebElement firstName;
    @FindBy(css = "#lastname")
    private WebElement lastName;
    @FindBy(css = "#email_address")
    private WebElement email;
    @FindBy(css = "#password")
    private WebElement password;
    @FindBy(css = "#password-confirmation")
    private WebElement confirmation;
    @FindBy(css = "button[title='Create an Account']")
    private WebElement registerBtn;
    @FindBy(css = "div[data-bind*='message.text']")
    private WebElement registerSuccessMsg;
    @FindBy(css = "#email_address-error")
    private WebElement invalidEmailFormatMsg;
    @FindBy(css = "#password-error")
    private WebElement invalidPasswordFormatMsg;
    @FindBy(css = "#password-confirmation-error")
    private WebElement invalidMismatchPasswordMsg;

    public <T>T register(String firstName,String lastName,String email,String password,String confirmation,boolean criteria,Class<T>nextPageClass){

        try{
            sendKeys(this.firstName,firstName,"firstName");
            sendKeys(this.lastName,lastName,"lastName");
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            sendKeys(this.confirmation,confirmation,"confirmation");

            if(criteria){
                clickAndNavigate(this.registerBtn,"registerBtn","My Account","MyAccountPage");
            }
            else{
                click(this.registerBtn,"registerBtn");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.error(getClass(),"Error during registration operation",e);
            throw new RuntimeException("Failed to complete registration operation",e);
        }


    }

    public String getRegisterResponseMsg(String operation){
        return switch (operation){
          case "valid register data"->getText(this.registerSuccessMsg,"register success message");
          case "invalid email format data"->getText(this.invalidEmailFormatMsg,"invalid email format message");
          case "invalid email in use data"->getText(this.registerSuccessMsg,"invalid email in use message");
          case "invalid passwords format data"->getText(this.invalidPasswordFormatMsg,"invalid passwords format message");
          case "invalid passwords mismatch data"->getText(this.invalidMismatchPasswordMsg,"invalid mismatch passwords message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }


}
