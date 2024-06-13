package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import org.openqa.selenium.By;

public class FooterPage extends PageObject {
    public CommonMethods commonMethods;

    public FooterPage(CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
    }

    public static final By ZONNIC_FOOTER_LOGO = By.xpath("//div[@class='bat-footer-zonnic-logo bat-logo--top']//a");
    public static By INSTAGRAM_LINK = By.xpath("//div[contains(@class,'bat-footer-zonnic-nav-menu ava-nav-menu')]//i[contains(@class,'bat-icon instagram')]");
    public static By WARNING_MESSG_FR = By.xpath("//p[contains(text(),'Les produits ZONNIC ne conviennent pas aux personn')]");
    public static By WARNING_MESSG_EN = By.xpath("//p[text()='ZONNIC products are not suitable for use by persons under 18 years of age.']");
    public static By WARNING_MESSG_EN1 = By.xpath("//footer[@class='bat-footer-bottom bat-footer-zonnic-disclaimer'] //p//b");
    public static By FOOTER_TEXT = By.cssSelector(".bat-footer-zonnic-nav .bat-footer-zonnic-nav-menu.ava-nav-menu .bat-footer-zonnic-nav-menu-title.menu-title");
    public static By TELEPHONE = By.xpath("//a[@data-cta-type='telephone']");
    public static By OPENING_HOURS_DROPDOWN = By.cssSelector(".opening-hours-group-start div i");
    public static By OPENING_HOURS = By.cssSelector(".submenu-content.opening-hours-element a");
    public static By OPENING_HOURS_PROD1 = By.cssSelector(".bat-footer-zonnic-nav-menu.ava-nav-menu .bat-footer-zonnic-submenu.submenu .submenu-content a");
    public static By SUBMIT_A_TICKET = By.xpath("//div[@class='submenu-content']//a[contains(text(),'Submit a ticket')]");
    public static By SUBMIT_A_TICKET_FR = By.xpath("//div[@class='submenu-content']//a[contains(text(),'SOUMETTRE UNE DEMANDE')]");
    public static By INSTAGRAM_ICON = By.cssSelector(".bat-section.bat-section-style.fit .responsivegrid .aem-Grid.aem-Grid--12.aem-Grid--default--12.aem-Grid--phone--12 .bat-image a");

    public void verifyLink(String url) {
        commonMethods.assertUrl(url);

    }

    public void userClickOnFooterZonnicLogo(String footerlogotitle) {
        hoverOnElement(ZONNIC_FOOTER_LOGO);
        waitForExpectedElement(ZONNIC_FOOTER_LOGO, 10);
        clickByElementByQueryJSExecutor(ZONNIC_FOOTER_LOGO);
        commonMethods.assertTitle(footerlogotitle);
    }

    public void instagramLink() throws InterruptedException {
        waitForExpectedElement(INSTAGRAM_LINK, 10);
        clickByElementByQueryJSExecutor(INSTAGRAM_LINK);
        Thread.sleep(2000);
    }

    public String getwarningMessage() {
        try {
            return getTrimTextFor(WARNING_MESSG_FR);
        } catch (Exception e) {
            try {
                return getTrimTextFor(WARNING_MESSG_EN);
            }catch (Exception e1) {
                return getTrimTextFor(WARNING_MESSG_EN1);
            }
        }
    }

    public void verifyInstagramUrl(String instagramUrl) {
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            String expectedURL = UrlBuilder.getMessage(instagramUrl);
            String actualURL = getCurrentUrl();
            assertTrueExpectedTextContainsActualText(expectedURL, actualURL);
        }
    }

    public void validateFooterText(String footerText) {
        switch (footerText) {
            case "getInTouchText":
                threadSleep(3000);
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("getInTouchText-" + UrlBuilder.LANGUAGE), getTrimTextForParticularIndexElement(FOOTER_TEXT, 2));
                break;
            case "legalText":
                threadSleep(3000);
                assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("legalText-" + UrlBuilder.LANGUAGE), getTrimTextForParticularIndexElement(FOOTER_TEXT, 1));
                break;
            case "hoursText1":
                if(Props.getTestSuite().equals("Live")) {
                    int linksSize = webDriver.findElements(OPENING_HOURS_PROD1).size();
                    assertTrueExpectedTextEqualsActualText(webDriver.findElements(OPENING_HOURS_PROD1).get(linksSize-2).getAttribute("innerText").trim(),
                            UrlBuilder.getMessage("hoursText1-" + UrlBuilder.LANGUAGE));
                } else {
                    assertTrueExpectedTextEqualsActualText(webDriver.findElements(OPENING_HOURS).get(0).getAttribute("innerText").trim(), UrlBuilder.getMessage("hoursText1-" + UrlBuilder.LANGUAGE));
                }
                break;
            case "hoursText2":
                if(Props.getTestSuite().equals("Live")) {
                    int linksSize = webDriver.findElements(OPENING_HOURS_PROD1).size();
                    assertTrueExpectedTextEqualsActualText(webDriver.findElements(OPENING_HOURS_PROD1).get(linksSize-1).getAttribute("innerText").trim(),
                            UrlBuilder.getMessage("hoursText2-" + UrlBuilder.LANGUAGE));
                } else {
                    assertTrueExpectedTextEqualsActualText(webDriver.findElements(OPENING_HOURS).get(1).getAttribute("innerText").trim(), UrlBuilder.getMessage("hoursText2-" + UrlBuilder.LANGUAGE));
                }
                break;
        }
    }

    public void clickOnTelephoneNumber() {
        threadSleep(3000);
        clickByElementByQueryJSExecutor(TELEPHONE);
    }

    public void validateOpeningHours() {
        if(Props.getTestSuite().equals("Regression")) {
            waitForExpectedElement(OPENING_HOURS_DROPDOWN).click();
        }
    }

    public void clickOnSubmitTicket() {
        try {
            waitForExpectedElement(SUBMIT_A_TICKET).click();
        }catch (Exception e) {
            waitForExpectedElement(SUBMIT_A_TICKET_FR).click();
        }
        threadSleep(10000);
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage("submitTicketURL-"+UrlBuilder.LANGUAGE), getCurrentUrl());
    }

    public void clickOnInstagramIcon() {
        waitForExpectedElement(INSTAGRAM_ICON).click();
        threadSleep(3000);
    }
}