package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import static com.salmon.test.framework.helpers.WebDriverHelper.BROWSER;

public class LiveChatPage extends PageObject {
    private PercyPage percyPage;
    public LiveChatPage(PercyPage percyPage) {
        this.percyPage = percyPage;
    }

    public final static By CHAT_ICON = By.cssSelector("body > div > div > div > button > div.sc-1q9fwvy-0.gHHEeh > svg");
    public final static By TEXT_BOX = By.xpath("//textarea[@data-garden-id = 'forms.textarea']");
    public final static By TALK_TO_HUMAN = By.xpath(" //button[@data-garden-id = 'buttons.button']");
    public final static By NEXT_BUTTON = By.xpath(" //button[@data-garden-id='buttons.button']");
    public final static By ENTER_NAME = By.xpath("//input[@data-garden-id='forms.input']");
    public final static By ENTER_EMAIL = By.xpath("(//input[@data-garden-id='forms.input'])[2]");
    public final static By ERROR = By.xpath("//div[@data-garden-id='forms.input_message']");
    public final static By CHAT_TEXT = By.cssSelector(".sc-19o5u7w-0.eFkXQN");
    public final static By SEND_MESSAGE_BUTTON = By.xpath("//button[@data-garden-id='buttons.icon_button']");
    public final static By SOUND_OFF = By.xpath("//ul[@data-garden-id='dropdowns.menu']");
    public final static By ZONNIC_LOGO = By.xpath("//figure[@data-garden-id ='avatars.avatar']");
    public final static By ZONNIC_HEADER = By.xpath("//div[@class='sc-vrqbdz-3 frlShw']//h2");

    public void openChatWindow() {
        try {
            getWebDriver().switchTo().frame(3);
            waitForExpectedElement(CHAT_ICON).click();
            getWebDriver().switchTo().defaultContent();
        } catch (Exception e1) {
            try {
                getWebDriver().switchTo().frame(2);
                waitForExpectedElement(CHAT_ICON).click();
                getWebDriver().switchTo().defaultContent();
            } catch (Exception e2) {
                try {
                    getWebDriver().switchTo().frame(4);
                    waitForExpectedElement(CHAT_ICON).click();
                    getWebDriver().switchTo().defaultContent();
                } catch (Exception e3) {
                    getWebDriver().switchTo().frame(1);
                    waitForExpectedElement(CHAT_ICON).click();
                    getWebDriver().switchTo().defaultContent();
                }
            }
        }
        percyPage.takePercyScreenshot("Chat window");
    }

    public void talkToHuman() {
        try {
            getWebDriver().switchTo().frame(3);
            talkToHuman1();
            getWebDriver().switchTo().defaultContent();
        } catch (Exception e1) {
            try {
                getWebDriver().switchTo().frame(2);
                talkToHuman1();
                getWebDriver().switchTo().defaultContent();
            } catch (Exception e2) {
                try {
                    getWebDriver().switchTo().frame(4);
                    talkToHuman1();
                    getWebDriver().switchTo().defaultContent();
                } catch (Exception e3) {
                    getWebDriver().switchTo().frame(1);
                    talkToHuman1();
                    getWebDriver().switchTo().defaultContent();
                }
            }
        }
    }

    public void talkToHuman1() {
        clickByElementByQueryJSExecutor(TALK_TO_HUMAN);
        validateErrorsForName();
        enterText(ENTER_NAME, "zonnic");
        waitForExpectedElement(NEXT_BUTTON).click();
        validateErrorsForEmail();
        enterText(ENTER_EMAIL, "zonnic@mailinator.com");
        waitForExpectedElement(NEXT_BUTTON).click();
        validateDefaultTexts();
    }

    public void validateErrorsForName() {
        String nameKey = "nameError" + "-" + UrlBuilder.LANGUAGE;

        waitForExpectedElement(NEXT_BUTTON).click();
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(nameKey), getTrimTextForParticularIndexElement(ERROR, 0));
    }

    public void validateErrorsForEmail() {
        String emailKey = "emailChatError" + "-" + UrlBuilder.LANGUAGE;

        waitForExpectedElement(NEXT_BUTTON).click();
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(emailKey), getTrimTextForParticularIndexElement(ERROR, 0));
    }

    public void validateDefaultTexts() {
        threadSleep(2000);
        String text1 = "chatText1" + "-" + UrlBuilder.LANGUAGE;
        String text2 = "chatText2" + "-" + UrlBuilder.LANGUAGE;
        String text3 = "chatText3" + "-" + UrlBuilder.LANGUAGE;
        String text4 = "chatText4" + "-" + UrlBuilder.LANGUAGE;

        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(text1), getTrimTextForParticularIndexElement(CHAT_TEXT, 0));
//        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(text2), getTrimTextForParticularIndexElement(CHAT_TEXT, 1));
        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(text3), getTrimTextForParticularIndexElement(CHAT_TEXT, 2));
//        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(text4), getTrimTextForParticularIndexElement(CHAT_TEXT, 3));
    }

    public void enterIssueDescription() {
        try {
            getWebDriver().switchTo().frame(3);
            enterDescription();
            getWebDriver().switchTo().defaultContent();
        } catch (Exception e1) {
            try {
                getWebDriver().switchTo().frame(2);
                enterDescription();
                getWebDriver().switchTo().defaultContent();
            } catch (Exception e2) {
                try {
                    getWebDriver().switchTo().frame(4);
                    enterDescription();
                    getWebDriver().switchTo().defaultContent();
                } catch (Exception e3) {
                    getWebDriver().switchTo().frame(1);
                    enterDescription();
                    getWebDriver().switchTo().defaultContent();
                }
            }
        }
        threadSleep(2000);
    }

    public void enterDescription() {
        threadSleep(2000);
        webDriver.findElements(TEXT_BOX).get(0).sendKeys("order placement");
        try {
            webDriver.findElements(SEND_MESSAGE_BUTTON).get(3).click();
        } catch (Exception e) {
            webDriver.findElements(SEND_MESSAGE_BUTTON).get(2).click();
        }
    }

    public void soundToggleOff() {
        try {
            getWebDriver().switchTo().frame(3);
            toggleOff();
            getWebDriver().switchTo().defaultContent();
        } catch (Exception e1) {
            try {
                getWebDriver().switchTo().frame(2);
                toggleOff();
                getWebDriver().switchTo().defaultContent();
            } catch (Exception e2) {
                try {
                    getWebDriver().switchTo().frame(4);
                    toggleOff();
                    getWebDriver().switchTo().defaultContent();
                } catch (Exception e3) {
                    getWebDriver().switchTo().frame(1);
                    toggleOff();
                    getWebDriver().switchTo().defaultContent();
                }
            }
        }
    }

    public void toggleOff() {
        threadSleep(4000);
        webDriver.findElements(SEND_MESSAGE_BUTTON).get(0).click();
        waitForExpectedElement(SOUND_OFF).click();
        try {
            webDriver.findElements(SEND_MESSAGE_BUTTON).get(1).isDisplayed();
        } catch (Exception e) {
            webDriver.findElements(SEND_MESSAGE_BUTTON).get(2).isDisplayed();
        }
    }

    public void closeChatIcon() {
        try {
            getWebDriver().switchTo().frame(3);
            closeChat();
            getWebDriver().switchTo().defaultContent();
        } catch (Exception e1) {
            try {
                getWebDriver().switchTo().frame(2);
                closeChat();
                getWebDriver().switchTo().defaultContent();
            } catch (Exception e2) {
                try {
                    getWebDriver().switchTo().frame(4);
                    closeChat();
                    getWebDriver().switchTo().defaultContent();
                } catch (Exception e3) {
                    getWebDriver().switchTo().frame(1);
                    closeChat();
                    getWebDriver().switchTo().defaultContent();
                }
            }
        }
        threadSleep(2000);
    }

    public void closeChat() {
        webDriver.findElements(ZONNIC_LOGO).get(0).isDisplayed();
        assertTrueExpectedTextEqualsActualText(getTrimTextFor(ZONNIC_HEADER), "ZONNIC");
        threadSleep(2000);
        if(BROWSER.equals("browserstackdesktop")) {
            try {
                webDriver.findElements(TEXT_BOX).get(0).sendKeys("order placement");
            }catch (Exception e) {
                enterText(TEXT_BOX,"order placement");
            }
            clickTabUsingKeyboard();
            clickTabUsingKeyboard();
            clickEnterUsingKeyboard();
        } else {
            webDriver.findElements(SEND_MESSAGE_BUTTON).get(1).click();
        }
    }
}