package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.salmon.test.page_objects.constants.Context.EMAIL_ID;
import static com.salmon.test.page_objects.constants.Context.PAYMENT_METHOD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class BATHelper extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(BATHelper.class);

    private HomePage homePage;
    private PDP pdp;
    private LogininPage logininPage;
    private RegistrationPage registrationPage;
    private PaymentPage paymentPage;
    private AccountDashboardPage accountDashboardPage;
    private PercyPage percyPage;
    private OrderSuccessPage orderSuccessPage;
    private BasketPage basketPage;
    private CheckoutPage epokCheckoutPage;
    private PLP plp;
    private ScenarioContext scenarioContext;
    private CommonMethods commonMethods;
    private static final By PAGE_HEADING_CA = By.cssSelector(".bat-order-confirmation-message-heading h1");

    public BATHelper(TestContext testContext, HomePage homePage, PDP pdp, LogininPage logininPage, PLP plp,
                     RegistrationPage registrationPage, PaymentPage paymentPage, AccountDashboardPage accountDashboardPage,
                     OrderSuccessPage orderSuccessPage,
                     BasketPage basketPage, CheckoutPage epokCheckoutPage, PercyPage percyPage, CommonMethods commonMethods) {
        this.homePage = homePage;
        this.pdp = pdp;
        this.logininPage = logininPage;
        this.registrationPage = registrationPage;
        this.paymentPage = paymentPage;
        this.accountDashboardPage = accountDashboardPage;
        this.orderSuccessPage = orderSuccessPage;
        this.basketPage = basketPage;
        this.epokCheckoutPage = epokCheckoutPage;
        this.plp = plp;
        scenarioContext = testContext.getScenarioContext();
        this.percyPage = percyPage;
        this.commonMethods = commonMethods;
    }

    public void checkoutAproduct(String guestOrNormalUser) throws InterruptedException {
        homePage.clickOnBasketIcon();
        Thread.sleep(2000);
        homePage.clickOnProceedToCheckoutButtonInMiniBasket();
        homePage.clickOnProceedToCheckoutButtonInCart(guestOrNormalUser);
        paymentPage.waitForElementToAppearAndDisappear(LOADER, 15, 30);
    }

    public void createNewAccountZonnic(String province, String addressEnteringMethod, String shippingMethod, String guestOrNormalUser) {
        switch (guestOrNormalUser) {
            case "normalUser":
                homePage.clickOnPersonIcon();
                percyPage.takePercyScreenshot("Signin popup");
                try {
                    logininPage.clickCreateAccountButton(guestOrNormalUser);
                } catch (Exception e) {

                }
                commonMethods.assertUrl("createAccountLink.key");
                commonMethods.assertFooterTitle("signUpTitle");

                percyPage.takePercyScreenshot("Create account Page");
                break;
            case "guestUser":
                try {
                    logininPage.clickCreateAccountButton(guestOrNormalUser);
                } catch (Exception e) {

                }
                commonMethods.assertUrl("createAccountForGuestLink.key");
                commonMethods.assertTitle("guestSignUpTitle");
                commonMethods.assertFooterTitle("guestSignUpTitle");
                break;
        }
        registrationPage.enterRegistrationDetailsZonnic(province, addressEnteringMethod, shippingMethod);
        registrationPage.enterPassword();
        registrationPage.clickOnCheckbox();
        registrationPage.whichAppliesToYou();
        percyPage.takePercyScreenshot("Create Account Page after filling details");
        registrationPage.clickOnCreateAccountButton();
        percyPage.takePercyScreenshot("My Account page after account creation");
        switch (guestOrNormalUser) {
            case "normalUser":
                break;
            case "guestUser":
                waitForURLToContain(UrlBuilder.getMessage("shippingAnPaymentLink.key"));
                commonMethods.assertUrl("shippingAnPaymentLink.key");
                break;
        }
    }

    public void paysBy(String cardType, String savedCardOrWithoutSaved, String bySavingCardOrWithoutSaving) {
        paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(), 30);
        if (savedCardOrWithoutSaved.equals("savedCard")) {
            paymentPage.selectSavedCard();
        } else if (savedCardOrWithoutSaved.equals("newCard")) {
            paymentPage.enterValidMasterCardDetailsAndSubmit(cardType);
        }
        scenarioContext.setContext(PAYMENT_METHOD, cardType);
        paymentPage.selectTermsAndConditionsCheckbox();
        percyPage.goToTermsAndConditionPageAndTakePercyScreenshot();
        if (bySavingCardOrWithoutSaving.equals("savingCard")) {
            paymentPage.savedCard();
        }
        percyPage.takePercyScreenshot("Checkout Page after filling details");
        if (Props.getTestSuite().equals("Live")) {

        } else {
            paymentPage.clickPlaceOrder(savedCardOrWithoutSaved);
        }
    }

    public void assertThatHeaderContains(String expectedText) {
        String actualText;
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                waitForExpectedElement(PAGE_HEADING_CA);
                actualText = returnHeaderTextCanada();
                assertThat(actualText.toLowerCase().contains(expectedText.toLowerCase()))
                        .as("ERROR expected header text was " + expectedText + " but got " + actualText).isTrue();
                break;
        }
    }

    public void createNewAccountVuse(String sameOrDiffProvince) {
        homePage.clickSignInIconVuse();
        logininPage.clickCreateAccountButtonVuse();
        registrationPage.enterRegistrationDetailsVuse(sameOrDiffProvince);
        registrationPage.enterPasswordVuse();
        registrationPage.enterConfirmPasswordVuse();
        registrationPage.acceptTAndCVuse();
        registrationPage.clickCreateAccountButtonVuse();
    }

    public void createNewAccountZonnic(String province) {
        String email = (String) scenarioContext.getContext(EMAIL_ID);
        homePage.clickOnPersonIcon();
        logininPage.clickCreateAccountButton();
        registrationPage.enterRegistrationDetailsZonnic(email, province);
        registrationPage.enterPassword();
        registrationPage.clickOnCheckbox();
        registrationPage.whichAppliesToYou();
        percyPage.takePercyScreenshot("Create Account Page");
        registrationPage.clickOnCreateAccountButton();
    }

    public void withoutFillingMandatoryFields(String province, String addressEnteringMethod, String shippingMethod) {
        homePage.clickOnPersonIcon();
        logininPage.clickCreateAccountButton();
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("FirstNameRequiredField" + "-" + UrlBuilder.LANGUAGE), registrationPage.firstNameRequiredFields());
        registrationPage.EnterFirstName();
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("LastNameRequiredField" + "-" + UrlBuilder.LANGUAGE), registrationPage.LastNameRequiredFields());
        registrationPage.EnterLastName();
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("DOBEmailrequired" + "-" + UrlBuilder.LANGUAGE), registrationPage.DobErrorMessage());
        registrationPage.EnterDobField();
        assertTrue(registrationPage.QickAddressFinders().contains(UrlBuilder.getMessage("QickAddressFinderRequiredField" + "-" + UrlBuilder.LANGUAGE)));
        registrationPage.QuickAddressFinder(province, addressEnteringMethod, shippingMethod);
        String telePhoneAddress = "TelephoneErrorMssg" + "-" + UrlBuilder.LANGUAGE;
        assertTrue(registrationPage.TelephonesRequiredFields().contains(UrlBuilder.getMessage("TelephoneErrorMssg" + "-" + UrlBuilder.LANGUAGE)));
        registrationPage.TelephoneReqFields();
    }

    public void createAccountWithNonWhitelistedEmailFormats(String emailType, String province, String addressEnteringMethod, String shippingMethod, String guestOrNormalUser) {
        switch (guestOrNormalUser) {
            case "normalUser":
                commonMethods.assertUrl("createAccountLink.key");
                commonMethods.assertFooterTitle("signUpTitle");

                percyPage.takePercyScreenshot("Create account Page");
                break;
            case "guestUser":
                try {
                    logininPage.clickCreateAccountButton(guestOrNormalUser);
                } catch (Exception e) {

                }
                commonMethods.assertUrl("createAccountForGuestLink.key");
                commonMethods.assertTitle("guestSignUpTitle");
                commonMethods.assertFooterTitle("guestSignUpTitle");
                break;
        }
        registrationPage.enterRegistrationDetailsZonnicNonWhitelistedEmal(emailType, province, addressEnteringMethod, shippingMethod);
        registrationPage.enterPassword();
        registrationPage.clickOnCheckbox();
        registrationPage.whichAppliesToYou();
        percyPage.takePercyScreenshot("Create Account Page after filling details");
        registrationPage.clickOnCreateAccountButton();
        percyPage.takePercyScreenshot("My Account page after account creation");
        switch (guestOrNormalUser) {
            case "normalUser":
                break;
            case "guestUser":
                waitForURLToContain(UrlBuilder.getMessage("shippingAnPaymentLink.key"));
                commonMethods.assertUrl("shippingAnPaymentLink.key");
                break;
        }
    }
}