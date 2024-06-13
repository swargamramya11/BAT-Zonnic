package com.salmon.test.page_objects.constants;

import lombok.Getter;

public enum Site {
    ZONNIC("zonnic");

    @Getter
    private String site;

    Site(String site) {
        this.site = site;
    }
}
