package com.evy.tests;

import com.evy.framework.data.LoginData;
import com.evy.framework.pages.authentication.LoginPage;
import com.evy.framework.pages.home.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * The LoginTest class contains test methods for verifying various user login scenarios.
 * <p>
 * Test Steps:
 * <ol>
 *     <li>User lands on the home page.</li>
 *     <li>User navigates to the authentication section.</li>
 *     <li>User clicks on the login button.</li>
 *     <li>User enters the email and password.</li>
 *     <li>User performs the login operation based on the provided criteria.</li>
 *     <li>The system displays an appropriate message based on the login operation.</li>
 * </ol>
 * <p>
 * This class uses TestNG for test execution and Allure for reporting. It verifies different login scenarios such as valid login data,
 * invalid login data, and missing data by checking the corresponding messages displayed on the page.
 */
@Feature("User Login Scenarios")
@Story("User Login Functionality")
public class LoginTest extends BaseTest {

    /**
     * Test method for verifying various user login scenarios using data from the LoginData data provider.
     *
     * @param email      The email to use for login.
     * @param password   The password to use for login.
     * @param operation  The type of login operation (e.g., "valid login data", "invalid login data", "invalid missing data").
     */
    @Test(dataProviderClass = LoginData.class, dataProvider = "loginData")
    @Parameters({"email", "password", "operation"})
    @Description("Test user login scenarios with various inputs to verify if the appropriate messages are displayed.")
    @Story("Verify login with various input scenarios")
    public void testUserLoginScenarios(String email, String password, String operation) {
        boolean actualMsgDisplayStatus = loginAndGetResponseDisplayMsg(email, password, operation);
        AssertionUtils.assertCondition(actualMsgDisplayStatus, "Verify login message display status");
    }

    /**
     * Logs in with the specified email and password and checks the response message display status based on the operation type.
     *
     * @param email     The email to use for login.
     * @param password  The password to use for login.
     * @param operation The type of login operation.
     * @return True if the corresponding login proper message is displayed based on operation
     */
    private boolean loginAndGetResponseDisplayMsg(String email, String password, String operation) {
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToLoginPage()
                .login(email, password, false, LoginPage.class)
                .isSuccessLoginMsgDisplay(operation);
    }
}
