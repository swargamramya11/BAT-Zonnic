package com.salmon.test.step_definitions;

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.When;

public class LoginPageSteps extends PageObject {

  private LogininPage loginPage;
  private HomePage homePage;
  private RegistrationPage registrationPage;
  private ForgotPasswordPage forgotPasswordPage;
  BATHelper batHelper;
  private ScenarioContext scenarioContext;
  private PDP pdp;
  private TermsAndConditions termsAndConditions;
  private PrivacyPolicyPage privacyPolicyPage;
  private PercyPage percyPage;

  public LoginPageSteps(PercyPage percyPage, TestContext testContext, LogininPage loginPage, HomePage homePage, BATHelper batHelper, RegistrationPage registrationPage,
                        TermsAndConditions termsAndConditions, PrivacyPolicyPage privacyPolicyPage, ForgotPasswordPage forgotPasswordPage, PDP pdp) {
    scenarioContext = testContext.getScenarioContext();
    this.loginPage = loginPage;
    this.homePage = homePage;
    this.batHelper = batHelper;
    this.registrationPage = registrationPage;
    this.termsAndConditions = termsAndConditions;
    this.privacyPolicyPage = privacyPolicyPage;
    this.forgotPasswordPage = forgotPasswordPage;
    this.pdp = pdp;
    this.percyPage = percyPage;
  }

  @When("^user signs in with customer properties '(.*)' '(.*)' as '(.*)'$")
  public void userSignsInWithCustomerProperties(String userName, String passWord, String guestOrNormal) throws InterruptedException {
    homePage.navigateToSignInPage();
    loginPage.loginWithConfigProperties(userName, passWord, guestOrNormal);
  }

  @When("^user signs in with new user and '(.*)'$")
  public void userSignsInWithCustomer(String passWord) {
    loginPage.loginWithNewLyCreatedCredentials(passWord);
  }

  @When("^navigate to signin Page$")
  public void navigateToSignInPage() {
    homePage.navigateToSignInPage();
  }
}