package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import static com.salmon.test.page_objects.constants.Context.*;

public class AddNewAddressPage extends PageObject {

	private AccountDashboardPage accountDashboardPage;
	private RegistrationPage registrationPage;
	private ScenarioContext scenarioContext;
	private ShippingPage shippingPage;

	public AddNewAddressPage(AccountDashboardPage accountDashboardPage, RegistrationPage registrationPage,ScenarioContext scenarioContext,ShippingPage shippingPage) {
		this.accountDashboardPage = accountDashboardPage;
		this.registrationPage = registrationPage;
		this.scenarioContext = scenarioContext;
		this.shippingPage=shippingPage;
	}

	private By ADDRESS = By.cssSelector("#orderInformation div");
	private By OTHER_ADDRESS = By.cssSelector(".addressbook__grid .addressbook__address");

	public void validateDeliveryAddress(String addressType) {
		String expectedTelePhoneNumber = null;
		String expectedFirstNameLastName = null;
		String[] expectedBillingAddress1 = null;
		switch (addressType) {
			case "billingAddress":
				threadSleep(3000);
				String actualBillingAddress = getTrimTextForParticularIndexElement(ADDRESS, 2);
				String expectedBillingAddress = (String) scenarioContext.getContext(ADDRESS_FROM_CREATE_ACCOUNT);
				expectedTelePhoneNumber = (String) scenarioContext.getContext(TELEPHONE_NUMBER_FROM_CREATE_ACCOUNT);
				expectedFirstNameLastName = (String) scenarioContext.getContext(FISTANDLASTNAME_ACCOUNT_CREATION);

				expectedBillingAddress1 = expectedBillingAddress.split(" ");
				assertTrueExpectedTextContainsActualText(expectedTelePhoneNumber, actualBillingAddress);
				assertTrueExpectedTextContainsActualText(expectedFirstNameLastName, actualBillingAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[0], actualBillingAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[1], actualBillingAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[2], actualBillingAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[3], actualBillingAddress);
				assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("billingAddressInOrderHistoryHeader"+"-"+UrlBuilder.LANGUAGE), actualBillingAddress);
				break;
			case "newDeliveryAddress":
				String actualnewDeliveryAddress = getTrimTextForParticularIndexElement(ADDRESS, 0);
				String expectednewDeliveryAddress = (String) scenarioContext.getContext(NEW_ADDRESS_CHECKOUT);
				expectedTelePhoneNumber = (String) scenarioContext.getContext(NEW_TELEPHONE_NUMBER);
				expectedFirstNameLastName = (String) scenarioContext.getContext(NEW_FIRST_LASTNAME_CHECKOUT);

				expectedBillingAddress1 = expectednewDeliveryAddress.split(" ");
				assertTrueExpectedTextContainsActualText(expectedTelePhoneNumber, actualnewDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedFirstNameLastName, actualnewDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[0], actualnewDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[1], actualnewDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[2], actualnewDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[3], actualnewDeliveryAddress);
				assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("deliveryAddressHeader"+"-"+UrlBuilder.LANGUAGE), actualnewDeliveryAddress);
				break;
			case "deliveryAddress":
//				String actualDeliveryAddress = getTrimTextForParticularIndexElement(ADDRESS, 0);
				String actualDeliveryAddress = getTrimTextFor(ADDRESS);
				String expectedDeliveryAddress = (String) scenarioContext.getContext(ADDRESS_FROM_CREATE_ACCOUNT);
				expectedTelePhoneNumber = (String) scenarioContext.getContext(TELEPHONE_NUMBER_FROM_CREATE_ACCOUNT);
				expectedFirstNameLastName = (String) scenarioContext.getContext(FISTANDLASTNAME_ACCOUNT_CREATION);

				expectedBillingAddress1 = expectedDeliveryAddress.split(" ");
				assertTrueExpectedTextContainsActualText(expectedTelePhoneNumber, actualDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedFirstNameLastName, actualDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[0], actualDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[1], actualDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[2], actualDeliveryAddress);
				assertTrueExpectedTextContainsActualText(expectedBillingAddress1[3], actualDeliveryAddress);
				assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("deliveryAddressHeader"+"-"+UrlBuilder.LANGUAGE), actualDeliveryAddress);
				break;
		}
	}

	public void validateOtherAddress(){
		String actualOtherAddress = getTrimTextForParticularIndexElement(OTHER_ADDRESS, 2);
		assertTrueExpectedTextContainsActualText((String) scenarioContext.getContext(TELEPHONE_NUMBER), actualOtherAddress);
		assertTrueExpectedTextContainsActualText((String) scenarioContext.getContext(NEW_FIRST_LASTNAME_CHECKOUT), actualOtherAddress);
	}
}