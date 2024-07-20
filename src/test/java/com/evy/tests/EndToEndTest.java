package com.evy.tests;

import com.github.javafaker.Faker;
import com.evy.framework.pages.home.HomePage;
import com.evy.framework.utils.AssertionUtils;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for end-to-end user journey.
 * This class contains tests that simulate a complete user journey from registration to product purchase.
 */
@Epic("E2E Tests")
@Feature("User Journey")
public class EndToEndTest extends BaseTest {

    /**
     * Test to verify the end-to-end product purchase process.
     * <p>
     * Test Steps:
     * 1. User navigates to the authentication page.
     * 2. User registers with valid details.
     * 3. User navigates to the product dropdown.
     * 4. User selects categories: Men > Tops > Jackets.
     * 5. User selects the product "Proteus Fitness Jackshirt".
     * 6. User sets product size to M, color to Black, and quantity to 1.
     * 7. User adds the product to the cart.
     * 8. User navigates to the cart page and confirm.
     * 9. User navigates to the checkout page.
     * 10. User fills in the shipping details.
     * 11. User selects the flat rate shipment method.
     * 12. User proceeds to the next page.
     * 13. User navigates to the success page and verifies the success order message.
     *
     * @param firstName      The first name for registration.
     * @param lastName       The last name for registration.
     * @param email          The email for registration.
     * @param password       The password for registration.
     * @param country        The shipping country.
     * @param address        The shipping address.
     * @param city           The shipping city.
     * @param postcode       The shipping postcode.
     * @param phoneNumber    The phone number for shipping.
     */
    @Test(dataProvider = "endToEndDataProvider")
    @Story("End-to-End User Journey")
    @Description("Verify that a user can register, select a product, and complete a purchase successfully.")
    public void testUserEnd2EndJourney(String firstName, String lastName, String email, String password,
                                       String country, String address, String city, String postcode, String phoneNumber) {
        AssertionUtils.assertEquality(end2end(firstName, lastName, email, password, country, address, city, postcode, phoneNumber),
                "Thank you for your purchase!", "Verify success end-to-end product purchase message");
    }

    @DataProvider(name = "endToEndDataProvider")
    public Object[][] endToEndDataProvider() {
        Faker faker = new Faker();
        return new Object[][] {
                { "John", "Doe", faker.internet().emailAddress(), "Password123", "Israel", "123 Main St", "Tel Aviv", "12345", "0501234567" },
        };
    }

    private String end2end(String firstName, String lastName, String email, String password,
                           String country, String address, String city, String postcode, String phoneNumber) {
        return HomePage.getInstance()
                .navigateToAuthentication()
                .navigateToRegisterPage()
                .register(firstName, lastName, email, password, password, true, HomePage.class)
                .navigateToProductDropdown()
                .selectCategoriesFromProductDropdown("Men", "Tops", "Jackets")
                .selectProductByName("Proteus Fitness Jackshirt")
                .setProductSize("M").setProductColor("Black").setProductQuantity("1")
                .clickAddToCartBtn()
                .navigateToCartPage()
                .navigateToCheckoutPage()
                .setFirstName(firstName).setCountry(country).setLastName(lastName).setAddress(address).setCity(city)
                .setPostcode(postcode).clickFlatRateShipmentMethod().setPhone(phoneNumber)
                .clickNextPageBtn()
                .clickAndNavigateToSuccessPage().getSuccessOrderMsg();
    }
}
