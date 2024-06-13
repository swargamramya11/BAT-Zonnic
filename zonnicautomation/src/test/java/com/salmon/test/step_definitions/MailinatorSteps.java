package com.salmon.test.step_definitions;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.LogininPage;
import com.salmon.test.page_objects.MailinatorPage;
import com.salmon.test.page_objects.PercyPage;
import com.salmon.test.page_objects.RegistrationPage;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;

import static com.salmon.test.framework.helpers.UrlBuilder.ENVIRONMENT;
import static com.salmon.test.framework.helpers.UrlBuilder.navigateToUrl;
import static com.salmon.test.page_objects.constants.Context.EMAIL_ID;

public class MailinatorSteps {
    private MailinatorPage mailinatorPage;
    private ScenarioContext scenarioContext;
    private LogininPage loginPage;
    private RegistrationPage registrationPage;
    private String emailId;
    private SoftAssertions softAssertions;
    private CommonMethods commonMethods;
    private PercyPage percyPage;

    public MailinatorSteps(PercyPage percyPage, CommonMethods commonMethods, TestContext testContext, MailinatorPage mailinatorPage, LogininPage loginPage, RegistrationPage registrationPage, SoftAssertions softAssertions) {
        scenarioContext = testContext.getScenarioContext();
        this.mailinatorPage = mailinatorPage;
        this.loginPage = loginPage;
        this.registrationPage = registrationPage;
        emailId = (String) scenarioContext.getContext(EMAIL_ID);
        this.softAssertions = softAssertions;
        this.commonMethods = commonMethods;
        this.percyPage = percyPage;
    }

    @Then("^assert that the email is received with subject '(.*)' with content '(.*)' and with header '(.*)'$")
    public void assertThatTheReferredReceivedEmail(String subject, String content, String key) {
        if(!ENVIRONMENT.equalsIgnoreCase("PRELIVE1")) {
            String url = mailinatorPage.getCurrentUrl();
            String email = (String) scenarioContext.getContext(EMAIL_ID);

            if (subject.equals("passwordChangedEmail")) {
                mailinatorPage.switchBetweenWindowTabs(0);
            }

            mailinatorPage.clickOnEmailWithSubject(email, UrlBuilder.getMessage(subject + "-" + UrlBuilder.LANGUAGE));
            mailinatorPage.waitForAjaxElementNotToBePresent(mailinatorPage.getWebDriver(), 10);
            mailinatorPage.switchToMessageBodyIframe();
            mailinatorPage.waitForPage();
            mailinatorPage.validateCustomerCareInfo();
            mailinatorPage.validateHeader(key);

            if (subject.equals("passwordChangedEmail")) {
                mailinatorPage.assertHiInEmail("hello");
                mailinatorPage.assertContentOfEmail(content + "-" + UrlBuilder.LANGUAGE, 1);
                mailinatorPage.assertContentOfEmail(content + "Part2" + "-" + UrlBuilder.LANGUAGE, 2);
            } else if (subject.equals("passwordResetEmail")) {
                mailinatorPage.assertHiInEmail("helloThere");
                mailinatorPage.assertContentOfEmail(content + "-" + UrlBuilder.LANGUAGE, 1);
                mailinatorPage.assertContentOfEmail(content + "Part2" + "-" + UrlBuilder.LANGUAGE, 2);
                mailinatorPage.assertContentOfEmail(content + "Part3" + "-" + UrlBuilder.LANGUAGE, 3);
            } else if (subject.equals("registrationConfirmationEmail")) {
                percyPage.takePercyScreenshot("Registration Email");
                mailinatorPage.assertHiInEmail("hello");
                mailinatorPage.assertContentOfEmail(content + "-" + UrlBuilder.LANGUAGE, 1);
            } else if (subject.equals("orderConfimationEmail")) {
                percyPage.takePercyScreenshot("Order Confirmation Email");
                mailinatorPage.assertHiInEmail("hi");
                mailinatorPage.assertContentOfEmail(content + "-" + UrlBuilder.LANGUAGE, 1);
            }
            if (subject.equals("passwordResetEmail")) {
                clickOnResetButton();
            } else {
                navigateToUrl(url);
            }
        }
    }

    @Then("^close existing tab and navigate to main tab$")
    public void navigateToMailinatorAndClickOnReset() {
        mailinatorPage.pageclose();
        mailinatorPage.switchBetweenWindowTabs(0);
    }

    public void clickOnResetButton(){
        try {
            mailinatorPage.waitForExpectedElement(mailinatorPage.RESET_PASSWORD_BUTTON).click();
        } catch (Exception e) {
            try {
                mailinatorPage.clickByElementByQueryJSExecutor(mailinatorPage.RESET_PASSWORD_BUTTON);
            } catch (Exception e1) {
                try {
                    mailinatorPage.clickUsingJS(mailinatorPage.RESET_PASSWORD_BUTTON);
                }catch (Exception e2){
                    mailinatorPage.waitForExpectedElement(mailinatorPage.RESET_PASSWORD_BUTTON1).click();
                }
            }
        }
        mailinatorPage.threadSleep(3000);
        mailinatorPage.switchBetweenWindowTabs(1);
        commonMethods.assertUrlContains("createNewPasswordURL.key");
        commonMethods.assertTitle("createNewPasswordTitle");
        commonMethods.assertFooterTitle("createNewPasswordTitle");
    }

    @Then("^validate price details in email with '(.*)' with '(.*)'$")
    public void validatePriceDetails(String address, String coupon) {
        mailinatorPage.clickBrowserBackButton();
        mailinatorPage.switchToMessageBodyIframe();
        mailinatorPage.validateOrderNumberHeader();
//        mailinatorPage.validateDate();
        mailinatorPage.validateNumberOfProductsAndQunatity();
        mailinatorPage.priceDetails(coupon);
        mailinatorPage.validateAddress(address);
        mailinatorPage.validateShippingMethod();
        mailinatorPage.validatePaymentMethod();
    }
}