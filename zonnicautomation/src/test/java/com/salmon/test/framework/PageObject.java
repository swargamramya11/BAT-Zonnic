package com.salmon.test.framework;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.Region;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.salmon.test.framework.helpers.WebDriverHelper.percy;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
public abstract class PageObject {
    public static By LOADER = By.cssSelector("div.LOADER, div.loader");
    private static long DRIVER_WAIT_TIME = 10;
    protected static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    protected static int DEFAULT_TIMEOUT=30;
    public static JavascriptExecutor js;
    @Getter
    protected WebDriverWait wait;
    @Getter
    protected WebDriver webDriver;
    @Getter
    protected Eyes eyes;

    protected PageObject() {
        this.webDriver = WebDriverHelper.getWebDriver();
        this.wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
    }

    public String returnHeaderTextCanada(){
        return webDriver.findElement(By.cssSelector(".bat-order-confirmation-message-heading h1")).getText();
    }

    // ******************************** // FINDERS // ************************************ //
    public boolean isElementPresentByby(By by){
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        boolean exists = webDriver.findElements(by).size() != 0;
        webDriver.manage().timeouts().implicitlyWait(DRIVER_WAIT_TIME, TimeUnit.SECONDS);
        return exists;
    }

    public boolean isPageLoadedWithPageTitleOf(String expectedPageTitle){
        webDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        boolean pageLoaded = false;
        try {
            wait.until(ExpectedConditions.titleContains(expectedPageTitle));
            pageLoaded = true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.error("\nPAGE NOT LOADED WITH THE EXPECTED TITLE OF : " + expectedPageTitle);
            LOG.error("\nEXPECTED PAGE TITLE : " + expectedPageTitle);
            LOG.error("\nACTUAL PAGE TITLE : " + webDriver.getTitle());
        }
        webDriver.manage().timeouts().implicitlyWait(DRIVER_WAIT_TIME, TimeUnit.SECONDS);
        return pageLoaded;
    }

    public void getAllButtons(){
        List<WebElement> buttons = getWebDriver().findElements(By.tagName("button"));
        for (WebElement button : buttons){
            if(button.getText().length() > 0){
                LOG.info("BUTTON (With Text)PRESENT : " + button.getText());
            }
        }
    }

    public void getAllLinks(){
        List<WebElement> links = getWebDriver().findElements(By.tagName("a"));
        Integer linksWithText = 0;
        for (WebElement link : links){
            if(link.getText().length() > 0){
                linksWithText ++;
            }
        }
    }

    public void getAllTables(){
        List<WebElement> tables = getWebDriver().findElements(By.tagName("table"));
        LOG.info("Tables found : " + tables.size());
        for (WebElement table : tables){
            LOG.info("table Name : " + table.getAttribute("class"));
        }
    }

    //TODO modify size to just displayed ul's with class name length > 0 - only care about dynamic content
    public void getAllLists(){
        List<WebElement> lists = getWebDriver().findElements(By.tagName("ul"));
        LOG.info("Total number of lists : " + lists.size());
        for (WebElement list : lists){
            if(list.getAttribute("class").length() > 0){
            }
        }
        LOG.info("Total number of LISTS found : " + lists.size());
    }

    public void getAllImages(){
        LOG.info("Running : getAllImages");
        List<WebElement> images = getWebDriver().findElements(By.tagName("img"));
        LOG.info("Total number of images found : " + images.size());
        for (WebElement image : images){
            if (image.getAttribute("class").equals("desktop")){
                LOG.info("IMAGE : " + image.getAttribute("alt"));
            }
        }
    }

    // ******************************** // ASSERTIONS // ************************************ //
    public void assertTrueExpectedTextEqualsActualText(String expectedText, String actualText){
        assertTrue("\n\n ******** ERROR *********** \n" +
            "\n NOT THE EXPECTED RESULT!! " +
            "\n EXPECTED : " + expectedText +
            "\n ACTUAL   : " + actualText,expectedText.equals(actualText));
    }

    // ******************************** // ASSERTIONS // ************************************ //
    public void assertTrueExpectedTextContainsActualText(String expectedText, String actualText){
        assertTrue("\n\n ******** ERROR *********** \n" +
                "\n NOT THE EXPECTED RESULT!! " +
                "\n EXPECTED : " + expectedText +
                "\n ACTUAL   : " + actualText,actualText.contains(expectedText));
    }

    public void assertTrueExpectedTextEqualsActualTextDouble(Double expectedText, Double actualText){
        assertTrue("\n\n ******** ERROR *********** \n" +
                "\n NOT THE EXPECTED RESULT!! " +
                "\n EXPECTED : " + expectedText +
                "\n ACTUAL   : " + actualText,actualText.equals(expectedText));
    }

    public void assertTrueExpectedTextEqualsActualTextLong(Long actualText, Long expectedText){
        assertTrue("\n\n ******** ERROR *********** \n" +
                "\n NOT THE EXPECTED RESULT!! " +
                "\n EXPECTED : " + expectedText +
                "\n ACTUAL   : " + actualText,actualText.equals(expectedText));
    }

    public void assertTrueExpectedTextEqualsActualTextIgnoreCase(String expectedText, String actualText){
        assertTrue("\n\n ******** ERROR *********** \n" +
                "\n NOT THE EXPECTED RESULT!! " +
                "\n EXPECTED : " + expectedText +
                "\n ACTUAL   : " + actualText,expectedText.equalsIgnoreCase(actualText));
    }

    public void assertTrueExpectedElementDisplayed(Boolean actualText){
        assertTrue("\n\n ******** ERROR *********** \n" +
                "\n DISPLAYED : " + actualText,actualText);
    }

    // ******************************** // GETTERS // ************************************ //
    /**
     * Returns the current Url from page
     **/
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public boolean isSelected(By by){
        return waitForExpectedElement(by).isSelected();
    }

    /**
     * Returns the current page title from page
     */
    public String getCurrentPageTitle() {
        return getWebDriver().getTitle();
    }

    public String getTextFor(By by) {
        return waitForExpectedElement(by).getText();
    }

    public String getTrimTextFor(By by) {
        return waitForExpectedElement(by).getText().trim();
    }

    public String getTrimUpperText(By by) {
        return waitForExpectedElement(by).getText().trim().toUpperCase();
    }

    public String getTrimTextForParticularIndexElement(By by, int index) {
        return webDriver.findElements(by).get(index).getText().trim();
    }

    public void clickForParticularIndexElement(By by, int index) {
        webDriver.findElements(by).get(index).click();
    }

    public String getValue(By by){
        return waitForExpectedElement(by).getAttribute("value");
    }

    public String getAttribute(By by, String attributeValue){
        return waitForExpectedElement(by).getAttribute(attributeValue);
    }

    public boolean hasAttribute(WebElement webElement, String attributeValue){
        return (webElement.getAttribute(attributeValue)==(null))? false: true;
    }

    public List<WebElement> getTableHeaders(By by){
        return waitForExpectedElement(by).findElements(By.tagName("th"));
    }

    public List<WebElement> getTableRows(By by){
        LOG.info("/n TABLE HEADER : " + waitForExpectedElement(by).findElements(By.tagName("tr")));
        return waitForExpectedElement(by).findElements(By.tagName("tr"));
    }

    public List<WebElement> getTableCols(By by){
        return waitForExpectedElement(by).findElements(By.tagName("td"));
    }

    public Integer getTableCount(By by){
        List<WebElement> listOfRows = waitForExpectedElement(by).findElements(By.tagName("tr"));
        return listOfRows.size()-1;
    }

    /**
     * Get all <code><option/></code> innerHTML attributes
     *
     */
    public List<String> getAllSelectOptions(By by) {
        isElementPresentByby(by);
        List<String> options = new ArrayList<String>();
        for (WebElement option : new Select(webDriver.findElement(by)).getOptions()) {
            if (!option.getAttribute("value").isEmpty()) options.add(option.getText());
            LOG.info("Select Option : " + option.getText());
        }
        return options;
    }

    public Set<org.openqa.selenium.Cookie> getCookiesViaSelenium(){
        return webDriver.manage().getCookies();
    }

    // ******************************** // ACTIONS // ************************************ //
    // *** HighLighter *** //
    public void highlightElement(By by, int duration) throws InterruptedException {
        WebElement element = webDriver.findElement(by);

        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
            ((JavascriptExecutor) webDriver).executeScript("scroll(0, -250);");
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='2px solid red'", element);
        }
        if (duration > 0) {
            Thread.sleep(duration * 2000);
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].style.border=''", element);
        }
    }
    // *** CLICKS *** //
    public void clickByElement(By by){
            try {
                clickByElementByQueryJSExecutor(by);
            } catch (Exception e) {
                LOG.info("**** ERROR FINDING ELEMENT : " + by);
                LOG.info("Failed to find element after 2 passes ");
                LOG.info("Page title : "  + getCurrentPageTitle());
                LOG.info("Please ensure element is present on page via isElementPresent methods");
            }
        }

    public void visit(String site){
        webDriver.get(site);
    }

    public void clickByElementByQueryJSExecutor(final By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("arguments[0].scrollIntoView()", waitForExpectedElement(by));
        jse.executeScript("arguments[0].click()",waitForExpectedElement(by));
    }

    public void clickElementByQueryJSExecutor(final WebElement ele) {
        wait.until(ExpectedConditions.visibilityOf(ele));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("arguments[0].scrollIntoView()", ele);
        jse.executeScript("arguments[0].click()",ele);
    }

    public void clickFirstElementByQueryJSExecutor(final By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        if(getWebDriver().findElements(by).size()>1) {
            jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElements(by).get(0));
            jse.executeScript("arguments[0].click()", webDriver.findElements(by).get(0));
        }else{
            jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(by));
            jse.executeScript("arguments[0].click()", webDriver.findElement(by));
        }
    }

    
    // *** SELECT *** //
    public void selectValueFromDropDownByby(String itemToSelect, By by){
        Select ProductDropDown;
        try {
            ProductDropDown = new Select(waitForExpectedElement(by, 10));
        }
        catch(WebDriverException e){
            ProductDropDown = new Select(webDriver.findElement(by));
        }
        try{
            ProductDropDown.selectByVisibleText(itemToSelect);
        }catch (Exception e){
            ProductDropDown.selectByIndex(1);
        }
    }

    public void selectValueFromDropDownByIndex(int index, By by) {
        Select ProductDropDown;
        try {
            ProductDropDown = new Select(waitForExpectedElement(by, 20));
        }
        catch(WebDriverException e){
            ProductDropDown = new Select(webDriver.findElement(by));
        }
        ProductDropDown.selectByIndex(index);
    }

    public void selectOptionFromDropDownByValue(String value, By by) {
        Select ProductDropDown = new Select(waitForExpectedElement(by,20));
        ProductDropDown.selectByValue(value.trim());
    }

    public void selectValueFromDropDownByWebElement(WebElement ele, String text) {
        Select dropdown = new Select(ele);
        dropdown.selectByVisibleText(text);
    }

    // *** POPULATE *** //
    public void waitClearAndEnterText(final By by, String textToEnter) {
        waitForExpectedElement(by).clear();
        waitForExpectedElement(by).sendKeys(textToEnter);
    }

    public void enterText(final By by, String textToEnter) {
        waitForExpectedElement(by,20).sendKeys(textToEnter);
    }

    // *** INJECT CSS *** Experimental //
    public void injectCSS(String cssToBeInjected){
        LOG.info("Running : injectCSS");
        // Example CSS : '<style type=\"text/css\">.toast-box { display: none !important; }</style>';"
        try {
            ((JavascriptExecutor) webDriver).executeScript("document.body.innerHTML = document.body.innerHTML + "+ cssToBeInjected +"" );
            webDriver.navigate().refresh();
        } catch (Exception e) {
            LOG.info("\n*** ERROR ***");
            LOG.info("\n*** Injecting CSS - Please check your CSS Block and speak to Front End Dev ***");
        }
    }

    // *** NAVIGATION *** //
    public void clickBrowserBackButton() {
        LOG.info("Running : clickBrowserBackButton()");webDriver.navigate().back();
    }

    public void clickBrowserForwardButton() { LOG.info("Running : clickBrowserForwardButton()");webDriver.navigate().forward();}
    public void refreshBrowser(){LOG.info("Running : refreshBrowser");webDriver.navigate().refresh();}
    public void clearCache(){LOG.info("Running : clearCache");webDriver.manage().deleteAllCookies(); refreshBrowser();}
    public void getAllCookies(){LOG.info("Running : getAllCookies");webDriver.manage().getCookies();}
    /**
     * Switch to another tab within window. tabNum is 0 to 1++ from left to right.
     */
    public void switchBetweenWindowTabs(int tabNum) {
        LOG.info("Running : switchBetweenWindowTabs");
        ArrayList<String> windowTabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(windowTabs.get(tabNum));
    }

    public void switchBetweenFrameTabsWithID(String tabID) {
        LOG.info("Running : switchBetweenWindowTabs");
        ArrayList<String> windowTabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().frame(tabID);
    }

    public void basicAuthentation(By byUserName, By byPassword, By submitButton, String userName, String passWord){
        waitClearAndEnterText(byUserName,userName);
        waitClearAndEnterText(byPassword,passWord);
        clickByElement(submitButton);
    }

    // ******************************** // API CMD // ************************************ //
    public String restAssuredReturnAllInfo(String webAddress) {
        LOG.info("Running : restAssuredReturnAllInfo");
        Response response = get(webAddress).andReturn();
        String jsonResponce = response.getBody().asString();
        LOG.info("JSON RESPONCE : " + jsonResponce);
        return jsonResponce;
    }

    public Integer restAssuredReturnStatusCode() {
        LOG.info("Running : restAssuredReturnAllInfo");
        Response response = get(webDriver.getCurrentUrl()).andReturn();
        Integer jsonResponce = response.getStatusCode();

        return jsonResponce;
    }

    public void restAssuredAssertStatusCode(Integer expectedStatusCode){
        RestAssured.given().get(webDriver.getCurrentUrl()).then().statusCode(expectedStatusCode);
    }

    public void restAssuredBasicAuthentication(String webAddress, String un, String pw, boolean success) {
        LOG.info("Running : restAssuredBasicAuthentication");
        String authAppApi = webAddress;
        String username = un;
        String incorrectUserName = "InC0rr3ctPa55W0rd";
        String password = pw;
        if (success == true){
            given().auth().preemptive().basic(username, password).when().get(authAppApi).then().statusCode(200).log().all();
        }
        if (success == false){
            given().auth().preemptive().basic(incorrectUserName, password).when().get(authAppApi).then().statusCode(401).log().all();
        }
    }

    public void restAssuredCookieExtraction(String webAddress) {
        LOG.info("Running : restAssuredCookieExtraction");
        Response response = get(webAddress);
        Map<String,String> cookies = response.getCookies();
        for (Map.Entry<String,String> cookie : cookies.entrySet()){
            LOG.info("\nkey : " + cookie.getKey()+ " : value : " + cookie.getValue());
        }
    }

    public void restAssuredCookieExtractionSingle(String webAddress, String cookieName) {
        LOG.info("Running : restAssuredCookieExtractionSingle");
        Response response = get(webAddress);
        Cookie cookies = response.getDetailedCookie(cookieName);
        LOG.info("\nCookie has expiry date: "+cookies.hasExpiryDate());
        LOG.info("\nCookie expiry date: "+cookies.getExpiryDate());
        LOG.info("\nCookie value: "+cookies.getValue());
        LOG.info("\nCookie value: "+cookies.getComment());
    }

    // ******************************** // PATTEN MATCHING // **************************** //

    private String extractTokenViaPattenMatch(String startmatchText, String endMatchText, String totaltext) {
        LOG.info("Running : extractTokenViaPattenMatch");
        String parsedString = "";
        Pattern p = Pattern.compile(startmatchText+"(.*?)"+endMatchText);
        Matcher m = p.matcher(totaltext);
        if(m.find()){
            parsedString = m.group(1);
        }
        return parsedString;
    }

    // ******************************** // WAITERS // ************************************ //
    public void waitForItemToBeClickableAndClick(WebDriver driver, Integer waitTime, By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        waitForExpectedElement(by).click();
    }

    public void waitForItemToBeClickableAndClick(By by,Integer waitTime){
        WebDriverWait wait = new WebDriverWait(getWebDriver(),waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        waitForExpectedElement(by).click();
    }

    public void waitForItemToBeClickableAndClick(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        waitForExpectedElement(by).click();
    }

    public void waitForPageTitleToContain(WebDriver driver, Integer waitTime, String expectedPageTitle){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.titleContains(expectedPageTitle));
    }

    public void waitForAjaxElementNotToBePresent(WebDriver driver, Integer waitTime){
            try {
                if (waitForExpectedElement(By.cssSelector(".loading-mask"),waitTime).isDisplayed()) {
                    LOG.info("Waiting for ajax LOADER to not be present");
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));
            } }catch (Exception e) {
                LOG.info("Ajax loading class no longer present");
            }
        }

    public void waitForPageTitleToEqual(WebDriver driver, Integer waitTime, String expectedPageTitle){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.titleIs(expectedPageTitle));
    }

    public void waitForTextToBePresentInELement(WebDriver driver, Integer waitTime, By by, String expectedText){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.textToBePresentInElementValue(by, expectedText));
    }

    public void waitForTextToBe(WebDriver driver, Integer waitTime, By by, String expectedText){
        WebDriverWait wait = new WebDriverWait(driver,waitTime);
        wait.until(ExpectedConditions.textToBe(by, expectedText));
    }

    public Boolean waitForURLToContain(String expectedURLContains){
        return wait.until(ExpectedConditions.urlContains(expectedURLContains));
    }

    public Boolean waitForURLToContain(String expectedURLContains, int timeOutInSeconds) {
        return new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.urlContains(expectedURLContains));
    }

    public Boolean doesURLContain(String urlContains){
        return getWebDriver().getCurrentUrl().contains(urlContains);
    }

    public void waitForElementToBeSelected(WebDriver driver, Integer waitTime, By by){
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }
    public void waitForPage() {
        new WebDriverWait(webDriver, 20).until(webDriver ->
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForPageSourceTextToBeTrue(WebDriver driver, Integer waitTime, String expectedText){
        LOG.info("Custom Wait for text to be present");
        ExpectedCondition<Boolean> elementTextEqualsString = arg0 -> webDriver.getPageSource().toLowerCase().contains(expectedText.toLowerCase());  //textPresent;
        wait.until(elementTextEqualsString);
    }

    public void waitForPage(Integer waitTime) {
        new WebDriverWait(webDriver, waitTime).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * An expectation for checking the title of a page.
     *
     * @param title the expected title, which must be an exact match
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitle(String title) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.titleIs(title));
    }

    /**
     * An expectation for checking that the title contains a case-sensitive
     * substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitleContains(String title) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.titleContains(title));
    }

    /**
     * An expectation for the URL of the current page to be a specific url.
     *
     * @param url the url that the page should be on
     * @return <code>true</code> when the URL is what it should be
     */
    public boolean checkPageUrlToBe(String url) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.urlToBe(url));
    }

    /**
     * An expectation for the URL of the current page to contain specific text.
     *
     * @param fraction the fraction of the url that the page should be on
     * @return <code>true</code> when the URL contains the text
     */
    public boolean checkPageUrlContains(String fraction) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.urlContains(fraction));
    }

    /**
     * Expectation for the URL to match a specific regular expression
     *
     * @param regex the regular expression that the URL should match
     * @return <code>true</code> if the URL matches the specified regular expression
     */
    public boolean checkPageUrlMatches(String regex) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.urlMatches(regex));
    }
    /**
     * Clear the text in the element
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected WebElement waitForExpectedElementToClear(final By by) {
        waitForExpectedElement(by).clear();
        return waitForExpectedElement(by);
    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public WebElement waitForExpectedElement(final By by) {
        try {
            return wait.until(visibilityOfElementLocated(by));
        } catch (StaleElementReferenceException e) {
            return wait.until(visibilityOfElementLocated(by));
        }
    }

    /**
     * Find the dynamic element wait until its visible for a specified time
     *
     * @param by                Element location found by css, xpath, id etc...
     * @param waitTimeInSeconds max time to wait until element is visible
     **/
    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), waitTimeInSeconds);
        try {
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        } catch (StaleElementReferenceException e) {
            LOG.info(e.getMessage());
            return wait.until(visibilityOfElementLocated(by));
        }
    }

    public List<WebElement> waitForExpectedElements(final By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (StaleElementReferenceException e) {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        }
    }

    /**
     * Find the dynamic element wait until its presence in the DOM
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    public WebElement waitForPresenceOfElement(final By by) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (StaleElementReferenceException e) {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
    }

    public void clickByJavaScriptExecutor(final WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
    }

    public boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                WebElement element = webDriver.findElement(by);
                element= wait.until(ExpectedConditions.elementToBeClickable(by));
                clickUsingJS(by);
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
                LOG.info("Try to click again.");
            }
            attempts++;
        }
        return result;
    }

    public void waitForElementToAppearAndDisappear(By by,int waitToAppear,int waitToDisappear){
        try{
            waitForExpectedElement(by,waitToAppear);
            waitForElementToDisappear(by,waitToDisappear);
        }catch(Exception e){
            LOG.warn("LOADER does not disappear in "+waitToDisappear+" seconds");
        }
    }

    public void waitForElementToDisappear(By by,int seconds) {
        (new WebDriverWait(getWebDriver(), seconds))
            .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public boolean urlToContainInSeconds(String urlFragment,int seconds) {
        try{
            (new WebDriverWait(getWebDriver(), seconds))
                .until(ExpectedConditions.urlContains(urlFragment));
            return true;
        }catch(Exception e){
            return false;
        }
    }

/*    public void waitForExpectedElementIgnoreStale(final By by) {
        LOG.info("Running : waitForExpectedElement with custom timeout");
        try {
            Wait wait = new FluentWait(webDriver).ignoring(StaleElementReferenceException.class);
           } catch (NoSuchElementException e) {
            LOG.info(e.getMessage());
        } catch (TimeoutException e) {
            LOG.info(e.getMessage());
        }
    }*/

    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOG.error("Error Message : " + e.getMessage());
            }
            WebElement element = getWebDriver().findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }

    /**
     * An expectation for checking if the given text is present in the specified element.
     *
     * @param element the WebElement
     * @param text    to be present in the element
     * @return true once the element contains the given text
     */
    public boolean textToBePresentInElement(WebElement element, String text) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * An expectation for checking if the given text is present in the element that matches
     * the given locator.
     *
     * @param by   used to find the element
     * @param text to be present in the element found by the locator
     * @return true once the first element located by locator contains the given text
     */
    public boolean textToBePresentInElementLocated(final By by, final String text) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     * @param element the WebElement
     * @param text    to be present in the element's value attribute
     * @return true once the element's value attribute contains the given text
     */
    public boolean textToBePresentInElementValue(final WebElement element, final String text) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     * @param by   used to find the element
     * @param text to be present in the value attribute of the element found by the locator
     * @return true once the value attribute of the first element located by locator contains
     * the given text
     */
    public boolean textToBePresentInElementValue(final By by, final String text) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.textToBePresentInElementValue(by, text));
    }

    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     *
     * @param frameLocator used to find the frame (id or name)
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final String frameLocator) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /**
     * An expectation for checking whether the given frame is available to switch
     * to. <p> If the frame is available it switches the given driver to the
     * specified frame.
     *
     * @param by used to find the frame
     */
    public WebDriver frameToBeAvailableAndSwitchToIt(final By by) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    /**
     * An expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param by used to find the element
     */
    public boolean invisibilityOfElementLocated(By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * An expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param by used to find the element
     */
    public boolean invisibilityOfElementLocated(By by, Integer timeout) {
        return (new WebDriverWait(getWebDriver(), timeout)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * An expectation for checking that an element with text is either invisible
     * or not present on the DOM.
     *
     * @param by   used to find the element
     * @param text of the element
     */
    public boolean invisibilityOfElementWithText(final By by, final String text) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param by used to find the element
     * @return the WebElement once it is located and clickable (visible and enabled)
     */
    public WebElement elementToBeClickable(By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is clickable (visible and enabled)
     */
    public WebElement elementToBeClickable(final WebElement element) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait until an element is no longer attached to the DOM.
     *
     * @param element The element to wait for.
     * @return false is the element is still attached to the DOM, true
     * otherwise.
     */
    public boolean stalenessOf(final WebElement element) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeSelected(by));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementToBeSelected(final WebElement element) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeSelected(element));
    }

    /**
     * An expectation for checking if the given runwelement is selected.
     */
    public boolean elementSelectionStateToBe(final WebElement element, final boolean selected) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    /**
     * An expectation for checking if the given element is selected.
     */
    public boolean elementSelectionStateToBe(final By by, final boolean selected) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementSelectionStateToBe(by, selected));
    }

    public void waitForAlert() {
        (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.alertIsPresent());
    }

    public boolean isAlertPresent() {
    WebDriverWait wait = new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME);
        try
        { getWebDriver().switchTo().alert();
            return true; }
        catch (NoAlertPresentException Ex)
        {
            return false; }
    }

    /**
     * An expectation for checking that all elements present on the web page that
     * match the locator are visible. Visibility means that the elements are not
     * only displayed but also have a height and width that is greater than 0.
     *
     * @param by used to find the element
     * @return the list of WebElements once they are located
     */
    public List<WebElement> visibilityOfAllElementsLocatedBy(final By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    /**
     * An expectation for checking that all elements present on the web page that
     * match the locator are visible. Visibility means that the elements are not
     * only displayed but also have a height and width that is greater than 0.
     *
     * @param elements list of WebElements
     * @return the list of WebElements once they are located
     */
    public List<WebElement> visibilityOfAllElements(final List<WebElement> elements) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * An expectation for checking that there is at least one element present on a
     * web page.
     *
     * @param by used to find the element
     * @return the list of WebElements once they are located
     */
    public List<WebElement> presenceOfAllElementsLocatedBy(final By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM
     * of a page, is visible. Visibility means that the element is not only
     * displayed but also has a height and width that is greater than 0.
     *
     * @param element the WebElement
     * @return the (same) WebElement once it is visible
     */
    public WebElement visibilityOf(final WebElement element) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param by used to find the element
     * @return the WebElement once it is located
     */
    public boolean isElementPresent(final By by) {
        try {
            new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
            return false;
        }
        return true;
    }

    public boolean isElementPresent(final By by,Integer waitTime) {
        try {
            new WebDriverWait(getWebDriver(), waitTime).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
            return false;
        }
        return true;
    }

    /**
     * An expectation for checking that an element is visibility on the DOM of a
     * page. This does not necessarily mean that the element is visible.
     *
     * @param by used to find the element
     * @return the WebElement once it is located
     */
    public boolean isElementDisplayedBySeconds(final By by,int seconds) {
        try {
            new WebDriverWait(getWebDriver(), seconds).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
            return false;
        }
        return true;
    }

    public WebDriver getBrowserByPageTitle(String pageTitle) {
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver = webDriver.switchTo().window(windowHandle);
            if (pageTitle.equalsIgnoreCase(webDriver.getTitle())) {
                return webDriver;
            }
        }
        return null;
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }

    public String getElementByTagNameWithJSExecutor(String tagName) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')").toString();
    }

    public String getElementByQueryJSExecutor(String cssSelector) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.querySelector('" + cssSelector + "')").toString();
    }

    public String getElementValueJSExecutor(String cssSelector) {
        return ((JavascriptExecutor) webDriver).executeScript("return document.querySelector('" + cssSelector + "').value").toString();
    }

    protected boolean isElementClickable(By by) {
        try {
            new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    public void hoverOnElement(WebElement element) {
        Actions action = new Actions(webDriver);
        action.moveToElement(element).build().perform();
    }

    public void hoverOnElement(By by) {
        Actions action = new Actions(webDriver);
        action.moveToElement(webDriver.findElement(by)).build().perform();
    }

    public void clickUsingJS(By by){
        WebElement element;
        try {
            element = getWebDriver().findElement(by);
        } catch (StaleElementReferenceException e) {
            element = getWebDriver().findElement(by);
        }
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", element);
    }

    public void enterDataAndWait(By by,String strEnterData){
        try {
            waitForExpectedElement(by,20).clear();
            waitForExpectedElement(by,10).sendKeys(strEnterData);
            Thread.sleep(2000);
        } catch(Exception ex) {
            LOG.info("Failed to enter data in text-field due to error: "+ex.getMessage());
        }
    }

    public void clickUsingJS(WebElement ele){
        try{((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", ele);
        }catch(Exception ex){
            LOG.info("Failed to click on the web-element due to error: "+ex.getMessage());
        }
    }

    public void waitAfterDropdownSelection(By by){
        try {new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.presenceOfNestedElementLocatedBy(by,By.tagName("option")));
        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
        }
    }
    
    public void scrollElementIntoView(final By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("arguments[0].scrollIntoView()", waitForExpectedElement(by),10);
    }

    public void verifyDropDownOptionsMatchesInputValues(By by,String strOptions) {
        Select ProductDropDown = new Select(waitForExpectedElement(by,10));
        clickByElementByQueryJSExecutor(by);
        String[] lstOptions = UrlBuilder.getMessage(strOptions).split(",");
        int intCounter=0;
        List<WebElement> options = ProductDropDown.getOptions();
        for(WebElement we:options)
        { try{
            if (we.getText().equals(lstOptions[intCounter]))
                intCounter++;}
            catch(Exception ex){
               LOG.info("Failed to verify drop-down options");
            }
        }
        assertEquals(lstOptions.length,intCounter);
    }

    public void verifyCountOfDropDownOptions(By by,String strCount) {
        Select ProductDropDown = new Select(waitForExpectedElement(by,10));
        clickByElementByQueryJSExecutor(by);
        List<WebElement> options = ProductDropDown.getOptions();
        assertEquals(options.size(), Integer.parseInt(strCount));
    }

    public void jsScrollElementInCenter(WebElement element){
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) getWebDriver()).executeScript(scrollElementIntoMiddle, element);
    }

    public String getPropertyValueFromCssPseudoByJsExecutor(String cssSelector, String pseudo, String property) {
        String script = "return window.getComputedStyle(document.querySelector('" + cssSelector + "'),'" + pseudo + "').getPropertyValue('" + property + "')";
        return ((JavascriptExecutor) webDriver).executeScript(script).toString();
    }

    public void scrollToElement(By byCss) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement Element = webDriver.findElement(byCss);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public void scrollToPageTop() {
        ((JavascriptExecutor) webDriver)
            .executeScript("window.scrollTo(0, 0)");
    }

    public void scrollToPageBottom() {
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToShowEntirePage() {
        scrollToPageTop();
        String heightScript = "return Math.max(" +
                "document.body.scrollHeight, document.documentElement.scrollHeight," +
                "document.body.offsetHeight, document.documentElement.offsetHeight," +
                "document.body.clientHeight, document.documentElement.clientHeight" +
                ");";
        int heightSize = Integer.parseInt(execJsScript(heightScript).toString());
        RectangleSize viewport = Eyes.getViewportSize(webDriver);
        for (int j = 0; j < heightSize; j += viewport.getHeight() - 20) {
            execJsScript("window.scrollTo(0," + j + ")");
            sleepFor(1000);
        }
        execJsScript("window.scrollTo(0," + heightSize + ")");
        sleepFor(3000);
        scrollToPageTop();
    }

    public static void sleepFor(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollDownByCoordinator(int  y) {
        ((JavascriptExecutor) webDriver)
            .executeScript("window.scrollTo(0, " + y + ")");
    }

    public void scrollUpByCoordinator(int  y) {
        ((JavascriptExecutor) webDriver)
            .executeScript("window.scrollBy(0, -" + y + ")");
    }

    public void clickIndexElementByQueryJSExecutor(final By by, int index) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        if(getWebDriver().findElements(by).size()>1) {
            jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElements(by).get(index));
            jse.executeScript("arguments[0].click()", webDriver.findElements(by).get(index));
        }else{
            jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(by));
            jse.executeScript("arguments[0].click()", webDriver.findElement(by));
        }
    }

    public Object execJsScript(String jsScript) {
        return ((JavascriptExecutor) webDriver).executeScript(jsScript);
    }

    public Region eyesRegion(WebElement element, int leftOffset, int topOffset, int widthOffset, int heightOffset) {
        return new Region(element.getLocation().x + leftOffset,
                element.getLocation().y + topOffset,
                element.getSize().width + widthOffset,
                element.getSize().height + heightOffset);
    }

    public Region eyesRegion(By by, int leftOffset, int topOffset, int widthOffset, int heightOffset) {
        return eyesRegion(waitForExpectedElement(by), leftOffset, topOffset, widthOffset, heightOffset);
    }

    public Region eyesRegion(WebElement element) {
        return eyesRegion(element, 0, 0, 0, 0);
    }

    public Region eyesRegion(By by) {
        return eyesRegion(by, 0, 0, 0, 0);
    }

    public Region[] eyesRegions(List<WebElement> elements, int leftOffset, int topOffset, int widthOffset, int heightOffset) {
        List<Region> regions = new ArrayList<>();
        elements.forEach(element -> regions.add(eyesRegion(element, leftOffset, topOffset, widthOffset, heightOffset)));
        return regions.toArray(new Region[0]);
    }

    public Region[] eyesRegions(By by, int leftOffset, int topOffset, int widthOffset, int heightOffset) {
        return eyesRegions(visibilityOfAllElementsLocatedBy(by), leftOffset, topOffset, widthOffset, heightOffset);
    }

    public void enterDataUsingJS(WebElement wb,String strValue) {
        JavascriptExecutor jse = (JavascriptExecutor)getWebDriver();
        jse.executeScript("arguments[0].value='"+strValue+"';",wb); }

    public void clearFieldUsingControlKeys(By by) {
        waitForItemToBeClickableAndClick(by,10);
        waitForExpectedElement(by).sendKeys(Keys.CONTROL + "a");
        waitForExpectedElement(by).sendKeys(Keys.CONTROL + "x"); }

    public boolean verifyURLStatus(String strURL) throws Throwable {
        URL obj = new URL(strURL);
        //Opening a connection
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        //Sending the request
        conn.setRequestMethod("GET");

        int responsePage = conn.getResponseCode();
        return responsePage == 200;
    }

    public int getURLResponseCode(String strURL) throws Throwable {
        URL obj = new URL(strURL);
        //Opening a connection
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        //Sending the request
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        return responseCode;
    }

    public void persistentClear(By by, WebDriver driver) { driver.findElement(by).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); }

    /**
     *
     * @param s the string witch contain the digital
     * @return string of digital
     */
    public String getNumberFromString(String s){
        char[] chars=s.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char c:chars){
            if(Character.isDigit(c)){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public void waitAndClickByElementByJSExecutor(final By by,long waitTimeInSeconds) {
        waitForExpectedElement(by,waitTimeInSeconds);
        clickByElementByQueryJSExecutor(by);
    }

    public void navigateToGivenPage(String page){
        getWebDriver().navigate().to( UrlBuilder.getUrl() + UrlBuilder.getMessage(page));
    }

    protected LocalDate getDateFromString(String date) throws ParseException {
        String monthStr;
        String dayStr;
        String yearStr;
        switch(UrlBuilder.getLocale()){
            default:
                monthStr=convertMonthString(date.split(" ")[1]);
                dayStr=date.split(" ")[0];
                yearStr=date.split(" ")[2];
                break;
        }
        return LocalDate.parse(dayStr+"/"+monthStr+"/"+yearStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    protected String convertMonthString(String monthStr){
        switch(monthStr.toUpperCase()) {
            case "JANUARY":
            case "JANUAR":
            case "STYCZNIA":
            case "GENNAIO":
            case "JANUARI":
            case "GEN":
            case "JANVIER":
                return "01";
            case "FEBRUARY":
            case "FEBRUAR":
            case "LUTEGO":
            case "FEBBRAIO":
            case "FEBRUARI":
            case "FEB":
            case "FÉVRIER":
                return "02";
            case "MARCH":
            case "MARSCH":
            case "MARCA":
            case "MARZO":
            case "MARS":
            case "MAR":
                return "03";
            case "APRIL":
            case "KWIETNIA":
            case "APRILE":
            case "APR":
            case "AVRIL":
                return "04";
            case "MAY":
            case "MAI":
            case "MAJA":
            case "MAGGIO":
            case "MAJ":
            case "MAG":
                return "05";
            case "JUNE":
            case "JUNI":
            case "CZERWCA":
            case "GIUGNO":
            case "GIU":
            case "JUIN":
                return "06";
            case "JULY":
            case "JULI":
            case "LIPCA":
            case "LUGLIO":
            case "LUG":
            case "JUILLET":
                return "07";
            case "AUGUST":
            case "SIERPNIA":
            case "AGOSTO":
            case "AUGUSTI":
            case "AGO":
            case "AOÛT":
                return "08";
            case "SEPTEMBER":
            case "WRZEŚNIA":
            case "SETTEMBRE":
            case "SETTEBRE":
            case "SET":
            case "SEPTEMBRE":
                return "09";
            case "OCTOBER":
            case "OKTOBER":
            case "PAŹDZIERNIKA":
            case "OTTOBRE":
            case "OTT":
            case "OCTOBRE":
                return "10";
            case "NOVEMBER":
            case "LISTOPADA":
            case "NOVEMBRE":
            case "NOV":
                return "11";
            case "DECEMBER":
            case "DEZEMBER":
            case "GRUDNIA":
            case "DICEMBRE":
            case "DIC":
            case "DÉCEMBRE":
                return "12";
        }
        return null;
    }

    public void switchToWindow(){
        String mainHandle =webDriver.getWindowHandle();
        Set<String> Handles = webDriver.getWindowHandles();
        for(String hand:Handles){
            if(!hand.equals((mainHandle))){
                webDriver.switchTo().window((hand));
                LOG.info("Current title: "+webDriver.getTitle());
                break;
            }
        }
    }

    public String getPseudoElementContent(String elementSelector){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return js.executeScript("return window.getComputedStyle(document.querySelector('" + elementSelector + "'),'::after').getPropertyValue('content')")
                .toString();
    }

    public boolean checkDropDownElementsAlphabeticalOrder(By by){
        Select dropDown=new Select(waitForExpectedElement(by, 10));
        List<WebElement> allOptionsElement=dropDown.getOptions();
        List options=new ArrayList();
        for(WebElement optionElement:allOptionsElement){
            String option=optionElement.getText();
            options.add(option);
        }
        options.remove("Please select country");
        System.out.println("Dropdown List"+options);
        List tempList=new ArrayList(options);
        Collections.sort(tempList);
        System.out.println("Sorted List"+tempList);
        boolean ifSortedAscending=options.equals(tempList);
        if(ifSortedAscending){
            LOG.info("\n ***** List is sorted in ascending order ******");
        }else{
            LOG.info("\n ***** List is not sorted in ascending order ******");
        }
        return ifSortedAscending;
    }

    public boolean waitForExpectedUrl(final String text) {
        try {
            return wait.until(ExpectedConditions.urlContains(text));
        } catch (StaleElementReferenceException e) {
            return wait.until(ExpectedConditions.urlContains(text));
        }
    }

    public void clearFieldUsingControlKeysSpecificElement(By by,int elementNo) {
        webDriver.findElements(by).get(elementNo).sendKeys(Keys.CONTROL + "a");
        webDriver.findElements(by).get(elementNo).sendKeys(Keys.CONTROL + "x");
    }

    public void waitForExpectedSpecificElementAndClick(final By by,int elementNo) {
        webDriver.findElements(by).get(elementNo).click();
    }

    public void enterText(final By by, String textToEnter, int elementNo) {
        waitForExpectedElement(by,20);
        webDriver.findElements(by).get(elementNo).sendKeys(textToEnter);
    }
    public void pageclose()
    {
        webDriver.close();
    }

    public String gettingEnglishNameForMonth(String month) {
        Map<String, String> myDate = new HashMap<>();
        myDate.put("janvier","January");
        myDate.put("février","Febraury");
        myDate.put("mars","March");
        myDate.put("avril","April");
        myDate.put("mai","May");
        myDate.put("juin","June");
        myDate.put("juillet","July");
        myDate.put("août", "August");
        myDate.put("septembre", "September");
        myDate.put("octobre","October");
        myDate.put("novembre","November");
        myDate.put("décembre","December");

        String s = myDate.get(month).toString();
        return s;
    }

    public String gettingOtherLanguageNameForMonth(String month) {
        Map<String, String> myDate = new HashMap<>();
        myDate.put("January", "janvier");
        myDate.put("Febraury", "février");
        myDate.put("March", "mars");
        myDate.put("April", "avril");
        myDate.put("May", "mai");
        myDate.put("June", "juin");
        myDate.put("July", "juillet");
        myDate.put("August", "août");
        myDate.put("September", "septembre");
        myDate.put("October", "octobre");
        myDate.put("November", "novembre");
        myDate.put("December", "décembre");

        String s = myDate.get(month).toString();
        return s;
    }

    public String splittingMonth(String month) {
        switch (month) {
            case "janvier":
                month="janv";
                break;
            case "février":
                month="févr";
                break;
            case "mars":
                month="mars";
                break;
            case "mai":
                month="mai";
                break;
            case "juin":
                month="juin";
                break;
            case "juillet":
                month="juil";
                break;
            case "août":
                month="août";
                break;
            case "septembre":
                month="sept";
                break;
            case "octobre":
                month="oct";
                break;
            case "novembre":
                month="nov";
                break;
            case "décembre":
                month="déc";
                break;
        }
        return month;
    }

    public void clickCssPseudoByJsExecutor(String cssSelector, String pseudo) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("document.querySelector(' "+ cssSelector + "','"+ pseudo + "').click();");
    }

    public void clickEnterUsingKeyboard(){
        Actions actions=new Actions(webDriver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void clickTabUsingKeyboard(){
        Actions actions=new Actions(webDriver);
        actions.sendKeys(Keys.TAB).build().perform();
    }

    public void threadSleep(int time){
        try{
            Thread.sleep(time);
        } catch (Exception e){

        }
    }

    public void clickBackSpaceKeyboard(By xpath){
        WebElement we=webDriver.findElement(xpath);
        we.sendKeys(Keys.BACK_SPACE);
    }

    public Double stringToDoubleConversion(String string) {
        return Double.parseDouble(string);
    }

    public String doubleToStringConversion(Double d) {
        return Double.toString(d);
    }

    public String getKeyForTextValidations(String key) {
        String key1 = key+"-"+UrlBuilder.LANGUAGE;
        String expectedTextFromKey = UrlBuilder.getMessage(key1);
        return expectedTextFromKey;
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("E, MMMM dd, yyyy");
        Date date = new Date();
        LOG.info("date " + date);
        return dateFormat.format(date);
    }

    public String getProvinceCode(String provinceFullForm) {
        String province = null;
        switch (provinceFullForm) {
            case "Alberta":
                province = "ab";
                break;
            case "British Columbia":
                province = "bc";
                break;
            case "Manitoba":
                province = "mb";
                break;
            case "New Brunswick":
                province = "nb";
                break;
            case "Newfoundland and Labrador":
                province = "nl";
                break;
            case "Nova Scotia":
                province = "ns";
                break;
            case "Ontario":
                province = "on";
                break;
            case "Prince Edward Island":
                province = "pe";
                break;
            case "Saskatchewan":
                province = "sk";
                break;
            case "Quebec":
                province = "qc";
                break;
        }
        return province;
    }


    public String getCurrentDateInFormat() {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date date = new Date();
        LOG.info("date " + date);
        return dateFormat.format(date);
    }

    public String getCurrentDateInFormatInFrench() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH);
        Date date = new Date();
        LOG.info("date " + date);
        return dateFormat.format(date);
    }

    public void clickWithinElementUsingActions(WebElement webElement) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement);
        builder.click();
        builder.perform();
    }

    public String getProvinceCode() {
        String province = null;
        String provinceFullForm = UrlBuilder.PROVINCE;
        switch (provinceFullForm) {
            case "Alberta":
                province = "ab";
                break;
            case "British Columbia":
                province = "bc";
                break;
            case "Manitoba":
                province = "mb";
                break;
            case "New Brunswick":
                province = "nb";
                break;
            case "Newfoundland and Labrador":
                province = "nl";
                break;
            case "Nova Scotia":
                province = "ns";
                break;
            case "Ontario":
                province = "on";
                break;
            case "Prince Edward Island":
                province = "pe";
                break;
            case "Saskatchewan":
                province = "sk";
                break;
            case "Quebec":
                province = "qc";
                break;
        }
        return province;
    }
}