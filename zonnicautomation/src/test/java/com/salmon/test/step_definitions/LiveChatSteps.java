package com.salmon.test.step_definitions;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.LiveChatPage;
import cucumber.api.java.en.Then;
public class LiveChatSteps {
    LiveChatPage liveChatPage;

    public LiveChatSteps(LiveChatPage liveChatPage){
        this.liveChatPage=liveChatPage;
    }

    @Then("^user click on chat icon$")
    public void chat_icon() {
        liveChatPage.openChatWindow();
    }

    @Then("^user click on talk to human button$")
    public void talkToHuman() {
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            liveChatPage.talkToHuman();
        }
    }
    @Then("^enter issue description in chat box$")
    public void enterIssueDescription() {
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            liveChatPage.enterIssueDescription();
        }
    }

    @Then("^click on toggle to off sound$")
    public void soundToggleOff() {
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            liveChatPage.soundToggleOff();
        }
    }

    @Then("^close the chat icon$")
    public void closeChatIcon() {
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            liveChatPage.closeChatIcon();
        }
    }
}
