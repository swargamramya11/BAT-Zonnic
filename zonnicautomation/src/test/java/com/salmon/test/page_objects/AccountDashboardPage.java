package com.salmon.test.page_objects;

import com.salmon.test.enums.PercyCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.constants.Context;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import org.openqa.selenium.*;
import java.text.DecimalFormat;
import java.util.List;
import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.constants.Context.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class AccountDashboardPage extends PageObject {
    private ScenarioContext scenarioContext;
    private CommonMethods commonMethods;
    private LogininPage logininPage;
    private CheckoutPage checkoutPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private PercyPage percyPage;
    public AccountDashboardPage(PercyPage percyPage, LogininPage logininPage,
                                ForgotPasswordPage forgotPasswordPage, ScenarioContext scenarioContext,
                                CommonMethods commonMethods, CheckoutPage checkoutPage,
                                RegistrationPage registrationPage) {
        this.scenarioContext = scenarioContext;
        this.commonMethods = commonMethods;
        this.checkoutPage = checkoutPage;
        this.registrationPage = registrationPage;
        this.forgotPasswordPage = forgotPasswordPage;
        this.logininPage = logininPage;
        this.percyPage = percyPage;
    }

    public static final By ORDER_NUMBER_ORDER_HISTORY = By.cssSelector("#orderHistory td");
    public static final By ORDER_DETAILS_BUTTON = By.cssSelector("#orderHistory .bat-cta-style.button-secondary-dark.center");
    public static final By ORDER_NUMBER_DETAILS = By.cssSelector("#orderDetails p");
    public static final By PRODUCTS_COUNT = By.cssSelector(".account-ordered-items .product-ordered");
    public static final By MY_ACCOUNT_LINK = By.cssSelector(".bat-header-account-icon-label-auth");
    public static final By MY_ACCOUNT_LINK2 = By.cssSelector(".bat-cta-style.bat-header-account-link");
    public static final By ORDER_HISTORY_EMPTY = By.cssSelector("#noOrderHistory .no-orders-message p");
    public static final By BUY_ALL_AGAIN_BUTTON = By.cssSelector("#buy-again");
    public static final By BUY_ALL_AGAIN_BUTTON_TEXT = By.cssSelector("#buy-again span");
    public static final By HEADERS = By.cssSelector(".bat-section.bat-section-style.fit.container-fluid div div h1");
    public static final By MARKETING_CHECKBOX = By.cssSelector("#isSubscribedCB");
    public static final By SUCCESS_MESSAGE = By.cssSelector(".bat-messagebar--zonnic-message.active");
    public static final By MARKETING_SAVE_BUTTON = By.cssSelector(".bat-form-field.bat-form--marketing-prefs-submit .bat-button.bat-button--dark.bat-button--secondary");
    public static final By MARKETING_SAVE_BUTTON_TEXT = By.cssSelector(".bat-form-field.bat-form--marketing-prefs-submit .bat-button.bat-button--dark.bat-button--secondary span");
    public static final By HEADERS_ACCOUNT_OVERVIEW = By.cssSelector("h2.bat-headline-style.headline2-color2");
    public static final By EMAIL_OVERVIEW_PAGE = By.cssSelector(".cta-account-email");
    public static final By FIST_LAST_NAME = By.cssSelector("#accountDetails p");
    public static final By SHIPPING_ADDRESS = By.cssSelector(".addressbook__address__label.addressbook__address__label_overview");
    public static final By WELCOME = By.cssSelector("h4#accountOverview");
    public static final By EDITDETAILS_VIEWORDERHISTORY = By.xpath("//div[@class='bat-cta bat-cta-list--horizontal']//a[@class='bat-cta-style button-dark']");
    public static final By EDITDETAILS_VIEWORDERHISTORY_BUTTON_TEXT = By.xpath("//div[@class='bat-cta bat-cta-list--horizontal']//a[@class='bat-cta-style button-dark']//span");
    public static final By EDITDETAILS_CHANGEPASSWORD = By.xpath("//div[@class='bat-cta bat-cta-list--horizontal']//a[@class='bat-cta-style button-secondary-dark']");
    public static final By EDITDETAILS_CHANGEPASSWORD_TEXT = By.xpath("//div[@class='bat-cta bat-cta-list--horizontal']//a[@class='bat-cta-style button-secondary-dark']//span");
    public static final By EDIT_ADDRESS_OVERVIEWPAGE = By.cssSelector(".magentoaddressbook .bat-cta-style.button-dark.center");
    public static final By EDIT_ADDRESS_OVERVIEWPAGE_TEXT = By.cssSelector(".magentoaddressbook .bat-cta-style.button-dark.center span");
    public static final By ACCOUNT_OVERVIEW_HEADER = By.cssSelector(".account-overview h1");
    public static final By EDIT_CHANGE_PASSWORD = By.cssSelector(".bat-form-field.bat-form--my-details-current-password #edit-current-password");
    public static final By CURRENT_PASSWORD = By.cssSelector("#currentPassword");
    public static final By NEW_PASSWORD = By.cssSelector("#newPassword");
    public static final By CONFIRM_PASSWORD = By.cssSelector("#confirmNewPassword");
    public static final By ERROR_NEW_PASSWORD = By.cssSelector(".bat-form-field.bat-form--my-details-new-password.active .bat-message.bat-form-msg.error-msg.error");
    public static final By ERROR_CURRENT_PASSWORD = By.cssSelector(".bat-form-field.bat-form--my-details-current-password .bat-message.bat-form-msg.error-msg.error");
    public static final By ERROR_CONFIRM_PASSWORD = By.cssSelector(".bat-form-field.bat-form--my-details-confirm-new-password.active .bat-message.bat-form-msg.error-msg.error");
    public static final By SAVE_CHANGES_CHANGE_PASSWORD = By.cssSelector(".bat-form-field.bat-form--my-details-submit button");
    public static final By SAVE_CHANGES_CHANGE_PASSWORD_TEXT = By.cssSelector(".bat-form-field.bat-form--my-details-submit button span");
    public static final By MY_DETAILS_FIRSTNAME = By.cssSelector(".bat-form.bat-form--my-details #step1Form #firstName");
    public static final By MY_DETAILS_LASTNAME = By.cssSelector(".bat-form.bat-form--my-details #step1Form #lastName");
    public static final By MY_DETAILS_EMAIL = By.cssSelector(".bat-form.bat-form--my-details #step1Form #email");
    public static final By MY_DETAILS_FIRSTNAME_HEADER = By.cssSelector(".bat-form-field.bat-form--my-details-first-name label");
    public static final By MY_DETAILS_LASTNAME_HEADER = By.cssSelector(".bat-form-field.bat-form--my-details-last-name label");
    public static final By MY_DETAILS_EMAIL_HEADER = By.cssSelector(".bat-form-field.bat-form--my-details-email label");
    public static final By MY_DETAILS_PASSWORD_HEADER = By.cssSelector(".bat-form-field.bat-form--my-details-current-password label");
    public static final By SHIPPING_ADDRESS_ADDRESS_BOOK = By.cssSelector(".addressbook__address__label");
    public static final By SHIPPING_ADDRESS_ADDRESS_BOOK_TEXT = By.cssSelector(".addressbook__address__container .addressbook__address");
    public static final By OTHER_ADDRESS_HEADER = By.cssSelector(".bat-headline-style.headline2-color2");
    public static final By ADD_ADDRESS_BUTTON = By.cssSelector(".bat-cta.bat-cta-list--vertical .bat-cta-style.button-dark");
    public static final By ADD_ADDRESS_BUTTON_TEXT = By.cssSelector(".bat-cta.bat-cta-list--vertical .bat-cta-style.button-dark span");
    public static final By FIRSTNAME = By.cssSelector("#firstname");
    public static final By LASTNAME = By.cssSelector("#lastname");
    public static final By SAVE_ADDRESS = By.cssSelector(".bat-form-field.bat-form--edit-address-submit .bat-button.bat-button--dark.bat-button--secondary");
    public static final By DEFAULT_SHIPPING_ADDRESS_CHECKBOX = By.cssSelector("#default_shipping");
    public static final By FIRSTANDLASTNAME_DEFAULT_SHIPPING_ADDRESS = By.cssSelector(".addressbook__address p");
    public static final By FIRSTANDLASTNAME_OTHER_ADDRESS = By.xpath("(//div[@class='addressbook__grid'])[2]//div[@class='addressbook__address']//p");
    public static final By DEFAULT_SHIPPING_ADDRESS = By.cssSelector(".addressbook__address .addressbook__address__makeDefaultShipping");
    public static final By FIRSTANDLASTNAME_DEFAULT_BILLING_ADDRESS = By.xpath("(//div[@class='addressbook__address__container'])[2]//div[@class='addressbook__address']//p");
    public static final By DELETE_ADDRESS_IN_OTHER_ADDRESS = By.xpath("//div[@class='addressbook__address__edit-buttons']//a[@title='Delete Address']");
    public static final By ERROR_MESSAGES = By.cssSelector(".bat-message.bat-form-msg.error-msg.error");
    static final By PERSON_ICON_ZONNIC_CA = By.cssSelector(".bat-header-account-link button");
    public By FORGOT_PASSWORD_LINK = By.cssSelector(".bat-form-field.bat-form--login-forgot-password a");
    public By HOME_BUTTON_ORDER_HISTORY = By.cssSelector(".no-orders-message .bat-cta-style.button-dark.center");
    public By ORDER_DETAILS_HEADERS = By.cssSelector("#orderDetails span");
    public By INVALID_LOGIN_ERROR = By.cssSelector(".bat-message.login-error-message.error.active p");
    public By ERROR_MESSAGE_EMPTY_FIELD = By.cssSelector(".bat-message.bat-form-msg.error-msg.error p");
    public By ERROR_MESSAGE_IN_GUEST = By.cssSelector(".bat-messagebar--zonnic-message-error.active p");
    public By SHIPPING_METHOD_CA = By.cssSelector("#orderInformation div:nth-child(2) p");
    public By PAYMENT_METHOD = By.cssSelector("#orderInformation div:nth-child(4) p");
    public By WRONG_CURRENT_PASSWORD = By.cssSelector(".bat-messagebar--zonnic-message-error.active");
    public By PREVIOUSLY_USED_ADDRESSES_HEADER = By.cssSelector(".bat-form-block.previously-used-addresses.active .bat-form-block-text");
    public By SIGNUP_NOW_BUTTON = By.cssSelector(".bat-cta.bat-cta-list--horizontal button");
    public By SIGNUP_NOW_BUTTON_TEXT = By.cssSelector(".bat-cta.bat-cta-list--horizontal button span");
    public By SIGNUP_NOW_BUTTON_LOGGEDIN = By.cssSelector(".aem-Grid.aem-Grid--4.aem-Grid--tablet--4.aem-Grid--default--4.aem-Grid--phone--12 .bat-cta.bat-cta-list--horizontal a");
    public By SIGNUP_NOW_BUTTON_LOGGEDIN_PROD = By.cssSelector(".bat-cta.bat-cta-list--horizontal .bat-cta-style.button-secondary-dark.center");

    public void gotoMyAccountSubPage(PercyCheckpoints subPage) {
        switch (subPage) {
            case ACCOUNT_MY_DETAILS_PAGE:
                usersClicksOnTheLinkByText("myDetails", "myDetailsLink.key", "myDetailsHeader", "myDetailsTitle");
                break;
            case ACCOUNT_ADDRESS_BOOK_PAGE:
                usersClicksOnTheLinkByText("addressBook", "addressBookLink.key", "addressHeader1", "addressBookTitle");
                break;
            case ACCOUNT_ORDER_HISTORY_PAGE:
                usersClicksOnTheLinkByText("orderHistory", "orderHistoryLink.key", "orderHistoryHeader", "orderHistoryTitle");
                break;
            case MARKETING_PREFERENCE_PAGE:
                usersClicksOnTheLinkByText("marketingPreference", "marketingPreferenceLink.key", "marketPreferenceHeader", "marketingPreferenceTitle");
                break;
            case ACCOUNT_ADD_A_NEW_ADDRESS_PAGE:
                clickAddNewAddressButton("addNewAddress");
                percyPage.takePercyScreenshot("Add new Address Page");
                addNewAddressInAddressBooks("addAddressWithDefaultShippingAddressChecked");
                break;
            case ACCOUNT_EDIT_A_ADDRESS_PAGE:
                clickAddNewAddressButton("editAddress");
                percyPage.takePercyScreenshot("Edit Address Page");
                break;
        }
    }

    public void orderVerification() {
        String ordernumber = getTextFor(ORDER_NUMBER_ORDER_HISTORY);
        String expectedOrderNumber = (String) scenarioContext.getContext(ORDER_NUMBER);
        assertTrueExpectedTextEqualsActualText(expectedOrderNumber, ordernumber);
    }

    public void usersClicksOnTheLinkByText(String linkText, String link, String header, String title) {
        String text = UrlBuilder.getMessage(linkText + "-" + UrlBuilder.LANGUAGE);
        String header1 = UrlBuilder.getMessage(header + "-" + UrlBuilder.LANGUAGE);
        try {
            clickByElementByQueryJSExecutor(By.xpath("//div[@id='accountNav']//a//span[contains(text(),'" + text + "')]"));
        } catch (Exception e) {
            refreshBrowser();
            clickByElementByQueryJSExecutor(By.xpath("//div[@id='accountNav']//a//span[contains(text(),'" + text + "')]"));
        }
        threadSleep(2000);
        commonMethods.assertUrl(link);
        commonMethods.assertTitle(title);
        if(linkText.equals("signOut")) {
            commonMethods.assertFooterTitle("homePageFooterTitle");
        }else{
            commonMethods.assertFooterTitle(title);
        }
        if (text.contains("Sign Out") || text.contains("connecter")) {
        } else {
            assertTrueExpectedTextEqualsActualText(header1, getTrimTextFor(HEADERS));
        }
    }

    public void validatePriceDetailsInOrderDetailsPage(String withOrWithoutCoupon) {
        DecimalFormat df = new DecimalFormat("#.##");
        String pstGstHst3 = " ";
        List<WebElement> we = webDriver.findElements(By.xpath("//div[@id='totalsAndTaxes']//p"));
        for (int i = 1; i <= we.size(); i++) {
            String taxType = webDriver.findElement(By.xpath("(//div[@id='totalsAndTaxes']//p)[" + i + "]")).getText();
            String tax = webDriver.findElement(By.xpath("(//div[@id='totalsAndTaxes']//p//span)[" + i + "]")).getText();
            String pstGstHst = tax.replace('$', ' ').trim();
            String pstGstHst1;
            try {
                pstGstHst1 = df.format(stringToDoubleConversion(pstGstHst));
            } catch (Exception e) {
                pstGstHst1 = pstGstHst.replace('-', ' ').trim();
            }
            String pstGstHst4 = " ";
            if (taxType.contains("GST")) {
                String expectedGst = (String) scenarioContext.getContext(GST);
                Double expectedGst1 = stringToDoubleConversion(expectedGst);
                Double pstGstHst2 = stringToDoubleConversion(pstGstHst1);
                assertThat(pstGstHst2).isIn(expectedGst1 + 0.01, expectedGst1 - 0.01, expectedGst1);
                pstGstHst4 = "GST";
            } else if (taxType.contains("HST")) {
                String expectedHst = (String) scenarioContext.getContext(HST);
                Double expectedHst1 = stringToDoubleConversion(expectedHst);
                Double pstGstHst2 = stringToDoubleConversion(pstGstHst1);
                assertThat(pstGstHst2).isIn(expectedHst1 + 0.01, expectedHst1 - 0.01, expectedHst1);
                pstGstHst4 = "HST";
            } else if (taxType.contains("PST")) {
                String expectedPst = (String) scenarioContext.getContext(PST);
                Double expectedPst1 = stringToDoubleConversion(expectedPst);
                Double pstGstPst2 = stringToDoubleConversion(pstGstHst1);
                assertThat(pstGstPst2).isIn(expectedPst1 + 0.01, expectedPst1 - 0.01, expectedPst1);
                pstGstHst4 = "PST";
            } else if (taxType.contains("SUBTOTAL")) {
                String subTotal = tax.replace('$', ' ').trim();
                String expectedSubTotal = (String) scenarioContext.getContext(SUBTOTAL);
                assertTrueExpectedTextEqualsActualText(expectedSubTotal, subTotal);
                pstGstHst4 = "SUBTOTAL";
            } else if (taxType.contains("SHIPPING")) {
                String expectedshipping = (String) scenarioContext.getContext(ESTIMATEDSHIPPINGCOST);
                assertTrueExpectedTextContainsActualText(pstGstHst1, expectedshipping);
                pstGstHst4 = "SHIPPING";
            } else if (taxType.contains("TOTAL")) {
                String expectedTotal = "0.00";

                if (scenarioContext.getContext(TOTALCOSTWITHSHIPPINGCOST) == null) {
                    expectedTotal = (String) scenarioContext.getContext(TOTALCOSTWITHSHIPPINGCOSTFORGUEST);
                } else {
                    expectedTotal = (String) scenarioContext.getContext(TOTALCOSTWITHSHIPPINGCOST);
                }
                assertTrueExpectedTextContainsActualText(pstGstHst1,expectedTotal);
                pstGstHst4 = "GRAND TOTAL";
            } else if (taxType.contains("ZONNIC_10_OFF_DESC")) {
                pstGstHst4 = "ZONNIC_10_OFF_DESC";
            }
            pstGstHst3 = pstGstHst3 + pstGstHst4;
        }
        if (withOrWithoutCoupon.equals("coupon")) {
            assertTrueExpectedTextContainsActualText("ZONNIC_10_OFF_DESC", pstGstHst3);
        }
        validateFields(pstGstHst3);
    }

    public void validateFields(String pstGstHst3) {
        if (UrlBuilder.getProvince().contains("Alberta")) {
            assertThat(pstGstHst3.contains("GST"));
            assertThat(pstGstHst3.contains("SUBTOTAL"));
            assertThat(pstGstHst3.contains("SHIPPING"));
            assertThat(pstGstHst3.contains("GRAND TOTAL"));
        } else if (UrlBuilder.getProvince().contains("British Columbia") || UrlBuilder.getProvince().contains("Manitoba")) {
            assertThat(pstGstHst3.contains("GST"));
            assertThat(pstGstHst3.contains("PST"));
            assertThat(pstGstHst3.contains("SUBTOTAL"));
            assertThat(pstGstHst3.contains("SHIPPING"));
            assertThat(pstGstHst3.contains("GRAND TOTAL"));
        } else if (UrlBuilder.getProvince().contains("New Brunswick") ||
                UrlBuilder.getProvince().contains("Newfoundland and Labrador") ||
                UrlBuilder.getProvince().contains("Nova Scotia") ||
                UrlBuilder.getProvince().contains("Prince Edward Island")) {
            assertThat(pstGstHst3.contains("HST"));
            assertThat(pstGstHst3.contains("SUBTOTAL"));
            assertThat(pstGstHst3.contains("SHIPPING"));
            assertThat(pstGstHst3.contains("GRAND TOTAL"));
        } else if (UrlBuilder.getProvince().contains("Ontario")) {
            assertThat(pstGstHst3.contains("HST"));
            assertThat(pstGstHst3.contains("SUBTOTAL"));
            assertThat(pstGstHst3.contains("SHIPPING"));
            assertThat(pstGstHst3.contains("GRAND TOTAL"));
        } else if (UrlBuilder.getProvince().contains("Saskatchewan")) {
            assertThat(pstGstHst3.contains("GST"));
            assertThat(pstGstHst3.contains("PST"));
            assertThat(pstGstHst3.contains("SUBTOTAL"));
            assertThat(pstGstHst3.contains("SHIPPING"));
            assertThat(pstGstHst3.contains("GRAND TOTAL"));
        }
    }

    public void navigateToMyAccountPage(String link) {
        switch (link) {
            case "myAccountLink":
                commonMethods.assertButtonText(getTrimTextFor(MY_ACCOUNT_LINK), "myAccountButtonTextInHeader");
                waitForExpectedElement(MY_ACCOUNT_LINK).click();
                clickForParticularIndexElement(MY_ACCOUNT_LINK2, 0);
                commonMethods.assertTitle("myAccountTitle");
                commonMethods.assertFooterTitle("myAccountTitle");
                break;
            case "signOutLink":
                waitForExpectedElement(MY_ACCOUNT_LINK).click();
                clickForParticularIndexElement(MY_ACCOUNT_LINK2, 1);
                threadSleep(3000);
                String url = (String) scenarioContext.getContext(URL);
                String expectedUrl = getCurrentUrl();
                assertTrueExpectedTextEqualsActualText(expectedUrl, url);
                break;
            case "personIcon":
                clickForParticularIndexElement(PERSON_ICON_ZONNIC_CA, 0);
                break;
            case "createAccount":
                logininPage.clickCreateAccountButton("normalUser");
                break;
        }
    }

    public void emptyOrderHistoryVerification() {
        String emptyMessage = getTrimTextFor(ORDER_HISTORY_EMPTY);
        String key = "emptyOrderHistoryMessage" + "-" + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), emptyMessage);
        clickOnRequiredButton("homeButtonInOrderHistoryPage");
    }

    public void marketingPreference(String marketing) {
        waitForExpectedElement(MARKETING_CHECKBOX).click();
        commonMethods.assertButtonText(getTrimTextFor(MARKETING_SAVE_BUTTON_TEXT), "saveInMarketingPreferenceButtonTextFromOverviewPage");
        waitForExpectedElement(MARKETING_SAVE_BUTTON).click();
        String key = null;
        switch (marketing) {
            case "subscribe":
                key = "subscribeMessage" + "-" + UrlBuilder.LANGUAGE;
                break;
            case "unsubscribe":
                key = "unsubscribeMessage" + "-" + UrlBuilder.LANGUAGE;
                break;
        }
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(SUCCESS_MESSAGE));
    }

    public void verifyTextOrHeader(String headerOrText, By xpath) {
        String key = headerOrText + "-" + UrlBuilder.LANGUAGE;
        String passwordHeader = getTrimTextFor(xpath);
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), passwordHeader);
    }

    public void verifyTextOrHeaderWithIndex(String headerOrText, By xpath, int index) {
        String key = headerOrText + "-" + UrlBuilder.LANGUAGE;
        String addressHeader = webDriver.findElements(xpath).get(index).getText().trim();
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), addressHeader);
    }

    public void verifyContainsTextOrHeaderWithIndex(String headerOrText, By xpath, int index) {
        String key = headerOrText + "-" + UrlBuilder.LANGUAGE;
        String addressHeader = webDriver.findElements(xpath).get(index).getText().trim();
        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(key), addressHeader);
    }

    public void VerifyIfFieldIsEditableAndHeader(By xpath, Context key) {
        String actualFirstName = getValue(xpath).trim();
        boolean editable = waitForExpectedElement(xpath).isEnabled();
        String expectedFirstName = (String) scenarioContext.getContext(key);
        assertTrueExpectedTextEqualsActualText(expectedFirstName, actualFirstName);
        assertTrue(!editable);
    }

    public void verifyText(String text) {
        String key;
        boolean editable;
        switch (text) {
            case "firstAndLastName":
                String firstAndLastName = scenarioContext.getContext(FIRST_NAME) + " " + scenarioContext.getContext(LAST_NAME);
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(FIST_LAST_NAME), firstAndLastName);
                break;
            case "email":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(EMAIL_OVERVIEW_PAGE), (String) scenarioContext.getContext(EMAIL_ID));
                break;
            case "addressHeader":
                verifyTextOrHeaderWithIndex("addressHeader", HEADERS_ACCOUNT_OVERVIEW, 2);
                break;
            case "recentOrders":
                verifyTextOrHeaderWithIndex("recentOrders", HEADERS_ACCOUNT_OVERVIEW, 1);
                break;
            case "myAccountDetails":
                verifyTextOrHeaderWithIndex("myAccountDetails", HEADERS_ACCOUNT_OVERVIEW, 0);
                break;
            case "defaultShippingAddressHeader":
                verifyTextOrHeaderWithIndex("defaultShippingAddressHeader", SHIPPING_ADDRESS, 0);
                break;
            case "defaultBillingAddressHeader":
                verifyTextOrHeaderWithIndex("defaultBillingAddressHeader", SHIPPING_ADDRESS, 1);
                break;
            case "welcome":
                key = "welcome" + "-" + UrlBuilder.LANGUAGE;
                String welcome = getTrimTextFor(WELCOME);
                String expectedWelcome = UrlBuilder.getMessage(key) + ", " + scenarioContext.getContext(FIRST_NAME);
                assertTrueExpectedTextEqualsActualText(expectedWelcome, welcome);
                break;
            case "firstNameInMyDetails":
                VerifyIfFieldIsEditableAndHeader(MY_DETAILS_FIRSTNAME, FIRST_NAME);
                break;
            case "lastNameInMyDetails":
                VerifyIfFieldIsEditableAndHeader(MY_DETAILS_LASTNAME, LAST_NAME);
                break;
            case "emailInMyDetails":
                VerifyIfFieldIsEditableAndHeader(MY_DETAILS_EMAIL, EMAIL_ID);
                break;
            case "currentPasswordInMyDetails":
                String actualPassword = getValue(CURRENT_PASSWORD).trim();
                editable = waitForExpectedElement(CURRENT_PASSWORD).isEnabled();
                assertTrueExpectedTextEqualsActualText("placeholder", actualPassword);
                assertTrue(!editable);
                break;
            case "firstNameHeader":
                verifyTextOrHeader("firstNameHeader", MY_DETAILS_FIRSTNAME_HEADER);
                break;
            case "lastNameHeader":
                verifyTextOrHeader("lastNameHeader", MY_DETAILS_LASTNAME_HEADER);
                break;
            case "currentPasswordHeader":
                verifyTextOrHeader("passwordHeader", MY_DETAILS_PASSWORD_HEADER);
                break;
            case "emailHeader":
                verifyTextOrHeader("emailHeader", MY_DETAILS_EMAIL_HEADER);
                break;
            case "defaultShippingAddressHeaderInAddressBook":
                threadSleep(2000);
                verifyTextOrHeaderWithIndex("defaultShippingAddressHeader", SHIPPING_ADDRESS_ADDRESS_BOOK, 0);
                break;
            case "defaultBillingAddressHeaderInAddressBook":
                threadSleep(2000);
                verifyTextOrHeaderWithIndex("defaultBillingAddressHeader", SHIPPING_ADDRESS_ADDRESS_BOOK, 1);
                break;
            case "defaultShippingAddress":
                threadSleep(2000);
                WebElement we = webDriver.findElements(SHIPPING_ADDRESS_ADDRESS_BOOK_TEXT).get(0);
                assertTrue(we.isDisplayed());
                break;
            case "defaultBillingAddress":
                threadSleep(2000);
                WebElement we1 = webDriver.findElements(SHIPPING_ADDRESS_ADDRESS_BOOK_TEXT).get(1);
                assertTrue(we1.isDisplayed());
                break;
            case "otherAddressHeader":
                verifyTextOrHeader("otherAdrressHeader", OTHER_ADDRESS_HEADER);
                break;
            case "resetPasswordHeader":
                verifyTextOrHeader("resetPasswordHeader", forgotPasswordPage.PASSWORD_RESET_HEADER);
                break;
            case "resetPasswordText":
                verifyTextOrHeader("resetPasswordText", forgotPasswordPage.PASSWORD_RESET_TEXT);
                break;
            case "resetPasswordsuccessfullHeader":
                verifyTextOrHeaderWithIndex("resetPasswordsuccessfullHeader", forgotPasswordPage.PASSWORD_RESET_HEADER, 1);
                break;
            case "resetPasswordsuccessfullTextPart1":
                verifyTextOrHeaderWithIndex("resetPasswordsuccessfullTextPart1", forgotPasswordPage.PASSWORD_RESET_TEXT, 2);
                break;
            case "resetPasswordsuccessfullTextPart2":
                key = "resetPasswordsuccessfullTextPart2" + "-" + UrlBuilder.LANGUAGE;
                String email = (String) scenarioContext.getContext(EMAIL_ID);
                String resetPasswordsuccessfullTextPart2 = webDriver.findElements(forgotPasswordPage.PASSWORD_RESET_TEXT).get(1).getText();
                String expectedText = UrlBuilder.getMessage(key) + " " + email;
                assertTrueExpectedTextEqualsActualText(expectedText, resetPasswordsuccessfullTextPart2);
                break;
            case "createNewPasswordHeader":
                verifyTextOrHeader("createNewPasswordHeader", forgotPasswordPage.CREATE_NEW_PASSWORD_HEADER);
                break;
            case "createNewPasswordTextPart1":
                verifyTextOrHeader("createNewPasswordTextPart1", forgotPasswordPage.CREATE_NEW_PASSWORD_TEXT);
                break;
            case "createNewPasswordTextPart2":
                verifyTextOrHeader("createNewPasswordTextPart2", forgotPasswordPage.CREATE_NEW_PASSWORD_TEXT2);
                break;
            case "emailInCreateNewAccountHeader":
                verifyTextOrHeader("emailInCreateNewAccountHeader", forgotPasswordPage.CREATE_NEW_PASSWORD_EMAIL_HEADER);
                break;
            case "enterNewPasswordInCreateNewAccountHeader":
                verifyTextOrHeaderWithIndex("enterNewPasswordInCreateNewAccountHeader", forgotPasswordPage.CREATE_NEW_PASSWORD_PASSWORD_HEADER, 0);
                break;
            case "reEnterNewPasswordInCreateNewAccountHeader":
                verifyTextOrHeaderWithIndex("reEnterNewPasswordInCreateNewAccountHeader", forgotPasswordPage.CREATE_NEW_PASSWORD_PASSWORD_HEADER, 1);
                break;
            case "statusHeader":
                verifyContainsTextOrHeaderWithIndex("statusHeader", ORDER_DETAILS_HEADERS, 2);
               break;
            case "orderHeader":
                verifyContainsTextOrHeaderWithIndex("orderHeader", ORDER_DETAILS_HEADERS, 0);
                break;
            case "placedHeader":
                verifyContainsTextOrHeaderWithIndex("placedHeader", ORDER_DETAILS_HEADERS, 1);
                break;
            case "orderNumber":
                String ordernumber = getTextFor(ORDER_NUMBER_DETAILS);
                String order_Number[] = ordernumber.split(":");
                String expectedOrderNumber = (String) scenarioContext.getContext(ORDER_NUMBER);
                String expectedURL = UrlBuilder.getMessage("orderDetailsURL.key") + "?orderId=" + expectedOrderNumber;
                assertTrueExpectedTextContainsActualText(expectedURL, getCurrentUrl());
                assertTrueExpectedTextEqualsActualText(expectedOrderNumber, order_Number[1]);
                break;
            case "status":
                String key1 = "status"+"-"+UrlBuilder.LANGUAGE;
                String expectedStatus = UrlBuilder.getMessage(key1);
                assertTrueExpectedTextContainsActualText(expectedStatus, getTrimTextForParticularIndexElement(ORDER_NUMBER_DETAILS,2));
                break;
            case "date":
                String expectedDate=getCurrentDate();
                String expDate = expectedDate.replace(" 0", " ");
                assertTrueExpectedTextContainsActualText(expDate, getTrimTextForParticularIndexElement(ORDER_NUMBER_DETAILS,1));
                break;
            case "shippingMethod":
                String expectedShippingMethod = (String) scenarioContext.getContext(SHIPPING_METHOD);
                assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("deliveryInOrderDetails"+"-"+UrlBuilder.LANGUAGE)+" "+expectedShippingMethod, getTrimTextForParticularIndexElement(SHIPPING_METHOD_CA,1));
                break;
            case "shippingMethodHeader":
                verifyTextOrHeaderWithIndex("shippingMethodHeader", SHIPPING_METHOD_CA, 0);
                break;
            case "paymentMethod":
                assertTrueExpectedTextContainsActualText("Credit Card", getTrimTextForParticularIndexElement(PAYMENT_METHOD,1));
                break;
            case "paymentMethodHeader":
                verifyTextOrHeaderWithIndex("paymentMethodHeader", PAYMENT_METHOD, 0);
                break;
            case "previouslyUsedAddressesHeader":
                String previouslykey= "previouslyUsedAddressesHeader"+"-"+UrlBuilder.LANGUAGE;
                String previouslyUsedAddressesHeader = UrlBuilder.getMessage(previouslykey);
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(PREVIOUSLY_USED_ADDRESSES_HEADER), previouslyUsedAddressesHeader);
                break;
        }
    }

    public void clickOnButtonWithIndex(By click, By text, int index, String compareText, String urlToCompare){
        commonMethods.assertButtonText(getTrimTextForParticularIndexElement(text,index), compareText);
        clickForParticularIndexElement(click, index);
        commonMethods.assertUrl(urlToCompare);
    }

    public void clickOnButton(By click, By text, String compareText, String urlToCompare){
        commonMethods.assertButtonText(getTrimTextFor(text), compareText);
        waitForExpectedElement(click).click();
        commonMethods.assertUrl(urlToCompare);
    }

    public void clickOnRequiredButton(String buttonName) {
        switch (buttonName) {
            case "editDetails":
                clickOnButtonWithIndex(EDITDETAILS_VIEWORDERHISTORY, EDITDETAILS_VIEWORDERHISTORY_BUTTON_TEXT, 0,
                        "myDetailsButtonTextFromOverviewPage", "myDetailsLink.key");
                break;
            case "viewOrderHistory":
                clickOnButtonWithIndex(EDITDETAILS_VIEWORDERHISTORY, EDITDETAILS_VIEWORDERHISTORY_BUTTON_TEXT, 1,
                        "viewOrderHistoryButtonTextFromOverviewPage", "orderHistoryLink.key");
                break;
            case "editAddress":
                clickOnButton(EDIT_ADDRESS_OVERVIEWPAGE, EDIT_ADDRESS_OVERVIEWPAGE_TEXT,
                        "editAddressButtonTextFromOverviewPage", "addressBookLink.key");
                break;
            case "changePassword":
                clickOnButton(EDITDETAILS_CHANGEPASSWORD, EDITDETAILS_CHANGEPASSWORD_TEXT,
                        "changePasswordButtonTextFromOverviewPage", "myDetailsLink.key");
                break;
            case "saveChangesButtonInPasswordChange":
                threadSleep(1000);
                commonMethods.assertButtonText(getTrimTextFor(SAVE_CHANGES_CHANGE_PASSWORD_TEXT), "saveChangesChangePassword");
                waitForExpectedElement(SAVE_CHANGES_CHANGE_PASSWORD).click();
                String key = "successMessageForChangePassword" + "-" + UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(SUCCESS_MESSAGE), UrlBuilder.getMessage(key));
                break;
            case "saveChangesButtonInPasswordChangeWithoutSuccessMessageValidation":
                commonMethods.assertButtonText(getTrimTextFor(SAVE_CHANGES_CHANGE_PASSWORD_TEXT), "saveChangesChangePassword");
                waitForExpectedElement(SAVE_CHANGES_CHANGE_PASSWORD).click();
                break;
            case "forgotPasswordLink":
                waitForExpectedElement(FORGOT_PASSWORD_LINK).click();
                commonMethods.assertTitle("resetPasswordTitle");
                commonMethods.assertFooterTitle("resetPasswordTitle");
                commonMethods.assertUrl("resetPasswordURL.key");
                break;
            case "resetButton":
                clickByElementByQueryJSExecutor(forgotPasswordPage.PASSWORD_RESET_BUTTON);
                commonMethods.assertTitle("resetPasswordTitle");
                commonMethods.assertFooterTitle("resetPasswordTitle");
                commonMethods.assertUrl("resetPasswordURL.key");
                threadSleep(3000);
                break;
            case "updatePasswordButton":
                clickByElementByQueryJSExecutor(forgotPasswordPage.UPDATE_PASSWORD_BUTTON);
                threadSleep(3000);
                break;
            case "orderDetailsButtonInOverViewPage":
                commonMethods.assertButtonText(getTrimTextFor(ORDER_DETAILS_BUTTON), "orderDetailsButtonTextFromOverviewPage");
                try{
                    clickByElementByQueryJSExecutor(ORDER_DETAILS_BUTTON);
                }catch (Exception e) {
                    waitForExpectedElement(ORDER_DETAILS_BUTTON).click();
                }
                commonMethods.assertTitle("orderDetailsTitle");
                commonMethods.assertFooterTitle("orderDetailsTitle");
                break;
            case "orderDetailsButtonInOrderHistoryPage":
                commonMethods.assertButtonText(getTrimTextFor(ORDER_DETAILS_BUTTON), "orderDetailsButtonTextInOrderHistory");
                try{
                    clickByElementByQueryJSExecutor(ORDER_DETAILS_BUTTON);
                }catch (Exception e) {
                    waitForExpectedElement(ORDER_DETAILS_BUTTON).click();
                }
                commonMethods.assertTitle("orderDetailsTitle");
                commonMethods.assertFooterTitle("orderDetailsTitle");
                percyPage.takePercyScreenshot("Order Details Page");
                break;
            case "buyAllAgainButton":
                commonMethods.assertButtonText(getTrimTextFor(BUY_ALL_AGAIN_BUTTON_TEXT), "buyAllAgainButtonText");
                waitForExpectedElement(BUY_ALL_AGAIN_BUTTON).click();
                break;
            case "homeButtonInOrderHistoryPage":
                commonMethods.assertButtonText(getTrimTextFor(HOME_BUTTON_ORDER_HISTORY), "homeButtonInOrderHistory");
                waitForExpectedElement(HOME_BUTTON_ORDER_HISTORY).click();
                commonMethods.assertUrl("plpLink.key");
                break;
            case "signUpNow":
                commonMethods.assertButtonText(getTrimTextFor(SIGNUP_NOW_BUTTON_TEXT), "signUpNowButton");
                waitForExpectedElement(SIGNUP_NOW_BUTTON).click();
                break;
            case "signUpNowLoggedIn":
                if (Props.getTestSuite().equals("Live")) {
                    scrollElementIntoView(SIGNUP_NOW_BUTTON_LOGGEDIN_PROD);
                    commonMethods.assertButtonText(getTrimTextFor(SIGNUP_NOW_BUTTON_LOGGEDIN_PROD), "signUpLoggedInButtonProd");
                    clickByElementByQueryJSExecutor(SIGNUP_NOW_BUTTON_LOGGEDIN_PROD);
                    commonMethods.assertUrl("marketingPreferenceLink.key");
                } else {
                    scrollElementIntoView(SIGNUP_NOW_BUTTON_LOGGEDIN);
                    commonMethods.assertButtonText(getTrimTextFor(SIGNUP_NOW_BUTTON_LOGGEDIN), "signUpLoggedInButton");
                    waitForExpectedElement(SIGNUP_NOW_BUTTON_LOGGEDIN).click();
                    commonMethods.assertUrl("marketingPreferenceLink.key");
                }
        }
    }

    public void clickEditButtonChangePassword() {
        commonMethods.assertButtonText(getTrimTextFor(EDIT_CHANGE_PASSWORD), "editForChangePassword");
        waitForExpectedElement(EDIT_CHANGE_PASSWORD).click();
    }

    public void enterData(String text) {
        switch (text) {
            case "currentPassword":
                waitClearAndEnterText(CURRENT_PASSWORD, UrlBuilder.getMessage("loginValidPassword.key"));
                break;
            case "wrongCurrentPassword":
                waitClearAndEnterText(CURRENT_PASSWORD, "Password@12345678");
                break;
            case "newPassword":
                waitClearAndEnterText(NEW_PASSWORD, UrlBuilder.getMessage("loginValidPasswordNew.key"));
                break;
            case "confirmPassword":
                waitClearAndEnterText(CONFIRM_PASSWORD, UrlBuilder.getMessage("loginValidPasswordNew.key"));
                break;
            case "enterNewPasswordSameAsCurrent":
                waitClearAndEnterText(NEW_PASSWORD, UrlBuilder.getMessage("loginValidPassword.key"));
                break;
            case "enterEmailForResetPassword":
                waitClearAndEnterText(forgotPasswordPage.PASSWORD_RESET, (String) scenarioContext.getContext(EMAIL_ID));
                break;
            case "enterEmailForCreateNewPassword":
                waitClearAndEnterText(forgotPasswordPage.EMAIL_TEXT_BOX, (String) scenarioContext.getContext(EMAIL_ID));
                break;
            case "enterNewPassword":
                waitClearAndEnterText(forgotPasswordPage.ENTER_PASSWORD_TEXT_BOX, UrlBuilder.getMessage("loginValidPasswordNew.key"));
                break;
            case "reEnterNewPassword":
                waitClearAndEnterText(forgotPasswordPage.RE_ENTER_PASSWORD_TEXT_BOX, UrlBuilder.getMessage("loginValidPasswordNew.key"));
                break;
            case "enterInvalidEmailForCreateNewPassword":
                waitClearAndEnterText(forgotPasswordPage.EMAIL_TEXT_BOX, UrlBuilder.getMessage("loginInvalidEmail.key"));
                break;
        }
    }

    public void validateErrorMessages(By xpath,By xpathOfError, String key) {
        waitForExpectedElement(xpath).click();
        waitForExpectedElement(xpath).sendKeys(Keys.TAB);
        String expectedText = UrlBuilder.getMessage(key + "-" + UrlBuilder.LANGUAGE);
        assertTrueExpectedTextEqualsActualText(expectedText, getTrimTextFor(xpathOfError));
    }

    public void validateErrorMessagesWithIndex(By xpath,By xpathOfError, String key, int index) {
        waitForExpectedElement(xpath).click();
        waitForExpectedElement(xpath).sendKeys(Keys.TAB);
        String expectedText = UrlBuilder.getMessage(key + "-" + UrlBuilder.LANGUAGE);
        assertTrueExpectedTextEqualsActualText(expectedText, webDriver.findElements(xpathOfError).get(index).getText().trim());
    }

    public void validateErrorForInValidFormatsWithIndex(By xpath, String data, By errorxpath, int index, String key) {
        String expectedText = UrlBuilder.getMessage(key + "-" + UrlBuilder.LANGUAGE);
        waitForExpectedElement(xpath).sendKeys(data);
        assertTrueExpectedTextEqualsActualText(expectedText, webDriver.findElements(errorxpath).get(index).getText().trim());
        waitForExpectedElement(xpath).clear();
    }

    public void validateErrorForInValidFormats(By xpath, String data, By errorxpath, String key) {
        String expectedText = UrlBuilder.getMessage(key + "-" + UrlBuilder.LANGUAGE);
        waitForExpectedElement(xpath).sendKeys(data);
        String sadas = getTrimTextFor(errorxpath);
        LOG.info(sadas);
        LOG.info(expectedText);
        assertTrueExpectedTextEqualsActualText(expectedText, getTrimTextFor(errorxpath));
        waitForExpectedElement(xpath).clear();
    }


    public void validateErrors(String errorMessage) {
        String expectedText = null;
        String key = null;
        switch (errorMessage) {
            case "newPasswordErrorWhenCharactersLessThan8":
                validateErrorForInValidFormats(NEW_PASSWORD, "Pass", ERROR_NEW_PASSWORD, "newPasswordError");
                break;
            case "confirmPasswordErrorWhenCharactersLessThan8":
                validateErrorForInValidFormats(CONFIRM_PASSWORD, "Pass", ERROR_CONFIRM_PASSWORD, "confirmPasswordError");
                break;
            case "newPasswordErrorWhenCharactersGreaterThan8WithNoSpecialCharacter":
                validateErrorForInValidFormats(NEW_PASSWORD, "Passsssssssssssssssssss", ERROR_NEW_PASSWORD, "newPasswordError");
                break;
            case "confirmPasswordErrorWhenCharactersGreaterThan8WithNoNumber":
                validateErrorForInValidFormats(CONFIRM_PASSWORD, "Passsssssssssssssssssss", ERROR_CONFIRM_PASSWORD, "confirmPasswordError");
                break;
            case "byEnteringWrongCurrentPassword":
                validateErrorMessages(CURRENT_PASSWORD, WRONG_CURRENT_PASSWORD, "wrongCurrentPasswordError");
                break;
            case "currentPassword":
                validateErrorMessages(CURRENT_PASSWORD, ERROR_CURRENT_PASSWORD, "currentPasswordError");
                break;
            case "newPassword":
                validateErrorMessages(NEW_PASSWORD, ERROR_NEW_PASSWORD, "newPasswordError");
                break;
            case "confirmPassword":
                validateErrorMessages(CONFIRM_PASSWORD, ERROR_CONFIRM_PASSWORD, "confirmPasswordError");
                break;
            case "wrongPassword":
                expectedText = UrlBuilder.getMessage("wrongPassword" + "-" + UrlBuilder.LANGUAGE);
                assertTrueExpectedTextEqualsActualText(expectedText, getTrimTextFor(INVALID_LOGIN_ERROR));
                break;
            case "firstName":
                validateErrorMessagesWithIndex(FIRSTNAME, ERROR_MESSAGES, "firstNameError", 0);
                break;
            case "lastName":
                validateErrorMessagesWithIndex(LASTNAME, ERROR_MESSAGES, "lastNameError", 1);
                break;
            case "phoneNumber":
                validateErrorMessagesWithIndex(checkoutPage.TELEPHONEINCHECKOUT, ERROR_MESSAGES, "phoneNumberError", 2);
                validateErrorForInValidFormatsWithIndex(checkoutPage.TELEPHONEINCHECKOUT, "sdfsddsgdg", ERROR_MESSAGES, 2, "phoneNumberError");
                break;
            case "resetPasswordError":
                validateErrorMessages(forgotPasswordPage.PASSWORD_RESET, forgotPasswordPage.PASSWORD_RESET_ERROR, "resetPasswordError");
                break;
            case "emailWhenEmpty":
                validateErrorMessages(forgotPasswordPage.EMAIL_TEXT_BOX, forgotPasswordPage.EMAIL_TEXT_BOX_ERROR, "emailError");
                break;
            case "enterPasswordWhenEmpty":
                validateErrorMessagesWithIndex(forgotPasswordPage.ENTER_PASSWORD_TEXT_BOX, forgotPasswordPage.PASSWORD_TEXT_BOX_ERROR, "newPassword", 0);
                break;
            case "reEnterPasswordWhenEmpty":
                validateErrorMessagesWithIndex(forgotPasswordPage.RE_ENTER_PASSWORD_TEXT_BOX, forgotPasswordPage.PASSWORD_TEXT_BOX_ERROR, "reEnterNewPassword", 1);
                break;
            case "emailWithWrongFormat":
                validateErrorForInValidFormats(forgotPasswordPage.EMAIL_TEXT_BOX, "email@", forgotPasswordPage.EMAIL_TEXT_BOX_ERROR, "emailError");
                break;
            case "enterPasswordErrorWhenCharactersLessThan8":
                validateErrorForInValidFormatsWithIndex(forgotPasswordPage.ENTER_PASSWORD_TEXT_BOX, "Pass", forgotPasswordPage.PASSWORD_TEXT_BOX_ERROR, 0, "newPassword");
                break;
            case "reEnterPasswordErrorWhenCharactersLessThan8":
                validateErrorForInValidFormatsWithIndex(forgotPasswordPage.RE_ENTER_PASSWORD_TEXT_BOX, "Pass", forgotPasswordPage.PASSWORD_TEXT_BOX_ERROR, 1, "reEnterNewPassword");
                break;
            case "enterPasswordErrorWhenCharactersGreaterThan8WithNoSpecialCharacter":
                validateErrorForInValidFormatsWithIndex(forgotPasswordPage.ENTER_PASSWORD_TEXT_BOX, "Passsssssssssssssssssss", forgotPasswordPage.PASSWORD_TEXT_BOX_ERROR, 0, "newPassword");
                break;
            case "enterTwoDifferentPasswords":
                waitClearAndEnterText(forgotPasswordPage.ENTER_PASSWORD_TEXT_BOX, UrlBuilder.getMessage("loginValidPasswordNew.key"));
                validateErrorForInValidFormatsWithIndex(forgotPasswordPage.RE_ENTER_PASSWORD_TEXT_BOX, "Pass", forgotPasswordPage.PASSWORD_TEXT_BOX_ERROR, 1, "reEnterNewPassword");
                break;
            case "enterPasswordErrorWhenCharactersGreaterThan8WithNoNumber":
                validateErrorForInValidFormatsWithIndex(forgotPasswordPage.ENTER_PASSWORD_TEXT_BOX, "Passsssssssssssssssssss@", forgotPasswordPage.PASSWORD_TEXT_BOX_ERROR, 0, "newPassword");
                break;
            case "enterDifferentEmailAndTryToUpdatePassword":
                String actualError = getTrimTextFor(forgotPasswordPage.CANT_RESET_PASSWORD_ERROR);
                String expectedError = UrlBuilder.getMessage("cantResetPasswordError" + "-" + UrlBuilder.LANGUAGE);
                assertTrueExpectedTextEqualsActualText(expectedError, actualError);
                break;
            case "invalidLoginErrorMessage":
                verifyTextOrHeader("invalidSignin", INVALID_LOGIN_ERROR);
                break;
            case "emailEmptyInSignIn":
                waitForExpectedElement(logininPage.EMAIL_INPUTBOX).clear();
                key = "errorEmailrequired"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextForParticularIndexElement(ERROR_MESSAGE_EMPTY_FIELD, 0));
                break;
            case "passwordEmptyInSignIn":
                waitForExpectedElement(logininPage.PASSWORD_INPUTBOX).clear();
                key = "emptyPassword"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextForParticularIndexElement(ERROR_MESSAGE_EMPTY_FIELD, 1));
                break;
            case "emailInvalidFormatSignIn":
                waitForExpectedElement(logininPage.EMAIL_INPUTBOX).clear();
                waitForExpectedElement(logininPage.EMAIL_INPUTBOX).sendKeys("invalidFormat");
                key = "errorEmailrequired"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextForParticularIndexElement(ERROR_MESSAGE_EMPTY_FIELD, 0));
                break;
            case "invalidLoginErrorMessageAsGuest":
                verifyTextOrHeader("invalidSigninGuest", ERROR_MESSAGE_IN_GUEST);
                break;
        }
    }

    public void successMessage(String successMessage) {
        String expectedText = null;
        String key = null;
        switch (successMessage) {
            case "successMessageForChangePassword":
                key = "successMessageForChangePassword"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(SUCCESS_MESSAGE));
                break;
        }
    }

    public void enterPostCode() {
        String province = UrlBuilder.PROVINCE;
        String postCode = getPostCodeForAddressBook(province);
        registrationPage.enterPostCode(postCode);
        try {
            clickByElementByQueryJSExecutor(registrationPage.selectFirstAddress);
        } catch (Exception e) {

        }
        threadSleep(1000);
        clickByElementByQueryJSExecutor(registrationPage.selectFirstAddress1);
    }

    public void addNewAddressInAddressBooks(String addressMethod) {
        String firstAndLastName = null;
        String firstAndLastNameDefaultShipping = null;
        switch (addressMethod) {
            case "addAddressWithDefaultShippingAddressChecked":
                commonMethods.assertUrl("addAddressUrl.key");
                enterFirstNameAndLastName();
                checkoutPage.enterTelephoneInCheckout();
                enterPostCode();
                waitForExpectedElement(DEFAULT_SHIPPING_ADDRESS_CHECKBOX).click();
                waitForExpectedElement(SAVE_ADDRESS).click();
                firstAndLastName = getTrimTextFor(FIRSTANDLASTNAME_DEFAULT_SHIPPING_ADDRESS);
                firstAndLastNameDefaultShipping = (String) scenarioContext.getContext(FISTANDLASTNAME_ADDRESSBOOK);
                assertTrueExpectedTextEqualsActualText(firstAndLastNameDefaultShipping, firstAndLastName);
                break;
            case "addAddressWithDefaultShippingAddressNotChecked":
                commonMethods.assertUrl("addAddressUrl.key");
                enterFirstNameAndLastName();
                checkoutPage.enterTelephoneInCheckout();
                enterPostCode();
                waitForExpectedElement(SAVE_ADDRESS).click();
                firstAndLastName = getTrimTextFor(FIRSTANDLASTNAME_OTHER_ADDRESS);
                firstAndLastNameDefaultShipping = (String) scenarioContext.getContext(FISTANDLASTNAME_ADDRESSBOOK);
                assertTrueExpectedTextEqualsActualText(firstAndLastName, firstAndLastNameDefaultShipping);
                break;
            case "editAddressWithDefaultShippingAddressChecked":
            case "editAddressInOthersWithDefaultShippingAddressChecked":
                commonMethods.assertUrlContains("editAddressUrl.key");
                enterFirstNameAndLastName();
                checkoutPage.enterTelephoneInCheckout();
                enterPostCode();
                waitForExpectedElement(DEFAULT_SHIPPING_ADDRESS_CHECKBOX).click();
                waitForExpectedElement(SAVE_ADDRESS).click();
                firstAndLastName = getTrimTextFor(FIRSTANDLASTNAME_DEFAULT_SHIPPING_ADDRESS);
                firstAndLastNameDefaultShipping = (String) scenarioContext.getContext(FISTANDLASTNAME_ADDRESSBOOK);
                assertTrueExpectedTextEqualsActualText(firstAndLastName, firstAndLastNameDefaultShipping);
                break;
            case "editAddressInOthersWithDefaultShippingAddressNotChecked":
                commonMethods.assertUrlContains("editAddressUrl.key");
                enterFirstNameAndLastName();
                checkoutPage.enterTelephoneInCheckout();
                enterPostCode();
                waitForExpectedElement(SAVE_ADDRESS).click();
                firstAndLastName = getTrimTextFor(FIRSTANDLASTNAME_OTHER_ADDRESS);
                firstAndLastNameDefaultShipping = (String) scenarioContext.getContext(FISTANDLASTNAME_ADDRESSBOOK);
                assertTrueExpectedTextEqualsActualText(firstAndLastName, firstAndLastNameDefaultShipping);
                break;
            case "removeAddress":
                threadSleep(3000);
                waitForExpectedElement(DELETE_ADDRESS_IN_OTHER_ADDRESS).click();
                threadSleep(3000);
                break;
        }
    }

    public String getPostCodeForAddressBook(String province) {
        String postCode = null;
        if (province.contains("Alberta")) {
            postCode = "T0A 0L";
        } else if (province.contains("British Columbia")) {
            postCode = "V0A 1H2";
        } else if (province.contains("Manitoba")) {
            postCode = "R2Y 2E9";
        } else if (province.contains("New Brunswick")) {
            postCode = "E8S 2H8";
        } else if (province.contains("Newfoundland and Labrador")) {
            postCode = "A1B 0G4";
        } else if (province.contains("Nova Scotia")) {
            postCode = "B0C 0A5";
        } else if (province.contains("Ontario")) {
            postCode = "M3J 3G1";
        } else if (province.contains("Prince Edward Island")) {
            postCode = "C0A1R0";
        } else if (province.contains("Quebec")) {
            postCode = "G0A 1H0";
        } else if (province.contains("Saskatchewan")) {
            postCode = "S0A0G0";
        }
        return postCode;
    }

    public void enterFirstNameAndLastName() {
        String firstNameData = random(6, ALPHABETS);
        String lastNameData = random(6, ALPHABETS);
        enterText(FIRSTNAME, firstNameData);
        enterText(LASTNAME, lastNameData);
        scenarioContext.setContext(FIRST_NAME_ADDRESSBOOK, firstNameData);
        scenarioContext.setContext(LAST_NAME_ADDRESSBOOK, lastNameData);
        String flName = firstNameData + " " + lastNameData;
        scenarioContext.setContext(FISTANDLASTNAME_ADDRESSBOOK, flName);
    }

    public void clickOnMakeDefaultShippingAddress(String addressSection) {
        String firstLastNameBeforeSwapping = null;
        String firstLastNameAfterSwapping = null;
        switch (addressSection) {
            case "otherAddresses":
                firstLastNameBeforeSwapping = getTrimTextFor(FIRSTANDLASTNAME_DEFAULT_SHIPPING_ADDRESS);
                webDriver.findElements(DEFAULT_SHIPPING_ADDRESS).get(1).click();
                threadSleep(3000);
                firstLastNameAfterSwapping = getTrimTextFor(FIRSTANDLASTNAME_DEFAULT_SHIPPING_ADDRESS);
                String fnameLname = (String) scenarioContext.getContext(FISTANDLASTNAME_ADDRESSBOOK);
                assertTrueExpectedTextEqualsActualText(fnameLname, firstLastNameAfterSwapping);
                assertTrueExpectedTextEqualsActualText(firstLastNameBeforeSwapping, getTrimTextFor(FIRSTANDLASTNAME_OTHER_ADDRESS));
                break;
            case "defaultBillingAddresses":
                firstLastNameBeforeSwapping = getTrimTextFor(FIRSTANDLASTNAME_DEFAULT_BILLING_ADDRESS);
                webDriver.findElements(DEFAULT_SHIPPING_ADDRESS).get(0).click();
                threadSleep(3000);
                firstLastNameAfterSwapping = getTrimTextFor(FIRSTANDLASTNAME_DEFAULT_SHIPPING_ADDRESS);
                assertTrueExpectedTextEqualsActualText(firstLastNameBeforeSwapping, firstLastNameAfterSwapping);
                break;
        }
    }

    public void clickAddNewAddressButton(String button) {
        switch (button) {
            case "addNewAddress":
                commonMethods.assertButtonText(getTrimTextFor(ADD_ADDRESS_BUTTON_TEXT), "addNewAddressButtonText");
                waitForExpectedElement(ADD_ADDRESS_BUTTON).click();
                commonMethods.assertTitle("addAddressTitle");
                commonMethods.assertFooterTitle("addAddressTitle");
                break;
            case "editAddress":
                String key = "editAddress" + "-" + UrlBuilder.LANGUAGE;
                String key1 = UrlBuilder.getMessage(key);
                By xpath = By.xpath("//div[@class='addressbook__address__edit-buttons']//a[@title='" + key1 + "']");
                waitForExpectedElement(xpath).click();
                commonMethods.assertTitle("editAddressTitle");
                commonMethods.assertFooterTitle("editAddressTitle");
                break;
        }
    }
}