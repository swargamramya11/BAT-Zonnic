package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import static com.salmon.test.page_objects.constants.Context.ITEMS_QTY_FROM_BASKET;
import static com.salmon.test.page_objects.constants.Context.PRODUCTS_QTY_FROM_BASKET;

public class OrderViewPage extends PageObject {
    private ScenarioContext scenarioContext;
    public OrderViewPage(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    public static final By ORDERED_ITEMS_COUNT = By.cssSelector("#orderedItems .product-ordered");
    public static final By QTY_ORDERED = By.cssSelector(".product-ordered .product-qty");
    public void validateProductsQuantity() {
        String totalQty = (String) scenarioContext.getContext(PRODUCTS_QTY_FROM_BASKET);
        String items_Qty_From_Basket = (String) scenarioContext.getContext(ITEMS_QTY_FROM_BASKET);
        String actualTotalQty = String.valueOf(webDriver.findElements(ORDERED_ITEMS_COUNT).size());

        int size = webDriver.findElements(QTY_ORDERED).size();
        int actual_items_Qty_From_Basket_int = 0;
        int items_Qty_From_Basket1 = 0;
        for (int i = 1; i <= size; i++) {
            String asds=webDriver.findElement(By.xpath("(//div[@class='product-ordered'] //div[@class='product-qty'])[" + i + "]")).getText().replace("QTY: ", " ").trim();
            actual_items_Qty_From_Basket_int = items_Qty_From_Basket1 + Integer.parseInt(webDriver.findElement(By.xpath("(//div[@class='product-ordered'] //div[@class='product-qty'])[" + i + "]")).getText().replace("QTY: ", " ").trim());
            items_Qty_From_Basket1 = actual_items_Qty_From_Basket_int;
        }
        String actual_items_Qty_From_Basket_String = String.valueOf(actual_items_Qty_From_Basket_int);
        assertTrueExpectedTextEqualsActualText(totalQty, actualTotalQty);
        assertTrueExpectedTextEqualsActualText(items_Qty_From_Basket, actual_items_Qty_From_Basket_String);
    }
}