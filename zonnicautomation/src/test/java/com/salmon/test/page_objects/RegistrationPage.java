package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;
import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.WebDriverHelper.percy;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.constants.Context.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class RegistrationPage extends PageObject {
    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    public String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private String passwordText = "M@nish1710";
    private String townOrCityData = "Hamburg";
    private ScenarioContext scenarioContext;
    private CommonMethods commonMethods;
    private Payloads payloads;
    public RegistrationPage(TestContext testContext, CommonMethods commonMethods, Payloads payloads) {
        this.scenarioContext = testContext.getScenarioContext();
        this.commonMethods = commonMethods;
        this.payloads = payloads;
    }
    public By firstNameInput = By.cssSelector("#firstname");
    public By firstNameInput_CA = By.cssSelector("#firstName");
    public By surNameInput = By.cssSelector("#lastname");
    public By surNameInput_CA = By.cssSelector("#lastName");
    public By DOBInput = By.cssSelector("input#dob"); // format = 08/05/2019
    public By phoneNumber_CA = By.cssSelector("#phoneNumber");
    public By city = By.cssSelector("#city");
    public By streetAddress_CA = By.cssSelector("#streetAddress");
    public By postCode_CA = By.cssSelector("#postalCode");
    public By emailaddress = By.cssSelector("#email_address");
    public By emailaddress_CA = By.cssSelector("input#email");
    public By password = By.cssSelector("input#password");
    public By password_confirm = By.cssSelector("#passwordConfirm");
    public By checkbox = By.cssSelector("#subscriptionConsent");
    public By createAccountButton = By.cssSelector(".bat-form-block > div.bat-form-field.bat-form--signup-submit button");
    public By createAccountButton_text = By.cssSelector(".bat-form-block > div.bat-form-field.bat-form--signup-submit button span");
    public By header = By.cssSelector(".bat-headline-style.headline2-color2");
    public By whichAppliesToYou = By.cssSelector("select[name='user_has_zonnic_can']");
    public By whichAppliesToYou1 = By.xpath("(//select[@name='user_has_zonnic_can'])[2]");
    public By confirmageButton = By.cssSelector("button.action.submit.primary");
    public By enterAddressManully = By.cssSelector("#enterAddressManually");
    public By quickAddressFinder = By.cssSelector("#addressLookUp");
    public By selectFirstAddress = By.cssSelector("p.container-address");
    public By selectFirstAddress1 = By.cssSelector("p.possible-address");
    public By FIRSTNAME_VUSE = By.cssSelector(".bat-form.bat-form--registration  .bat-form-field.bat-form--registration-first-name  input");
    public By LASTNAME_VUSE = By.cssSelector(".bat-form.bat-form--registration  .bat-form-field.bat-form--registration-last-name  input");
    public By EMAIL_VUSE = By.xpath("//label[@for='regEmail']//parent::div[@class='bat-form-field bat-form--registration-email   ']//input");
    public By DOB_VUSE = By.cssSelector(".bat-form-field.bat-form--registration-datePicker.bat-form-field__datePicker  input");
    public By ADDRESS_VUSE = By.cssSelector(".bat-form-field.bat-form--registration-lookup-field.address-autocomplete-field  input");
    public By PHONE_NUMBER_VUSE = By.cssSelector(".bat-form-field.bat-form--registration-phone input");
    public By PASSWORD_VUSE = By.xpath("//label[@for='password']//parent::div[@class='bat-form-field bat-form--registration-email   ']//input");
    public By CONFIRM_PASSWORD_VUSE = By.xpath("//label[@for='passwordConfirm']//parent::div[@class='bat-form-field bat-form--registration-email   ']//input");
    public By TERMS_AND_CONDITIONS_VUSE = By.cssSelector("#signup .bat-form-block.bat-form-tos .bat-form-field.bat-form--registration-tos .bat-form-field-set.required .bat-toggle__button");
    public By CREATE_ACCOUNT_BUTTON_VUSE = By.cssSelector("#signupForm .bat-form-field.bat-form--registration-submit button");
    public By PROVINCE_VUSE = By.cssSelector("#province");
    public By EXISTING_ACCOUNT_MESSAGE = By.cssSelector(".bat-form-block.existing-user.active p");
    public By CLOSE_EXISTING_ACCOUNT_POPUP = By.cssSelector(".bat-form-block.existing-user.active .bat-form-block-text button");
    public By FIRST_NAME_FIELD = By.xpath("//p[normalize-space()='First Name Required.']");
    public By LAST_NAME_FIELD = By.xpath("//p[normalize-space()='Last Name Required.']");
    public By EMAIL_REQUIRED_ERROR_MESSAGE = By.xpath("//div[contains(@class,'bat-form-field bat-form--signup-email')]//p[contains(@class,'sm')][normalize-space()='Please enter a valid email address.']");
    public By emailaddress_1 = By.xpath("//div[@class='bat-form-field bat-form--signup-email']//input[@id='email']");
    public By DOB_ERROR_FIELD = By.xpath("//p[normalize-space()='Please enter your date of birth']");
    public By QUICK_ADDRESS_FINDMESSAGE_ERROR = By.xpath("//div[@class='bat-message-text'][normalize-space()='Please select a residential address. For legal reasons, we cannot ship to PO Boxes.']");
    public By PHONE_NUMBER_ERROR_MESSAGE = By.xpath("//p[contains(text(),'Please add phone number in this format 888-888-888')]");
    public By PASSWORD_ERROR_MSSG = By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > bat-section-default:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > bat-form-signup:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(4) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > p:nth-child(1)");
    public By PASSWORD_ERROR_MSSG_CONFIRM = By.cssSelector("body > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > bat-section-default:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > bat-form-signup:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(4) > div:nth-child(3) > div:nth-child(3) > div:nth-child(1) > p:nth-child(1)");
    public By password_1 = By.xpath("//div[@class='bat-form-field bat-form--signup-password']//input[@id='password']");
    public By PASSWORD_TOGGLE_INVISIBLE = By.cssSelector(".bat.d-block.d-sm-block.d-md-block.d-lg-block.d-xl-block.margin-small.ready.loaded[data-template-url='/content/dam/zonnic/components/content/form/signup/template.hbs']");
    public By FIRST_NAME_FIELD_FR = By.xpath("//p[normalize-space()='Entrez un prénom.']");
    public By LAST_NAME_FIELD_FR = By.xpath("//p[normalize-space()='Entrez un nom.']");
    public By EMAIL_REQUIRED_ERROR_MESSAGE_FR = By.xpath("//div[contains(@class,'bat-form-field bat-form--signup-email')]//p[contains(@class,'sm')][normalize-space()='Saisissez une adresse e-mail valide.']");
    public By BOB_ERROR_FIELD_FR = By.xpath("//p[normalize-space()='Saisissez une date de naissance valide.']");
    public By QUICK_ADDRESS_FINDMESSAGE_ERROR_FR = By.xpath("//div[contains(@class,'bat-message-text')][normalize-space()='Veuillez sélectionner une adresse résidentielle. Nous ne pouvons pas expédier de colis à des boîtes postales pour des raisons légales.']");
    public By PHONE_NUMBER_ERROR_MESSAGE_FR = By.xpath("//p[contains(text(),'Veuillez indiquer un numéro de téléphone valide. E')]");
    public By PASSWORD_ERROR_MSSG_fr = By.xpath("//p[contains(text(),'Le mot de passe doit contenir au moins 8 caractère')]");
    public By PASSWORD_ERROR_MSSG_CONFIRM_FR = By.xpath("//p[contains(text(),'Oups, ce mot de passe ne correspond pas. Veuillez ')]");
    public By CONTACT_US_SIGNUP = By.cssSelector(".bat-form-field.bat-form--signup-subscription-consent.consent-block label p a");
    public By TERMS_AND_CONDITIONS_PRIVACY_SIGNUP = By.cssSelector(".bat-form-block.consent-block div p a");
    public By ERROR_IN_SIGNUP = By.cssSelector(".bat-messagebar--zonnic-message-error.active");

    public void enterFirstNameAndLastName() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                enterText(firstNameInput_CA, firstNameData);
                enterText(surNameInput_CA, lastNameData);
                scenarioContext.setContext(FIRST_NAME, firstNameData);
                scenarioContext.setContext(LAST_NAME, lastNameData);
                scenarioContext.setContext(FISTANDLASTNAME_ACCOUNT_CREATION, firstNameData+" "+lastNameData);
                break;
            default:
                enterText(firstNameInput, firstNameData);
                enterText(surNameInput, lastNameData);
        }
    }

    public void enterEmailAddress() {
        String emailAddressData = this.emailAddressData;
        String passwordText = this.passwordText;
        scenarioContext.setContext(EMAIL_ID, emailAddressData);
        scenarioContext.setContext(PASSWORD, passwordText);
        LOG.info(emailAddressData);

        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                try {
                    webDriver.findElements(emailaddress_CA).get(1).sendKeys(emailAddressData);
                } catch (Exception e) {
                    enterText(emailaddress_CA, emailAddressData);
                }
                break;
        }
    }

    public void clickOnConfirmAgeButton() {
        waitForExpectedElement(confirmageButton);
        clickByElementByQueryJSExecutor(confirmageButton);
    }

    public void enterDOB(String DOBToEnter) {
        enterText(DOBInput, DOBToEnter);
        waitForExpectedElement(DOBInput).submit();
    }

    public void enterPassword() {
        try {
            webDriver.findElements(password).get(1).sendKeys(UrlBuilder.getMessage("loginValidPassword.key"));
        } catch (Exception e) {
            webDriver.findElement(password).sendKeys(UrlBuilder.getMessage("loginValidPassword.key"));
        }
        try {
            webDriver.findElement(password_confirm).sendKeys(UrlBuilder.getMessage("loginValidPassword.key"));
        } catch (Exception e) {
            waitForExpectedElement(password_confirm).sendKeys(UrlBuilder.getMessage("loginValidPassword.key"));
        }
    }

    public void clickOnCheckbox() {
        try {
            waitForExpectedElement(checkbox).click();
        } catch (Exception e) {
            try {
                webDriver.findElements(checkbox).get(1).click();
            } catch (Exception e1) {
                webDriver.findElements(checkbox).get(0).click();
            }
        }
    }

    public void clickOnCreateAccountButton() {
        commonMethods.assertButtonText(getTrimTextFor(createAccountButton_text), "createAccountButton");
        try {
            waitForExpectedElement(createAccountButton).click();
        } catch (Exception e) {
            try {
                clickByElementByQueryJSExecutor(createAccountButton);
            } catch (Exception e1) {
                try {
                    clickUsingJS(createAccountButton);
                } catch (Exception e2) {

                }
            }
        }
    }

    public void whichAppliesToYou() {
        try {
            selectValueFromDropDownByIndex(2, whichAppliesToYou);
        } catch (Exception e) {
            selectValueFromDropDownByIndex(2, whichAppliesToYou1);
        }
    }

    public void enterRegistrationDetailsZonnic(String province, String addressEnteringMethod, String shippingMethod) {
        enterFirstNameAndLastName();
        enterEmailAddress();
        populateAddressFieldsZonnic(province, addressEnteringMethod, shippingMethod);
        try {
            webDriver.findElement(DOBInput).sendKeys(UrlBuilder.getMessage("regpageValidDOB.key"));
        } catch (Exception e) {
            enterDOB("regpageValidDOB.key");
        }
        webDriver.findElement(phoneNumber_CA).sendKeys(UrlBuilder.getMessage("validPhoneNo.key"));
        scenarioContext.setContext(TELEPHONE_NUMBER_FROM_CREATE_ACCOUNT, getValue(phoneNumber_CA));
    }

    public void populateAddressFieldsZonnic(String province, String addressEnteringMethod, String shippingMethod) {
        province = UrlBuilder.PROVINCE;
        String postCode = null;
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                if (addressEnteringMethod.equals("enterManually")) {
                    waitForExpectedElement(enterAddressManully).click();
                    enterText(streetAddress_CA, "12 The Cloisters");
                    enterText(city, townOrCityData);
                    postCode = getPostCode(province, shippingMethod);
                    enterText(postCode_CA, postCode);
                } else {
                    postCode = getPostCode(province, shippingMethod);
                    scrollElementIntoView(quickAddressFinder);
                    enterPostCode(postCode);
                    try {
                        clickByElementByQueryJSExecutor(selectFirstAddress);
                        waitForExpectedElement(selectFirstAddress1, 30);
                        clickByElementByQueryJSExecutor(selectFirstAddress1);
                    } catch (Exception e) {
                        clickByElementByQueryJSExecutor(selectFirstAddress1);
                    }
                    String selectedAddress = getValue(quickAddressFinder);
                    scenarioContext.setContext(ADDRESS_FROM_CREATE_ACCOUNT, selectedAddress);
                    if (selectedAddress.contains(postCode)) {
                        System.out.println("Post code entered correctly");
                        break;
                    } else {
                        System.out.println("Post code not entered correctly");
                    }
                    assertTrueExpectedTextContainsActualText(postCode, selectedAddress);
                }
                break;
        }
    }

    public String getPostCode(String province, String shippingMethod) {
        province = UrlBuilder.PROVINCE;
        String postCode = null;
        String shippingMethod1 = UrlBuilder.getMessage(shippingMethod + "-" + UrlBuilder.LANGUAGE);
        if (shippingMethod1.contains("SAME DAY DELIVERY")
                || shippingMethod1.equals("NEXT DAY DELIVERY")
                || shippingMethod1.equals("LIVRAISON LE JOUR SUIVANT")) {
            if (province.equalsIgnoreCase("alberta")) {
                postCode = UrlBuilder.getMessage("sameOrNextDay-alberta.key");
            } else if (province.equalsIgnoreCase("Ontario")) {
                postCode = UrlBuilder.getMessage("sameOrNextDay-ontario.key");
            } else if (province.equalsIgnoreCase("British Columbia")) {
                postCode = UrlBuilder.getMessage("sameOrNextDay-BC.key");
            }
        } else if (shippingMethod1.contains("FEDEX NEXT DAY")) {
            if (province.equalsIgnoreCase("alberta")) {
                postCode = UrlBuilder.getMessage("fedexNextDay-alberta.key");
            } else if (province.equalsIgnoreCase("Ontario")) {
                postCode = UrlBuilder.getMessage("fedexNextDay-ontario.key");
            } else if (province.equalsIgnoreCase("Saskatchewan")) {
                postCode = UrlBuilder.getMessage("fedexNextDay-Saskatchewan.key");
            } else if (province.equalsIgnoreCase("British Columbia")) {
                postCode = UrlBuilder.getMessage("fedexNextDay-BC.key");
            }
        } else {
            postCode = getPostCode(province);
        }
        return postCode;
    }

    public void enterPostCode(String postCode) {
        String val = postCode;
        WebElement element = webDriver.findElement(quickAddressFinder);

        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
            threadSleep(2000);
        }
    }

    public String getPostCode(String province) {
        String postCode = null;
        if (province.contains("Alberta")) {
            postCode = UrlBuilder.getMessage("validPostCode-alberta.key");
        } else if (province.contains("British Columbia")) {
            postCode = UrlBuilder.getMessage("validPostCode-BC.key");
        } else if (province.contains("Manitoba")) {
            postCode = UrlBuilder.getMessage("validPostCode-manitoba.key");
        } else if (province.contains("New Brunswick")) {
            postCode = UrlBuilder.getMessage("validPostCode-NB.key");
        } else if (province.contains("Newfoundland and Labrador")) {
            postCode = UrlBuilder.getMessage("validPostCode-NL.key");
        } else if (province.contains("Nova Scotia")) {
            postCode = UrlBuilder.getMessage("validPostCode-NS.key");
        } else if (province.contains("Ontario")) {
            postCode = UrlBuilder.getMessage("validPostCode-ON.key");
        } else if (province.contains("Prince Edward Island")) {
            postCode = UrlBuilder.getMessage("validPostCode-PE.key");
        } else if (province.contains("Quebec")) {
            postCode = UrlBuilder.getMessage("validPostCode-quebec.key");
        } else if (province.contains("Saskatchewan")) {
            postCode = UrlBuilder.getMessage("validPostCode-Saskatchewan.key");
        }
        return postCode;
    }

    public String getPostCodecheckout(String province) {
        String postCode = null;
        if (province.contains("Alberta")) {
            postCode = UrlBuilder.getMessage("validPostCode-alberta1.key");
        } else if (province.contains("British Columbia")) {
            postCode = UrlBuilder.getMessage("validPostCode-BC1.key");
        } else if (province.contains("Manitoba")) {
            postCode = UrlBuilder.getMessage("validPostCode-manitoba1.key");
        } else if (province.contains("New Brunswick")) {
            postCode = UrlBuilder.getMessage("validPostCode-NB1.key");
        } else if (province.contains("Newfoundland and Labrador")) {
            postCode = UrlBuilder.getMessage("validPostCode-NL1.key");
        } else if (province.contains("Nova Scotia")) {
            postCode = UrlBuilder.getMessage("validPostCode-NS1.key");
        } else if (province.contains("Ontario")) {
            postCode = UrlBuilder.getMessage("validPostCode-ON1.key");
        } else if (province.contains("Prince Edward Island")) {
            postCode = UrlBuilder.getMessage("validPostCode-PE1.key");
        } else if (province.contains("Quebec")) {
            postCode = UrlBuilder.getMessage("validPostCode-quebec1.key");
        } else if (province.contains("Saskatchewan")) {
            postCode = UrlBuilder.getMessage("validPostCode-Saskatchewan1.key");
        }
        return postCode;
    }

    public void enterFirstNameVuse() {
        enterText(FIRSTNAME_VUSE, firstNameData);
    }

    public void enterLastNameVuse() {
        enterText(LASTNAME_VUSE, lastNameData);
    }

    public void enterEmailVuse() {
        enterText(EMAIL_VUSE, emailAddressData);
        scenarioContext.setContext(EMAIL_ID, emailAddressData);
        LOG.info("Email: " + emailAddressData);
    }

    public void enterDOBVuse() {
        enterText(DOB_VUSE, "1998/08/02");
    }

    public void enterAddressVuse(String sameOrDiffProvince) {
        String province = UrlBuilder.PROVINCE;
        if (sameOrDiffProvince.equals("different") && !province.equals("Saskatchewan")) {
            province = "Saskatchewan";
        } else if (sameOrDiffProvince.equals("different") && province.equals("Saskatchewan")) {
            province = "Alberta";
        } else {
            province = UrlBuilder.PROVINCE;
        }
        String postCode = getPostCode(province);
        waitForExpectedElement(enterAddressManully).click();
        enterText(streetAddress_CA, "asddas");
        enterText(city, "sfdsds");
        enterText(postCode_CA, postCode);
        selectValueFromDropDownByby(province, PROVINCE_VUSE);
    }

    public void enterPhoneNumberVuse() {
        enterText(PHONE_NUMBER_VUSE, UrlBuilder.getMessage("validPhoneNo.key"));
    }

    public void enterPasswordVuse() {
        enterText(PASSWORD_VUSE, UrlBuilder.getMessage("loginValidPassword.key"));
        scenarioContext.setContext(PASSWORD, UrlBuilder.getMessage("loginValidPassword.key"));
        LOG.info("Password: " + UrlBuilder.getMessage("loginValidPassword.key"));
    }

    public void enterConfirmPasswordVuse() {
        enterText(CONFIRM_PASSWORD_VUSE, UrlBuilder.getMessage("loginValidPassword.key"));
    }

    public void acceptTAndCVuse() {
        clickByElementByQueryJSExecutor(TERMS_AND_CONDITIONS_VUSE);
    }

    public void enterPostCodeVuse(String postCode) {
        String val = postCode;
        WebElement element = webDriver.findElement(ADDRESS_VUSE);

        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }

    public void clickCreateAccountButtonVuse() {
        waitForExpectedElement(CREATE_ACCOUNT_BUTTON_VUSE).click();
    }

    public void enterRegistrationDetailsVuse(String sameOrDiffProvince) {
        enterFirstNameVuse();
        enterLastNameVuse();
        enterEmailVuse();
        enterAddressVuse(sameOrDiffProvince);
        enterDOBVuse();
        enterPhoneNumberVuse();
    }

    public void enterRegistrationDetailsZonnic(String email, String province) {
        enterFirstNameAndLastName();
        enterEmailAddressAndPassword(email);
        populateAddressFieldsZonnic(province);
        try {
            webDriver.findElement(DOBInput).sendKeys(UrlBuilder.getMessage("regpageValidDOB.key"));
        } catch (Exception e) {
            enterDOB("regpageValidDOB.key");
        }
        webDriver.findElement(phoneNumber_CA).sendKeys(UrlBuilder.getMessage("validPhoneNo.key"));
    }

    public void enterEmailAddressAndPassword(String email) {
        String passwordText = this.passwordText;
        scenarioContext.setContext(PASSWORD, passwordText);

        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                webDriver.findElements(emailaddress_CA).get(1).sendKeys(email);
                break;
            default:
                enterText(emailaddress, emailAddressData);
                enterText(password, passwordText);
        }
    }

    public void closePopup() {
        waitForExpectedElement(CLOSE_EXISTING_ACCOUNT_POPUP).click();
    }

    public void validateErrorPopup() {
        String key = "alreadyAccountExisting" + "-" + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(key), getTrimTextFor(EXISTING_ACCOUNT_MESSAGE));
        closePopup();
    }

    public void populateAddressFieldsZonnic(String province) {
        province = UrlBuilder.PROVINCE;
        String postCode = null;
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                postCode = getPostCode(province);
                scrollElementIntoView(quickAddressFinder);
                enterPostCode(postCode);
                try {
                    clickByElementByQueryJSExecutor(selectFirstAddress);
                    waitForExpectedElement(selectFirstAddress1, 30);
                    clickByElementByQueryJSExecutor(selectFirstAddress1);
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(selectFirstAddress1);
                }
                String selectedAddress = getValue(quickAddressFinder);
                if (selectedAddress.contains(postCode)) {
                    System.out.println("Post code entered correctly");
                    break;
                } else {
                    System.out.println("Post code not entered correctly");
                }
        }
    }

    public String firstNameRequiredFields() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(firstNameInput_CA));
        getWebDriver().findElement(firstNameInput_CA).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        try {
            return getTrimTextFor(FIRST_NAME_FIELD);
        } catch (Exception e) {
            return getTrimTextFor(FIRST_NAME_FIELD_FR);
        }
    }


    public String LastNameRequiredFields() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(surNameInput_CA));
        getWebDriver().findElement(surNameInput_CA).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        try {
            return getTrimTextFor(LAST_NAME_FIELD);
        } catch (Exception e1) {
            return getTrimTextFor(LAST_NAME_FIELD_FR);
        }
    }

    public void IncorrectEmailFields() {
        getWebDriver().findElement(emailaddress_1).sendKeys(UrlBuilder.getMessage("InValidtEmail.key"));
        getWebDriver().findElement(emailaddress_1).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
    }

    public String getEmailErrorMessage() {
        try {
            return getTrimTextFor(EMAIL_REQUIRED_ERROR_MESSAGE);
        } catch (Exception e) {
            return getTrimTextFor(EMAIL_REQUIRED_ERROR_MESSAGE_FR);
        }
    }

    public String DobErrorMessage() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(DOBInput));
        getWebDriver().findElement(DOBInput).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        try {
            return getTrimTextFor(DOB_ERROR_FIELD);
        } catch (Exception e1) {
            return getTrimTextFor(BOB_ERROR_FIELD_FR);
        }
    }

    public String QickAddressFinders() {
        WebElement button2 = wait.until(ExpectedConditions.elementToBeClickable(quickAddressFinder));
        getWebDriver().findElement(quickAddressFinder).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        try {
            return getTrimTextFor(QUICK_ADDRESS_FINDMESSAGE_ERROR);
        } catch (Exception e) {
            return getTrimTextFor(QUICK_ADDRESS_FINDMESSAGE_ERROR_FR);
        }
    }

    public String TelephonesRequiredFields() {
        WebElement button2 = wait.until(ExpectedConditions.elementToBeClickable(phoneNumber_CA));
        getWebDriver().findElement(phoneNumber_CA).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        try {
            return getTrimTextFor(PHONE_NUMBER_ERROR_MESSAGE);
        } catch (Exception e1) {
            return getTrimTextFor(PHONE_NUMBER_ERROR_MESSAGE_FR);
        }
    }

    public void enterPasswordOnRegistrations() {
        getWebDriver().findElement(password_1).sendKeys(UrlBuilder.getMessage("passwordWithoutRule.key"));
    }

    public String passwordRulesSuggestions() {
        WebElement button2 = wait.until(ExpectedConditions.elementToBeClickable(password_1));
        getWebDriver().findElement(password_1).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        try {
            return waitForExpectedElement(PASSWORD_ERROR_MSSG).getAttribute("innerText").replaceAll("\n", ", ");
        } catch (Exception e1) {
            return waitForExpectedElement(PASSWORD_ERROR_MSSG_fr).getAttribute("innerText").replaceAll("\n", ", ");
        }
    }

    public void validateDifferentsPasswordCombination() {
        waitForExpectedElementToClear(password_1);
        getWebDriver().findElement(password_1).sendKeys(UrlBuilder.getMessage("passwordWithoutRule.key"));
        waitForExpectedElementToClear(password_confirm).sendKeys(UrlBuilder.getMessage("PasswordRule_UpperCaseNumber.key"));
    }

    public String diffPasswordCombinations() {
        try {
            return getTrimTextFor(PASSWORD_ERROR_MSSG_CONFIRM);
        } catch (Exception e3) {
            return getTrimTextFor(PASSWORD_ERROR_MSSG_CONFIRM_FR);
        }
    }

    public void passworddStrengthIndicator() {
        waitForExpectedElement(PASSWORD_TOGGLE_INVISIBLE).isDisplayed();
        waitForExpectedElementToClear(password_1);
        waitForExpectedElement(password_1).sendKeys(UrlBuilder.getMessage("passwordWithRule.key"));
    }

    public void confirmPassword() {
        waitForExpectedElementToClear(password_confirm);
        waitForExpectedElement(password_confirm).sendKeys(UrlBuilder.getMessage("Confirmpassword.key"));
    }

    public void WithoutClickUserAcceptsTCS() {
        WebElement tc = getWebDriver().findElement(By.id("subscriptionConsent"));
        Assert.assertEquals(false, tc.isSelected());
        System.out.println("checkbox is not selected");
        Assert.assertFalse(getWebDriver().findElement(createAccountButton).isEnabled());
        WebElement dropdown = getWebDriver().findElement(whichAppliesToYou);
        Assert.assertEquals(false, tc.isSelected());
        System.out.println("Dropdown  is not selected");
        Assert.assertFalse(getWebDriver().findElement(createAccountButton).isEnabled());
    }

    public void EnterFirstName() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                enterText(firstNameInput_CA, firstNameData);
                scenarioContext.setContext(FIRST_NAME, firstNameData);
                break;
        }
    }

    public void EnterLastName() {
        enterText(surNameInput_CA, lastNameData);
        scenarioContext.setContext(LAST_NAME, lastNameData);
    }

    public void EnterDobField() {
        try {
            webDriver.findElement(DOBInput).sendKeys(UrlBuilder.getMessage("regpageValidDOB.key"));
        } catch (Exception e) {
            enterDOB("regpageValidDOB.key");
        }
    }

    public void TelephoneReqFields() {
        webDriver.findElement(phoneNumber_CA).sendKeys(UrlBuilder.getMessage("validPhoneNo.key"));
    }

    public void QuickAddressFinder(String province, String addressEnteringMethod,String shippingMethod) {
        province = UrlBuilder.PROVINCE;
        String postCode = null;
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                if (addressEnteringMethod.equals("enterManually")) {
                    waitForExpectedElement(enterAddressManully).click();
                    enterText(streetAddress_CA, "12 The Cloisters");
                    enterText(city, townOrCityData);
                     postCode = getPostCode(province, shippingMethod);
                    enterText(postCode_CA, postCode);
                } else {
                    postCode = getPostCode(province, shippingMethod);
                    scrollElementIntoView(quickAddressFinder);
                    enterPostCode(postCode);
                    try {
                        clickByElementByQueryJSExecutor(selectFirstAddress);
                        waitForExpectedElement(selectFirstAddress1, 30);
                        clickByElementByQueryJSExecutor(selectFirstAddress1);
                    } catch (Exception e) {
                        clickByElementByQueryJSExecutor(selectFirstAddress1);
                    }
                    String selectedAddress = getValue(quickAddressFinder);
                    if (selectedAddress.contains(postCode)) {
                        System.out.println("Post code entered correctly");
                        break;
                    } else {
                        System.out.println("Post code not entered correctly");
                    }
                    assertTrueExpectedTextContainsActualText(selectedAddress, postCode);
                }
                break;
        }
    }

    public void clickOnLinksInSignUp(DataTable dtList) {
        List<List<String>> lstLinks = dtList.raw();
        String link = null;
        for (int i = 1; i < lstLinks.size(); i++) {
            link = lstLinks.get(i).get(0);
            switch (link) {
                case "contactUs":
                    waitForExpectedElement(CONTACT_US_SIGNUP).click();
                    threadSleep(1000);
                    switchBetweenWindowTabs(1);
                    commonMethods.assertUrl("contactUsUrlForSignUp.key");
                    commonMethods.assertTitle("helpTitle");
                    break;
                case "privacyPolicy":
                    switchBetweenWindowTabs(0);
                    clickForParticularIndexElement(TERMS_AND_CONDITIONS_PRIVACY_SIGNUP, 0);
                    threadSleep(1000);
                    switchBetweenWindowTabs(2);
                    commonMethods.assertUrl("privacyPolicyUrlSignUp.key");
                    commonMethods.assertTitle("privacyPolicyTitle");
                    break;
                case "terms&Conditions":
                    switchBetweenWindowTabs(0);
                    clickForParticularIndexElement(TERMS_AND_CONDITIONS_PRIVACY_SIGNUP, 1);
                    threadSleep(1000);
                    switchBetweenWindowTabs(3);
                    commonMethods.assertUrl("terms&ConditionsUrlSignUp.key");
                    commonMethods.assertTitle("terms&ConditionsTitle");
                    break;
            }
        }
    }

    public void validateMyAccountUrl() {
        try {
            waitForURLToContain(UrlBuilder.getMessage("myAccountLink.key"));
        } catch (Exception e) {
            try {
                clickOnCreateAccountButton();
                waitForURLToContain(UrlBuilder.getMessage("myAccountLink.key"));
            } catch (Exception e1) {
                refreshBrowser();
                waitForURLToContain(UrlBuilder.getMessage("myAccountLink.key"));
            }
        }
        if(Props.PERCY_ON){
            LOG.info("Percy On");
            percy.snapshot("My Account Page");
        }
    }

    public void createAccountWithAPI() {
        String createCustomerCreationQuery = payloads.createAccountPayload();
        String url = null;
        if(Props.getTestSuite().equals("Live")){
            url = "https://www." + UrlBuilder.SITE + ".ca/ca/" + getProvinceCode().toLowerCase() + "/" + UrlBuilder.LANGUAGE + "/shop/graphql";
            LOG.info(url);
        } else {
            url = "https://www-" + "ca-" + UrlBuilder.ENVIRONMENT + "-canada-" + UrlBuilder.SITE + ".non-prod.marketing.bat.net/" + "ca/" + getProvinceCode().toLowerCase() + "/" + UrlBuilder.LANGUAGE + "/shop/graphql";
        }

        given().log().all()
                .contentType("application/json")
                .body(createCustomerCreationQuery)
                .when().log().all()
                .post(url)
                .then().log().all()
                .cookie("M2SESSID", notNullValue())
                .body(notNullValue())
                .statusCode(200);
    }

    public void enterRegistrationDetailsZonnicNonWhitelistedEmal(String emailType, String province, String addressEnteringMethod, String shippingMethod) {
        enterFirstNameAndLastName();
        enterEmailAddressNonwhitelistedEmailFormat(emailType);
        populateAddressFieldsZonnic(province, addressEnteringMethod, shippingMethod);
        try {
            webDriver.findElement(DOBInput).sendKeys(UrlBuilder.getMessage("regpageValidDOB.key"));
        } catch (Exception e) {
            enterDOB("regpageValidDOB.key");
        }
        webDriver.findElement(phoneNumber_CA).sendKeys(UrlBuilder.getMessage("validPhoneNo.key"));
        scenarioContext.setContext(TELEPHONE_NUMBER_FROM_CREATE_ACCOUNT, getValue(phoneNumber_CA));
    }

    public void enterEmailAddressNonwhitelistedEmailFormat(String emailType) {
        String emailAddressData = firstNameData.toLowerCase() + "@gmail.com";;
        String passwordText = this.passwordText;
        scenarioContext.setContext(EMAIL_ID, emailAddressData);
        scenarioContext.setContext(PASSWORD, passwordText);
        LOG.info(emailAddressData);

        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                try {
                    webDriver.findElements(emailaddress_CA).get(1).sendKeys(emailAddressData);
                } catch (Exception e) {
                    enterText(emailaddress_CA, emailAddressData);
                }
                break;
        }
    }

    public void validateErrorMessageInAccountCreation(String error) {
        String expectedText;
        switch (error) {
            case "errorMessage1":
                waitForExpectedElement(ERROR_IN_SIGNUP,100000);
                expectedText = UrlBuilder.getMessage("registrationError1" + "-" + UrlBuilder.LANGUAGE);
                assertTrueExpectedTextEqualsActualText(expectedText, getTrimTextFor(ERROR_IN_SIGNUP));
                break;
            case "errorMessage2":
                waitForExpectedElement(ERROR_IN_SIGNUP,100000);
                expectedText = UrlBuilder.getMessage("registrationError2" + "-" + UrlBuilder.LANGUAGE);
                assertTrueExpectedTextEqualsActualText(expectedText, getTrimTextFor(ERROR_IN_SIGNUP));
                break;
        }
    }
}