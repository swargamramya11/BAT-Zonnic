package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.And;

public class PDPSteps {

    private PDP pdp;
    private HomePage homepage;
    BATHelper batHelper;
    private PaymentPage paymentPage;
    private ScenarioContext scenarioContext;
    private RegistrationPage registrationPage;
    private LogininPage loginPage;

    public PDPSteps(TestContext testContext, PDP pdp, HomePage homepage, BATHelper batHelper, PaymentPage paymentPage, RegistrationPage registrationPage, LogininPage loginPage) {
        scenarioContext = testContext.getScenarioContext();
        this.pdp = pdp;
        this.homepage = homepage;
        this.batHelper = batHelper;
        this.paymentPage = paymentPage;
        this.registrationPage = registrationPage;
        this.loginPage = loginPage;
    }

    @And("^user goes to PDP page and click add to cart basket$")
    public void pdpPageZonnic() throws InterruptedException {
        pdp.addProductToBasketFromPDPZonnic();
    }

    @And("^user validate all pdp elements$")
    public void validateAllPdp() {
        pdp.validateAllPdp();
    }
}