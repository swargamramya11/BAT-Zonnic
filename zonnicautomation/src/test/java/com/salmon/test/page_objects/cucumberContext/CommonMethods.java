package com.salmon.test.page_objects.cucumberContext;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import static com.salmon.test.page_objects.constants.Context.URL;
import static org.testng.AssertJUnit.assertTrue;

public class CommonMethods extends PageObject {

    private ScenarioContext scenarioContext;
    public final static By BREAD_CRUMB = By.cssSelector(".bat-footer-zonnic-container .bat-footer-zonnic-label-page-breadcrumb");

    public CommonMethods(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    public void assertUrl(String key) {
        String baseURL = (String) scenarioContext.getContext(URL);
        String expectedURL = baseURL + UrlBuilder.getMessage(key);
        String actualURL = getCurrentUrl();
        assertTrueExpectedTextEqualsActualText(expectedURL, actualURL);
    }

    public void assertUrlContains(String key) {
        String baseURL = (String) scenarioContext.getContext(URL);
        String expectedURL = baseURL + UrlBuilder.getMessage(key);
        String actualURL = getCurrentUrl();
        assertTrueExpectedTextContainsActualText(expectedURL, actualURL);
    }

    public void assertUrlContainsText(String key) {
        String expectedURL = UrlBuilder.getMessage(key);
        String actualURL = getCurrentUrl();
        assertTrueExpectedTextContainsActualText(expectedURL, actualURL);
    }

    public void assertTitle(String key) {
        String key1 = key+"-"+UrlBuilder.LANGUAGE;
        String expectedTitle = UrlBuilder.getMessage(key1);
        LOG.info(expectedTitle);
        String actualTitle = getCurrentPageTitle();
        LOG.info(actualTitle);
        assertTrueExpectedTextEqualsActualText(expectedTitle, actualTitle);
    }

    public void assertTitleContains(String key) {
        String key1 = key+"-"+UrlBuilder.LANGUAGE;
        String expectedTitle = UrlBuilder.getMessage(key1);
        LOG.info(expectedTitle);
        String actualTitle = getCurrentPageTitle();
        LOG.info(actualTitle);
        assertTrue(actualTitle.contains(expectedTitle));
    }

    public void assertButtonText(String actualText, String expectedTextKey) {
        String expectedText = getKeyForTextValidations(expectedTextKey);
        assertTrueExpectedTextEqualsActualText(actualText, expectedText);
    }

    public void assertFooterTitle(String key) {
        String key1 = key+"-"+UrlBuilder.LANGUAGE;
        String key2 = "breadCrumb"+"-"+UrlBuilder.LANGUAGE;
        String expectedFooterTitle = UrlBuilder.getMessage(key2)+" "+UrlBuilder.getMessage(key1).toUpperCase();
        LOG.info(expectedFooterTitle);
        String actualFooterTitle = getTrimTextFor(BREAD_CRUMB);
        LOG.info(actualFooterTitle);
        assertTrueExpectedTextEqualsActualText(expectedFooterTitle, actualFooterTitle);
    }
}