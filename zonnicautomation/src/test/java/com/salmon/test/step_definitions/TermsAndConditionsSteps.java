package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.TermsAndConditions;
import cucumber.api.java.en.And;

public class TermsAndConditionsSteps {
    private TermsAndConditions termsAndConditions;
    public TermsAndConditionsSteps(TermsAndConditions termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    @And("^validate terms and conditions Page in '(.*)'$")
    public void validateTermsAndConditions(String page) {
        termsAndConditions.validateTermsAndConditions(page);
    }
}