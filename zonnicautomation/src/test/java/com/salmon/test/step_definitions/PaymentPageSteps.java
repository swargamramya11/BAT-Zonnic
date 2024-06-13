package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.And;

public class PaymentPageSteps {

    private OrderSuccessPage orderSuccessPage;
    private AccountDashboardPage accountDashboardPage;
    private PaymentPage paymentPage;
    private AddNewAddressPage addNewAddressPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private BATHelper batHelper;
    private PercyPage percyPage;
    private CheckoutPage checkoutPage;

    public PaymentPageSteps(CheckoutPage checkoutPage, TestContext testContext, OrderSuccessPage orderSuccessPage, BATHelper batHelper, AccountDashboardPage accountDashboardPage, PaymentPage paymentPage, AddNewAddressPage addNewAddressPage, HomePage homePage, PercyPage percyPage) {
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

    @And("^validate error messages of card details$")
    public void errorValidations() {
        paymentPage.validateErrorMessageOfCardNumberField();
        paymentPage.validateErrorMessageOfNameField();
        paymentPage.validateErrorMessageOfExiryDateField();
        paymentPage.validateErrorMessageOfCVVField();
    }
}
