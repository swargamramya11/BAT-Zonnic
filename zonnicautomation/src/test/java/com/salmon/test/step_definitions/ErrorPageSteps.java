package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.*;
import cucumber.api.java.en.And;

public class ErrorPageSteps {

    private ErrorPage errorPage;

    public ErrorPageSteps(ErrorPage errorPage) {
        this.errorPage = errorPage;
    }

    @And("^validate '(.*)'$")
    public void validateElements(String element) {
       errorPage.validateElements(element);
    }

    @And("^click on '(.*)' button in error Page$")
    public void clickOnButton(String buttonName) {
        errorPage.clickOnButton(buttonName);
    }
}
