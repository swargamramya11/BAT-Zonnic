package com.salmon.test.page_objects.cucumberContext;

import com.salmon.test.page_objects.MailinatorPage;
import com.salmon.test.page_objects.OrderViewPage;
import lombok.Getter;
import org.testng.asserts.SoftAssert;

@Getter
public class PageObjectManager {
    private OrderViewPage orderViewPage;
    private MailinatorPage mailinatorPage;
    private SoftAssert softAssert;
    public PageObjectManager(OrderViewPage orderViewPage, MailinatorPage mailinatorPage,SoftAssert softAssert) {
        this.orderViewPage = orderViewPage;
        this.mailinatorPage = mailinatorPage;
        this.softAssert = softAssert;
    }
}
