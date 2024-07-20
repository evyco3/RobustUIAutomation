package com.evy.framework.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public final class RegisterData {

    private static final Faker faker =new Faker();
    private RegisterData(){}


    @DataProvider(name = "registerData")
    public static Object[][]getData(){
        String pw=getPassword();
        return new Object[][]{
                {getFirstName(),getLastName(),getEmail(),pw,pw,"valid register data","Thank you for registering with Main Website Store."},
                {getFirstName(),getLastName(),"evy@",pw,pw,"invalid email format data","Please enter a valid email address (Ex: johndoe@domain.com)."},
                {getFirstName(),getLastName(),"evy@example.org",pw,pw,"invalid email in use data","There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account."},
                {getFirstName(),getLastName(),getEmail(),"Password","Password","invalid passwords format data","Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters."},
                {getFirstName(),getLastName(),getEmail(),"password1","password2","invalid passwords mismatch data","Please enter the same value again."}
        };
    }





    private static String getFirstName(){
        return faker.name().firstName();
    }

    private static String getLastName(){
        return faker.name().lastName();
    }

    private static String getEmail(){
        return faker.internet().emailAddress();
    }

    private static String getPassword(){
        return "Password123";
    }








}
