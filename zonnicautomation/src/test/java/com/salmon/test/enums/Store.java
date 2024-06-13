package com.salmon.test.enums;

public enum Store {

    zonnic_ca_en_ca("42", "27", "zonnicca");

    private String storeId;
    private String websiteID;
    private String locale;
    private Store(String storeId, String websiteID, String locale) {
        this.storeId=storeId;
        this.websiteID=websiteID;
        this.locale=locale;
    }
    public String getLocale() {
        return locale;
    }
}
