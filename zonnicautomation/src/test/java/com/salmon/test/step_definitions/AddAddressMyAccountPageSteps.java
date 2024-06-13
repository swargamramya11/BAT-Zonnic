package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.AddNewAddressPage;
import cucumber.api.java.en.And;

public class AddAddressMyAccountPageSteps {
    private AddNewAddressPage addNewAddressPage;

    public AddAddressMyAccountPageSteps(AddNewAddressPage addNewAddressPage) {
        this.addNewAddressPage = addNewAddressPage;
    }

    @And("^validate if '(.*)' is changed in order details page$")
    public void validateDeliveryAddress(String addressType) {
        addNewAddressPage.validateDeliveryAddress(addressType);
    }

    @And("^validate other addresses$")
    public void otherAddreses() {
        addNewAddressPage.validateOtherAddress();
    }
}