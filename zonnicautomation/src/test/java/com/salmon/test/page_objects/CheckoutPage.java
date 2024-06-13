package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import org.openqa.selenium.By;
import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.UrlBuilder.PROVINCE;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.constants.Context.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class CheckoutPage extends PageObject {

	private String firstNameData = random(6, ALPHABETS);
	private String lastNameData = random(6, ALPHABETS);
	private RegistrationPage registrationPage;
	private ScenarioContext scenarioContext;
	private CommonMethods commonMethods;
	public CheckoutPage(RegistrationPage registrationPage, TestContext testContext, CommonMethods commonMethods) {
		this.registrationPage = registrationPage;
		this.scenarioContext = testContext.getScenarioContext();
		this.commonMethods = commonMethods;
	}

	public static By CHANGEADDRESSBUTTONCHECKOUT = By.cssSelector(".bat-cta-style.arrow-link-dark.left.right.shipping-address-change");
	public static By ADDADDRESSBUTTONCHECKOUT = By.cssSelector(".bat-cta-style.bat-cta-style.link-dark.icon-left.add-new-address.center.icon-left");
	public static By FIRSTNAMEINCHECKOUT = By.cssSelector("#firstname");
	public static By LASTNAMEINCHECKOUT = By.cssSelector("#lastname");
	public static By TELEPHONEINCHECKOUT = By.cssSelector("#telephone");
	public static By ADDRESSINCHECKOUT = By.cssSelector("#addressLookUp");
	public static By USETHISADDRESSBUTTONINCHECKOUT = By.cssSelector(".bat-form-field.bat-form--edit-address-submit .bat-button.bat-button--dark.bat-button--secondary");
	public static By FIRSTNAME_ERROR = By.xpath("//label[@for='firstname']//parent::div//div[@class='bat-message  bat-form-msg error-msg error']");
	public static By LASTNAME_ERROR = By.xpath("//label[@for='lastname']//parent::div//div[@class='bat-message  bat-form-msg error-msg error']");
	public static By TELEPHONE_ERROR = By.xpath("//label[@for='telephone']//parent::div//div[@class='bat-message  bat-form-msg error-msg error']");
	public static By CHANGED_NAME_CHECKOUT = By.cssSelector(".bat-form-block.shipping-address .shipping-address-display p");
	public static By PRODUCT_PRICE_CHECKOUT = By.cssSelector(".bat-basket-summary-items-header-total");
	public static By TOTAL_PRICE_CHECKOUT = By.cssSelector(".bat-basket-summary-breakdown-total-amount-value");
	public static By SUBTOTAL_PRICE_CHECKOUT = By.cssSelector(".bat-basket-summary-breakdown-adjustments-subtotal-value");
	public static By SHIPPING_ADDRESS_HEADING_CHECKOUT = By.cssSelector(".bat-form-block.shipping-address .bat-form-block-text h2");
	public static By SUMMARY_HEADING_CHECKOUT = By.cssSelector(" .bat-basket-summary.bat-basket-summary--alt-design  h2");
	public static By SHIPPING_HEADING_CHECKOUT = By.cssSelector(".bat-form-block.checkout-shipping-method-headline div h2");
	public static By BILLING_HEADING_CHECKOUT = By.cssSelector(".bat-form-block.billing-address div h4");
	public static By PAYMENT_HEADING_CHECKOUT = By.cssSelector(".bat-form-block.payment-voucher h2");
	public static By PAYMENT_DETAILS_HEADING_CHECKOUT = By.cssSelector(".bat-form-block.payment-details div h4");
	public static By PLUS_BUTTON_CHECKOUT = By.cssSelector(".bat-basket-summary-items-header .bat-basket-summary-items-header-toggle");
	public static By PRODUCT_DETAILS_CHECKOUT = By.cssSelector(".bat-basket-summary-items-list-item");
	public By SHIPPING_COST = By.cssSelector(".checkout-shipping-method-options-option .checkout-shipping-method-options-option-amount");
	public By RETURN_TO_BASKET_TEXT = By.cssSelector(".bat-cta.bat-cta-list--vertical a span");
	public By CONTINUE_SHOPPING_TEXT = By.cssSelector(".bat-cta.bat-cta-list--vertical a[title='continue shopping'] span");
	public By CONTINUE_SHOPPING_TEXT_FR = By.cssSelector(".bat-cta.bat-cta-list--vertical a[title='Continue vos achats'] span");
	public By RETURN_TO_BASKET_LINK = By.cssSelector(".bat-cta.bat-cta-list--vertical a span");
	public By BACK_BUTTON = By.cssSelector(".bat-header .bat-header-back button");
	public By ZONNIC_LOGO = By.cssSelector(".bat-header .bat-header-logo a");
	public By RADIO_BUTTON_IN_ADDRESS = By.xpath("//input[@name='previouslyUsedAddresses']");
	public By USE_THIS_ADDRESS = By.cssSelector(".bat-form-field.bat-form--checkout-shipping-submit .bat-button.bat-button--dark.bat-button--secondary");
	public By CANCEL_ADDRESS = By.cssSelector(".bat-form-field.bat-form--checkout-shipping-submit .bat-cta-style.bat-button.bat-button--dark.bat-button--secondary--dark.change-address-cancel");

	public void clickChangeAddressButtonInCheckout() {
		waitForExpectedElement(CHANGEADDRESSBUTTONCHECKOUT).click();
		commonMethods.assertButtonText(getTrimTextFor(CHANGEADDRESSBUTTONCHECKOUT), "changeAddressesButton");
		commonMethods.assertButtonText(getTrimTextFor(USE_THIS_ADDRESS), "useOtherAddressesButton");
		commonMethods.assertButtonText(getTrimTextFor(CANCEL_ADDRESS), "cancelButton");
	}

	public void clickAddAddressButtonInCheckout() {
		waitForExpectedElement(ADDADDRESSBUTTONCHECKOUT).click();
	}

	public void enterFirstNameInCheckout() {
		waitForExpectedElement(FIRSTNAMEINCHECKOUT).sendKeys(firstNameData);
	}

	public void enterLastNameInCheckout() {
		waitForExpectedElement(LASTNAMEINCHECKOUT).sendKeys(lastNameData);
	}

	public void enterTelephoneInCheckout() {
		waitForExpectedElement(TELEPHONEINCHECKOUT).sendKeys("324-253-2345");
		scenarioContext.setContext(TELEPHONE_NUMBER, "3242532345");
		scenarioContext.setContext(NEW_TELEPHONE_NUMBER, getValue(TELEPHONEINCHECKOUT));
	}

	public void enterAddressInCheckout() {
		String postCode = registrationPage.getPostCodecheckout(PROVINCE);
		registrationPage.enterPostCode(postCode);
		threadSleep(1000);
		try {
			clickByElementByQueryJSExecutor(registrationPage.selectFirstAddress);
		}catch (Exception e){

		}
		threadSleep(1000);
		clickByElementByQueryJSExecutor(registrationPage.selectFirstAddress1);
		scenarioContext.setContext(NEW_ADDRESS_CHECKOUT, getValue(ADDRESSINCHECKOUT));
	}

	public void getAddressAndStore() {
		String address1 = getValue(ADDRESSINCHECKOUT);
		String address = getTrimTextFor(ADDRESSINCHECKOUT);
		scenarioContext.setContext(UPDATED_ADDRESS, address);
		LOG.info("address " + address);
		LOG.info("address " + address1);
	}

	public void clickUseThisAddressButtonInCheckout() {
		clickByElementByQueryJSExecutor(USETHISADDRESSBUTTONINCHECKOUT);
	}

	public void errorValidationForFirstNameInCheckout() {
		String key = "firstNameError" + "-" + UrlBuilder.LANGUAGE;
		waitForExpectedElement(FIRSTNAMEINCHECKOUT).sendKeys("Z");
		clickBackSpaceKeyboard(FIRSTNAMEINCHECKOUT);
		assertThat(getTrimTextFor(FIRSTNAME_ERROR).contains(UrlBuilder.getMessage(key)));
	}

	public void errorValidationForLastNameInCheckout() {
		String key = "lastNameError" + "-" + UrlBuilder.LANGUAGE;
		waitForExpectedElement(LASTNAMEINCHECKOUT).sendKeys("Z");
		clickBackSpaceKeyboard(LASTNAMEINCHECKOUT);
		assertThat(getTrimTextFor(LASTNAME_ERROR).contains(UrlBuilder.getMessage(key)));
	}

	public void errorValidationForTelephoneInCheckout() {
		String key = "phoneNumberError" + "-" + UrlBuilder.LANGUAGE;
		waitForExpectedElement(TELEPHONEINCHECKOUT).sendKeys("Z");
		clickBackSpaceKeyboard(TELEPHONEINCHECKOUT);
		assertThat(getTrimTextFor(TELEPHONE_ERROR).contains(UrlBuilder.getMessage(key)));
	}

	public void addressErrorValidationsInCheckout() {
		errorValidationForFirstNameInCheckout();
		errorValidationForLastNameInCheckout();
		errorValidationForTelephoneInCheckout();
	}

	public void clickOnAddAddressButton() {
		threadSleep(2000);
		clickChangeAddressButtonInCheckout();
		clickAddAddressButtonInCheckout();
	}

	public void enterAllAddressFieldsInCheckout() {
		enterFirstNameInCheckout();
		enterLastNameInCheckout();
		String name = firstNameData + " " + lastNameData;
		scenarioContext.setContext(NEW_FIRST_LASTNAME_CHECKOUT, name);
		enterTelephoneInCheckout();
		enterAddressInCheckout();
		getAddressAndStore();
		clickUseThisAddressButtonInCheckout();
	}

	public void selectaddresses() {
		threadSleep(1000);
		waitForPage();
		refreshBrowser();
		threadSleep(2000);
		clickChangeAddressButtonInCheckout();
		waitForExpectedElement(CANCEL_ADDRESS).click();
		clickChangeAddressButtonInCheckout();
		webDriver.findElements(RADIO_BUTTON_IN_ADDRESS).get(0).click();
		waitForExpectedElement(USE_THIS_ADDRESS).click();
	}

	public void validateIfAddressUpdated() {
		threadSleep(1000);
		waitForPage();
		refreshBrowser();
		threadSleep(2000);
		String expectedName = (String) scenarioContext.getContext(NEW_FIRST_LASTNAME_CHECKOUT);
		waitForPage();
		threadSleep(2000);
		String actualUpdatedName = webDriver.findElements(CHANGED_NAME_CHECKOUT).get(0).getText();
		String actualUpdatedNumber = webDriver.findElements(CHANGED_NAME_CHECKOUT).get(2).getText();
		String actualUpdatedAddress = webDriver.findElements(CHANGED_NAME_CHECKOUT).get(1).getText();
		String expectedTelePhone = (String) scenarioContext.getContext(TELEPHONE_NUMBER);
		String address[] = actualUpdatedAddress.split(" ");
		assertTrueExpectedTextContainsActualText(expectedTelePhone, actualUpdatedNumber);
		assertTrueExpectedTextContainsActualText(expectedName, actualUpdatedName);
		assertTrueExpectedTextContainsActualText(address[0], actualUpdatedAddress);
		assertTrueExpectedTextContainsActualText(address[2], actualUpdatedAddress);
		assertTrueExpectedTextContainsActualText(address[3], actualUpdatedAddress);
	}

	public void validateCurrency(String pageName) {
		switch (pageName) {
			case "checkout":
				assertThat(getTrimTextFor(PRODUCT_PRICE_CHECKOUT).contains("."));
				assertThat(getTrimTextFor(PRODUCT_PRICE_CHECKOUT).contains("$"));
				assertThat(getTrimTextFor(SUBTOTAL_PRICE_CHECKOUT).contains("."));
				assertThat(getTrimTextFor(SUBTOTAL_PRICE_CHECKOUT).contains("$"));
				assertThat(getTrimTextFor(TOTAL_PRICE_CHECKOUT).contains("."));
				assertThat(getTrimTextFor(TOTAL_PRICE_CHECKOUT).contains("$"));
				break;
		}
	}

	public void validateAllHeadings(){
		assertTrue(waitForExpectedElement(SHIPPING_ADDRESS_HEADING_CHECKOUT).isDisplayed());
		assertTrue(waitForExpectedElement(SUMMARY_HEADING_CHECKOUT).isDisplayed());
		assertTrue(waitForExpectedElement(SHIPPING_HEADING_CHECKOUT).isDisplayed());
		assertTrue(waitForExpectedElement(BILLING_HEADING_CHECKOUT).isDisplayed());
		assertTrue(waitForExpectedElement(PAYMENT_HEADING_CHECKOUT).isDisplayed());
		assertTrue(waitForExpectedElement(PAYMENT_DETAILS_HEADING_CHECKOUT).isDisplayed());
		threadSleep(3000);
		waitForExpectedElement(PLUS_BUTTON_CHECKOUT).click();
		assertTrue(waitForExpectedElement(PRODUCT_DETAILS_CHECKOUT).isDisplayed());
		waitForExpectedElement(PLUS_BUTTON_CHECKOUT).click();
	}

	public void shippingCostValidation(String cost) {
		String actualCost = getTrimTextFor(SHIPPING_COST);
		if (cost.equals("<=75")) {
			scenarioContext.setContext(ESTIMATEDSHIPPINGCOST, "$20.00");
			assertTrueExpectedTextContainsActualText("$20.00", actualCost);
		} else if (cost.equals(">=75")) {
			if(UrlBuilder.LANGUAGE.equalsIgnoreCase("en")){
				scenarioContext.setContext(ESTIMATEDSHIPPINGCOST, "FREE");
				assertTrueExpectedTextContainsActualText("FREE", actualCost);
			} else {
				scenarioContext.setContext(ESTIMATEDSHIPPINGCOST, "GRATUIT");
				assertTrueExpectedTextContainsActualText("GRATUIT", actualCost);
			}

		}
	}

	public void clickOnRequiredButtons(String buttonName, String pageName) {
		switch (pageName) {
			case "checkout":
				switch (buttonName) {
					case "returnToBasket":
						commonMethods.assertButtonText(getTrimTextFor(RETURN_TO_BASKET_TEXT), "returnToBasketButton");
						clickByElementByQueryJSExecutor(RETURN_TO_BASKET_LINK);
						commonMethods.assertUrl("cartPageLink.key");
						break;
					case "backButton":
						scrollElementIntoView(BACK_BUTTON);
						clickByElementByQueryJSExecutor(BACK_BUTTON);
						commonMethods.assertUrl("cartPageLink.key");
						break;
					case "zonnicIcon":
						scrollElementIntoView(ZONNIC_LOGO);
						clickByElementByQueryJSExecutor(ZONNIC_LOGO);
						commonMethods.assertUrl("homePageLink.key");
						break;
				}
				break;
			case "thankYouPage":
				switch (buttonName) {
					case "backButton":
						waitForExpectedElement(BACK_BUTTON).click();
						commonMethods.assertUrl("shippingAnPaymentLink.key");
						break;
					case "zonnicIcon":
						waitForExpectedElement(ZONNIC_LOGO).click();
						commonMethods.assertUrl("homePageLink.key");
						break;
				}
				break;
			case "basket":
				switch (buttonName) {
					case "backButton":
						threadSleep(2000);
						waitForExpectedElement(BACK_BUTTON).click();
						commonMethods.assertUrl("nicotinePouchesUrl.key");
						break;
					case "zonnicIcon":
						threadSleep(2000);
						waitForExpectedElement(ZONNIC_LOGO).click();
						commonMethods.assertUrl("homePageLink.key");
						break;
					case "continueShopping":
						threadSleep(2000);
						try {
							commonMethods.assertButtonText(getTrimTextFor(CONTINUE_SHOPPING_TEXT), "continueShoppingButton");
						} catch (Exception e) {
							commonMethods.assertButtonText(getTrimTextFor(CONTINUE_SHOPPING_TEXT_FR), "continueShoppingButton");
						}
						webDriver.findElements(RETURN_TO_BASKET_LINK).get(0).click();
						commonMethods.assertUrl("nicotinePouchesUrl.key");
						break;
				}
				break;
		}
	}

	public void validateIfSelectedAddressIsDisplayed() {
		String firstName = (String) scenarioContext.getContext(FIRST_NAME);
		String lastName = (String) scenarioContext.getContext(LAST_NAME);
		String expectedName = firstName+" "+lastName;
		threadSleep(10000);
		String actualUpdatedName = webDriver.findElements(CHANGED_NAME_CHECKOUT).get(0).getText();
		String actualUpdatedNumber = webDriver.findElements(CHANGED_NAME_CHECKOUT).get(2).getText();
		String expectedTelePhone = (String) scenarioContext.getContext(TELEPHONE_NUMBER_FROM_CREATE_ACCOUNT);
		assertTrueExpectedTextContainsActualText(expectedTelePhone.replace("-",""), actualUpdatedNumber);
		assertTrueExpectedTextContainsActualText(expectedName, actualUpdatedName);
	}
}