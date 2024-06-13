package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.FeedBackPage;
import cucumber.api.java.en.And;

public class FeedBackFormSteps {
    public FeedBackPage feedBackPage;

    public FeedBackFormSteps(FeedBackPage feedBackPage) {
        this.feedBackPage = feedBackPage;
    }

    @And("^click '(.*)' button$")
        public void clickOnButton(String buttonName) {
        feedBackPage.clickOnButton(buttonName);
    }

    @And("^validate '(.*)' in feedback page$")
    public void validateText(String text) {
        feedBackPage.validateText(text);
    }

    @And("^select an option of '(.*)'$")
    public void selectOption(String option) {
        feedBackPage.selectOption(option);
    }

    @And("^enter comments in text box$")
    public void enterComments() {
        feedBackPage.enterComments();
    }

    @And("^change frame$")
    public void changeFrame() {
        feedBackPage.changeFrame();
    }

    @And("^click on NEXT button to continue survey$")
    public void clickNexttoContiueSurvey(){
        feedBackPage.clickNexttoContiueSurvey();
    }
}