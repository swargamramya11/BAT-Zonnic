package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.And;

public class CheckoutPageSteps {
    private OrderSuccessPage orderSuccessPage;
    private AccountDashboardPage accountDashboardPage;
    private PaymentPage paymentPage;
    private AddNewAddressPage addNewAddressPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private BATHelper batHelper;
    private PercyPage percyPage;
    private CheckoutPage checkoutPage;

    public CheckoutPageSteps(CheckoutPage checkoutPage, TestContext testContext, OrderSuccessPage orderSuccessPage, BATHelper batHelper, AccountDashboardPage accountDashboardPage, PaymentPage paymentPage, AddNewAddressPage addNewAddressPage, HomePage homePage, PercyPage percyPage) {
        this.orderSuccessPage = orderSuccessPage;
        this.batHelper=batHelper;
        this.accountDashboardPage=accountDashboardPage;
        this.paymentPage=paymentPage;
        this.addNewAddressPage=addNewAddressPage;
        this.homePage=homePage;
        this.scenarioContext = testContext.getScenarioContext();
        this.percyPage = percyPage;
        this.checkoutPage = checkoutPage;
    }

    @And("^validate currency in \"([^\"]*)\" Page$")
    public void validateCurrency(String pageName) {
        checkoutPage.validateCurrency(pageName);
    }

    @And("^validate if all sections are present in checkout Page$")
    public void validateAllHeadings() {
        checkoutPage.validateAllHeadings();
    }

    @And("^user should validate shipping cost if '(.*)' in checkout page$")
    public void shippingCostValidation(String cost) {
        checkoutPage.shippingCostValidation(cost);
    }

    @And("^click on '(.*)' button in '(.*)' Page$")
    public void clickOnRequiredButtons(String buttonName, String pageName) {
        checkoutPage.clickOnRequiredButtons(buttonName, pageName);
    }
}