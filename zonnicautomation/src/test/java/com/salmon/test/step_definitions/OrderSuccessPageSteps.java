package com.salmon.test.step_definitions;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderSuccessPageSteps {
    private static final Logger LOG = LoggerFactory.getLogger(OrderSuccessPageSteps.class);
    public static String orderNumber;
    private OrderSuccessPage orderSuccessPage;
    private AccountDashboardPage accountDashboardPage;
    private PaymentPage paymentPage;
    private AddNewAddressPage addNewAddressPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private BATHelper batHelper;
    private PercyPage percyPage;
    private CheckoutPage checkoutPage;
    private CommonMethods commonMethods;

    public OrderSuccessPageSteps(CommonMethods commonMethods, CheckoutPage checkoutPage, TestContext testContext, OrderSuccessPage orderSuccessPage, BATHelper batHelper, AccountDashboardPage accountDashboardPage, PaymentPage paymentPage, AddNewAddressPage addNewAddressPage, HomePage homePage, PercyPage percyPage) {
        this.orderSuccessPage = orderSuccessPage;
        this.batHelper = batHelper;
        this.accountDashboardPage = accountDashboardPage;
        this.paymentPage = paymentPage;
        this.addNewAddressPage = addNewAddressPage;
        this.homePage = homePage;
        this.scenarioContext = testContext.getScenarioContext();
        this.percyPage = percyPage;
        this.checkoutPage = checkoutPage;
        this.commonMethods = commonMethods;
    }

    @And("^Customer makes a home delivery purchase with card type '(.*)' and with '(.*)' by '(.*)'$")
    public void customerMakesAHomeDeliveryPurchaseWith(String cardType, String savedCardOrWithoutSaved, String bySavingCardOrWithoutSaving) {
        switch (UrlBuilder.getSite()) {
            case "zonnic":
                batHelper.paysBy(cardType, savedCardOrWithoutSaved, bySavingCardOrWithoutSaving);
                break;
        }
    }

    @And("^assert that the '(.*)' thank you message is displayed in the page header$")
    public void assertThatTheThankForPurchaseKeyThankYouMessageIsDisplayed(String key) {
        String expectedThanks;
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                percyPage.takePercyScreenshot("Order Confirmation page");
                expectedThanks = UrlBuilder.getMessage(key + "-" + UrlBuilder.LANGUAGE).toLowerCase();
                batHelper.assertThatHeaderContains(expectedThanks);
                orderSuccessPage.getOrderNumber();
//                commonMethods.assertTitle("orderConfirmationTitle");
//                commonMethods.assertFooterTitle("orderConfirmationTitle");
                break;
        }
    }

    @And("^user navigate to basket and then to checkout page as '(.*)'$")
    public void navigateToCheckout(String guestOrNormalUser) throws Throwable {
        switch (UrlBuilder.getSite()) {
            case "zonnic":
                batHelper.checkoutAproduct(guestOrNormalUser);
                commonMethods.assertUrl("shippingAnPaymentLink.key");
                break;
        }
    }

    @And("^click on '(.*)' in Thank you Page$")
    public void clickMyAccountButton(String buttonName) {
        orderSuccessPage.clickButtonInThankYouPage(buttonName);
    }

    @And("^add address fields in checkout page$")
    public void enterAddressInCheckout() {
        checkoutPage.enterAllAddressFieldsInCheckout();
    }

    @And("^check error messages if fields are empty$")
    public void errorMessageValidationsAddressInCheckout() {
        checkoutPage.addressErrorValidationsInCheckout();
    }

    @And("^click on add address button$")
    public void clickOnAddAddressButton() {
        checkoutPage.clickOnAddAddressButton();
    }

    @And("^select another address from list of addresses$")
    public void selectaddresses() {
        checkoutPage.selectaddresses();
    }

    @And("^validate if address is updated in checkout Page$")
    public void validateIfAddressUpdated() {
        checkoutPage.validateIfAddressUpdated();
    }

    @And("^Customer selects shipment method '(.*)'$")
    public void selectShipmentMethod(String shippingMethod) {
        switch (UrlBuilder.getSite()) {
            case "zonnic":
                paymentPage.selectShippingMethod(shippingMethod);
                break;
        }
    }

    @And("^validate if selected address is displayed$")
    public void validateIfSelectedAddressIsDisplayed() {
        checkoutPage.validateIfSelectedAddressIsDisplayed();
    }
}
