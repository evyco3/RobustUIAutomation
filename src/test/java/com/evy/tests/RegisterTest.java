package com.evy.tests;

import com.evy.framework.data.RegisterData;
import com.evy.framework.pages.authentication.RegisterPage;
import com.evy.framework.pages.home.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for user registration scenarios.
 * This class contains tests that verify different registration scenarios based on provided data.
 */
@Epic("Registration Tests")
@Feature("User Registration")
public class RegisterTest extends BaseTest {

    /**
     * Test to verify user registration scenarios with different data.
     * <p>
     * Test Steps:
     * 1. User navigates to the authentication page.
     * 2. User navigates to the registration page.
     * 3. User registers with the provided details.
     * 4. User retrieves and verifies the registration response message.
     *
     * @param firstName       The first name of the user.
     * @param lastName        The last name of the user.
     * @param email           The email address of the user.
     * @param password        The password for the user account.
     * @param confirmation    The confirmation password.
     * @param operation       The operation to be performed (e.g., registration type).
     * @param expectedMsg     The expected response message after registration.
     */
    @Test(dataProviderClass = RegisterData.class, dataProvider = "registerData")
    @Story("User Registration Scenarios")
    @Description("Verify different user registration scenarios with various data inputs.")
    @Parameters({"firstName", "lastName", "email", "password", "confirmation", "operation", "expectedMsg"})
    public void testUserRegistrationScenarios(String firstName, String lastName, String email, String password, String confirmation,
                                              String operation, String expectedMsg
    ) {
        String actualMsg = registerAndGetResponseMsg(firstName, lastName, email, password, confirmation, operation);
        AssertionUtils.assertEquality(actualMsg, expectedMsg, "Verify actualRegistration Message" +
                "Equals to the expectedMessage");
    }

    /**
     * Registers a user with the provided details and retrieves the response message.
     *
     * @param firstName       The first name of the user.
     * @param lastName        The last name of the user.
     * @param email           The email address of the user.
     * @param password        The password for the user account.
     * @param confirmation    The confirmation password.
     * @param operation       The operation to be performed (e.g., registration type).
     * @return                The response message received after registration.
     */
    private String registerAndGetResponseMsg(String firstName, String lastName, String email, String password, String confirmation, String operation) {
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToRegisterPage()
                .register(firstName, lastName, email, password, confirmation, false, RegisterPage.class)
                .getRegisterResponseMsg(operation);
    }
}
