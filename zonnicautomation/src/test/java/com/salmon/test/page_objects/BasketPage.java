package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import java.text.DecimalFormat;
import java.util.List;
import static com.salmon.test.page_objects.constants.Context.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class BasketPage extends PageObject {

    private SoftAssert softAssert;
    private HomePage homePage;
    private PLP plp;
    private PDP pdp;
    private ScenarioContext scenarioContext;
    private AccountDashboardPage accountDashboardPage;
    private CommonMethods commonMethods;

    public By SHIPPING_COST_CHECKOUT = By.xpath("//h5[@class='bat-basket-summary-breakdown-adjustments-shipping']//span[@class='bat-basket-summary-breakdown-adjustments-shipping-value']");
    public By TOTAL_COST_CHECKOUT = By.xpath("//h5[@class='bat-basket-summary-breakdown-total-amount']//span[@class='bat-basket-summary-breakdown-total-amount-value']");
    public By SUBTOTAL_CART_AND_CHECKOUT = By.xpath("//h5[@class='bat-basket-summary-breakdown-adjustments-subtotal']//span[@class='bat-basket-summary-breakdown-adjustments-subtotal-value']");
    public By TAXES = By.xpath("//h5[@class='bat-basket-summary-breakdown-taxes-tax']");
    public By ENTER_COUPON = By.cssSelector(".bat-basket-summary-wrapper .bat-basket-summary-voucher-container form input");
    public By COUPON_PROCEED_BUTTON = By.cssSelector(".bat-basket-summary-wrapper .bat-basket-summary-voucher-container form button");
    public By COUPON_CODE_IN_SUMMARY = By.cssSelector(".bat-basket-summary-breakdown-adjustments-discounts .bat-basket-summary-breakdown-adjustments-discounts-discount");
    public By INVALID_COUPON_ERROR_MESSAGE = By.cssSelector(".bat-basket-summary-wrapper .bat-basket-summary-voucher-container form div p");
    public By REMOVE_COUPON = By.cssSelector(".bat-basket-summary-breakdown-adjustments-discounts-discount-label .bat-basket-summary-breakdown-adjustments-discounts-discount-label-remove");
    public By ENTER_COUPON_CHECKOUT = By.cssSelector(".bat-form-field.bat-form--checkout-payment-voucher-code #voucherCode");
    public By COUPON_PROCEED_BUTTON_CHECKOUT = By.cssSelector(".bat-form-field.bat-form--checkout-payment-voucher-submit button.bat-button.bat-button--dark.bat-button--secondary");
    public By INVALID_COUPON_ERROR_MESSAGE_CHECKOUT = By.cssSelector(".bat-form-field.bat-form--checkout-payment-voucher-code .bat-message.bat-form-msg.error-msg.error.active div div p");
    public By SUMMARY_LOADER = By.cssSelector(".bat-basket-summary-loader");
    public By ADD_TO_CART_BUTTON_CROSS_SELL = By.cssSelector(".productcard-ctas button");
    public By PRODUCTS_COUNT_IN_CART = By.cssSelector(".basket__list .basket__item");
    public By PRODUCTS_COUNT_IN_CHECKOUT = By.cssSelector(".bat-basket-summary-items-header h5 span[class='bat-basket-summary-items-header-label-total']");
    public By CROSS_SELL_HEADING = By.cssSelector("h2[class='bat-headline-style headline2']");
    public By VIEW_ALL_LINK_CROSS_SELL = By.cssSelector(".bat-cta.bat-cta-list--horizontal");
    public By INCREASE_QTY_BASKET = By.cssSelector(".basket__item__quantity__controls button[title='Increase Quantity']");
    public By DECREASE_QTY_BASKET = By.cssSelector(".basket__item__quantity__controls button[title='Decrease Quantity']");
    public By ZONNIC_ICON_BASKET = By.cssSelector(".bat-header-logo a");
    public By ADDED_TO_BASKET_TEXT_MINI_BASKET = By.cssSelector(".bat-minicart-count");
    public By BASKET_TOTAL_MINI_BASKET = By.cssSelector(".bat-minicart-subtotal-label span");
    public By ITEM_TEXT_MINI_BASKET = By.cssSelector(".bat-minicart-basket-total .bat-minicart-total-items");
    public By PRICE_FORMAT_MINI_BASKET = By.cssSelector(".bat-minicart-subtotal-value.formatPrice");
    public By PRODUCT_IN_MINI_BASKET = By.cssSelector(".bat-minicart-cart-item-container .bat-minicart-cart-item");
    public By PRODUCT_IN_BASKET = By.cssSelector(".basket__list .basket__item");
    public By HEADER_IN_BASKET = By.cssSelector(".basket__container .basket__title");
    public By SUMMARY_HEADER_IN_BASKET = By.cssSelector(".bat-basket-summary-headline");

    public BasketPage(CommonMethods commonMethods, SoftAssert softAssert, HomePage homePage, PLP plp, PDP pdp, ScenarioContext scenarioContext, AccountDashboardPage accountDashboardPage) {
        this.softAssert = softAssert;
        this.homePage = homePage;
        this.plp = plp;
        this.pdp = pdp;
        this.scenarioContext = scenarioContext;
        this.accountDashboardPage = accountDashboardPage;
        this.commonMethods = commonMethods;
    }

    public void taxValidation(String withCouponOrWithoutCoupon, String page) {
        DecimalFormat df = new DecimalFormat("#.##");
        waitForElementToDisappear(SUMMARY_LOADER, 100);
        threadSleep(5000);
        String actualGST = null;
        String actualHST = null;
        String actualPST = null;
        String calculateGST;
        String calculateHST;
        String calculatePST;
        Double expectedGST;
        Double expectedHST;
        Double expectedPST;
        waitForPage();
        List<WebElement> we = webDriver.findElements(TAXES);
        threadSleep(7000);
        for (int i = 1; i <= we.size(); i++) {
            threadSleep(3000);
            String taxType = webDriver.findElement(By.xpath("(//h5[@class='bat-basket-summary-breakdown-taxes-tax']//span[@class='bat-basket-summary-breakdown-taxes-tax-label'])[" + i + "]")).getText();
            String actualTax = webDriver.findElement(By.xpath("(//h5[@class='bat-basket-summary-breakdown-taxes-tax']//span[@class='bat-basket-summary-breakdown-taxes-tax-value'])[" + i + "]")).getText();
            String round = df.format(stringToDoubleConversion(actualTax.replace('$', ' ')));
            if (taxType.equals("GST")) {
                actualGST = round;
            } else if (taxType.equals("HST")) {
                actualHST = round;
            } else {
                actualPST = round;
            }
        }

        String expectedDiscount = "0.00";
        if (withCouponOrWithoutCoupon.equals("coupon")) {
            expectedDiscount = couponCalculation();
        } else {
            expectedDiscount = "0.00";
            scenarioContext.setContext(DISCOUNT, expectedDiscount);
        }
        Double actualSubTotal = 0.00;

        Double shippingCost = shippingCostCalculation();
        if(page.equalsIgnoreCase("cart") || page.equalsIgnoreCase("checkoutBeforeSelectingShipping")) {
            actualSubTotal = stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) - stringToDoubleConversion(expectedDiscount);
        } else if(page.equalsIgnoreCase("checkoutAfterSelectingShipping")) {
            actualSubTotal = stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) + shippingCost - stringToDoubleConversion(expectedDiscount);
        }

//        actualSubTotal = stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) - stringToDoubleConversion(expectedDiscount) + shippingCostCalculation();
        switch (UrlBuilder.getProvince()) {
            case "Alberta":
                calculateGST = df.format(actualSubTotal * 5 / 100);
                scenarioContext.setContext(GST, calculateGST);
                expectedGST = stringToDoubleConversion(calculateGST);
                Double actualGST1 = stringToDoubleConversion(actualGST);
                scenarioContext.setContext(ACTUALGST, actualGST1);
                assertThat(actualGST1).isIn(expectedGST + 0.01, expectedGST - 0.01, expectedGST);
                break;
            case "British Columbia":
            case "Manitoba":
                calculateGST = df.format(actualSubTotal * 5 / 100);
                calculatePST = df.format(actualSubTotal * 7 / 100);
                scenarioContext.setContext(GST, calculateGST);
                scenarioContext.setContext(PST, calculatePST);

                expectedGST = stringToDoubleConversion(calculateGST);
                expectedPST = stringToDoubleConversion(calculatePST);
                Double actualGST2 = stringToDoubleConversion(actualGST);
                Double actualPST1 = stringToDoubleConversion(actualPST);
                scenarioContext.setContext(ACTUALGST, actualGST2);
                scenarioContext.setContext(ACTUALPST, actualPST1);

                assertThat(actualGST2).isIn(expectedGST + 0.01, expectedGST - 0.01, expectedGST);
                assertThat(actualPST1).isIn(expectedPST + 0.01, expectedPST - 0.01, expectedPST);
                break;
            case "New Brunswick":
            case "Newfoundland and Labrador":
            case "Nova Scotia":
            case "Prince Edward Island":
                calculateHST = df.format(actualSubTotal * 15 / 100);
                scenarioContext.setContext(HST, calculateHST);
                expectedHST = stringToDoubleConversion(calculateHST);
                Double actualHST1 = stringToDoubleConversion(actualHST);
                scenarioContext.setContext(ACTUALHST, actualHST1);

                assertThat(actualHST1).isIn(expectedHST + 0.01, expectedHST - 0.01, expectedHST);
                break;
            case "Ontario":
                calculateHST = df.format(actualSubTotal * 13 / 100);
                scenarioContext.setContext(HST, calculateHST);
                expectedHST = stringToDoubleConversion(calculateHST);
                Double actualHST2 = stringToDoubleConversion(actualHST);
                scenarioContext.setContext(ACTUALHST, actualHST2);

                assertThat(actualHST2).isIn(expectedHST + 0.01, expectedHST - 0.01, expectedHST);
                break;
            case "Saskatchewan":
                calculateGST = df.format(actualSubTotal * 5 / 100);
                calculatePST = df.format(actualSubTotal * 6 / 100);
                scenarioContext.setContext(GST, calculateGST);
                scenarioContext.setContext(PST, calculatePST);

                expectedGST = stringToDoubleConversion(calculateGST);
                expectedPST = stringToDoubleConversion(calculatePST);
                Double actualGST3 = stringToDoubleConversion(actualGST);
                Double actualPST3 = stringToDoubleConversion(actualPST);
                scenarioContext.setContext(ACTUALGST, actualGST3);
                scenarioContext.setContext(ACTUALPST, actualPST3);

                assertThat(actualGST3).isIn(expectedGST + 0.01, expectedGST - 0.01, expectedGST);
                assertThat(actualPST3).isIn(expectedPST + 0.01, expectedPST - 0.01, expectedPST);
                break;
        }
        Double gst = (Double) scenarioContext.getContext(ACTUALGST);
        Double hst = (Double) scenarioContext.getContext(ACTUALHST);
        Double pst = (Double) scenarioContext.getContext(ACTUALPST);
        if (gst == null) {
            gst = 0.0;
        }
        if (pst == null) {
            pst = 0.0;
        }
        if (hst == null) {
            hst = 0.0;
        }

        Double totalTax = gst + hst + pst;
        scenarioContext.setContext(TOTALTAX, totalTax);
    }

    public void totalValidation(String page, String withOrWithOutCoupon) {
        DecimalFormat df = new DecimalFormat("#.##");
        switch (page) {
            case "cart":
            case "checkoutBeforeSelectingShipping":
                String subTotal = getTrimTextFor(SUBTOTAL_CART_AND_CHECKOUT);
                String total = getTrimTextFor(TOTAL_COST_CHECKOUT);
                Double subTotal1 = stringToDoubleConversion(df.format(stringToDoubleConversion(subTotal.replace('$', ' '))));
                Double total1 = stringToDoubleConversion(df.format(stringToDoubleConversion(total.replace('$', ' '))));
                scenarioContext.setContext(TOTAL, total1);

                Double cost;
                Double totalShippingCost;
                String cost1;
                String totalShippingCost1;
                if (subTotal1 <= 75) {
                    cost = 20.00;
                    totalShippingCost = total1 + cost;
                    cost1 = doubleToStringConversion(cost);
                    totalShippingCost1 = doubleToStringConversion(totalShippingCost);
                    scenarioContext.setContext(ESTIMATEDSHIPPINGCOST, cost1);
                    scenarioContext.setContext(TOTALCOSTWITHSHIPPINGCOSTFORGUEST, totalShippingCost1);
                } else if (subTotal1 >= 75) {
                    cost = 0.00;
                    totalShippingCost = total1 + cost;
                    cost1 = doubleToStringConversion(cost);
                    totalShippingCost1 = doubleToStringConversion(totalShippingCost);
                    scenarioContext.setContext(ESTIMATEDSHIPPINGCOST, cost1);
                    scenarioContext.setContext(TOTALCOSTWITHSHIPPINGCOSTFORGUEST, totalShippingCost1);
                }
                Double expectedDiscount = 0.00;
                if (withOrWithOutCoupon.equals("coupon")) {
                    String expectedDiscount1 = couponCalculation();
                    String expectedDiscount2 = df.format(stringToDoubleConversion(expectedDiscount1));
                    expectedDiscount = stringToDoubleConversion(expectedDiscount2);
                    scenarioContext.setContext(DISCOUNT, expectedDiscount);
                } else {
                    expectedDiscount = 0.00;
                    scenarioContext.setContext(DISCOUNT, expectedDiscount);
                }

                Double expectedTotal = 0.00;
                if (UrlBuilder.getProvince().contains("Alberta")) {
                    expectedTotal = stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(GST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                } else if (UrlBuilder.getProvince().contains("British Columbia") ||
                        UrlBuilder.getProvince().contains("Manitoba") ||
                        UrlBuilder.getProvince().contains("Saskatchewan")) {
                    expectedTotal = stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(GST)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(PST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                } else if (UrlBuilder.getProvince().contains("New Brunswick") ||
                        UrlBuilder.getProvince().contains("Newfoundland and Labrador") ||
                        UrlBuilder.getProvince().contains("Nova Scotia") ||
                        UrlBuilder.getProvince().contains("Prince Edward Island") ||
                        UrlBuilder.getProvince().contains("Ontario")) {
                    expectedTotal = stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(HST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                }
                Long expectedTotal1 = Math.round(expectedTotal);
                Long actualTotal = Math.round(total1);
                assertThat(expectedTotal1).isIn(actualTotal + 1, actualTotal - 1, actualTotal);
                break;
            case "checkoutAfterSelectingShipping":
                threadSleep(3000);
                String shippingCostText = getTrimTextFor(SHIPPING_COST_CHECKOUT).replace('$', ' ');
                Double actualShippingCost = stringToDoubleConversion(shippingCostText);
                String totalWithShippingCostInString = getTrimTextFor(TOTAL_COST_CHECKOUT).replace('$', ' ');
                Double totalWithShippingCost1 = stringToDoubleConversion(totalWithShippingCostInString);
                scenarioContext.setContext(TOTALCOSTWITHSHIPPINGCOST, totalWithShippingCostInString);

                String shippingCost = (String) scenarioContext.getContext(ESTIMATEDSHIPPINGCOST);
                Double expectedShippingCost = 0.00;
                String expectedShippingCostInString;
                if (shippingCost.equals("FREE") || shippingCost.equals("GRATUIT")) {
                    expectedShippingCost = 0.00;
                    expectedShippingCostInString = doubleToStringConversion(expectedShippingCost);
                    scenarioContext.setContext(ESTIMATEDSHIPPINGCOST, expectedShippingCostInString);
                    scenarioContext.setContext(TOTALCOSTWITHSHIPPINGCOSTFORGUEST, totalWithShippingCost1);
                } else {
                    expectedShippingCost = 20.00;
                    expectedShippingCostInString = doubleToStringConversion(expectedShippingCost);
                    scenarioContext.getContext(ESTIMATEDSHIPPINGCOST);
                    scenarioContext.setContext(ESTIMATEDSHIPPINGCOST, expectedShippingCostInString);
                    scenarioContext.setContext(TOTALCOSTWITHSHIPPINGCOSTFORGUEST, totalWithShippingCost1);
                }

                expectedDiscount = 0.00;
                if (withOrWithOutCoupon.equals("coupon")) {
                    String expectedDiscount1 = couponCalculation();
                    String expectedDiscount2 = df.format(stringToDoubleConversion(expectedDiscount1));
                    expectedDiscount = stringToDoubleConversion(expectedDiscount2);
                    scenarioContext.setContext(DISCOUNT, expectedDiscount);
                } else {
                    expectedDiscount = 0.00;
                    scenarioContext.setContext(DISCOUNT, expectedDiscount);
                }

                Double expectedTotalWithShipping = 0.00;
                if (UrlBuilder.getProvince().contains("Alberta")) {
                    expectedTotalWithShipping = expectedShippingCost + stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(GST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                } else if (UrlBuilder.getProvince().contains("British Columbia") || UrlBuilder.getProvince().contains("Manitoba")) {
                    expectedTotalWithShipping = expectedShippingCost + stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(GST)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(PST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                } else if (UrlBuilder.getProvince().contains("New Brunswick") ||
                        UrlBuilder.getProvince().contains("Newfoundland and Labrador") ||
                        UrlBuilder.getProvince().contains("Nova Scotia") ||
                        UrlBuilder.getProvince().contains("Prince Edward Island")) {
                    expectedTotalWithShipping = expectedShippingCost +
                            stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(HST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                } else if (UrlBuilder.getProvince().contains("Ontario")) {
                    expectedTotalWithShipping = expectedShippingCost + stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(HST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                } else if (UrlBuilder.getProvince().contains("Saskatchewan")) {
                    expectedTotalWithShipping = expectedShippingCost + stringToDoubleConversion((String) scenarioContext.getContext(SUBTOTAL)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(GST)) +
                            stringToDoubleConversion((String) scenarioContext.getContext(PST)) -
                            (Double) scenarioContext.getContext(DISCOUNT);
                }
                Long expectedTotalWithShipping1 = Math.round(expectedTotalWithShipping);
                Long actualTotalWithShippingCost = Math.round(totalWithShippingCost1);
                LOG.info("actualShippingCost " + actualShippingCost);
                LOG.info("expectedShippingCost " + expectedShippingCost);
                assertTrueExpectedTextEqualsActualTextDouble(expectedShippingCost, actualShippingCost);
//                assertTrueExpectedTextEqualsActualTextLong(expectedTotalWithShipping1, actualTotalWithShippingCost);
                assertThat(expectedTotalWithShipping1).isIn(actualTotalWithShippingCost + 1, actualTotalWithShippingCost - 1, actualTotalWithShippingCost);
                break;
        }
    }

    public Double shippingCostCalculation() {
        DecimalFormat df = new DecimalFormat("#.##");
        String subTotal = getTrimTextFor(SUBTOTAL_CART_AND_CHECKOUT);
        Double subTotal1 = stringToDoubleConversion(df.format(stringToDoubleConversion(subTotal.replace('$', ' '))));

        Double cost = 0.0;

        if (subTotal1 <= 75) {
            cost = 20.00;
        } else if (subTotal1 >= 75) {
            cost = 0.00;
        }
        return cost;
    }

    public void applyCoupon(String validOrInvalidCoupon, String coupon, String page) {
        waitForElementToDisappear(SUMMARY_LOADER,100);
        switch (page) {
            case "cart":
            case "signup":
                waitClearAndEnterText(ENTER_COUPON, coupon);
                waitForExpectedElement(COUPON_PROCEED_BUTTON).click();
                if (validOrInvalidCoupon.equals("invalid")) {
                    String key = "couponErrorMessage" + "-" + UrlBuilder.LANGUAGE;
                    assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(INVALID_COUPON_ERROR_MESSAGE));
                } else if (validOrInvalidCoupon.equals("valid")) {
                    threadSleep(5000);
                    waitClearAndEnterText(ENTER_COUPON, coupon);
                    waitForExpectedElement(COUPON_PROCEED_BUTTON).click();
                    String key = "alreadycouponExistingError" + "-" + UrlBuilder.LANGUAGE;
                    assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(INVALID_COUPON_ERROR_MESSAGE));
                    waitForExpectedElement(REMOVE_COUPON).click();
                    threadSleep(5000);
                    waitClearAndEnterText(ENTER_COUPON, coupon);
                    threadSleep(5000);
                    waitForExpectedElement(COUPON_PROCEED_BUTTON).click();
                    threadSleep(5000);
                    waitForExpectedElement(COUPON_CODE_IN_SUMMARY).isDisplayed();
                }
                break;
            case "checkout":
                waitClearAndEnterText(ENTER_COUPON_CHECKOUT, coupon);
                waitForExpectedElement(COUPON_PROCEED_BUTTON_CHECKOUT).click();

                if (validOrInvalidCoupon.equals("invalid")) {
                    String key = "couponErrorMessage" + "-" + UrlBuilder.LANGUAGE;
                    assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(INVALID_COUPON_ERROR_MESSAGE_CHECKOUT));
                } else if (validOrInvalidCoupon.equals("valid")) {
                    threadSleep(5000);
                    waitClearAndEnterText(ENTER_COUPON_CHECKOUT, coupon);
                    threadSleep(5000);
                    waitForExpectedElement(COUPON_PROCEED_BUTTON_CHECKOUT).click();
                    String key = "alreadycouponExistingError" + "-" + UrlBuilder.LANGUAGE;
                    assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(INVALID_COUPON_ERROR_MESSAGE_CHECKOUT));
                    waitForExpectedElement(REMOVE_COUPON).click();
                    threadSleep(5000);
                    waitClearAndEnterText(ENTER_COUPON_CHECKOUT, coupon);
                    threadSleep(5000);
                    waitForExpectedElement(COUPON_PROCEED_BUTTON_CHECKOUT).click();
                    threadSleep(5000);
                    waitForExpectedElement(COUPON_CODE_IN_SUMMARY).isDisplayed();
                }
                break;
        }
    }

    public String couponCalculation() {
        DecimalFormat df = new DecimalFormat("#.#");
        String subTotal = (String) scenarioContext.getContext(SUBTOTAL);
        String expectedDiscount = df.format((stringToDoubleConversion(subTotal) * 10) / 100);
        return expectedDiscount;
    }

    public void addCrossSellProduct() {
        threadSleep(2000);
        String key = "crossSellHeader"+"-"+UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key), getTrimTextFor(CROSS_SELL_HEADING));
        waitForExpectedElement(VIEW_ALL_LINK_CROSS_SELL).isDisplayed();
        clickByElementByQueryJSExecutor(ADD_TO_CART_BUTTON_CROSS_SELL);
    }

    public void validateCrossSellProductAddedOrNot(String page) {
        switch (page) {
            case "cart":
                threadSleep(7000);
                waitForElementToDisappear(SUMMARY_LOADER, 500);
                List<WebElement> productsCount_webElements = webDriver.findElements(PRODUCTS_COUNT_IN_CART);
                int productsCount = productsCount_webElements.size();
                assertTrue(productsCount == 2);
                threadSleep(7000);
                assertTrueExpectedTextEqualsActualText("2", getTrimTextFor(PRODUCTS_COUNT_IN_CHECKOUT));
                break;
            case "checkout":
                threadSleep(5000);
                waitForElementToDisappear(SUMMARY_LOADER, 200);
                assertTrueExpectedTextEqualsActualText("2", getTrimTextFor(PRODUCTS_COUNT_IN_CHECKOUT));
                break;
            case "orderDetailsPage":
                threadSleep(5000);
                List<WebElement> productsCountInOrderDetails_webElements = webDriver.findElements(accountDashboardPage.PRODUCTS_COUNT);
                int productsCountInOrderDetails = productsCountInOrderDetails_webElements.size();
                assertTrue(productsCountInOrderDetails == 2);
                break;
        }
    }

    public void validateProductsAddedToBasket() {
        threadSleep(7000);
        waitForElementToDisappear(SUMMARY_LOADER, 500);
        List<WebElement> productsCount_webElements = webDriver.findElements(PRODUCTS_COUNT_IN_CART);
        int productsCount = productsCount_webElements.size();
        threadSleep(7000);
        assertTrue(productsCount == 1);
        threadSleep(7000);
        assertTrueExpectedTextEqualsActualText("1", getTrimTextFor(PRODUCTS_COUNT_IN_CHECKOUT));
    }

    public void updateQuantity(String incOrDec, String page, String index) {
        switch (page) {
            case "basket":
                switch (incOrDec) {
                    case "increase":
                        waitForItemToBeClickableAndClick(INCREASE_QTY_BASKET);
                        threadSleep(3000);
                        break;
                    case "decrease":
                        waitForItemToBeClickableAndClick(DECREASE_QTY_BASKET);
                        threadSleep(3000);
                        break;
                }
                break;
            case "miniBasket":
                By UPDATE_BUTTON_MINIBASKET = By.xpath("(//button[@class='bat-cta-style bat-cta-style update-item link-dark center '])[" + index + "]");
                By UPDATE_BUTTON_MINIBASKET_FR = By.xpath("(//button[@class='bat-cta-style bat-cta-style update-item  center '])[" + index + "]");
//                By INCREASE_QTY_MINI_BASKET = By.cssSelector("bat-quantity-button bat-quantity-button--plus button-plus");
               By INCREASE_QTY_MINI_BASKET = By.xpath("(//div[@class='bat-minicart-cart-item-product-container-content']//button[@class='bat-quantity-button bat-quantity-button--plus button-plus'])[" + index + "]");
                By DECREASE_QTY_MINI_BASKET = By.xpath("(//div[@class='bat-minicart-cart-item-product-container-content']//button[@class='bat-quantity-button bat-quantity-button--minus button-minus'])[" + index + "]");
                switch (incOrDec) {
                    case "increase":
                        waitForItemToBeClickableAndClick(INCREASE_QTY_MINI_BASKET);
                        try {
                            waitForItemToBeClickableAndClick(UPDATE_BUTTON_MINIBASKET);
                        }catch (Exception e) {
                            waitForItemToBeClickableAndClick(UPDATE_BUTTON_MINIBASKET_FR);
                        }
                        threadSleep(3000);
                        break;
                    case "decrease":
                        waitForItemToBeClickableAndClick(DECREASE_QTY_MINI_BASKET);
                        try {
                            waitForItemToBeClickableAndClick(UPDATE_BUTTON_MINIBASKET);
                        }catch (Exception e) {
                            waitForItemToBeClickableAndClick(UPDATE_BUTTON_MINIBASKET_FR);
                        }
                        threadSleep(3000);
                        break;
                }
                break;
            case "plp":
                String ADDTOCART=UrlBuilder.getMessage("addToCart-"+ UrlBuilder.LANGUAGE);
                if(UrlBuilder.LANGUAGE.contains("fr")){
                    ADDTOCART=ADDTOCART+" ";
                }
                By DECREASE_QTY_PLP = By.xpath("(//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity']//button[@class='bat-quantity-button bat-quantity-button--minus button-minus'])[" + index + "]");
                By INCREASE_QTY_PLP = By.xpath("(//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity']//button[@class='bat-quantity-button bat-quantity-button--plus button-plus'])[" + index + "]");
                By INCREASE_QTY_PLP_UPDATED = By.xpath("(//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity changed']//button[@class='bat-quantity-button bat-quantity-button--plus button-plus'])[" + index + "]");
                By DECREASE_QTY_PLP_UPDATED = By.xpath("(//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity changed']//button[@class='bat-quantity-button bat-quantity-button--minus button-minus'])[" + index + "]");
                switch (incOrDec) {
                    case "increase":
                        try {
                            waitForItemToBeClickableAndClick(INCREASE_QTY_PLP_UPDATED);
                        } catch (Exception e) {
                            waitForItemToBeClickableAndClick(INCREASE_QTY_PLP);
                        }
                        threadSleep(3000);
                        break;
                    case "decrease":
                        try {
                            waitForItemToBeClickableAndClick(DECREASE_QTY_PLP_UPDATED);
                        } catch (Exception e) {
                            waitForItemToBeClickableAndClick(DECREASE_QTY_PLP);
                        }
                        threadSleep(3000);
                        break;
                }
                break;
        }
    }

    public void navigateToHomePageFromBasket() {
        waitForItemToBeClickableAndClick(ZONNIC_ICON_BASKET);
    }

    public void validateElementsInMiniBasket(String page) {
        switch (page) {
            case "miniBasket":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(ADDED_TO_BASKET_TEXT_MINI_BASKET), UrlBuilder.getMessage("addedToBasketText" + "-" + UrlBuilder.LANGUAGE));
                assertTrueExpectedTextEqualsActualText(getTrimTextForParticularIndexElement(BASKET_TOTAL_MINI_BASKET, 0), UrlBuilder.getMessage("basketTotalText" + "-" + UrlBuilder.LANGUAGE));
                assertTrueExpectedTextEqualsActualText(getTrimTextForParticularIndexElement(BASKET_TOTAL_MINI_BASKET, 1), UrlBuilder.getMessage("basketTotalText2" + "-" + UrlBuilder.LANGUAGE));
                assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("itemText" + "-" + UrlBuilder.LANGUAGE), getTrimTextFor(ITEM_TEXT_MINI_BASKET));
                assertTrueExpectedTextContainsActualText("$", getTrimTextFor(PRICE_FORMAT_MINI_BASKET));
                assertTrue(waitForExpectedElement(PRODUCT_IN_MINI_BASKET).isDisplayed());
                break;
            case "basket":
                assertTrueExpectedTextEqualsActualText(getTrimTextFor(SUMMARY_HEADER_IN_BASKET), UrlBuilder.getMessage("summaryHeader" + "-" + UrlBuilder.LANGUAGE));
                assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("basketHeaderPart1" + "-" + UrlBuilder.LANGUAGE), getTrimTextFor(HEADER_IN_BASKET));
                assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage("basketHeaderPart2" + "-" + UrlBuilder.LANGUAGE), getTrimTextFor(HEADER_IN_BASKET));
                assertTrue(waitForExpectedElement(PRODUCT_IN_BASKET).isDisplayed());
                break;
        }
    }

    public void removeItems(String page) {
        switch (page) {
            case "miniBasket":
                homePage.emptyBasket();
                break;
            case "basket":
                homePage.emptyBasketFromBasketPage();
                break;
        }
    }

    public void addProductToCartAccoringToPage(String page, String index) {
        switch (page) {
            case "PLP":
                plp.addToBasketButton(index, page);
                threadSleep(3000);
                break;
            case "PDP":

                break;
        }
    }
}