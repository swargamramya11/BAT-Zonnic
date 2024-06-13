package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StoreLocatorPage extends PageObject {

    private CommonMethods commonMethods;
    public StoreLocatorPage(CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
    }

    public By HEADER = By.cssSelector(".bat-storelocator--title");
    public By SUB_TITLE = By.cssSelector(".bat-storelocator--subtitle");
    public By USE_CURRENT_LOCATION_TEXT = By.cssSelector(".bat-storelocator--getloc-label");
    public By ENTER_LOCATION = By.cssSelector(".bat-storelocator--search .mapboxgl-ctrl-geocoder--input");
    public By SUGGESTIONS = By.cssSelector(".suggestions-wrapper .suggestions .mapboxgl-ctrl-geocoder--suggestion");
    public By REMOVE_TEXT = By.cssSelector("button.mapboxgl-ctrl-geocoder--button");
    public By SEARCH_ICON = By.cssSelector(".mapboxgl-ctrl-geocoder--icon.mapboxgl-ctrl-geocoder--icon-search");
    public By GET_DIRECTIONS_BUTTON = By.cssSelector(".button.bat-cta-style.button-secondary-dark");

    public void headervalidation(By xpath, String header) {
        String key = header + "-" + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(xpath));
    }
    public void validateText(String text) {
        switch (text) {
            case "storeLocatorHeader":
                headervalidation(HEADER, "storeLocatorHeader");
                break;
            case "belowHeader":
                headervalidation(SUB_TITLE, "storeLocatorHeaderText");
                break;
            case "useCurrentLocationText":
                headervalidation(USE_CURRENT_LOCATION_TEXT, "useCurrentLocationText");
                break;
        }
    }

    public void enterData() {
        enterText(ENTER_LOCATION, "Alberta");
        waitForExpectedElement(REMOVE_TEXT).click();
        waitForExpectedElement(SEARCH_ICON).isDisplayed();
        enterText(ENTER_LOCATION, "Alberta");
        threadSleep(2000);
        String text = getTrimTextForParticularIndexElement(SUGGESTIONS, 1).replace("\n",", ");
        webDriver.findElements(SUGGESTIONS).get(1).click();
        String text1 = getValue(ENTER_LOCATION);
        assertTrueExpectedTextEqualsActualText(text, text1);
    }

    public void clickGetDirectionsButton(String buttonname) {
        switch (buttonname) {
            case "getDirections":
                commonMethods.assertButtonText(getTrimTextFor(GET_DIRECTIONS_BUTTON), "getDirectionsButtonText");
                clickForParticularIndexElement(GET_DIRECTIONS_BUTTON, 0);
                switchBetweenWindowTabs(1);
                commonMethods.assertTitleContains("googleTitle");
                assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("googleUrl.key"), getCurrentUrl());
                switchBetweenWindowTabs(0);
                break;
            case "currentLocation":
                clickForParticularIndexElement(USE_CURRENT_LOCATION_TEXT, 0);
                break;
        }
    }

    public void clickLocationMapbox() {
        List<WebElement> locations = webDriver.findElements(By.xpath("//div[@class='mapboxgl-marker mapboxgl-marker-anchor-center']"));
        int size = locations.size();
        for (int i = 1; i <= size; i++) {
            clickWithinElementUsingActions(webDriver.findElement(By.xpath("(//div[@class='mapboxgl-marker mapboxgl-marker-anchor-center'])[" + i + "]")));
            clickWithinElementUsingActions(webDriver.findElement(By.xpath("//div[@class='mapboxgl-popup-content']//a")));
            switchBetweenWindowTabs(1);
            commonMethods.assertTitleContains("googleTitle");
            assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("googleUrl.key"), getCurrentUrl());
            switchBetweenWindowTabs(0);
        }
    }
}