package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.constants.Locale;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.salmon.test.page_objects.constants.Context.*;
import static org.testng.AssertJUnit.assertTrue;

public class HomePage extends PageObject {
    private OrderSuccessPage orderSuccessPage;
    private LogininPage loginPage;
    private AddNewAddressPage addNewAddressPage;
    private RegistrationPage registrationPage;
    private AccountDashboardPage accountDashboardPage;
    private ScenarioContext scenarioContext;
    public PaymentPage paymentPage;
    public CommonMethods commonMethods;
    public PercyPage percyPage;

    public HomePage() {
    }

    public HomePage(PercyPage percyPage, CommonMethods commonMethods, OrderSuccessPage orderSuccessPage, LogininPage loginPage, AddNewAddressPage addNewAddressPage, RegistrationPage registrationPage, ScenarioContext scenarioContext, AccountDashboardPage accountDashboardPage) {
        this.orderSuccessPage = orderSuccessPage;
        this.loginPage = loginPage;
        this.addNewAddressPage = addNewAddressPage;
        this.registrationPage = registrationPage;
        this.scenarioContext = scenarioContext;
        this.accountDashboardPage = accountDashboardPage;
        this.commonMethods = commonMethods;
        this.percyPage = percyPage;
    }

    public final static By buttonAgeAllowCA = By.cssSelector("#age-gate-over");
    private static final By PERSON_ICON_ZONNIC_CA = By.cssSelector(".bat-header-account-link button");
    private static final By ACCOUNT_ZONNIC_CA = By.cssSelector(".bat-header-account-menu a span");
    public final static By ACCOUNT_LINK_CA = By.cssSelector(".bat-header-account-link button");
    private final static By MY_ACCOUNT_ON_ACCOUNT_DROPDOWN = By.cssSelector(".account-dropdown [href*='/account/index']");
    private final static By MY_ACCOUNT_ON_ACCOUNT_DROPDOWN_CA = By.cssSelector("a.bat-cta-style.bat-header-account-link");
    public By basketQty_CA = By.cssSelector(".bat-header-cart .bat-cta-style i");
    public By clickToCheckOutButton_ca = By.cssSelector(".bat-minicart-button-checkout a[href*='checkout/cart']");
    public By clickToCheckOutButton_button_text = By.cssSelector(".bat-minicart-button-checkout a[href*='checkout/cart'] span");
    public By PROCEED_TO_CHECKOUT_CA = By.cssSelector(".bat-cta.bat-cta-list--vertical a[href*='shipping-and-payment']");
    public By PROCEED_TO_CHECKOUT_BUTTON_TEXT = By.cssSelector(".bat-cta.bat-cta-list--vertical a[href*='shipping-and-payment'] span");
    public By ageGate_CA = By.cssSelector(".bat-agegate--zonnic-content-dob-input");
    public By ageGate_Providence_CA = By.cssSelector("select#location");
    public static By homePage = By.xpath("//div[@class='hero-image-content']//h2");
    public final static By LANGUAGE_SELECTOR = By.cssSelector(".bat-header-support-link a");
    public final static By CLOSE_LANGUAGE_SELECTOR = By.cssSelector(".bat-section-modal #locationselectormodal div button.bat-cta-style.bat-modal-close");
    public static final By CART_CA = By.cssSelector(".bat-header-utils .bat-header-cart button");
    public static final By REMOVE_ITEM_FROM_MINIBASKET_ICON_CA = By.cssSelector(".bat-minicart-cart-item-product-container div button.bat-cta-style.remove-item");
    private static final By CART_VALUE_CA = By.cssSelector(".bat-header-utils .bat-header-cart button span[class='bat-header-cart-label']");
    public static final By LOADER_ICON = By.cssSelector("div.loading-mask");
    public static final By SUBTOTAL_MINIBASKET = By.cssSelector(".bat-minicart-subtotal-value.formatPrice");
    public static final By MINIBASKET = By.cssSelector(".minicart .bat-minicart.d-none");
    public static final By SIGNIN_AND_CHECKOUT_BUTTON = By.cssSelector(".bat-cta.bat-cta-list--vertical a[href*='sign-in']");
    public static final By SIGNIN_AND_CHECKOUT_BUTTON_TEXT = By.cssSelector(".bat-cta.bat-cta-list--vertical a[href*='sign-in'] span");
    public static final By ACCEPT_COOKIES_VUSE = By.xpath("//button[text()='Accept All Cookies']");
    public static final By PROVINCE_DROPDOWN_VUSE = By.cssSelector("select.js-agegate-province");
    public static final By IAM_18_ABOVE_BUTTON_VUSE = By.cssSelector(".bat-agegate--default-content-greeting-cta-list .bat-cta-style.button-dark.center");
    public static final By SIGNIN_VUSE = By.cssSelector(".bat-header__top-right .bat-header__account a");
    public static final By LOGIN_CONSENT = By.cssSelector("#loginConsent");
    public static final By ZONNIC_CONSENT = By.cssSelector("#subscriptionConsent");
    public By PROVINCE_IN_HEADER = By.cssSelector(".bat-header-support-link a span");
    public By ZONNIC_LOGO = By.cssSelector(".bat-header-logo a");
    public By SHOOPING_CART_LINK = By.cssSelector(".bat-messagebar--zonnic-message.active p a");
    public By WHY_ZONNIC_DETAILS = By.cssSelector("h2[class='bat-headline-style headline1'] center");
    public By WHY_ZONNIC_DETAILS_PAGE = By.xpath("//h2[@class='bat-headline-style headline1']");
    public By FAQ_CONTENT_MSSG = By.cssSelector(".bat-card-info.bat-faq-default");
    public By FAQ_BUTTON = By.xpath("//i[@class='bat-faq__button bat-icon fa-plus']");
    public By FAQ_MESSAGE = By.xpath("//center[text()=' FAQ ']");
    public By SIGNUP_NOW_BUTTON = By.cssSelector(".bat-cta.bat-cta-list--horizontal button");
    public By SIGNUP_NOW_CLOSE = By.cssSelector("#ZonnicNewsletterModal .bat-cta-style.bat-modal-close");
    public By INSTAGRAM_ICON_FOOTER = By.cssSelector(".bat-footer-zonnic-nav-menu.ava-nav-menu .bat-footer-social-nav .bat-icon.instagram");
    public By PRODUCTS_QTY_IN_CART = By.cssSelector(".basket__list .basket__item");
    public By QTY_IN_CART = By.cssSelector(".bat-basket-summary-items-header-label .bat-basket-summary-items-header-label-total");
    public By REMOVE_FROM_BASKET = By.xpath("//div[@class='basket__item']//div[@class='basket__item__details']//i[@class='bat-icon icon-close']//parent::button");
    public By EMPTY_MINI_BASKET_TEXT = By.cssSelector(".bat-minicart-empty-message");
    public static final By SUBTOTAL_MINIBASKET1 = By.cssSelector(".bat-minicart-subtotal-value");
    public By EMPTY_BASKET_TEXT1 = By.cssSelector(".basket__empty h1");
    public By EMPTY_BASKET_TEXT2 = By.cssSelector(".basket__empty p");
    public By START_SHOPPING = By.cssSelector(".basket__empty a");
    public By CONTACTUS_TEXT=By.xpath("//h1[@class='bat-headline-style headline1-large']");
    public By INFORMATION_DETAILS=By.xpath("(//div[@class='bat-card--contact-text'])[1]");
    public By INFORMATION_DETAILS_1=By.xpath("(//div[@class='bat-card--contact-text'])[2]");
    public By INFORMATION_DETAILS_2=By.xpath("(//div[@class='bat-card--contact-text'])[3]");
    public By INFORMATION_DETAILS_3=By.xpath("(//div[@class='bat-card--contact-text'])[4]");
    public By  START_CHAT_ICON=By.xpath("//div[contains(@class,'responsivegrid aem-GridColumn--tablet--12 aem-GridColumn--offset--tablet--0 aem-GridColumn--default--none aem-GridColumn--phone--none aem-GridColumn--phone--12 aem-GridColumn--tablet--none aem-GridColumn aem-GridColumn--default--12 aem-GridColumn--offset--phone--0 aem-GridColumn--offset--default--0')]//div[contains(@class,'card aem-GridColumn--tablet--12 aem-GridColumn--offset--tablet--0 aem-GridColumn--default--none aem-GridColumn--phone--none aem-GridColumn--phone--12 aem-GridColumn--tablet--none aem-GridColumn aem-GridColumn--offset--default--2 aem-GridColumn--offset--phone--0 aem-GridColumn--default--4')]//a[contains(@class,'bat-cta-style button-dark')]");
    public By CALLUS_BUTTON=By.cssSelector(".bat-cta-style.button-dark[data-cta-type='telephone']");
    public By LOGTICKET_BUTTON=By.cssSelector("div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(2) div:nth-child(1) div:nth-child(1) bat-card-contact:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(2) div:nth-child(3) a:nth-child(1)");
    public By  SEE_ALL_FAQ=By.cssSelector("div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(2) div:nth-child(1) div:nth-child(2) bat-card-contact:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(2) div:nth-child(3) a:nth-child(1) span:nth-child(1)");
    public final static By buttonAgeLess = By.cssSelector("#age-gate-under");
    public By ZONNIC_LOGO_AGE_GATE = By.cssSelector(".bat-agegate--zonnic-content-image img");
    public By WELCOME_ZONNIC_TEXT_AGE_GATE = By.cssSelector(".bat-agegate--zonnic-content-greeting-headline");
    public By SELECT_PROVINCE_TEXT_AGE_GATE = By.cssSelector(".bat-agegate--zonnic-content-input div label");
    public By TEXT_AGE_GATE = By.cssSelector(".bat-agegate--zonnic-content-greeting-middle-text p");
    public By TEXT_AT_LAST_AGE_GATE = By.cssSelector(".bat-agegate--zonnic-content-greeting-footnote p");
    public By TEXT_AT_TOP_AGE_GATE = By.cssSelector(".bat-agegate--zonnic-content-greeting-text p");
    public final static By BUTTONTEXT18PLUS = By.xpath("//button[@id='age-gate-over']//span");
    public final static By BUTTONTEXTLESS18 = By.xpath("//button[@id='age-gate-under']//span");
    public By OOPS = By.cssSelector(".bat-agegate--zonnic-content-error-message.active h1");
    public By OOPS_PROD = By.cssSelector(".bat-agegate--zonnic-content-error-message.active b");
    public By TEXT_IN_AGE_LESS_PAGE = By.cssSelector(".bat-agegate--zonnic-content-error-message.active p");
    public By LANGUAGE_SELECTOR_HEADER = By.cssSelector(".bat-location--zonnic-content-greeting-headline h2");
    public By LANGUAGE_SELECTOR_TEXT = By.cssSelector(".bat-location--zonnic-content-greeting-text > p:nth-child(2)");
    public By SELECT_YOUR_LANGUAGE_HEADER = By.cssSelector(".bat-location--zonnic-content-language > label:nth-child(1)");
    public By CONFIRM_BUTTON = By.cssSelector(".bat-location--zonnic-content-greeting-cta-list button");
    public By FAQ_MESSAGE_1 = By.xpath("//center[normalize-space()='FAQ']");
    public By EXPLORE_FLAVOURS=By.xpath("//a[@class='bat-cta-style arrow-link-dark left  ']");
    public By EXPLORE_FLAVOURS_2=By.cssSelector("div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(3) bat-cta-default:nth-child(1) div:nth-child(1) a:nth-child(1) span:nth-child(1)");
    public By  LEARN_MORE_BUTTON=By.cssSelector("//bat-card-mastheadzonnic[@class='bat card-masthead-product bg-berry-frost-gradient d-block d-sm-block d-md-block d-lg-block d-xl-block margin-xsmall ready loaded']//a[@title='Learn more']");
    public By LEARN_MORE_BUTTON1=By.cssSelector("bat-card-mastheadzonnic[class='bat card-masthead-product bg-chill-mint-green-gradient d-block d-sm-block d-md-block d-lg-block d-xl-block margin-xsmall ready loaded'] span");
    public By LEARN_MORE_BUTTON2=By.cssSelector("bat-card-mastheadzonnic[class='bat card-masthead-product bg-tropic-breeze-gradient d-block d-sm-block d-md-block d-lg-block d-xl-block margin-xsmall ready loaded'] span");
    public By  LEARN_MORE=By.xpath("//a[@title='Learn more']");
    public By   LEARN_MORE_TITLE=By.cssSelector(".producthero-content-name");
    public void clickOnProceedToCheckoutButtonInMiniBasket() {
            switch (UrlBuilder.getLocale()) {
                case "zonnicca":
                    commonMethods.assertButtonText(getTrimTextFor(clickToCheckOutButton_button_text), "proceedMiniBasketButtonText");
                    try {
                        waitForItemToBeClickableAndClick(clickToCheckOutButton_ca, 10);
                    } catch (Exception e) {
                        clickOnBasketIcon();
                        waitForItemToBeClickableAndClick(clickToCheckOutButton_ca, 10);
                    }
//                    commonMethods.assertTitle("cartPageTitle");
//                    commonMethods.assertFooterTitle("cartPageTitle");
                    percyPage.takePercyScreenshot("Cart Page");
                    break;
            }
        }

    public void clickOnProceedToCheckoutButtonInCart(String guestOrNormalUser) {
        switch (guestOrNormalUser) {
            case "normalUser":
                commonMethods.assertButtonText(getTrimTextFor(PROCEED_TO_CHECKOUT_BUTTON_TEXT), "proceedToCheckoutButtonText");
                String products_qty = String.valueOf(webDriver.findElements(PRODUCTS_QTY_IN_CART).size());
                String total_qty = getTrimTextFor(QTY_IN_CART);
                scenarioContext.setContext(PRODUCTS_QTY_FROM_BASKET, products_qty);
                scenarioContext.setContext(ITEMS_QTY_FROM_BASKET, total_qty);
                try {
                    waitForItemToBeClickableAndClick(PROCEED_TO_CHECKOUT_CA, 10);
                } catch (Exception e) {
                    clickOnBasketIcon();
                    waitForItemToBeClickableAndClick(PROCEED_TO_CHECKOUT_CA, 10);
                }
                percyPage.takePercyScreenshot("Checkout Page");
                commonMethods.assertTitle("checkoutPageTitle");
                commonMethods.assertFooterTitle("checkoutPageTitle");
                break;
            case "guestUser":
                commonMethods.assertButtonText(getTrimTextFor(SIGNIN_AND_CHECKOUT_BUTTON_TEXT), "signInAndCheckoutButtonText");
                waitForItemToBeClickableAndClick(SIGNIN_AND_CHECKOUT_BUTTON, 10);
                commonMethods.assertUrl("signInForGuestLink.key");
                commonMethods.assertTitle("signInTitle");
                commonMethods.assertFooterTitle("signInTitle");
                break;
        }
    }

    public void clickOnBasketIcon() {
        boolean miniBasket = false;
        boolean displayed = false;
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                try {
                    waitForExpectedElement(CART_CA).click();
                    try {
                        waitForExpectedElement(MINIBASKET).isDisplayed();
                        miniBasket = false;
                    } catch (Exception e3) {
                        miniBasket = true;
                    }
                } catch (Exception e) {
                    try {
                        refreshBrowser();
                        clickByElementByQueryJSExecutor(CART_CA);
                        try {
                            waitForExpectedElement(MINIBASKET).isDisplayed();
                            miniBasket = false;
                        } catch (Exception e3) {
                            miniBasket = true;
                        }
                    } catch (Exception e2) {
                        refreshBrowser();
                        clickUsingJS(CART_CA);
                        try {
                            waitForExpectedElement(MINIBASKET).isDisplayed();
                            miniBasket = false;
                        } catch (Exception e3) {
                            miniBasket = true;
                        }
                    }
                }
                percyPage.takePercyScreenshot("Mini Basket");
                break;
        }
    }

    public void confirmMiniBasketDisplayedAmountOf(String strBasketQty) {
        threadSleep(5000);
        assertTrueExpectedTextEqualsActualText(strBasketQty, getAttribute(basketQty_CA, "data-cart-quantity"));
    }

    public void clickOver18(String provinceFromFeature) {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                String language;
                threadSleep(2000);

                percyPage.takePercyScreenshot("Age gate popup");

                if (provinceFromFeature.equals("<province>")) {
                    provinceFromFeature = UrlBuilder.PROVINCE;
                } else {
                    UrlBuilder.PROVINCE = provinceFromFeature;
                }

                if (UrlBuilder.LANGUAGE.contains("en")) {
                    language = "English";
                } else {
                    language = "Français";
                }

                By LANGUAGE1 = By.xpath("//div[@class='bat-agegate--zonnic-content-language-list']//a[text()='" + language + "']");
                try {
                    waitForExpectedElement(LANGUAGE1).click();
                } catch (Exception e) {
                    refreshBrowser();
                    waitForExpectedElement(LANGUAGE1).click();
                }

                waitForExpectedElement(ageGate_Providence_CA);
                try {
                    selectOptionFromDropDownByValue(provinceFromFeature, ageGate_Providence_CA);
                } catch (Exception e) {

                }
                try {
                    waitForExpectedElement(ageGate_CA, 15).isDisplayed();
                    waitForExpectedElement(ageGate_CA, 15).sendKeys("09081998");
                } catch (Exception e) {

                }

                tryClickIAmOver18();
                percyPage.takePercyScreenshot("Home Page");
                break;
        }
        commonMethods.assertTitle("HomePageTitle");
        commonMethods.assertFooterTitle("homePageFooterTitle");
    }

    public void languageSelector() {
        waitForExpectedElement(LANGUAGE_SELECTOR).click();
        percyPage.takePercyScreenshot("Language Selector");
    }

    public void languageSelectorClose() {
        waitForExpectedElement(CLOSE_LANGUAGE_SELECTOR).click();
    }

    public void clickAccountLink() {
        By accountSelector;
        if (UrlBuilder.getLocale().equalsIgnoreCase("zonnicca")) {
            accountSelector = ACCOUNT_LINK_CA;
            if (UrlBuilder.isDesktop()) {
                try {
                    waitForExpectedElement(accountSelector, 10).click();
                } catch (Exception e) {
                    clickByElementByQueryJSExecutor(accountSelector);
                }
            }
        }
    }

    public void chooseMyAccountOnAccountDropdown() {
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case ZONNICCA:
                percyPage.takePercyScreenshot("Dropdown with My Account");
                clickUsingJS(MY_ACCOUNT_ON_ACCOUNT_DROPDOWN_CA);
                break;
            default:
                waitForExpectedElement(MY_ACCOUNT_ON_ACCOUNT_DROPDOWN, 5).click();
        }
    }

    public void tryClickIAmOver18() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                clickByElementByQueryJSExecutor(buttonAgeAllowCA);
                break;
        }
    }

    public void waitForPage() {
        new WebDriverWait(webDriver, 20).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void clickOnPersonIcon() {
        switch (UrlBuilder.getLocale().toLowerCase()) {
            case "zonnicca":
                waitForExpectedElement(PERSON_ICON_ZONNIC_CA).click();
                break;
        }
    }

    public void navigateToSignInPage() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                clickPersonIcon();
                break;
        }
    }

    public void clickPersonIcon() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                try {
                    waitForExpectedElement(PERSON_ICON_ZONNIC_CA).click();
                    waitForExpectedElement(ACCOUNT_ZONNIC_CA).click();
                } catch (Exception e) {

                }
                break;
        }
    }

    public void emptyBasket() {
        if (!isBasketEmpty()) {
            waitForLoaderIconDisappear();
            try {
                clickBasketIcon();
            } catch (Exception e) {

            }
            switch (UrlBuilder.getLocale()) {
                case "zonnicca":
                    int size = getWebDriver().findElements(REMOVE_ITEM_FROM_MINIBASKET_ICON_CA).size();
                    while (size != 0) {
                        clickByElementByQueryJSExecutor(REMOVE_ITEM_FROM_MINIBASKET_ICON_CA);
                        threadSleep(3000);
                        clickBasketIcon();
                        size--;
                    }
                    break;
            }
        }
    }

    public void emptyBasketFromBasketPage() {
        waitForLoaderIconDisappear();
        int size = getWebDriver().findElements(REMOVE_FROM_BASKET).size();
        while (size != 0) {
            clickByElementByQueryJSExecutor(REMOVE_FROM_BASKET);
            threadSleep(3000);
            size--;
        }
    }

    private Boolean isBasketEmpty() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                return getTrimTextFor(CART_VALUE_CA).contains("0.00") ||
                        getTrimTextFor(CART_VALUE_CA).contains("0,00");
            default:
                return getTrimTextFor(CART_VALUE_CA).contains("0.00") ||
                        getTrimTextFor(CART_VALUE_CA).contains("0,00");
        }
    }

    public void waitForLoaderIconDisappear() {
        try {
            waitForElementToAppearAndDisappear(LOADER, 5, 5);
            waitForElementToDisappear(LOADER_ICON, 10);
        } catch (Exception e) {
            waitForElementToDisappear(LOADER_ICON, 20);
        }
    }

    public void clickBasketIcon() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                waitForExpectedElement(CART_CA).click();
                break;
        }
    }

    public void getSubTotalInMini() {
        String subTotal = null;
        try {
            subTotal = getTrimTextFor(SUBTOTAL_MINIBASKET).replace('$', ' ').trim();
        } catch (Exception e) {
            clickOnBasketIcon();
            subTotal = getTrimTextFor(SUBTOTAL_MINIBASKET).replace('$', ' ').trim();
        }
        scenarioContext.setContext(SUBTOTAL, subTotal);
    }

    public void selectCookiesAndAge(String provinceFromFeature) {
        if (provinceFromFeature.equals("<province>")) {
            provinceFromFeature = UrlBuilder.PROVINCE;
        } else {
            UrlBuilder.PROVINCE = provinceFromFeature;
        }
        try {
            waitForExpectedElement(ACCEPT_COOKIES_VUSE).click();
        } catch (Exception e) {

        }
        selectOptionFromDropDownByValue(provinceFromFeature, PROVINCE_DROPDOWN_VUSE);
        waitForExpectedElement(IAM_18_ABOVE_BUTTON_VUSE).click();
    }

    public void clickSignInIconVuse() {
        waitForExpectedElement(SIGNIN_VUSE).click();
    }

    public void acceptZonnicContent() {
        waitForExpectedElement(LOGIN_CONSENT).click();
        waitForExpectedElement(ZONNIC_CONSENT).click();
        registrationPage.whichAppliesToYou();
        waitForExpectedElement(loginPage.SIGNIN_BUTTON_CA).click();
        waitForURLToContain(UrlBuilder.getMessage("myAccountLink.key"));
    }

    public void redirectedToAppropriateProvince(String sameOrDiffProvince) {
        waitForPage();
        String province = UrlBuilder.PROVINCE;
        if (sameOrDiffProvince.equals("different") && !province.equals("Saskatchewan")) {
            province = "Saskatchewan";
        } else if (sameOrDiffProvince.equals("different") && province.equals("Saskatchewan")) {
            province = "Alberta";
        } else {
            province = UrlBuilder.PROVINCE;
        }
        String ActualProvinceAndLanguage = null;
        try {
            ActualProvinceAndLanguage = getTrimTextFor(PROVINCE_IN_HEADER);
        } catch (Exception e) {
            refreshBrowser();
            ActualProvinceAndLanguage = getTrimTextFor(PROVINCE_IN_HEADER);
        }
        String expectedProvinceAndLanguage = convertToCodeForProvince(province) + " | " + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualTextIgnoreCase(expectedProvinceAndLanguage, ActualProvinceAndLanguage);
    }

    public String convertToCodeForProvince(String province) {
        String code = null;
        if (province.equals("Alberta")) {
            code = "AB";
        } else if (province.equals("British Columbia")) {
            code = "BC";
        } else if (province.equals("Manitoba")) {
            code = "MB";
        } else if (province.equals("New Brunswick")) {
            code = "NB";
        } else if (province.equals("Newfoundland and Labrador")) {
            code = "NL";
        } else if (province.equals("Nova Scotia")) {
            code = "NS";
        } else if (province.equals("Ontario")) {
            code = "ON";
        } else if (province.equals("Prince Edward Island")) {
            code = "PI";
        } else if (province.equals("Saskatchewan")) {
            code = "SK";
        } else if (province.equals("Quebec")) {
            code = "QB";
        }
        return code;
    }

    public void cLickZonnicLogo() {
        waitForExpectedElement(ZONNIC_LOGO).click();
    }

    public void clickOnShoppingCartLink() {
        waitForExpectedElement(SHOOPING_CART_LINK).click();
        commonMethods.assertUrl("cartPageLink.key");
    }

    public void assertUrlText(String url) {
        waitForAjaxElementNotToBePresent(getWebDriver(), 4);
        assertTrue(getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(url)));
    }

    public void validateAllWhyzonnicpage() {
//        assertTrueExpectedElementDisplayed(waitForExpectedElement(WHY_ZOONIC).isDisplayed());
        assertTrueExpectedElementDisplayed(waitForExpectedElement(WHY_ZONNIC_DETAILS).isDisplayed());
        assertTrueExpectedElementDisplayed(waitForExpectedElement(WHY_ZONNIC_DETAILS_PAGE).isDisplayed());
    }

    public void faqIsDisplayed() {
        assertTrueExpectedElementDisplayed(webDriver.findElement(FAQ_MESSAGE).isDisplayed());
        waitForExpectedElement(FAQ_CONTENT_MSSG).isDisplayed();
        List<WebElement> FAQbutton = getWebDriver().findElements(FAQ_BUTTON);
        for (int i = 0; i < FAQbutton.size(); i++) {
            try {
                FAQbutton.get(i).click();
            } catch (Exception e) {
                FAQbutton = getWebDriver().findElements(FAQ_BUTTON);
                FAQbutton.get(i).click();
            }
        }
    }

    public void navigateToRequiredHeaderLink(String linkName, String url, String title) {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(linkName + "-" + UrlBuilder.LANGUAGE)));
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(linkName + "-" + UrlBuilder.LANGUAGE)));
        commonMethods.assertTitleContains(title);
        commonMethods.assertUrl(url);
        commonMethods.assertFooterTitle(title);
    }

    public void refreshBrowser1() {
        refreshBrowser();
    }

    public void validateEmptyMiniBasket(String page) {
        switch (page) {
            case "miniBasket":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(EMPTY_MINI_BASKET_TEXT), UrlBuilder.getMessage("emptyMiniBasketMessage-" + UrlBuilder.LANGUAGE));
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(SUBTOTAL_MINIBASKET1), "$0.00");
                break;
            case "basket":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(EMPTY_BASKET_TEXT1), UrlBuilder.getMessage("yourBasket-" + UrlBuilder.LANGUAGE));
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(EMPTY_BASKET_TEXT2), UrlBuilder.getMessage("emptyBasketMessage-" + UrlBuilder.LANGUAGE));
                break;
        }
    }

    public void clickOnStartShoppingButton() {
        waitForExpectedElement(START_SHOPPING).click();
        commonMethods.assertUrl("nicotinePouchesUrl.key");
    }

    public void browserBack() {
        clickBrowserBackButton();
    }

    public void validateMssg(String text) {
        switch(text){
            case "contactUsHeaderMssg" :
                headervalidation(CONTACTUS_TEXT, "contactUsHeaderMssg");
                break;
            case "LiveChatText":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(INFORMATION_DETAILS), UrlBuilder.getMessage("LiveChatText-" + UrlBuilder.LANGUAGE));
                break;
            case "callUsDetailsText":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(INFORMATION_DETAILS_1), UrlBuilder.getMessage("LiveChatText-" + UrlBuilder.LANGUAGE));
                break;
            case "contactUsDetailsText":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(INFORMATION_DETAILS_2), UrlBuilder.getMessage("LiveChatText-" + UrlBuilder.LANGUAGE));
                break;
            case "browserFaqText":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(INFORMATION_DETAILS_3), UrlBuilder.getMessage("LiveChatText-" + UrlBuilder.LANGUAGE));
                break;
        }
    }

    private void headervalidation(By xpath, String header) {
        String key = header + "-" + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(xpath));
    }

    public void userclickOnChatIcon() {
        waitForExpectedElement(START_CHAT_ICON,20);
        clickByElementByQueryJSExecutor(START_CHAT_ICON);
    }

    public void userclickOnCallusButton() {
        getWebDriver().navigate().back();
        waitForExpectedElement(CALLUS_BUTTON, 20);
        clickByElementByQueryJSExecutor(CALLUS_BUTTON);
    }

    public void logAticketButton() {
        waitForExpectedElement(LOGTICKET_BUTTON,20);
        clickByElementByQueryJSExecutor(LOGTICKET_BUTTON);
    }

    public void seeAllFaqButton() {
        getWebDriver().navigate().back();
        waitForExpectedElement(SEE_ALL_FAQ,20);
        clickByElementByQueryJSExecutor(SEE_ALL_FAQ);
    }

    public void validateTheURL(String url) {
        String key = url + "-" + UrlBuilder.LANGUAGE;
        String expectedURL = UrlBuilder.getMessage(key);
        String actualURL = getCurrentUrl();
        assertTrueExpectedTextContainsActualText(expectedURL, actualURL);
    }

    public void verifyElementsInAgeGatePopup(String element) {
        String key=null;
        switch (element) {
            case "zonnicLogo":
                assertTrueExpectedElementDisplayed(waitForExpectedElement(ZONNIC_LOGO_AGE_GATE).isDisplayed());
                break;
            case "welcomeZonnicText":
                threadSleep(3000);
                key = "welcomeToZonnicText"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(WELCOME_ZONNIC_TEXT_AGE_GATE), UrlBuilder.getMessage(key));
                break;
            case "selectProvinceText":
                key = "selectProvinceText"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(SELECT_PROVINCE_TEXT_AGE_GATE), UrlBuilder.getMessage(key));
                break;
            case "textUnderDropDown":
                if (Props.getTestSuite().equals("Live")) {
                    key = "textUnderDropDownProd" + "-" + UrlBuilder.LANGUAGE;
                } else {
                    key = "textUnderDropDown" + "-" + UrlBuilder.LANGUAGE;
                }
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(TEXT_AGE_GATE), UrlBuilder.getMessage(key));
                break;
            case "textAtLast":
                key = "textAtLast"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(TEXT_AT_LAST_AGE_GATE), UrlBuilder.getMessage(key));
                break;
            case "textAtTop":
                key = "textAtTop"+"-"+UrlBuilder.LANGUAGE;
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(TEXT_AT_TOP_AGE_GATE), UrlBuilder.getMessage(key));
                break;
            case "alphabeticalOrderOfDropdownElements":
                List allProvinces = new ArrayList();

                List<WebElement> allProvincesElement = getWebDriver().findElements(By.cssSelector("select#location"));

                for (WebElement provinceElement : allProvincesElement) {
                    String countries = provinceElement.getText();
                    allProvinces.add(countries);
                }
                System.out.println("Actual Provinces List " + allProvinces);

                List tempProvinces = new ArrayList(allProvinces);

                Collections.sort(tempProvinces);
                System.out.println("Sorted Provinces List " + tempProvinces);
                boolean ifSortedAscending = allProvinces.equals(tempProvinces);
                assertTrue(ifSortedAscending);
                break;
            case "countOfDropDownElements":
                verifyCountOfDropDownOptions(ageGate_Providence_CA, "10");
                break;
            case "oops":
                if(Props.getTestSuite().equals("Live")) {
                    assertTrueExpectedTextEqualsActualText(getTrimTextFor(OOPS_PROD), UrlBuilder.getMessage("oops-" + UrlBuilder.LANGUAGE));
                } else {
                    assertTrueExpectedTextEqualsActualText(getTrimTextFor(OOPS), UrlBuilder.getMessage("oops-" + UrlBuilder.LANGUAGE));
                }
                break;
            case "textInAgeLessPage":
                if(Props.getTestSuite().equals("Live")) {
                    assertTrueExpectedTextEqualsActualText(getTrimTextForParticularIndexElement(TEXT_IN_AGE_LESS_PAGE, 1), UrlBuilder.getMessage("lessThan18PageText-" + UrlBuilder.LANGUAGE));
                } else {
                    assertTrueExpectedTextEqualsActualText(getTrimTextFor(TEXT_IN_AGE_LESS_PAGE), UrlBuilder.getMessage("lessThan18PageText-" + UrlBuilder.LANGUAGE));
                }
                break;
            case "otherWelcomeZonnicText":
                String lang = UrlBuilder.LANGUAGE;
                if(lang.equals("en")) {
                    lang = "fr";
                } else {
                    lang = "en";
                }
                key = "welcomeToZonnicText"+"-"+lang;
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(WELCOME_ZONNIC_TEXT_AGE_GATE), UrlBuilder.getMessage(key));
                break;
        }
    }

    public void selectLanguageOrProvince(String languageOrProvince) {
        switch (languageOrProvince) {
            case "language":
                String language;
                threadSleep(2000);
                if (UrlBuilder.LANGUAGE.contains("en")) {
                    language = "English";
                } else {
                    language = "Français";
                }

                By LANGUAGE1 = By.xpath("//div[@class='bat-agegate--zonnic-content-language-list']//a[text()='" + language + "']");
                try {
                    waitForExpectedElement(LANGUAGE1).click();
                } catch (Exception e) {
                    refreshBrowser();
                    waitForExpectedElement(LANGUAGE1).click();
                }
                break;
            case "province":
                waitForExpectedElement(ageGate_Providence_CA);
                selectOptionFromDropDownByValue(UrlBuilder.PROVINCE, ageGate_Providence_CA);

                try {
                    waitForExpectedElement(ageGate_CA, 15).isDisplayed();
                    waitForExpectedElement(ageGate_CA, 15).sendKeys("09081998");
                } catch (Exception e) {

                }
                break;
            case "otherLanguage":
                String language1;
                threadSleep(2000);
                if (UrlBuilder.LANGUAGE.contains("fr")) {
                    language = "English";
                } else {
                    language = "Français";
                }

                By LANG = By.xpath("//div[@class='bat-agegate--zonnic-content-language-list']//a[text()='" + language + "']");
                try {
                    waitForExpectedElement(LANG).click();
                } catch (Exception e) {
                    refreshBrowser();
                    waitForExpectedElement(LANG).click();
                }
                break;
        }
    }

    public void clickOnButton(String button) {
        switch (button) {
            case "YesIam18+":
                commonMethods.assertButtonText(getTrimTextFor(BUTTONTEXT18PLUS), "moreThan18");
                clickByElementByQueryJSExecutor(buttonAgeAllowCA);
                break;
            case "NoIamNot18+":
                commonMethods.assertButtonText(getTrimTextFor(BUTTONTEXTLESS18), "lessThan18");
                clickByElementByQueryJSExecutor(buttonAgeLess);
                break;
        }
    }

    public void selectOtherLanguage() {
        String language;
        if(UrlBuilder.LANGUAGE.equalsIgnoreCase("en")) {
            language = "Français";
        } else {
            language = "English";
        }

        webDriver.findElement(By.xpath("//div[@class='bat-location--zonnic-content-language-list']//div//input[@id='"+language+"']")).click();
    }

    public void validateElementsInLanguageSelector(String element) {
        String lang = UrlBuilder.LANGUAGE;
        if(lang.equals("en")) {
            lang = "fr";
        } else {
            lang = "en";
        }
        switch (element) {
            case "languageSelectorHeading":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(LANGUAGE_SELECTOR_HEADER), UrlBuilder.getMessage("languageSelectorHeading-" + UrlBuilder.LANGUAGE));
                break;
            case "languageSelectorText":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(LANGUAGE_SELECTOR_TEXT), UrlBuilder.getMessage("languageSelectorText-" + UrlBuilder.LANGUAGE));
                break;
            case "selectYourLanguageHeader":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(SELECT_YOUR_LANGUAGE_HEADER), UrlBuilder.getMessage("selectYourLanguageHeader-" + UrlBuilder.LANGUAGE));
                break;
            case "otherLanguageSelectorHeading":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(LANGUAGE_SELECTOR_HEADER), UrlBuilder.getMessage("languageSelectorHeading-" + lang));
                break;
            case "otherLanguageSelectorText":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(LANGUAGE_SELECTOR_TEXT), UrlBuilder.getMessage("languageSelectorText-" + lang));
                break;
            case "otherSelectYourLanguageHeader":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(SELECT_YOUR_LANGUAGE_HEADER), UrlBuilder.getMessage("selectYourLanguageHeader-" + lang));
                break;
        }
    }

    public void clickOnConfirmButton() {
        waitForExpectedElement(CONFIRM_BUTTON).click();
    }

    public void userclicksOnTheExploreFlavour(String link) {
        try {
            waitForExpectedElement(By.linkText(UrlBuilder.getMessage(link + "-" + UrlBuilder.LANGUAGE)));
            clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(link + "-" + UrlBuilder.LANGUAGE)));
        }catch(Exception e){
            clickByElementByQueryJSExecutor(EXPLORE_FLAVOURS);
        }
    }

    public void navigateBackTopage() {
        getWebDriver().navigate().back();
    }

    public void secondExploreFlavours() {
        waitForExpectedElement(EXPLORE_FLAVOURS_2,10);
        clickByElementByQueryJSExecutor(EXPLORE_FLAVOURS_2);
    }

    public void clickOnMoreLink() {
        try {
            waitForExpectedElement(LEARN_MORE_BUTTON, 10);
            clickByElementByQueryJSExecutor(LEARN_MORE_BUTTON);
        } catch (Exception e) {
            waitForExpectedElement(LEARN_MORE_BUTTON1, 10);
            clickByElementByQueryJSExecutor(LEARN_MORE_BUTTON1);
        }
    }

    public void verifyUrl(String url) {
        String actualURL = getCurrentUrl();
        String expectedURL = UrlBuilder.getMessage(url);
        assertTrueExpectedTextContainsActualText(expectedURL, actualURL);
    }

    public void assertFaqTextIsDisplayed() {
        assertTrueExpectedElementDisplayed(webDriver.findElement(FAQ_MESSAGE_1).isDisplayed());
        waitForExpectedElement(FAQ_CONTENT_MSSG).isDisplayed();
        List<WebElement> FAQbutton = getWebDriver().findElements(FAQ_BUTTON);
        for (int i = 0; i < FAQbutton.size(); i++) {
            try {
                FAQbutton.get(i).click();
            } catch (Exception e) {
                FAQbutton = getWebDriver().findElements(FAQ_BUTTON);
                FAQbutton.get(i).click();
            }
        }
    }

    public void clickOnFirstsLearnmoreButton() {
        waitForExpectedElement(LEARN_MORE_BUTTON2, 10);
        clickByElementByQueryJSExecutor(LEARN_MORE_BUTTON2);
    }
}