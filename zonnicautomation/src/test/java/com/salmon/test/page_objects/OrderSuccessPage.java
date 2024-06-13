package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import org.openqa.selenium.By;
import static com.salmon.test.page_objects.constants.Context.LANGUAGE;
import static com.salmon.test.page_objects.constants.Context.ORDER_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderSuccessPage extends PageObject {
    private ScenarioContext scenarioContext;
    private CommonMethods commonMethods;
    public static By MYACCOUNT_CHECKOUT = By.cssSelector(".bat-order-confirmation-ctalist .bat-cta-style.button-dark.center");
    public static By ORDER_NUMBER_CHECKOUT = By.cssSelector(".bat-order-confirmation-message .bat-order-confirmation-message-text p");
    public static By MYACCOUNT_CHECKOUT_BUTTON_TEXT = By.cssSelector(".bat-order-confirmation-ctalist .bat-cta-style.button-dark.center span");
    public static By RETURN_TO_HOME_CHECKOUT_BUTTON_TEXT = By.cssSelector(".bat-order-confirmation-ctalist .bat-cta-style.arrow-link-dark-back.center span");
    public static By RETURN_TO_HOME_CHECKOUT = By.cssSelector(".bat-order-confirmation-ctalist .bat-cta-style.arrow-link-dark-back.center");

    public OrderSuccessPage(TestContext testContext, CommonMethods commonMethods) {
        this.scenarioContext = testContext.getScenarioContext();
        this.commonMethods = commonMethods;
    }

    public void clickButtonInThankYouPage(String buttonName) {
        switch (buttonName) {
            case "MyAccountButton":
                commonMethods.assertButtonText(getTrimTextFor(MYACCOUNT_CHECKOUT_BUTTON_TEXT), "goToMyAccountButtonText");
                waitForExpectedElement(MYACCOUNT_CHECKOUT).click();
                assertThat(getCurrentUrl().contains("my-account"));
                commonMethods.assertTitle("myAccountTitle");
                commonMethods.assertFooterTitle("myAccountTitle");
                commonMethods.assertUrl("myAccountLink.key");
                break;
            case "returnToHomeButton":
                commonMethods.assertButtonText(getTrimTextFor(RETURN_TO_HOME_CHECKOUT_BUTTON_TEXT), "returnToHomeButton");
                waitForExpectedElement(RETURN_TO_HOME_CHECKOUT).click();
                commonMethods.assertTitle("HomePageTitle");
                commonMethods.assertFooterTitle("homePageFooterTitle");
                commonMethods.assertUrl("homePageLink.key");
                break;
        }
    }

    public void getOrderNumber() {
        String order_Number = getTrimTextFor(ORDER_NUMBER_CHECKOUT);
        String orderNumber[] = order_Number.split(" ");
        if (UrlBuilder.LANGUAGE.equalsIgnoreCase("fr")) {
            scenarioContext.setContext(ORDER_NUMBER, orderNumber[6]);
        } else {
            scenarioContext.setContext(ORDER_NUMBER, orderNumber[4]);
        }
    }
}