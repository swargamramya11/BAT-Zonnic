package com.salmon.test.step_definitions;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class NavigationSteps {
    private ScenarioContext scenarioContext;

    public NavigationSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("^user navigates to Vuse site$")
    public void userNavigatesToVuseSite() {
        UrlBuilder.urlBuilderForVuse();
    }

    @Given("^user invokes browser$")
    public void userInvokesBrowser() {
        UrlBuilder.invokeBrowser();
    }

    @Given("^user navigates to Zonnic site$")
    public void userNavigatesToZonnicSite() {
        UrlBuilder.urlBuilder();
    }

    @And("^user navigates to invalid url$")
    public void navigateToInvalidURL() {
        UrlBuilder.navigateToRelativeUrl("null");
    }
}

