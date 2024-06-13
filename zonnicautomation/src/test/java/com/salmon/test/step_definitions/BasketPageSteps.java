package com.salmon.test.step_definitions;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.*;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.cucumberContext.TestContext;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasketPageSteps {
    private static final Logger LOG = LoggerFactory.getLogger(OrderSuccessPageSteps.class);
    public static String orderNumber;
    private OrderSuccessPage orderSuccessPage;
    private AccountDashboardPage accountDashboardPage;
    private PaymentPage paymentPage;
    private AddNewAddressPage addNewAddressPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private BATHelper batHelper;
    private PercyPage percyPage;
    private BasketPage basketPage;

    public BasketPageSteps(BasketPage basketPage, TestContext testContext, OrderSuccessPage orderSuccessPage, BATHelper batHelper, AccountDashboardPage accountDashboardPage, PaymentPage paymentPage, AddNewAddressPage addNewAddressPage, HomePage homePage, PercyPage percyPage) {
        this.orderSuccessPage = orderSuccessPage;
        this.batHelper = batHelper;
        this.accountDashboardPage = accountDashboardPage;
        this.paymentPage = paymentPage;
        this.addNewAddressPage = addNewAddressPage;
        this.homePage = homePage;
        this.scenarioContext = testContext.getScenarioContext();
        this.percyPage = percyPage;
        this.basketPage = basketPage;
    }

    @And("^user click on proceed to checkout button in cart page as '(.*)'$")
    public void clickOnProceedToCheckoutButtonInCart(String guestOrNormalUser) {
        switch (UrlBuilder.getSite()) {
            case "zonnic":
                homePage.clickOnProceedToCheckoutButtonInCart(guestOrNormalUser);
                break;
        }
    }

    @And("^user should validate percentage of tax in cart page with '(.*)' in '(.*)'$")
    public void taxValidation(String withCouponOrWithoutCoupon, String page) {
        basketPage.taxValidation(withCouponOrWithoutCoupon, page);
    }

    @And("^user should validate subtotal and total '(.*)' page with '(.*)'$")
    public void totalValidation(String page, String withOrWithOutCoupon) {
        basketPage.totalValidation(page, withOrWithOutCoupon);
    }

    @And("^add '(.*)' coupon code '(.*)' in '(.*)'$")
    public void applyCoupon(String validOrInvalidCoupon, String coupon, String page) {
        basketPage.applyCoupon(validOrInvalidCoupon, coupon, page);
    }

    @And("^add cross sell product$")
    public void addcCrossSellProduct() {
        basketPage.addCrossSellProduct();
    }

    @And("^validate if cross sell product added to '(.*)'$")
    public void validateCrossSellProductAddedOrNot(String page) {
        basketPage.validateCrossSellProductAddedOrNot(page);
    }

    @And("^validate if products added to basket$")
    public void validateProductsAddedToBasket() {
        basketPage.validateProductsAddedToBasket();
    }


    @And("^'(.*)' quantity in '(.*)' of index '(.*)'$")
    public void increaseQuantity(String incOrDec, String page, String index) {
        basketPage.updateQuantity(incOrDec, page, index);
    }

    @And("^navigate to home page from basket$")
    public void navigateToHomePageFromBasket() {
        basketPage.navigateToHomePageFromBasket();
    }

    @And("^validate elements in '(.*)'$")
    public void validateElementsInMiniBasket(String page) {
        basketPage.validateElementsInMiniBasket(page);
    }

    @And("^remove items from '(.*)'$")
    public void removeItems(String page) {
        basketPage.removeItems(page);
    }

    @And("^add product to basket from '(.*)' of index '(.*)'$")
    public void addProductToBasket(String page, String index) {
        basketPage.addProductToCartAccoringToPage(page, index);
    }
}