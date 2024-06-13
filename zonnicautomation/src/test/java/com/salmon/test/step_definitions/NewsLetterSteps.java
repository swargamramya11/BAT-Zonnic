package com.salmon.test.step_definitions;

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.Newsletter;
import cucumber.api.java.en.And;

public class NewsLetterSteps extends PageObject {
    private Newsletter newsletter;
    public NewsLetterSteps(Newsletter newsletter) {
        this.newsletter = newsletter;
    }

    @And("^validate error message of '(.*)' in newsletter$")
    public void validateErrors(String error) {
        newsletter.validateErrors(error);
    }

    @And("^validate header '(.*)' in newsletter$")
    public void validateHeader(String header) {
        newsletter.validateHeader(header);
    }

    @And("^click on link '(.*)' in newsletter$")
    public void clickOnLinks(String link) {
        newsletter.clickOnLinks(link);
    }

    @And("^validate if field '(.*)' is displayed in newsletter$")
    public void validateFieldsDisplayedOrNot(String fieldName) {
        newsletter.validateFieldsDisplayedOrNot(fieldName);
    }

    @And("^validate if field header '(.*)' is displayed in newsletter$")
    public void validateFieldsHeaderDisplayedOrNot(String fieldNameHeader) {
        newsletter.validateFieldsHeaderDisplayedOrNot(fieldNameHeader);
    }

    @And("^enter data '(.*)'$")
    public void enterData(String fieldName) {
        newsletter.enterData(fieldName);
    }

    @And("^select which applies to you dropdown$")
    public void whichApplies() {
        newsletter.whichApplies();
    }
}
