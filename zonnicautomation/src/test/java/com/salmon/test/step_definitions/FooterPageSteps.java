package com.salmon.test.step_definitions;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.FooterPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import static org.testng.Assert.assertTrue;

public class FooterPageSteps {

    public FooterPage footerPage;

    public FooterPageSteps(FooterPage footerPage) {
        this.footerPage = footerPage;
    }

    @And("^user clicks on the '(.*)' text$")
    public void userClickOnFooterZonnicLogo(String footerlogotitle) {
        footerPage.userClickOnFooterZonnicLogo(footerlogotitle);
    }

    @And("^verify link '(.*)'$")
    public void verifyLink(String url) {
        footerPage.verifyLink(url);
    }

    @And("^user click on instagram link$")
    public void instagramLink() throws InterruptedException {
        footerPage.instagramLink();
        footerPage.waitForURLToContain(UrlBuilder.getMessage("InstagramURL.key"));
    }

    @Then("^verify below warningmessage displayed$")
    public void belowWarningMssg() {
        if(Props.getTestSuite().equals("Live")) {
            assertTrue(footerPage.getwarningMessage().contains(UrlBuilder.getMessage("warningMessageProd" + "-" + UrlBuilder.LANGUAGE)));
        } else {
            assertTrue(footerPage.getwarningMessage().contains(UrlBuilder.getMessage("warningMessage" + "-" + UrlBuilder.LANGUAGE)));
        }
    }

    @And("^assert url contains '(.*)'$")
    public void assertUrlContains(String instagramUrl) {
        footerPage.verifyInstagramUrl(instagramUrl);
    }

    @And("^user validate '(.*)' in footer$")
    public void validateFooter(String footerText) {
        footerPage.validateFooterText(footerText);
    }

    @And("^user click on telephone number and cancel alert$")
    public void clickOnTelephoneNumber() {
        footerPage.clickOnTelephoneNumber();
    }

    @And("^validate opening hours$")
    public void validateOpeningHours() {
        footerPage.validateOpeningHours();
    }

    @And("^user click on submit a ticket$")
    public void clickOnSubmitTicket() {
        footerPage.clickOnSubmitTicket();
    }

    @And("^user click on instagram icon in home page$")
    public void clickOnInstagramIcon() {
        footerPage.clickOnInstagramIcon();
    }
}