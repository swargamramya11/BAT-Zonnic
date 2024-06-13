package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.constants.Context.*;

public class Payloads extends PageObject {
    private String firstNameData = random(6, ALPHABETS);
    private String lastNameData = random(6, ALPHABETS);
    public String emailAddressData = RandomGenerator.randomEmailAddress(6);
    private boolean is_subscribed = true;
    String streetKey = getProvinceCode()+"-"+"street";
    String postCodeKey = getProvinceCode()+"-"+"postCode";
    String cityKey = getProvinceCode()+"-"+"city";
    private String street = UrlBuilder.getMessage(streetKey);
    private String postCode = UrlBuilder.getMessage(postCodeKey);
    private String city = UrlBuilder.getMessage(cityKey);

    private ScenarioContext scenarioContext;

    public Payloads(TestContext testContext) {
        this.scenarioContext = testContext.getScenarioContext();
    }

    public String createAccountPayload() {
        scenarioContext.setContext(EMAIL_ID, emailAddressData);
        scenarioContext.setContext(FIRST_NAME, firstNameData);
        scenarioContext.setContext(LAST_NAME, lastNameData);

        return "{\"query\":\"mutation {\\r\\n  " +
                "createCustomerV2 (\\r\\n    " +
                "input: {\\r\\n      " +
                "firstname: \\\"" + firstNameData + "\\\"\\r\\n      " +
                "lastname: \\\"" + lastNameData + "\\\"\\r\\n      " +
                "email: \\\"" + emailAddressData + "\\\"\\r\\n      " +
                "password: \\\"Password@1234\\\"\\r\\n      " +
                "date_of_birth: \\\"1992-11-28\\\"\\r\\n      " +
                "is_subscribed: " + is_subscribed + "\\r\\n      " +
                "user_has_zonnic_can: YES\\r\\n      " +
                "address: {\\r\\n        " +
                "firstname: \\\"" + firstNameData + "\\\"\\r\\n        " +
                "lastname: \\\"" + lastNameData + "\\\"\\r\\n        " +
                "street: [\\\"" + street + "\\\"]\\r\\n        " +
                "telephone: \\\"8195555555\\\"\\r\\n        " +
                "postcode: \\\"" + postCode + "\\\"\\r\\n        " +
                "city: \\\"" + city + "\\\"\\r\\n        " +
                "region: {\\r\\n          " +
                //"region_id: 68\\r\\n          " +
                "region_code: \\\"" + UrlBuilder.getProvinceCode() + "\\\"\\r\\n        }\\r\\n        " +
                "country_code: CA\\r\\n        " +
                "default_shipping: true\\r\\n        " +
                "default_billing: true\\r\\n      }\\r\\n    }\\r\\n  ) {\\r\\n    " +
                "customer {\\r\\n      " +
                "firstname\\r\\n      " +
                "lastname\\r\\n      " +
                "email\\r\\n      " +
                "is_subscribed\\r\\n      " +
                "created_at\\r\\n      " +
                "addresses {\\r\\n            " +
                "id\\r\\n            " +
                "firstname\\r\\n            " +
                "lastname\\r\\n            " +
                "street\\r\\n            " +
                "city\\r\\n            " +
                "region {\\r\\n                " +
                "region_code\\r\\n                " +
                "region\\r\\n                " +
                "region_id\\r\\n            }\\r\\n            " +
                "postcode\\r\\n            " +
                "country_code\\r\\n            " +
                "telephone\\r\\n            " +
                "default_billing\\r\\n            " +
                "default_shipping\\r\\n        }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{}}";
    }
}

