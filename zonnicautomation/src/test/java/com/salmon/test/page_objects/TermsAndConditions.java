package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

public class TermsAndConditions extends PageObject {
    public static final By TERMS_CONDITION_BUTTON = By.cssSelector(".bat-form-field.bat-form--checkout-payment-cc-payment-tos button");
    public static final By CLOSE_BUTTON = By.cssSelector("#termsAndConditions div button.bat-cta-style.bat-modal-close");
    public static final By TERMS_CONDITION_HEADER = By.cssSelector(".bat-headline h1");
    public void validateTermsAndConditions(String page){
        switch (page) {
            case "checkout":
                waitForItemToBeClickableAndClick(TERMS_CONDITION_BUTTON);
                String key;
                if(Props.getTestSuite().equalsIgnoreCase("Live")) {
                    key = "terms&ConditionsHeaderProd" + "-" + UrlBuilder.LANGUAGE;
                } else {
                    key = "terms&ConditionsHeader" + "-" + UrlBuilder.LANGUAGE;
                }
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(TERMS_CONDITION_HEADER));
                waitForItemToBeClickableAndClick(CLOSE_BUTTON);
                break;
        }
    }
}