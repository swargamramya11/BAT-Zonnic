package com.salmon.test.enums;

public enum PercyCheckpoints {

    PLP("PLP", true),
    PDP("PDP", true),
    MARKETING_PREFERENCE_PAGE("Marketing Preference Page", true),
    ACCOUNT_ADDRESS_BOOK_PAGE("Account Address Book Page", true),
    ACCOUNT_MY_DETAILS_PAGE("My Details Page",true),
    ACCOUNT_ADD_A_NEW_ADDRESS_PAGE("Account Add A New Address Page", true),
    ACCOUNT_ORDER_HISTORY_PAGE("Account Order History Page", true),
    ACCOUNT_EDIT_A_ADDRESS_PAGE("Account Order Details Page", true);

    private final String name;
    private final boolean switchOn;

    PercyCheckpoints(String name, boolean switchOn) {
        this.name = name;
        this.switchOn = switchOn;
    }

    public String getName() {
        return name;
    }

    public boolean isSwitchOn() {
        return switchOn;
    }
}
