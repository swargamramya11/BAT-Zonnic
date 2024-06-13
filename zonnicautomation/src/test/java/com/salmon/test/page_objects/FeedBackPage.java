package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;

public class FeedBackPage extends PageObject {
    public static By FEEDBACK_BUTTON = By.cssSelector(".QSIFeedbackButton button");
    public static By QUESTION = By.cssSelector(".QuestionText.BorderColor");
    public static By NEXT_BUTTON = By.cssSelector("#NextButton");
    public static By BACK_BUTTON = By.cssSelector("#PreviousButton");
    public static By COMMENTS_TEXTBOX = By.cssSelector(".ChoiceStructure input");
    public static By END_OF_SURVEY_TEXT = By.cssSelector("#EndOfSurvey");
    public static By CONTACT_US_TEXT = By.cssSelector(".QuestionText.BorderColor");
    public static By NEXTFEEDBACK_BUTTON = By.cssSelector(".NextButton.Button");
    public static By TEXT = By.cssSelector("#EndOfSurvey");

    public void clickOnButton(String buttonName) {
        switch (buttonName) {
            case "feedback":
                waitForItemToBeClickableAndClick(FEEDBACK_BUTTON);
                switchBetweenFrameTabsWithID("QSIFeedbackButton-survey-iframe");
                break;
            case "nextButton":
                waitForExpectedElement(NEXT_BUTTON).click();
                threadSleep(5000);
                break;
            case "backButton":
                clickByElementByQueryJSExecutor(BACK_BUTTON);
                threadSleep(5000);
                break;
        }
    }

    public void validateText(String text) {
        switch (text) {
            case "questionPart1":
            case "giveWebSiteFeedbackQuestion":
            case "satisfiedAndVerySatisfiedQuestion":
            case "veryDisSatisfiedAndDisSatisfied":
            case "neitherSatisfiedNorDisSatisfied":
            case "bestDescribes":
            case "wereUAbleToSuccessfullyComplete":
            case "whatElseYouShare":
            case "reportIssueQuestion":
            case "otherQuestion":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(QUESTION), UrlBuilder.getMessage(text+"-"+UrlBuilder.LANGUAGE));
                break;
            case "questionPart2":
                assertTrueExpectedTextEqualsActualText(getTrimTextForParticularIndexElement(QUESTION, 1), UrlBuilder.getMessage("questionPart2"+"-"+UrlBuilder.LANGUAGE));
                break;
            case "surveyEndMessage":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(END_OF_SURVEY_TEXT), UrlBuilder.getMessage(text+"-"+UrlBuilder.LANGUAGE));
                break;
            case "contactUsPageText":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(CONTACT_US_TEXT), UrlBuilder.getMessage(text+"-"+UrlBuilder.LANGUAGE));
                break;
        }
    }

    public void selectOption(String option) {
        String option_1 = option+"-"+UrlBuilder.LANGUAGE;
        try {
            webDriver.findElement(By.xpath("//span[text()='" + UrlBuilder.getMessage(option_1) + "']/parent::label")).click();
        }catch (Exception e) {

        }
    }

    public void enterComments() {
        enterText(COMMENTS_TEXTBOX, "zonnic site is good");
    }

    public void changeFrame() {
        switchBetweenFrameTabsWithID("QSIFeedbackButton-survey-iframe");
    }

    public void clickNexttoContiueSurvey() {
        boolean yes = false;
        try {
            waitForExpectedElement(TEXT).isDisplayed();
            yes = true;
        } catch (Exception e) {
            yes = false;
        }
        if (yes) {
            try {
                clickByElementByQueryJSExecutor(NEXTFEEDBACK_BUTTON);
            } catch (Exception e) {
                waitForExpectedElement(NEXTFEEDBACK_BUTTON).click();
            }
        }
    }
}
