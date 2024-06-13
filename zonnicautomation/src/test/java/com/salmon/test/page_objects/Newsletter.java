package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import org.openqa.selenium.By;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.testng.AssertJUnit.assertTrue;

public class Newsletter extends PageObject {
    private CommonMethods commonMethods;
    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    public String emailAddressData = RandomGenerator.randomEmailAddress(6);
    public By whichAppliesToYou = By.cssSelector("select[name='haveYouEverUsedVeloBefore']");

    public Newsletter(CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
    }
    public static final By FIRSTNAME = By.cssSelector("#firstName");
    public static final By LASTNAME = By.cssSelector("#lastName");
    public static final By DOB = By.cssSelector("#dateofBirth");
    public static final By EMAIL = By.cssSelector("#emailAddress");
    public static final By PROVINCE = By.cssSelector(".bat-form-field.bat-form--newsletter-email-province #selectProvince");
    public static final By POSTAL_CODE = By.cssSelector("#postalCode");
    public static final By TELEPHONE = By.cssSelector("#mobileNumber");
    public static final By WHERE_TO_REDEEM = By.cssSelector("#selectOffer");
    public static final By FIRSTNAME_ERROR = By.cssSelector(".bat-form-field.bat-form--newsletter-email-first-name .bat-message.bat-form-msg.error-msg.error");
    public static final By LASTNAME_ERROR = By.cssSelector(".bat-form-field.bat-form--newsletter-email-last-name .bat-message.bat-form-msg.error-msg.error");
    public static final By DOB_ERROR = By.cssSelector(".bat-form-field.bat-form--newsletter-email-date-of-birth .bat-message.bat-form-msg.error-msg.error");
    public static final By EMAIL_ERROR = By.cssSelector(".bat-form-field.bat-form--newsletter-email-email .bat-message.bat-form-msg.error-msg.error");
    public static final By NEWSLETTER_HEADER1 = By.cssSelector(".bat-headline .bat-headline-style.headline3");
    public static final By NEWSLETTER_HEADER2 = By.cssSelector("#ZonnicNewsletterModal div .text .bat-text p");
    public static final By ZONNIC_LOGO = By.cssSelector("#ZonnicNewsletterModal div .image .bat-image img");
    public static final By CONTACT_US_LINK = By.cssSelector(".bat-form-field.bat-form--newsletter-email-assisted-trial-message .bat-message-text a");
    public static final By POLICY_LINK = By.cssSelector(".bat-form-block.newsletter-disclaimer p a");
    public static final By SUBMIT_BUTTON = By.cssSelector(".bat-form-field.bat-form--newsletter-email-submit button");
    public static final By SUBMIT_BUTTON_TEXT = By.cssSelector(".bat-form-field.bat-form--newsletter-email-submit button span");
    public static final By DISCLAIMER_TEXT = By.cssSelector(".bat-form-block.newsletter-disclaimer p");
    public static final By FIRST_NAME_HEADER = By.cssSelector(".bat-form-field.bat-form--newsletter-email-first-name label");
    public static final By EMAIL_HEADER = By.cssSelector(".bat-form-field.bat-form--newsletter-email-email label");
    public static final By LAST_NAME_HEADER = By.cssSelector(".bat-form-field.bat-form--newsletter-email-last-name label");
    public static final By DOB_HEADER = By.cssSelector(".bat-form-field.bat-form--newsletter-email-date-of-birth label");
    public static final By PROVINCE_WHERE_HEADER = By.cssSelector(".bat-form-field.bat-form--newsletter-email-province label");
    public static final By PHONE_HEADER = By.cssSelector(".bat-form-field.bat-form--newsletter-email-phone label");
    public static final By POSTAL_CODE_HEADER = By.cssSelector(".bat-form-field.bat-form--newsletter-email-address-zip-code.floating-label label");
    public static final By LOGO_SUBMISSION = By.cssSelector(".form .bat-form.bat-form--zonnic-newsletter .bat-headline-style.headline3 .zonnic-logo");
    public static final By SUBMISSION_TEXT = By.cssSelector(".form .bat-form.bat-form--zonnic-newsletter .bat-form-text p");
    public static final By CLOSE_BUTTTON = By.cssSelector(".bat-button.bat-button--dark.bat-button-close-step.margin-top-small");
    public static final By CLOSE_BUTTTON_TEXT = By.cssSelector(".bat-button.bat-button--dark.bat-button-close-step.margin-top-small span");

    public void leaveFieldEmpty(String name, By xpath, By errorXpath) {
        waitForItemToBeClickableAndClick(xpath);
        clickTabUsingKeyboard();
        String key=name+"-"+UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(errorXpath));
    }

    public void validateErrors(String error){
        switch (error) {
            case "firstNameIsBlank":
                leaveFieldEmpty("firstNameLastNameEmptyError", FIRSTNAME, FIRSTNAME_ERROR);
                break;
            case "lastNameIsBlank":
                leaveFieldEmpty("firstNameLastNameEmptyError", LASTNAME, LASTNAME_ERROR);
                break;
            case "dobIsBlank":
                leaveFieldEmpty("dobEmptyError", DOB, DOB_ERROR);
                break;
            case "emailIsBlank":
                leaveFieldEmpty("emailEmptyError", EMAIL, EMAIL_ERROR);
                break;
            case "enterInvalidEmailFormat":
                enterText(EMAIL, "invalidformat" );
                String key="emailEmptyError"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(EMAIL_ERROR));
                break;
        }
    }


    public void headervalidation(By xpath, String header) {
        String key = header + "-" + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(xpath));
    }

    public void validateHeader(String header) {
        switch (header) {
            case "header1":
                headervalidation(NEWSLETTER_HEADER1, "newsletterHeader1");
                break;
            case "header2":
                if (Props.getTestSuite().equals("Live")) {
                    headervalidation(NEWSLETTER_HEADER2, "newsletterHeader2Prod");
                } else {
                    headervalidation(NEWSLETTER_HEADER2, "newsletterHeader2");
                }
                break;
            case "logo":
                waitForExpectedElement(ZONNIC_LOGO).isDisplayed();
                break;
            case "disclaimer":
                headervalidation(DISCLAIMER_TEXT, "disclaimer");
                break;
            case "submissionHeader":
                headervalidation(SUBMISSION_TEXT, "newsletterSubmissionText");
                break;
            case "submissionLogo":
                waitForExpectedElement(LOGO_SUBMISSION).isDisplayed();
        }
    }

    public void clickOnLinks(String link) {
        switch (link) {
            case "contactUs":
                clickOnLinks(CONTACT_US_LINK, "helpTitle", "contactUsUrlForSignUp.key");
                break;
            case "policy":
                clickOnLinks(POLICY_LINK, "privacyPolicyTitle", "privacyPolicyUrlSignUp.key");
                break;
            case "signUp":
                scrollElementIntoView(SUBMIT_BUTTON);
                commonMethods.assertButtonText(getTrimTextFor(SUBMIT_BUTTON_TEXT), "signUpButton");
                waitForExpectedElement(SUBMIT_BUTTON).click();
                threadSleep(2000);
                break;
            case "close":
                commonMethods.assertButtonText(getTrimTextFor(CLOSE_BUTTTON_TEXT), "close");
                waitForExpectedElement(CLOSE_BUTTTON).click();
                break;
        }
    }

    public void clickOnLinks(By link, String title, String url) {
        waitForItemToBeClickableAndClick(link);
        threadSleep(2000);
        commonMethods.assertUrlContainsText(url);
        commonMethods.assertTitle(title);
        clickBrowserBackButton();
    }

    public void fieldValidation(By byName) {
       assertTrue(waitForExpectedElement(byName).isDisplayed());
    }

    public void validateFieldsDisplayedOrNot(String fieldName) {
        switch (fieldName) {
            case "telephone":
                fieldValidation(TELEPHONE);
                break;
            case "province":
                fieldValidation(PROVINCE);
                break;
            case "postalCode":
                fieldValidation(POSTAL_CODE);
                break;
            case "whereToRedeem":
                fieldValidation(WHERE_TO_REDEEM);
                break;
        }
    }

    public void validateFieldsHeaderDisplayedOrNot(String fieldNameHeader) {
        switch (fieldNameHeader) {
            case "telephoneHeader":
                headervalidation(PHONE_HEADER, "phoneNumber");
                break;
            case "provinceHeader":
                String key = "provinceHeader" + "-" + UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextForParticularIndexElement(PROVINCE_WHERE_HEADER, 0));
                break;
            case "postalCodeHeader":
                headervalidation(POSTAL_CODE_HEADER, "postalCodeHeader");
                break;
            case "whereToRedeemHeader":
                String key1 = null;
                if (Props.getTestSuite().equals("Live")) {
                    key1 = "whereToRedeemProd" + "-" + UrlBuilder.LANGUAGE;
                } else {
                    key1 = "whereToRedeem" + "-" + UrlBuilder.LANGUAGE;
                }
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key1), getTrimTextForParticularIndexElement(PROVINCE_WHERE_HEADER, 1));
                break;
            case "firstNameHeader":
                headervalidation(FIRST_NAME_HEADER, "firstNameHeader");
                break;
            case "lastNameHeader":
                headervalidation(LAST_NAME_HEADER, "lastNameNewsLetterHeader");
                break;
            case "dobHeader":
                headervalidation(DOB_HEADER, "dobHeader");
                break;
            case "emailHeader":
                headervalidation(EMAIL_HEADER, "newsLetterEmailHeader");
                break;
        }
    }

    public void enterData(String fieldName) {
        switch (fieldName) {
            case "firstName":
                waitClearAndEnterText(FIRSTNAME, firstNameData);
                break;
            case "lastName":
                waitClearAndEnterText(LASTNAME, lastNameData);
                break;
            case "dob":
                waitClearAndEnterText(DOB, "09091998");
                break;
            case "email":
                waitClearAndEnterText(EMAIL, emailAddressData);
                break;
        }
    }

    public void whichApplies() {
        if(Props.getTestSuite().equals("Live") || UrlBuilder.LANGUAGE.equalsIgnoreCase("fr")){

        } else {
            selectValueFromDropDownByIndex(2, whichAppliesToYou);
        }
    }
}