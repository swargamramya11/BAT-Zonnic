package com.salmon.test.step_definitions;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import static com.salmon.test.enums.PermittedCharacters.*;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.testng.Assert.assertTrue;

@Getter
public class RegistrationSteps {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationSteps.class);
    private static final String MISMATCH_PASSWORD = "mismatchPassword";
    public By header = By.cssSelector(".bat-headline-style.headline2-color2");

    private ScenarioContext scenarioContext;
    private RegistrationPage registrationPage;
    private AccountDashboardPage accountDashboardPage;
    private BATHelper batHelper;
    private SoftAssertions softAssertions;
    private LoginPageSteps loginPageSteps;
    private PaymentPage paymentPage;
    private LogininPage logininPage;
    private HomePage homePage;
    private PLP plp;
    private PDP pdp;
    private ForgotPasswordPage forgotPasswordPage;
    private ForgotPasswordPageSteps forgotPasswordPageSteps;
    private CommonMethods commonMethods;

    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    private String DOBData = "08/05/2019";

    private String companyName = random(20, ALPHABETS);
    private String phoneNumber = "01" + random(10, NUMERIC);
    private String PHONENUMBER_CO = "6" + random(9, NUMERIC);
    private String streetAddressData = "12 The Cloisters";
    private String IntMGMPromoCode = "MGM-" + random(5, NUMERIC);
    private String phoneNumberCO = random(11, NUMERIC);
    private String phoneNumberIT = "1" + random(9, NUMERIC);
    private String phoneNumberMX = "1" + random(9, NUMERIC);

    private String loginIdData = random(6, ALPHANUMERIC);
    private String passwordData = random(6, ALPHANUMERIC);
    private String titleData = "Dr.";

    private String postCodeData = "UB10 9DW";
    private String address1Data = random(6, ALPHABETS);
    private String townOrCityData = random(6, ALPHABETS);
    private String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private String password = "Pa55word";
    private String RandomPostCodeData = random(4, NUMERIC) + " " + random(3, NUMERIC);

    private String user_email_address = System.getProperty("UserEmailAddress.key");
    private String user_first_name = System.getProperty("UserFirstName.key");
    private String user_last_name = System.getProperty("UserLastName.key");
    private String user_dob = System.getProperty("UserDOB.key");
    private String user_address_post_code = System.getProperty("UserPostCode.key");
    private List<String> actualDataProtectionUrlLink;
    private static final String INVALID_EMAIL = "test@test";
    private static final String INVALID_MOBILE = "23444322@";
    private static final String VALID_MOBILE = "234443255";
    private static final String EMAIL_MORETHAN_35CHAR ="battestemailmorethan35characters12345@yahoo.com";
    public RegistrationSteps(CommonMethods commonMethods, TestContext testContext, RegistrationPage registrationPage, AccountDashboardPage accountDashboardPage, BATHelper batHelper, SoftAssertions softAssertions, LoginPageSteps loginPageSteps, ForgotPasswordPage forgotPasswordPage, HomePage homePage, LogininPage logininPage, PaymentPage paymentPage, PLP plp, PDP pdp, ForgotPasswordPageSteps forgotPasswordPageSteps) {
        scenarioContext = testContext.getScenarioContext();
        this.registrationPage = registrationPage;
        this.accountDashboardPage = accountDashboardPage;
        this.batHelper = batHelper;
        this.softAssertions = softAssertions;
        this.loginPageSteps = loginPageSteps;
        this.logininPage = logininPage;
        this.homePage = homePage;
        this.plp = plp;
        this.pdp = pdp;
        this.paymentPage = paymentPage;
        this.forgotPasswordPage = forgotPasswordPage;
        this.forgotPasswordPageSteps = forgotPasswordPageSteps;
        this.commonMethods = commonMethods;
    }

    @And("^validate if account is created or not$")
    public void validateIfAccountIsCreated(){
        registrationPage.validateMyAccountUrl();
    }

    @And("^create a new account with province '(.*)' and address should be entered by '(.*)' with '(.*)' as '(.*)'$")
    public void createANewAccountZonnnic(String province, String addressEnteringMethod, String shippingMethod, String guestOrNormalUser) throws Throwable {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                batHelper.createNewAccountZonnic(province, addressEnteringMethod, shippingMethod, guestOrNormalUser);
                break;
        }
    }

    @And("^click on create account button for '(.*)'$")
    public void craeteAccountButton(String guestOrNormalUser) {
        logininPage.clickCreateAccountButton(guestOrNormalUser);
    }

    @And("^user try to register with same email which is registered in vuse with province '(.*)'$")
    public void createANewAccountInZonnicWithVuseEmail(String province) throws Throwable {
        batHelper.createNewAccountZonnic(province);
    }

    @And("^validate if error popup is appeared as account is already present in vuse site and close it$")
    public void validateErrorPopup() {
        registrationPage.validateErrorPopup();
    }

    @And("^create account in Vuse site in '(.*)' province$")
    public void createANewAccountVuse(String sameOrDiffProvince) {
        batHelper.createNewAccountVuse(sameOrDiffProvince);
    }

    @And("^click on Create account button '(.*)' and Without filling mandatory fields '(.*)' with '(.*)'$")
    public void withoutFillingMandatoryFields(String province, String addressEnteringMethod,String shippingMethod) {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                batHelper.withoutFillingMandatoryFields(province,addressEnteringMethod,shippingMethod);
                break;
        }
    }

    @Then("^give incorrect format input for the E-mail field$")
    public void IncorrectEmailFields(){
        registrationPage.IncorrectEmailFields();
    }
    @And("assert Error message for invalid format of email$")
    public void assertErroMessg(){
        String emailerror="errorEmailrequired"+"-"+UrlBuilder.LANGUAGE;
        assertTrue(registrationPage.getEmailErrorMessage().contains(UrlBuilder.getMessage("errorEmailrequired"+"-"+UrlBuilder.LANGUAGE )));
    }
    @And("^validate password rule suggestion$")
    public void passwordRuleSuggestions() {
        registrationPage.enterEmailAddress();
        registrationPage.enterPasswordOnRegistrations();
        String passwd="passwordErrorList"+"-"+UrlBuilder.LANGUAGE;
        assertTrue(registrationPage.passwordRulesSuggestions().contains( UrlBuilder.getMessage("passwordErrorList"+"-"+UrlBuilder.LANGUAGE)));
    }
    @And("^validate different password combination$")
    public void validateDifferentPasswordCombination() throws InterruptedException {
        registrationPage.validateDifferentsPasswordCombination();
        String passwderror="passwordErrorList_ONE"+"-"+UrlBuilder.LANGUAGE;
        assertTrue(registrationPage.diffPasswordCombinations().contains(
                UrlBuilder.getMessage("passwordErrorList_ONE"+"-"+UrlBuilder.LANGUAGE)));
    }
    @And("^validate password strength indicator$")
    public void passwordStrengthIndicator(){
        registrationPage.passworddStrengthIndicator();
    }
    @And("^enter confirm password$")
    public void confirmpassword(){
        registrationPage.confirmPassword();
    }

    @Then("^Without click user accepts T&C$")
    public void WithoutClickUserAcceptsTC(){
        registrationPage.WithoutClickUserAcceptsTCS();

    }
    @And("^User click on checkbox Link$")
    public void userClickOnCheckBoxLink(){
        registrationPage.clickOnCheckbox();
        registrationPage.whichAppliesToYou();
    }
   @Then("^click on create an account$")
    public void clickOnCreateAccount(){
       registrationPage.clickOnCreateAccountButton();
   }

    @Then("^click on '(.*)' and verify url and title in signUp Page$")
    public void clickOnLink(String link, DataTable dtList){
        registrationPage.clickOnLinksInSignUp(dtList);
    }

    @When("^user create account through API and login with created credentials as '(.*)'$")
    public void createcustomeraccountAPI(String normalOrGuestUser) {
        registrationPage.createAccountWithAPI();
        if(normalOrGuestUser.equals("normalUser")) {
            homePage.navigateToSignInPage();
        }
        logininPage.loginWithNewLyCreatedCredentials("loginValidPassword.key");
        if(normalOrGuestUser.equals("normalUser")) {
            registrationPage.validateMyAccountUrl();
        } else {
            registrationPage.waitForURLToContain(UrlBuilder.getMessage("shippingAnPaymentLink.key"));
            commonMethods.assertUrl("shippingAnPaymentLink.key");
        }
    }

    @Then("^user try to create a new account with '(.*)' email format province '(.*)' and '(.*)' with '(.*)' as '(.*)'$")
    public void createAccountWithNonWhitelistedEmailFormats(String emailType, String province, String addressEnteringMethod, String shippingMethod, String guestOrNormalUser){
        batHelper.createAccountWithNonWhitelistedEmailFormats(emailType, province, addressEnteringMethod, shippingMethod, guestOrNormalUser);
    }

    @Then("^validate '(.*)' in account creation$")
    public void validateErrorMessageInAccountCreation(String error){
        registrationPage.validateErrorMessageInAccountCreation(error);
    }
}