package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;

import static com.salmon.test.page_objects.constants.Context.SKU;
import static org.testng.Assert.assertTrue;

@Getter
@Setter
public class PLP extends PageObject {
    PDP pdp;
    BATHelper batHelper;
    private PercyPage percyPage;
    private ScenarioContext scenarioContext;
    HomePage homePage = new HomePage();

    public PLP(PercyPage percyPage, PDP pdp, ScenarioContext scenarioContext) {
        this.pdp = pdp;
        this.scenarioContext = scenarioContext;
        this.percyPage = percyPage;
    }

    private static final By CART_CA = By.cssSelector(".bat-header-utils .bat-header-cart button");
    public static final By PLP_HERO_BANNER_CA = By.cssSelector(".bat-hero-zonnic.no-padding");
    private final static By PLP_ADDTOBASKET_CA = By.cssSelector(".productcard-ctas button.bat-cta-style.button-dark.center");
    private final static By PLP_ADDTOBASKET_BUTTON_TEXT = By.cssSelector(".productcard-ctas button.bat-cta-style.button-dark.center span");
    private final static By PLP_PRODUCT_NAME_CA = By.xpath("//div[@class='productcard-text-name']//a");
    private final static By SUCCESSFULL_MESSAGE_FOR_ADDING_PRODUCT_TO_BASKET = By.cssSelector(".bat-messagebar--zonnic-message.active");
    private final static By ERROR_MESSAGE_FOR_QUANTITY = By.cssSelector(".bat-messagebar--zonnic-message-error.active");
    private final static By ERROR_MESSAGE_FOR_QUANTITY_FR = By.cssSelector(".bat-messagebar--default-message-error.active");
    public void headerPageNavigation(String category, String subCategory) {
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage(category)));
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage(category)));
        if(category.contains("FAQ")){
            clickBrowserBackButton();
        }
    }

    public void PLPZonnic() {
        waitForExpectedElement(CART_CA).click();
        percyPage.takePercyScreenshot("Empty Mini Basket");
        waitForExpectedElement(By.linkText(UrlBuilder.getMessage("nicotinePouches-"+ UrlBuilder.LANGUAGE)));
        clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("nicotinePouches-"+ UrlBuilder.LANGUAGE)));
//        assertTrue(waitForExpectedElement(SORT_OPTION_CA).isDisplayed());
//        assertTrue(waitForExpectedElement(FILTER_OPTION_CA).isDisplayed());
        String ADDTOCART=UrlBuilder.getMessage("addToCart-"+ UrlBuilder.LANGUAGE);
        if(UrlBuilder.LANGUAGE.contains("fr")){
            ADDTOCART=ADDTOCART+" ";
        }
        By PLP_PRODUCT_HEADING=By.xpath("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//a");
        assertTrue(waitForExpectedElement(PLP_PRODUCT_HEADING).isDisplayed());
        assertTrue(waitForExpectedElement(PLP_HERO_BANNER_CA).isDisplayed());
        By PLP_PRODUCT_QUANTITY_CA=By.xpath("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='productcard-controls']//div[@class='bat-quantity']");
        assertTrue(waitForExpectedElement(PLP_PRODUCT_QUANTITY_CA).isDisplayed());
//        assertTrue(waitForExpectedElement(PLP_SHOWING_FLAVOURS_CA).isDisplayed());
        By PLP_PRODUCT_DESCRIPTION_CA=By.xpath("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='productcard-text-description']");
        assertTrue(webDriver.findElements(PLP_PRODUCT_DESCRIPTION_CA).get(1).isDisplayed());
        By PLP_PRODUCT_PRICE_CA=By.xpath("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='productcard-text-price']");
        assertTrue(webDriver.findElements(PLP_PRODUCT_PRICE_CA).get(1).isDisplayed());
        String price=webDriver.findElements(PLP_PRODUCT_PRICE_CA).get(1).getText();
        assertTrueExpectedTextContainsActualText(".", price);
        assertTrueExpectedTextContainsActualText("$", price);
    }

    public void addProductToBasket() {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                waitForExpectedElement(PLP_ADDTOBASKET_CA, 30);
                clickUsingJS(PLP_ADDTOBASKET_CA);
                String successMessage = getTrimTextFor(SUCCESSFULL_MESSAGE_FOR_ADDING_PRODUCT_TO_BASKET);
                if(UrlBuilder.LANGUAGE.equalsIgnoreCase("en")) {
                    assertTrueExpectedTextContainsActualText("YOU ADDED", successMessage);
                } else {
                    assertTrueExpectedTextContainsActualText("VOUS AVEZ", successMessage);
                }
                threadSleep(2000);
                percyPage.takePercyScreenshot("Success message after adding product to cart");
                break;
        }
    }

    public void updateQuantityInPLP(String qty)  {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                String ADDTOCART=UrlBuilder.getMessage("addToCart-"+ UrlBuilder.LANGUAGE);
                if(UrlBuilder.LANGUAGE.contains("fr")){
                    ADDTOCART=ADDTOCART+" ";
                }
                By QTY=By.xpath("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity']//input");
                By QTY_CHANGED=By.xpath("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity changed']//input");
                waitForExpectedElement(QTY).clear();
                waitForExpectedElement(QTY_CHANGED).sendKeys(qty);
                threadSleep(1000);
        }
    }

    public void addProductToBasket1() throws InterruptedException {
        switch (UrlBuilder.getLocale()) {
            case "zonnicca":
                String ADDTOCART=UrlBuilder.getMessage("addToCart-"+ UrlBuilder.LANGUAGE);
                if(UrlBuilder.LANGUAGE.contains("fr")){
                    ADDTOCART=ADDTOCART+" ";
                }
                By ADDTOCART_1=By.xpath("(//span[text()='"+ADDTOCART+"'])[1]");
                Thread.sleep(1000);
                waitForExpectedElement(ADDTOCART_1).click();
        }
    }

    public void updateQuantityInPLP1(String qty, String index, String page) {
        String ADDTOCART=null;
        ADDTOCART = UrlBuilder.getMessage("addToCart-" + UrlBuilder.LANGUAGE);
        if (UrlBuilder.LANGUAGE.contains("fr")) {
            ADDTOCART = ADDTOCART + " ";
        }
        By QTY;
        By QTY_CHANGED;
        By SKU_Number;
        String SKU1;
        switch (page) {
            case "PLP":
                SKU_Number = By.xpath("(//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard'])[" + index + "]");
                SKU1=getAttribute(SKU_Number, "data-sku");
                scenarioContext.setContext(SKU, SKU1);
                QTY = By.xpath("(//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity']//input)[" + index + "]");
                QTY_CHANGED = By.xpath("//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='bat-quantity changed']//input");
                waitForExpectedElement(QTY).clear();
                waitForExpectedElement(QTY_CHANGED).sendKeys(qty);
                threadSleep(1000);
                break;
            case "PDP":
                waitForExpectedElement(pdp.QTY_PDP).clear();
                waitForExpectedElement(pdp.QTY_PDP_CHANGED).sendKeys(qty);
                threadSleep(1000);
                break;
        }
    }

    public void addToBasketButton(String index, String page) {
        switch (page) {
            case "PLP":
                String ADDTOCART = UrlBuilder.getMessage("addToCart-" + UrlBuilder.LANGUAGE);
                if (UrlBuilder.LANGUAGE.contains("fr")) {
                    ADDTOCART = ADDTOCART + " ";
                }
                By addToCartButton = By.xpath("(//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='productcard-ctas']//button)[" + index + "]");
                waitForExpectedElement(addToCartButton).click();
                threadSleep(1000);
                break;
            case "PDP":
                waitForExpectedElement(pdp.ADDTOBASKET).click();
                break;
        }
    }

    public void addProductToCart(String qty, String page, String index) {
        switch (page) {
            case "PLP":
                updateQuantityInPLP1(qty, index, page);
                addToBasketButton(index, page);
                threadSleep(3000);
                break;
            case "PDP":
                pdp.navigateToPdp(index);
                updateQuantityInPLP1(qty, index, page);
                addToBasketButton(index, page);
                break;
        }
    }

    public void validateQuantityErrorMessages(String error, String page) {
        String SKU1 = (String) scenarioContext.getContext(SKU);
        String key1 = null;
        String key2 = null;
        String expectedError = null;
        String actualError = null;
        switch (page) {
            case "pdp":
            case "plp":
                switch (error) {
                    case "maxQuantityForSingleProduct":
                        key1 = "maxQuantityForSingleProductMessagePart1" + "-" + UrlBuilder.LANGUAGE;
                        key2 = "maxQuantityForSingleProductMessagePart2" + "-" + UrlBuilder.LANGUAGE;
                        expectedError = UrlBuilder.getMessage(key1) + " " + SKU1 + " " + UrlBuilder.getMessage(key2);
                        actualError = getTextFor(ERROR_MESSAGE_FOR_QUANTITY);
                        break;
                    case "maxQuantityForCombinationProduct":
                        break;
                }
                break;
            case "basket":
            case "miniBasket":
                switch (error) {
                    case "maxQuantityForSingleProduct":
                        key1 = "maxQuantityForSingleProductMessageBasketPart1" + "-" + UrlBuilder.LANGUAGE;
                        key2 = "maxQuantityForSingleProductMessageBasketPart2" + "-" + UrlBuilder.LANGUAGE;
                        expectedError = UrlBuilder.getMessage(key1) + " " + UrlBuilder.getMessage(key2);

                        try {
                            actualError = getTextFor(ERROR_MESSAGE_FOR_QUANTITY);
                        } catch (Exception e) {
                            actualError = getTextFor(ERROR_MESSAGE_FOR_QUANTITY_FR);
                        }
                        break;
                    case "maxQuantityForCombinationProduct":
                        break;
                }
                break;
        }

        switch (error) {
            case "maxQuantityForSingleProduct":
                assertTrueExpectedTextEqualsActualText(expectedError, actualError);
                break;
            case "maxQuantityForCombinationProduct":
                if(Props.getTestSuite().equals("Live")) {
                    key1 = "maxQuantityForCombinationProductProd" + "-" + UrlBuilder.LANGUAGE;
                } else {
                    key1 = "maxQuantityForCombinationProduct" + "-" + UrlBuilder.LANGUAGE;
                }
                key2 = "maxQuantityForCombinationProductCans" + "-" + UrlBuilder.LANGUAGE;
                expectedError = UrlBuilder.getMessage(key1);
                actualError = getTextFor(ERROR_MESSAGE_FOR_QUANTITY);
                if(actualError.contains(expectedError)) {
                    expectedError = UrlBuilder.getMessage(key1);
                } else {
                    expectedError = UrlBuilder.getMessage(key2);
                }
                assertTrueExpectedTextContainsActualText(expectedError, actualError);
                break;
        }
    }
}