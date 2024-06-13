package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import org.openqa.selenium.By;

public class ErrorPage extends PageObject {
    public static By TEXT1 = By.cssSelector(".card .bat-card .bat-card--404-error .bat-card--404-error-heading h4");
    public static By TEXT2 = By.cssSelector(".card .bat-card .bat-card--404-error .bat-card--404-error-text p");
    public static By CONTINUE_BROWSING_BUTTON = By.cssSelector(".bat-card--404-error-cta a");
    public static By CONTINUE_BROWSING_BUTTON_TEXT = By.cssSelector(".bat-card--404-error-cta a span");

    CommonMethods commonMethods;
    public ErrorPage(CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
    }

    public void validateElements(String element) {
        switch (element) {
            case "text1":
                threadSleep(2000);
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("errorPagetext1"+"-"+UrlBuilder.LANGUAGE), getTrimTextForParticularIndexElement(TEXT1, 0));
                break;
            case "text2":
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("errorPagetext2"+"-"+UrlBuilder.LANGUAGE), getTrimTextForParticularIndexElement(TEXT2, 0));
                break;
        }
    }

    public void clickOnButton(String buttonName) {
        switch (buttonName) {
            case "continueBrowsing":
                commonMethods.assertTitle("errorPageTitle");
                commonMethods.assertFooterTitle("errorPageTitle");
                commonMethods.assertButtonText(getTrimTextForParticularIndexElement(CONTINUE_BROWSING_BUTTON_TEXT, 0), "continueBrowsingButtonText");
                webDriver.findElements(CONTINUE_BROWSING_BUTTON).get(0).click();
                commonMethods.assertUrl("homePageLink.key");
                break;
            case "contactUs":
                commonMethods.assertTitle("errorPageTitle");
                commonMethods.assertFooterTitle("errorPageTitle");
                commonMethods.assertButtonText(getTrimTextForParticularIndexElement(CONTINUE_BROWSING_BUTTON_TEXT, 1), "contactUsButtonText");
                webDriver.findElements(CONTINUE_BROWSING_BUTTON).get(1).click();
                commonMethods.assertUrl("contactUsUrlForSignUp.key");
                break;
        }
    }
}