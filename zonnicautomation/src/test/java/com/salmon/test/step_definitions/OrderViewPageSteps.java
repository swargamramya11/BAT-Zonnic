package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.OrderViewPage;
import cucumber.api.java.en.And;

public class OrderViewPageSteps {
    private OrderViewPage orderViewPage;
    public OrderViewPageSteps(OrderViewPage orderViewPage) {
        this.orderViewPage = orderViewPage;
    }

    @And("^validate products quantity in order details page$")
    public void validateProductsQuantity() {
        orderViewPage.validateProductsQuantity();
    }
}