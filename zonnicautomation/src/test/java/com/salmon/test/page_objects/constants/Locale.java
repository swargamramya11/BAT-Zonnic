package com.salmon.test.page_objects.constants;

public enum Locale {
    ZONNICCA("zonnicca") {
    };

    private String name;

    Locale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
