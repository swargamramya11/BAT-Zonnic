package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.StoreLocatorPage;
import cucumber.api.java.en.And;

public class StoreLocatorSteps {

    private StoreLocatorPage storeLocatorPage;
    public StoreLocatorSteps(StoreLocatorPage storeLocatorPage) {
        this.storeLocatorPage = storeLocatorPage;
    }

    @And("^validate text '(.*)'$")
    public void validateText(String text) {
        storeLocatorPage.validateText(text);
    }

    @And("^enter data$")
    public void enterData() {
        storeLocatorPage.enterData();
    }

    @And("^click on '(.*)' button$")
    public void clickGetDirectionsButton(String buttonname) {
        storeLocatorPage.clickGetDirectionsButton(buttonname);
    }

    @And("^click on locations in mapbox$")
    public void clickLocationMapbox() {
        storeLocatorPage.clickLocationMapbox();
    }
}