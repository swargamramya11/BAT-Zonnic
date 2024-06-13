package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

public class BlogPage extends PageObject {
    public By BLOGS = By.cssSelector(".bat-card--blog-ctalist");
    public By Blogs_RM = By.cssSelector(".bat-card--blog-ctalist a");
    public By BLOGS_TITLE = By.cssSelector(".bat-card--blog-text");
    public By DATE_ON_MAIN_BLOG_PAGE = By.cssSelector(".bat-card--blog-text");
    public By TITLE = By.cssSelector(".bat-headline h1");
    public By DATE_BLOG_PAGE = By.cssSelector("bat-text-default[class='bat d-block d-sm-block d-md-block d-lg-block d-xl-block margin-small ready loaded'] span[class='dark']");
    public By DATE_BLOG_PAGE1 = By.cssSelector("bat-text-default[class='bat d-block d-sm-block d-md-block d-lg-block d-xl-block margin-small ready loaded'] span[class='sm dark']");
    public By FAQ_MESSAGE=By.xpath("//center[text()=' FAQ ']");

    public void navigatetoEachandEveryPageInBlogPage() {
        int size = webDriver.findElements(BLOGS).size();
        for (int i = 1; i < size; i++) {
            String titleInMainBlogPage = webDriver.findElements(BLOGS_TITLE).get(i).getText();
            String dateInMainBlogPage = webDriver.findElements(DATE_ON_MAIN_BLOG_PAGE).get(i).getText();
            LOG.info("titleInMainBlogPage " + titleInMainBlogPage);
            LOG.info("dateInMainBlogPage " + dateInMainBlogPage);
            try {
                webDriver.findElements(Blogs_RM).get(i).click();
            }catch(Exception e){
                webDriver.findElements(Blogs_RM).get(i).click();
            }
            String title;
            String date = null;
            try {
                title = waitForExpectedElement(TITLE).getText();
            } catch (Exception e1) {
                title = waitForExpectedElement(TITLE).getText();
            }
            try {
                date = waitForExpectedElement(DATE_BLOG_PAGE).getText();
            } catch (Exception e) {
                date = waitForExpectedElement(DATE_BLOG_PAGE1).getText();
            }
            LOG.info("title " + title);
            LOG.info("date " + date);
            assertTrueExpectedElementDisplayed(webDriver.findElement(FAQ_MESSAGE).isDisplayed());
            clickBrowserBackButton();
        }
    }
}

