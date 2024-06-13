package com.salmon.test.page_objects;

import com.salmon.test.enums.PercyCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import static com.salmon.test.framework.helpers.WebDriverHelper.percy;

public class PercyPage extends PageObject {
    private TermsAndConditions termsAndConditions;

    public PercyPage(TermsAndConditions termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public void takePercyScreenshot(String name) {
        if (Props.PERCY_ON) {
            percy.snapshot(name);
        }
    }

    public void goToTermsAndConditionPageAndTakePercyScreenshot(){
        if (Props.PERCY_ON) {
            waitForItemToBeClickableAndClick(termsAndConditions.TERMS_CONDITION_BUTTON);
            takePercyScreenshot("Terms and conditions page in checkout");
            waitForItemToBeClickableAndClick(termsAndConditions.CLOSE_BUTTON);
        }
    }

    public void percyCheckAccountSubPage(PercyCheckpoints subPage) {
        if (Props.PERCY_ON && subPage.isSwitchOn()) {
            final String checkpointName = subPage.getName();
            switch (subPage) {
                case ACCOUNT_MY_DETAILS_PAGE:
                case ACCOUNT_ORDER_HISTORY_PAGE:
                case MARKETING_PREFERENCE_PAGE:
                case ACCOUNT_ADDRESS_BOOK_PAGE:
                case ACCOUNT_EDIT_A_ADDRESS_PAGE:
                case ACCOUNT_ADD_A_NEW_ADDRESS_PAGE:
                    scrollToPageBottom();
                    percy.snapshot(checkpointName);
                    break;
            }
        }
    }
}