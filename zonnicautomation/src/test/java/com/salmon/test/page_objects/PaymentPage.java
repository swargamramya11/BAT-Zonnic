package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.constants.Context.SHIPPING_METHOD;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentPage extends PageObject {

    private AccountDashboardPage accountDashboardPage;
    private HomePage homePage;
    private AddNewAddressPage addNewAddressPage;
    private OrderSuccessPage orderSuccessPage;
    private ScenarioContext scenarioContext;
    private BasketPage basketPage;
    private PLP plp;
    private RegistrationPage registrationPage;
    private PDP pdp;
    private ShippingPage shippingPage;
    private CommonMethods commonMethods;
    private PercyPage percyPage;
    String language=UrlBuilder.LANGUAGE;

    public PaymentPage(AccountDashboardPage accountDashboardPage,
                       AddNewAddressPage addNewAddressPage,
                       HomePage homePage,
                       ScenarioContext scenarioContext,
                       BasketPage basketPage,
                       PLP plp, OrderSuccessPage orderSuccessPage, PDP pdp, ShippingPage shippingPage,
                       RegistrationPage registrationPage, CommonMethods commonMethods,
                       PercyPage percyPage) {
        this.accountDashboardPage = accountDashboardPage;
        this.addNewAddressPage = addNewAddressPage;
        this.homePage = homePage;
        this.scenarioContext = scenarioContext;
        this.orderSuccessPage = orderSuccessPage;
        this.basketPage = basketPage;
        this.plp = plp;
        this.registrationPage = registrationPage;
        this.pdp = pdp;
        this.shippingPage = shippingPage;
        this.commonMethods = commonMethods;
        this.percyPage = percyPage;
    }

    private static final Logger LOG = LoggerFactory.getLogger(PaymentPage.class);
    private By creditCardVerificationNumberField_CA = By.cssSelector("#cardCVV");
    public final static By TERMSANDCONDITIONSCHECKBOX = By.cssSelector(".bat-form-field-set.required #paymentTos");
    public final static By TERMSANDCONDITIONSCHECKBOX1 = By.cssSelector("(//input[@id='paymentTos'])[2]");
    public final static By LOADER = By.xpath("//div[@class='loading-mask' and @style='display: none;']//div[@class='loader']");
    public By CAcreditCardNumberField = By.cssSelector("#ccNumber");
    private final static By loader = By.cssSelector("div.loader img");
    private By yearAndMonth = By.cssSelector("#cardExpirationDate");
    public static final By PLACE_ORDER_CA = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-submit button.bat-button.bat-button--dark.bat-button--secondary");
    public static final By PLACE_ORDER_BUTTON_TEXT = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-submit button.bat-button.bat-button--dark.bat-button--secondary span");
    public static final By PLP_CA = By.cssSelector(".bat-navigation-group-list-item a[href*='pouches']");
    public static final By creditCardHolderNameField_CA = By.cssSelector("#cardHoldersName");
    public static final By creditCardNumberField_Error_CA = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc .bat-message.bat-form-msg.error-msg.error");
    public static final By creditCardHolderNameField_Error_CA = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-card-holders-name .bat-message.bat-form-msg.error-msg.error");
    public static final By creditExpiryDateField_Error_CA = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-card-expiration-date .bat-message.bat-form-msg.error-msg.error");
    public static final By creditCVV_Error_CA = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-card-cvv .bat-message.bat-form-msg.error-msg.error");
    public static final By SAVED_CARD_CHECKBOX = By.cssSelector("#saveCard");
    private static final By SAVED_CARD_BUTTON = By.cssSelector(".checkout-payment-cc-vault-toggle.toggle-payment-button");
    private static final By SAVED_CARD_RADIO_BUTTON = By.cssSelector("#CCVaultOption_0");
    private static final By CVV_SAVED_CARD = By.cssSelector("#CCVaultCardCVV");
    private static final By PLACE_ORDER_SAVED_CARD = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-vault-submit button.bat-button.bat-button--dark.bat-button--secondary");
    private static final By PLACE_ORDER_SAVED_CARD_BUTTON_TEXT = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-vault-submit button.bat-button.bat-button--dark.bat-button--secondary span");
    public void waitForLoaderToDisapear() {
        waitForElementToDisappear(loader, 40);
    }

    public void enterCreditCardVerificationNumber() {
        if (UrlBuilder.getLocale().contains("zonnicca")) {
            waitClearAndEnterText(creditCardVerificationNumberField_CA, "123");
        }
    }

    public void enterCVVNumberForSavedCard() {
        if (UrlBuilder.getLocale().contains("zonnicca")) {
            waitClearAndEnterText(CVV_SAVED_CARD, "123");
        }
    }

    private void enterCardExpiryMonthAndyear() {
        if (UrlBuilder.getLocale().contains("zonnicca")) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR) + 2;
            String expiryNumber = 11 + (Integer.toString(year));
            waitForExpectedElement(yearAndMonth).sendKeys(expiryNumber);
        }
    }

    private void enterCustomCreditCardNumber(String creditCardNumber) {
        if (UrlBuilder.getLocale().contains("zonnicca")) {
            waitForExpectedElement(CAcreditCardNumberField).sendKeys(creditCardNumber);
        }
    }

    private void enterCardHolderName(String creditCardHolderName) {
        if (UrlBuilder.getLocale().equals("zonnicca")) {
            waitForExpectedElement(creditCardHolderNameField_CA).sendKeys(creditCardHolderName);
        }
    }

    public void navigateToPlp() {
        if (UrlBuilder.isDesktop()) {
            switch (UrlBuilder.getLocale()) {
                case "zonnicca":
                    waitAndClickByElementByJSExecutor(PLP_CA, 3);
                    percyPage.takePercyScreenshot("PLP");
                    break;
            }
        }
    }

    public void selectShippingMethod(String shippingMethod) {
        String shippingMethod1=UrlBuilder.getMessage(shippingMethod+"-"+UrlBuilder.LANGUAGE);
        scenarioContext.setContext(SHIPPING_METHOD, shippingMethod1);
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                SimpleDateFormat formatter=new SimpleDateFormat("hh:mm a");
                formatter.setTimeZone(TimeZone.getTimeZone("EST"));
                String time=formatter.format(new Date());
                System.out.println(time);
                LocalTime currentTimeIn24HourFormat_In_CA=LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a"));
                currentTimeIn24HourFormat_In_CA=currentTimeIn24HourFormat_In_CA.plusMinutes(60);
                LocalTime actualTime=currentTimeIn24HourFormat_In_CA.plusMinutes(0);

                if (UrlBuilder.PROVINCE.equalsIgnoreCase("Alberta")) {
                    actualTime = currentTimeIn24HourFormat_In_CA.minusMinutes(120);
                } else if (UrlBuilder.PROVINCE.equalsIgnoreCase("Manitoba") ||
                        UrlBuilder.PROVINCE.equalsIgnoreCase("Saskatchewan")) {
                    actualTime = currentTimeIn24HourFormat_In_CA.minusMinutes(60);
                } else if (UrlBuilder.PROVINCE.equalsIgnoreCase("British Columbia")) {
                    actualTime = currentTimeIn24HourFormat_In_CA.minusMinutes(180);
                } else if (UrlBuilder.PROVINCE.equalsIgnoreCase("New Brunswick") ||
                        UrlBuilder.PROVINCE.equalsIgnoreCase("Nova Scotia") ||
                        UrlBuilder.PROVINCE.equalsIgnoreCase("Newfoundland and Labrador") ||
                        UrlBuilder.PROVINCE.equalsIgnoreCase("Prince Edward Island")) {
                    actualTime = currentTimeIn24HourFormat_In_CA.plusMinutes(60);
                } else if (UrlBuilder.PROVINCE.equalsIgnoreCase("Ontario") ||
                        UrlBuilder.PROVINCE.equalsIgnoreCase("Quebec")) {
                    actualTime = currentTimeIn24HourFormat_In_CA.minusMinutes(0);
                }

                System.out.println("actualTime "+actualTime);

//Expected time for comparision
                LocalTime limit=LocalTime.parse("11:00 AM",DateTimeFormatter.ofPattern("hh:mm a"));
                LocalTime limitFedex=LocalTime.parse("11:00 AM",DateTimeFormatter.ofPattern("hh:mm a"));
                Boolean isAfter=actualTime.isAfter(limit);
                Boolean isAfterFedex=actualTime.isAfter(limitFedex);

                if (shippingMethod1.contains("SAME DAY DELIVERY")
                        || shippingMethod1.equalsIgnoreCase("NEXT DAY DELIVERY")
                        || shippingMethod1.equalsIgnoreCase("LIVRAISON LE JOUR SUIVANT")) {
                    if (isAfter) {
                        if(UrlBuilder.LANGUAGE.equalsIgnoreCase("EN")) {
                            shippingMethod1 = "NEXT DAY DELIVERY";
                        }else{
                            shippingMethod1 = "LIVRAISON LE JOUR SUIVANT";
                        }
                    } else {
                        shippingMethod1 = "SAME DAY DELIVERY";
                    }
                } else if (shippingMethod1.contains("FEDEX NEXT DAY")) {
                    if (isAfterFedex) {
                        shippingMethod1 = "FEDEX STANDARD";
                    } else {
                        shippingMethod1 = "FEDEX NEXT DAY DELIVERY";
                    }
                }

                By PAYMENT_OPTION = By.xpath("//label[@class='checkout-shipping-method-options-option']//span[@class='checkout-shipping-method-options-option-title' and contains(text(),'"+shippingMethod1+"')]");
                try {
                    clickByElementByQueryJSExecutor(PAYMENT_OPTION);
                }catch (Exception e){
                    refreshBrowser();
                    clickByElementByQueryJSExecutor(PAYMENT_OPTION);
                }
                threadSleep(3000);
                break;
        }
    }

    public void enterValidMasterCardDetailsAndSubmit(String cardType) {
        enterCardHolderName(random(8, ALPHABETS));
        enterCustomCreditCardNumber(UrlBuilder.getMessage(cardType));
        enterCardExpiryMonthAndyear();
        enterCreditCardVerificationNumber();
    }

    public void clickPlaceOrder(String savedCardOrWithoutSaved) {
        if(savedCardOrWithoutSaved.equals("newCard")) {
            commonMethods.assertButtonText(getTrimTextFor(PLACE_ORDER_BUTTON_TEXT), "placeYourOrderButtonText");
            clickByElementByQueryJSExecutor(PLACE_ORDER_CA);
            waitForAjaxElementNotToBePresent(webDriver, 10);
        } else if(savedCardOrWithoutSaved.equals("savedCard")) {
            commonMethods.assertButtonText(getTrimTextFor(PLACE_ORDER_SAVED_CARD_BUTTON_TEXT), "placeYourOrderButtonText");
            clickByElementByQueryJSExecutor(PLACE_ORDER_SAVED_CARD);
            waitForAjaxElementNotToBePresent(webDriver, 10);
        }
    }

    public void savedCard() {
        clickByElementByQueryJSExecutor(SAVED_CARD_CHECKBOX);
    }

    public void selectSavedCard() {
        waitForExpectedElement(SAVED_CARD_BUTTON).click();
        waitForExpectedElement(SAVED_CARD_RADIO_BUTTON).click();
        enterCVVNumberForSavedCard();
    }

    public void selectTermsAndConditionsCheckbox() {
        try {
            clickByElementByQueryJSExecutor(TERMSANDCONDITIONSCHECKBOX1);
        }catch (Exception e){
            try {
                webDriver.findElements(TERMSANDCONDITIONSCHECKBOX).get(1).click();
            }catch (Exception e1){
                try {
                    webDriver.findElements(TERMSANDCONDITIONSCHECKBOX).get(2).click();
                }catch (Exception e2){
                    webDriver.findElements(TERMSANDCONDITIONSCHECKBOX).get(0).click();
                }
            }
        }
    }

    public void validateErrorMessageOfCardNumberField(){
        waitForExpectedElement(CAcreditCardNumberField).sendKeys("a");
        clickBackSpaceKeyboard(CAcreditCardNumberField);
        waitForExpectedElement(CAcreditCardNumberField).click();
        String key="cardErrorMessage"+"-"+language;
        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(key),getTrimTextFor(creditCardNumberField_Error_CA));
    }

    public void validateErrorMessageOfNameField(){
        waitForExpectedElement(creditCardHolderNameField_CA).sendKeys("1");
        clickBackSpaceKeyboard(creditCardHolderNameField_CA);
        waitForExpectedElement(creditCardHolderNameField_CA).click();
        String key="nameErrorMessage"+"-"+language;
        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(key),getTrimTextFor(creditCardHolderNameField_Error_CA));
    }

    public void validateErrorMessageOfExiryDateField(){
        waitForExpectedElement(yearAndMonth).sendKeys("1");
        clickBackSpaceKeyboard(yearAndMonth);
        waitForExpectedElement(yearAndMonth).click();
        String key="expiryDateErrorMessage"+"-"+language;
        assertThat(UrlBuilder.getMessage(key).contains(getTrimTextFor(creditExpiryDateField_Error_CA)));
        waitForExpectedElement(yearAndMonth).sendKeys("091998");
        String key1="expiryDateErrorMessageByEnteringPastDate"+"-"+language;
        assertThat(UrlBuilder.getMessage(key1).contains(getTrimTextFor(creditExpiryDateField_Error_CA)));
    }

    public void validateErrorMessageOfCVVField(){
        waitForExpectedElement(creditCardVerificationNumberField_CA).sendKeys("1");
        clickBackSpaceKeyboard(creditCardVerificationNumberField_CA);
        waitForExpectedElement(creditCardVerificationNumberField_CA).click();
        String key="cvvErrorMessage"+"-"+language;
        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(key),getTrimTextFor(creditCVV_Error_CA));
    }
}