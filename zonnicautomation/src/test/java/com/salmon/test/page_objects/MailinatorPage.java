package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.text.DecimalFormat;
import java.util.List;
import static com.salmon.test.page_objects.constants.Context.*;
import static com.salmon.test.page_objects.constants.Context.ITEMS_QTY_FROM_BASKET;
import static org.testng.AssertJUnit.assertTrue;

public class MailinatorPage extends PageObject {
    ScenarioContext scenarioContext;

    public MailinatorPage(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    private static final By EMAIL_MESSAGE_BODY_IFRAME = By.cssSelector("iframe#html_msg_body");
    private static final By EMAIL_RECEIVED = By.cssSelector("table.table-striped.jambo_table>tbody>tr>td:nth-child(3)");
    private static final By EMAIL_CONFIRMATION_MESSAGE = By.cssSelector("table.table-striped.jambo_table tbody tr");
    private static final By CONTENT = By.cssSelector(".main table .main-content p");
    public static final By RESET_PASSWORD_BUTTON = By.cssSelector("table.button td a");
    public static final By RESET_PASSWORD_BUTTON1 = By.cssSelector(".main-content table td table tr td a[href*='https://www.mailinator.com']");
    public static final By ORDER_NUMBER_IN_EMAIL = By.cssSelector(".main-content .email-summary h1");
    public static final By COUNT_OF_PRODUCTS_ORDERED = By.cssSelector(".email-items tbody p.sku");
    public static final By EACH_PRODUCT_QTY = By.cssSelector(".email-items tbody td.item-qty");
    public static final By SUB_TOTAL = By.cssSelector("tr.subtotal td");
    public static final By SHIPPING = By.cssSelector("tr.shipping td");
    public static final By TAX = By.cssSelector("tr.totals-tax td");
    public static final By GRAND_TOTAL = By.cssSelector("tr.grand_total_incl td");
    public static final By COUPON = By.cssSelector("tr.discount td");
    public static final By BILLING_SHIPPING_HEADER = By.cssSelector(".email-information .order-details .address-details h3");
    public static final By BILLING_SHIPPING_INFO = By.cssSelector(".email-information .order-details .address-details p");
    public static final By PAYMENT_SHIPMENT_HEADER = By.cssSelector(".email-information .method-info h3");
    public static final By SHIPMENT_METHOD = By.cssSelector(".email-information .method-info p");
    public static final By PAYMENT_METHOD1 = By.xpath("//tr[@class='email-information'] //td[@class='method-info']");
    public static final By DATE = By.cssSelector(".email-summary p");
    public static final By CUSTOMER_CARE_INFO = By.cssSelector(".cc-info");
    public static final By HEADER_IN_EMAIL = By.cssSelector(".main .feature h1");

    public void clickOnEmailWithSubject(String email, String subject) {
        for (int i = 0; i <= 20; i++) {
            List<WebElement> receivedEmailElements = getReceivedEmailElements(email);
            boolean clicked=false;
            try {
                threadSleep(2000);
                receivedEmailElements.stream()
                        .filter(webElement -> webElement.getText().contains(subject))
                        .findFirst().get().click();
                clicked=true;
            } catch (Exception e) {
                threadSleep(1000);
                refreshBrowser();
                clicked=false;
            }
            if(clicked){
                break;
            }
        }
    }

    public void switchToMessageBodyIframe() {
        frameToBeAvailableAndSwitchToIt(EMAIL_MESSAGE_BODY_IFRAME);
    }

    public void getInbox(String emailAddressData) {
        getWebDriver().get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + emailAddressData
                .substring(0, emailAddressData.indexOf("@")) + "#/#inboxpane");
        waitForAjaxElementNotToBePresent(getWebDriver(), 10);
    }

    public List<WebElement> getReceivedEmailElements(String emailAddressData) {
        LOG.info("email substring " + emailAddressData.substring(0, emailAddressData.indexOf("@")));
        //Redirect to Mailinator
        getInbox(emailAddressData);
        switch (UrlBuilder.getLocale()) {
            default:
                try {
                    waitForExpectedElement(EMAIL_CONFIRMATION_MESSAGE, 40);
                    waitForExpectedElement(EMAIL_RECEIVED, 40);
                    return presenceOfAllElementsLocatedBy(EMAIL_RECEIVED);
                } catch (Exception e) {
                    LOG.info("Exception when waitForExpectedElement EMAIL_CONFIRMATION_MESSAGE: " + e);
                    return presenceOfAllElementsLocatedBy(EMAIL_RECEIVED);
                }
        }
    }

    public void assertContentOfEmail(String content, int index){
        assertTrue(UrlBuilder.getMessage(content).contains(webDriver.findElements(CONTENT).get(index).getText()));
        assertTrueExpectedTextContainsActualText(UrlBuilder.getMessage(content), webDriver.findElements(CONTENT).get(index).getText());
    }

    public void assertHiInEmail(String hello){
        String key=hello + "-" + UrlBuilder.LANGUAGE;
        String firstName=(String) scenarioContext.getContext(FIRST_NAME);
        LOG.info("firstName: "+firstName);
        String expectedHi = UrlBuilder.getMessage(key)+" "+firstName+",";
        assertTrueExpectedTextContainsActualText(expectedHi, webDriver.findElements(CONTENT).get(0).getText());
    }

    public void validateOrderNumberHeader() {
        String actualOrderNumberInEmail = getTrimTextFor(ORDER_NUMBER_IN_EMAIL);
        String key = "orderNumberHeader"+"-"+UrlBuilder.LANGUAGE;
        String orderNumber = (String) scenarioContext.getContext(ORDER_NUMBER);
        String expectedOrderNumberInEmail = UrlBuilder.getMessage(key)+orderNumber;
        assertTrueExpectedTextEqualsActualText(expectedOrderNumberInEmail, actualOrderNumberInEmail);
    }

    public void validateDate() {
        String actualDate = getTrimTextFor(DATE);
        String expectedDate = null;

        if(UrlBuilder.LANGUAGE.equalsIgnoreCase("EN")) {
            expectedDate = getCurrentDateInFormat();
        } else {
            expectedDate = getCurrentDateInFormatInFrench();
        }
        assertTrueExpectedTextContainsActualText(expectedDate, actualDate);
    }

    public void validateNumberOfProductsAndQunatity() {
        String totalQty = (String) scenarioContext.getContext(PRODUCTS_QTY_FROM_BASKET);
        String items_Qty_From_Basket = (String) scenarioContext.getContext(ITEMS_QTY_FROM_BASKET);
        String actualTotalQty = String.valueOf(webDriver.findElements(COUNT_OF_PRODUCTS_ORDERED).size());

        int size = webDriver.findElements(EACH_PRODUCT_QTY).size();
        int actual_items_Qty_From_Basket_int = 0;
        int items_Qty_From_Basket1 = 0;
        for (int i = 1; i <= size; i++) {
            actual_items_Qty_From_Basket_int = items_Qty_From_Basket1 + Integer.parseInt(webDriver.findElement(By.xpath("(//table[@class='email-items']//td[@class='item-qty'])[" + i + "]")).getText().replace("QTY: ", " ").trim());
            items_Qty_From_Basket1 = actual_items_Qty_From_Basket_int;
        }
        String actual_items_Qty_From_Basket_String = String.valueOf(actual_items_Qty_From_Basket_int);
        assertTrueExpectedTextEqualsActualText(totalQty, actualTotalQty);
        assertTrueExpectedTextEqualsActualText(items_Qty_From_Basket, actual_items_Qty_From_Basket_String);
    }

    public void priceDetails(String coupon) {
        String expectedSubTotal = (String) scenarioContext.getContext(SUBTOTAL);
        String expectedShippingCost = (String) scenarioContext.getContext(ESTIMATEDSHIPPINGCOST);

        String GST_tax_String = null;
        double GST_tax_Int = 0;
        String PST_tax_String = null;
        double PST_tax_Int = 0;
        String HST_tax_String = null;
        double HST_tax_Int = 0;

        try {
            GST_tax_String = (String) scenarioContext.getContext(GST);
            GST_tax_Int = stringToDoubleConversion(GST_tax_String);
        } catch (Exception e) {
            try {
                PST_tax_String = (String) scenarioContext.getContext(PST);
                PST_tax_Int = stringToDoubleConversion(PST_tax_String);
            } catch (Exception e1) {
                try {
                    HST_tax_String = (String) scenarioContext.getContext(HST);
                    HST_tax_Int = stringToDoubleConversion(HST_tax_String);
                } catch (Exception e3) {

                }
            }
        }

        double totalActualTax = (Double) scenarioContext.getContext(TOTALTAX);

        String expectedTax = doubleToStringConversion(totalActualTax);
        String expectedGrandTotal;
        try {
            expectedGrandTotal = (String) scenarioContext.getContext(TOTALCOSTWITHSHIPPINGCOSTFORGUEST);
        }catch (Exception e) {
            Double expectedGrandTotal12 = (Double) scenarioContext.getContext(TOTALCOSTWITHSHIPPINGCOSTFORGUEST);
            expectedGrandTotal = doubleToStringConversion(expectedGrandTotal12);
        }

        String actualSubTotal = getTrimTextFor(SUB_TOTAL);
        String actualShipping = getTrimTextFor(SHIPPING);
        String actualTax = getTrimTextFor(TAX);
        String actualGrandTotal = getTrimTextFor(GRAND_TOTAL);

        if(coupon.equals("coupon")) {
            Double expectedDiscount = (Double) scenarioContext.getContext(DISCOUNT);
            String expectedDiscount1= doubleToStringConversion(expectedDiscount);
            String couponCode = getTrimTextFor(COUPON).replace("(","").replace(")","");
            assertTrueExpectedTextEqualsActualText("-$"+expectedDiscount1+"0", couponCode);
        }

        DecimalFormat df = new DecimalFormat("$#,###.00");

        double expectedSubTotalDouble  = stringToDoubleConversion(expectedSubTotal);
        double expectedGrandTotalDouble  = stringToDoubleConversion(expectedGrandTotal);

        assertTrueExpectedTextEqualsActualText(df.format(expectedSubTotalDouble), actualSubTotal);
        assertTrueExpectedTextEqualsActualText("$"+expectedShippingCost+"0", actualShipping);
        assertTrueExpectedTextEqualsActualText(df.format(expectedGrandTotalDouble), actualGrandTotal);

        String expectedTax1 = "$" + expectedTax;
        if(expectedTax1.equals(actualTax)) {
            assertTrueExpectedTextEqualsActualText(expectedTax1, actualTax);
        } else {
            assertTrueExpectedTextEqualsActualText(expectedTax1 + "0", actualTax);
        }
    }

    public void validateAddress(String address) {
        String expectedTelePhoneNumber = null;
        String expectedFirstNameLastName = null;
        String[] expectedBillingAddress1 = null;

        String billingAddressHeader = getTrimTextForParticularIndexElement(BILLING_SHIPPING_HEADER, 0);
        String shippingAddressHeader = getTrimTextForParticularIndexElement(BILLING_SHIPPING_HEADER, 1);
        String billingAddressHeaderKey = "billingAddressHeader" + "-" + UrlBuilder.LANGUAGE;
        String shippingAddressHeaderKey = "shippingAddressHeader" + "-" + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(billingAddressHeader, UrlBuilder.getMessage(billingAddressHeaderKey));
        assertTrueExpectedTextEqualsActualText(shippingAddressHeader, UrlBuilder.getMessage(shippingAddressHeaderKey));

//Billing Info =================================================================================================================

        threadSleep(3000);
        String actualBillingAddressInfo = getTrimTextForParticularIndexElement(BILLING_SHIPPING_INFO, 0);
        String expectedBillingAddressInfo = (String) scenarioContext.getContext(ADDRESS_FROM_CREATE_ACCOUNT);
        LOG.info(expectedBillingAddressInfo);
        String[] expectedBillingAddressInfo1 = expectedBillingAddressInfo.split(" ");

        expectedTelePhoneNumber = (String) scenarioContext.getContext(TELEPHONE_NUMBER_FROM_CREATE_ACCOUNT);
        expectedFirstNameLastName = (String) scenarioContext.getContext(FISTANDLASTNAME_ACCOUNT_CREATION);

        assertTrueExpectedTextContainsActualText(expectedTelePhoneNumber.replace("-",""), actualBillingAddressInfo);
        assertTrueExpectedTextContainsActualText(expectedFirstNameLastName, actualBillingAddressInfo);
        assertTrueExpectedTextContainsActualText(expectedBillingAddressInfo1[0], actualBillingAddressInfo);
        assertTrueExpectedTextContainsActualText(expectedBillingAddressInfo1[1], actualBillingAddressInfo);
        assertTrueExpectedTextContainsActualText(expectedBillingAddressInfo1[2], actualBillingAddressInfo);
        assertTrueExpectedTextContainsActualText(expectedBillingAddressInfo1[3], actualBillingAddressInfo);

        switch (address) {
            case "oldDeliveryAddress":
                String actualDeliveryAddress = getTrimTextForParticularIndexElement(BILLING_SHIPPING_INFO, 1);
                String expectedDeliveryAddress = (String) scenarioContext.getContext(ADDRESS_FROM_CREATE_ACCOUNT);
                expectedTelePhoneNumber = (String) scenarioContext.getContext(TELEPHONE_NUMBER_FROM_CREATE_ACCOUNT);
                expectedFirstNameLastName = (String) scenarioContext.getContext(FISTANDLASTNAME_ACCOUNT_CREATION);
                LOG.info("expectedDeliveryAddress "+expectedDeliveryAddress);
                expectedBillingAddress1 = expectedDeliveryAddress.split(" ");

                assertTrueExpectedTextContainsActualText(expectedTelePhoneNumber.replace("-",""), actualDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedFirstNameLastName, actualDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[0], actualDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[1], actualDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[2], actualDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[3], actualDeliveryAddress);
                break;
            case "newDeliveryAddress":
                String actualnewDeliveryAddress = getTrimTextForParticularIndexElement(BILLING_SHIPPING_INFO, 1);
                String expectednewDeliveryAddress = (String) scenarioContext.getContext(NEW_ADDRESS_CHECKOUT);
                expectedTelePhoneNumber = (String) scenarioContext.getContext(NEW_TELEPHONE_NUMBER);
                expectedFirstNameLastName = (String) scenarioContext.getContext(NEW_FIRST_LASTNAME_CHECKOUT);
                LOG.info("expectednewDeliveryAddress "+expectednewDeliveryAddress);
                expectedBillingAddress1 = expectednewDeliveryAddress.split(" ");

                assertTrueExpectedTextContainsActualText(expectedTelePhoneNumber.replace("-",""), actualnewDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedFirstNameLastName, actualnewDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[0], actualnewDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[1], actualnewDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[2], actualnewDeliveryAddress);
                assertTrueExpectedTextContainsActualText(expectedBillingAddress1[3], actualnewDeliveryAddress);
                break;
        }
    }

    public void validatePaymentMethod() {
        String paymentMethodHeader = getTrimTextForParticularIndexElement(PAYMENT_SHIPMENT_HEADER, 0);
        String paymentMethodHeaderKey = "paymentMethodHeader" + "-" + UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(paymentMethodHeader, UrlBuilder.getMessage(paymentMethodHeaderKey));

        threadSleep(2000);
        String actualPaymentMethod1 = getTrimTextForParticularIndexElement(PAYMENT_METHOD1, 0);
        String[] actualPaymentMethod2 = actualPaymentMethod1.replace("\n"," ").split(" ");
        String actualPaymentMethod = null;
        if(UrlBuilder.LANGUAGE.equals("en")) {
            actualPaymentMethod = actualPaymentMethod2[2] + " " + actualPaymentMethod2[3] + " " + actualPaymentMethod2[4];
        } else {
            actualPaymentMethod = actualPaymentMethod2[3] + " " + actualPaymentMethod2[4] + " " + actualPaymentMethod2[5];
        }
        String key = null;

        String cardType = (String) scenarioContext.getContext(PAYMENT_METHOD);
        if(cardType.equals("visaCardDetails")) {
            key = "carPaymentTypeVisa"+"-"+UrlBuilder.LANGUAGE;
        } else if(cardType.equals("masterCardDetails")) {
            key = "carPaymentTypeMaster"+"-"+UrlBuilder.LANGUAGE;
        } else if(cardType.equals("amexCardDetails")) {
            key = "carPaymentTypeAmex"+"-"+UrlBuilder.LANGUAGE;
        }
        String expectedPaymentMethod = UrlBuilder.getMessage(key);
        assertTrueExpectedTextEqualsActualText(actualPaymentMethod, expectedPaymentMethod);
    }

    public void validateShippingMethod() {
        String ShippingMethodHeader = getTrimTextForParticularIndexElement(PAYMENT_SHIPMENT_HEADER, 1);
        String ShippingMethodHeaderKey = "shippingMethodHeader" + "-" + UrlBuilder.LANGUAGE;

        if(!ShippingMethodHeader.equals(UrlBuilder.getMessage(ShippingMethodHeaderKey))) {
            ShippingMethodHeaderKey = "shippingMethodHeader1" + "-" + UrlBuilder.LANGUAGE;
        }

        assertTrueExpectedTextEqualsActualText(ShippingMethodHeader, UrlBuilder.getMessage(ShippingMethodHeaderKey));

        String actualShippingMethod = getTextFor(SHIPMENT_METHOD);
        String shippingMethodHeaderKey = (String) scenarioContext.getContext(SHIPPING_METHOD);
        String key = "delivery"+"-"+UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(UrlBuilder.getMessage(key)+" "+shippingMethodHeaderKey, actualShippingMethod);
    }

    public void validateCustomerCareInfo() {
        String customerCareInfo = getTrimTextFor(CUSTOMER_CARE_INFO);
        String key = "customerCareDetails"+"-"+UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(customerCareInfo, UrlBuilder.getMessage(key));
    }

    public void validateHeader(String key) {
        String headerEmail = getTrimTextFor(HEADER_IN_EMAIL);
        String key1 = key+"-"+UrlBuilder.LANGUAGE;
        assertTrueExpectedTextEqualsActualText(headerEmail, UrlBuilder.getMessage(key1));
    }
}