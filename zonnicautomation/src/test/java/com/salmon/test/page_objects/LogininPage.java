package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import org.openqa.selenium.*;
import static com.salmon.test.page_objects.constants.Context.*;

public class LogininPage extends PageObject {
    private ScenarioContext scenarioContext;
    private CommonMethods commonMethods;
    private HomePage homePage = new HomePage();
    private PercyPage percyPage;

    public LogininPage(PercyPage percyPage, TestContext testContext, CommonMethods commonMethods) {
        this.scenarioContext = testContext.getScenarioContext();
        this.commonMethods = commonMethods;
        this.percyPage = percyPage;
    }

    public By ToRegisterBtnZonnicCA = By.cssSelector(".bat-form-field.bat-form--login-create-account a");
    public By ToRegisterBtn_Text = By.cssSelector(".bat-form-field.bat-form--login-create-account a span");
    public final static By EMAIL_INPUTBOX = By.cssSelector("#email");
    public final static By PASSWORD_INPUTBOX = By.cssSelector("#password");
    public final static By SIGNIN_BUTTON_CA = By.cssSelector(".bat-form.bat-form--login-zonnic button[type='submit']");
    public By elePageTitle = By.cssSelector("span.base");
    public By CREATE_ACCONT_BUTTON_VUSE = By.cssSelector(".bat-cta a");
    public By CREATE_ACCOUNT_BUTTON_IN_GUEST = By.cssSelector(".bat-cta.bat-cta-list--vertical a[href*='signup']");
    public By EYE_ICON_SIGN_IN = By.cssSelector(".toggle-visibility");

    public void clickCreateAccountButton(String guestOrNormalUser) {
        switch (guestOrNormalUser) {
            case "normalUser":
                commonMethods.assertButtonText(getTrimTextFor(ToRegisterBtn_Text), "createAccountButtonHomePage");
                waitForExpectedElement(ToRegisterBtnZonnicCA);
                clickByElementByQueryJSExecutor(ToRegisterBtnZonnicCA);
                commonMethods.assertTitle("signUpTitle");
                commonMethods.assertFooterTitle("signUpTitle");
                break;
            case "guestUser":
                waitForExpectedElement(CREATE_ACCOUNT_BUTTON_IN_GUEST);
                clickByElementByQueryJSExecutor(CREATE_ACCOUNT_BUTTON_IN_GUEST);
                commonMethods.assertUrl("createAccountForGuestLink.key");
                break;
        }
    }

    public void loginWithConfigProperties(String username, String password, String guestOrNormal) {
        String actualUserName = null;
        if(username.contains("loginInvalidEmail")){
            actualUserName = username;
        } else {
            String provinceCode = getProvinceCode(UrlBuilder.PROVINCE);
            actualUserName = username + "-" + provinceCode + "-" + UrlBuilder.LANGUAGE;
        }
        login(UrlBuilder.getMessage(actualUserName), UrlBuilder.getMessage(password));

        if(guestOrNormal.equals("normalUser") && username.equalsIgnoreCase("loginValidEmail.key")) {
            commonMethods.assertUrl("myAccountLink.key");
            homePage.emptyBasket();
        } else if(guestOrNormal.equals("guestUser") && username.equalsIgnoreCase("loginValidEmail.key")){
            commonMethods.assertUrl("shippingAnPaymentLink.key");
        }
    }

    public void login(String username, String password) {
        waitForAjaxElementNotToBePresent(webDriver, 5);
        enterText(EMAIL_INPUTBOX, username);
        enterText(PASSWORD_INPUTBOX, password);
        waitForExpectedElement(EYE_ICON_SIGN_IN).click();
        percyPage.takePercyScreenshot("Login Page after filling details");
        waitForExpectedElement(SIGNIN_BUTTON_CA).click();
        waitForExpectedElement(elePageTitle, 5);
        threadSleep(3000);
    }

    public void clickCreateAccountButtonVuse(){
        waitForExpectedElement(CREATE_ACCONT_BUTTON_VUSE);
        clickByElementByQueryJSExecutor(CREATE_ACCONT_BUTTON_VUSE);
    }

    public void loginWithNewLyCreatedCredentials(String passWord) {
        waitForAjaxElementNotToBePresent(webDriver, 5);
        waitClearAndEnterText(EMAIL_INPUTBOX, (String) scenarioContext.getContext(EMAIL_ID));
        waitClearAndEnterText(PASSWORD_INPUTBOX, UrlBuilder.getMessage(passWord));
        waitForExpectedElement(EYE_ICON_SIGN_IN).click();
        waitForExpectedElement(SIGNIN_BUTTON_CA).click();
        threadSleep(3000);
    }

    public void clickCreateAccountButton() {
        waitForExpectedElement(ToRegisterBtnZonnicCA);
        clickByElementByQueryJSExecutor(ToRegisterBtnZonnicCA);
    }
}

