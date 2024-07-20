package com.evy.framework.data;


import org.testng.annotations.DataProvider;

public final class LoginData {

    private LoginData(){}


    @DataProvider(name = "loginData")
    public static Object[][]getData(){
        return new Object[][]{
                {"evyuser@example.com","Password123","valid login data"}, //valid login data
                {"wrongEmail@gmail.com","Password123","invalid login data"},//invalid email+valid password
                {"evyuser@example.com","wrongPassword","invalid login data"},//valid email+invalid password
                {"","Password123","invalid missing data"}, //empty email
                {"evy@user@example.com","","invalid missing data"}, //empty password
                {"","","invalid missing data"}//empty email+password

        };
    }
}
