package com.salmon.test.step_definitions;

import com.salmon.test.enums.PercyCheckpoints;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.AccountDashboardPage;
import com.salmon.test.page_objects.HomePage;
import com.salmon.test.page_objects.PercyPage;
import com.salmon.test.page_objects.constants.Locale;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PercySteps {
    private PercyPage percyPage;
    private AccountDashboardPage accountDashboardPage;
    private HomePage homePage;

    public PercySteps(PercyPage percyPage, AccountDashboardPage accountDashboardPage, HomePage homePage) {
        this.percyPage = percyPage;
        this.accountDashboardPage = accountDashboardPage;
        this.homePage = homePage;
    }

    @Then("^take percy screenshot$")
    public void takePercyScreenshot(List<String> pages) throws Throwable {
        if (Props.PERCY_ON) {
            for (String page : pages) {
                switch (page) {
                    case "EmptyOrderHistoryPage":
                        percyPage.takePercyScreenshot("Empty Order History Page");
                        break;
                    case "NewsLetterPage":
                        percyPage.waitForExpectedElement(homePage.SIGNUP_NOW_BUTTON).click();
                        percyPage.takePercyScreenshot("Newsletter Page");
                        percyPage.waitForExpectedElement(homePage.SIGNUP_NOW_CLOSE).click();
                        break;
                    case "instagramPageInFooter":
                        percyPage.waitForExpectedElement(homePage.INSTAGRAM_ICON_FOOTER).click();
                        percyPage.takePercyScreenshot("Instagram Page");
                        percyPage.clickBrowserBackButton();
                        break;
                }
            }
        }
    }

    @And("^percy check sub pages on My Account page$")
    public void percyCheckSubPagesOnMyAccountPage() {
        if (!Props.PERCY_ON) {
            return;
        }

        List<PercyCheckpoints> pages = new ArrayList<>();
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case ZONNICCA:
                homePage.clickAccountLink();
                homePage.chooseMyAccountOnAccountDropdown();
                pages = Arrays.asList(
                        PercyCheckpoints.ACCOUNT_MY_DETAILS_PAGE,
//                        PercyCheckpoints.ACCOUNT_ORDER_HISTORY_PAGE,
                        PercyCheckpoints.MARKETING_PREFERENCE_PAGE,
                        PercyCheckpoints.ACCOUNT_ADDRESS_BOOK_PAGE,
                        PercyCheckpoints.ACCOUNT_ADD_A_NEW_ADDRESS_PAGE,
                        PercyCheckpoints.ACCOUNT_EDIT_A_ADDRESS_PAGE
                );
                break;
        }
        pages.forEach(subPage -> {
            accountDashboardPage.gotoMyAccountSubPage(subPage);
            percyPage.percyCheckAccountSubPage(subPage);
        });
    }
}