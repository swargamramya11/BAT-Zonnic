package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.And;

import static org.testng.AssertJUnit.assertTrue;

public class AccountDashboardPageSteps {
    private OrderSuccessPage orderSuccessPage;
    private AccountDashboardPage accountDashboardPage;
    private PaymentPage paymentPage;
    private AddNewAddressPage addNewAddressPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private BATHelper batHelper;
    private PercyPage percyPage;
    private CheckoutPage checkoutPage;

    public AccountDashboardPageSteps(CheckoutPage checkoutPage, TestContext testContext, OrderSuccessPage orderSuccessPage, BATHelper batHelper, AccountDashboardPage accountDashboardPage, PaymentPage paymentPage, AddNewAddressPage addNewAddressPage, HomePage homePage, PercyPage percyPage) {
        this.orderSuccessPage = orderSuccessPage;
        this.batHelper = batHelper;
        this.accountDashboardPage = accountDashboardPage;
        this.paymentPage = paymentPage;
        this.addNewAddressPage = addNewAddressPage;
        this.homePage = homePage;
        this.scenarioContext = testContext.getScenarioContext();
        this.percyPage = percyPage;
        this.checkoutPage = checkoutPage;
    }

    @And("^verify order if present or not$")
    public void verifyOrderNumber() {
        accountDashboardPage.orderVerification();
    }

    @And("^validate if all price details are correct with '(.*)'$")
    public void validatePriceDetailsInOrderDetailsPage(String withOrWithoutCoupon) {
        accountDashboardPage.validatePriceDetailsInOrderDetailsPage(withOrWithoutCoupon);
    }

    @And("^user clicks on the '(.*)' text and verify link '(.*)' with header '(.*)' and title '(.*)'$")
    public void usersClicksOnTheLinkByText(String linkText, String link, String header, String title) {
        accountDashboardPage.usersClicksOnTheLinkByText(linkText, link, header, title);
    }

    @And("^click on '(.*)' in header$")
    public void  navigateToMyAccountPage(String link) {
        accountDashboardPage.navigateToMyAccountPage(link);
    }

    @And("^validate empty order history page$")
    public void emptyOrderHistory() {
        accountDashboardPage.emptyOrderHistoryVerification();
    }

    @And("^'(.*)' to newsletter$")
    public void marketingPreference(String marketing) {
        accountDashboardPage.marketingPreference(marketing);
    }

    @And("^verify '(.*)'$")
    public void verifyText(String text) {
        accountDashboardPage.verifyText(text);
    }

    @And("^user click on '(.*)'$")
    public void clickOnRequiredButton(String buttonName) {
        accountDashboardPage.clickOnRequiredButton(buttonName);
    }

    @And("^validate if account is created for vuse$")
    public void validateIfAccountIsCreatedVuse() {
        accountDashboardPage.waitForExpectedElement(accountDashboardPage.ACCOUNT_OVERVIEW_HEADER,30);
        assertTrue(accountDashboardPage.waitForExpectedElement(accountDashboardPage.ACCOUNT_OVERVIEW_HEADER).isDisplayed());
        assertTrue(accountDashboardPage.getCurrentUrl().contains("account"));
    }

    @And("^click on edit for change password$")
    public void clickEditButtonChangePassword() {
        accountDashboardPage.clickEditButtonChangePassword();
    }

    @And("^enter '(.*)'$")
    public void enterData(String text) {
        accountDashboardPage.enterData(text);
    }

    @And("^validate error message of '(.*)'$")
    public void validateErrors(String errorMessage) {
        accountDashboardPage.validateErrors(errorMessage);
    }

    @And("^'(.*)' in address book$")
    public void addNewAddressInAddressBooks(String addressMethod) {
        accountDashboardPage.addNewAddressInAddressBooks(addressMethod);
    }

    @And("^click on make default shipping address in '(.*)'$")
    public void clickOnMakeDefaultShippingAddress(String addressSection) {
        accountDashboardPage.clickOnMakeDefaultShippingAddress(addressSection);
    }

    @And("^click on '(.*)'$")
    public void clickAddNewAddressButton(String button) {
        accountDashboardPage.clickAddNewAddressButton(button);
    }

    @And("^validate success message of '(.*)'$")
    public void successMessageValidation(String errorMessage) {
        accountDashboardPage.successMessage(errorMessage);
    }
}