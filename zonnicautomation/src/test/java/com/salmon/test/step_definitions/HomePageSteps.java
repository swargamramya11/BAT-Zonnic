package com.salmon.test.step_definitions;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.HomePage;
import com.salmon.test.page_objects.PLP;
import com.salmon.test.page_objects.PaymentPage;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.*;
import static com.salmon.test.page_objects.constants.Context.URL;
import static org.testng.AssertJUnit.assertTrue;

public class HomePageSteps {
    PLP plp;
    private HomePage homePage;
    private PaymentPage paymentPage;
    private CommonMethods commonMethods;
    private ScenarioContext scenarioContext;
    public final static By HOME_PAGE_TO_LOAD = By.cssSelector("a.pagebuilder-button-primary");
    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    public HomePageSteps(PLP plp, ScenarioContext scenarioContext, HomePage homePage, PaymentPage paymentPage, CommonMethods commonMethods) {
        this.homePage=homePage;
        this.paymentPage=paymentPage;
        this.commonMethods = commonMethods;
        this.scenarioContext = scenarioContext;
        this.plp = plp;
    }

    @Then("^url contains '(.*)'$")
    public void urlContainsSubscriptions(String urlContains) {
        homePage.waitForPage();
        String url = UrlBuilder.getMessage(urlContains);

        if(UrlBuilder.LANGUAGE.equalsIgnoreCase("fr") && urlContains.equals("contactUsUrl.key")) {
            assertTrue("**** ERROR - following was expected in URL : " + UrlBuilder.getMessage("contactUseFRURL.key") + " but full URL was : \n" + homePage.getWebDriver().getCurrentUrl(), homePage.getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage("contactUseFRURL.key")));
        } else {
            assertTrue("**** ERROR - following was expected in URL : " + UrlBuilder.getMessage(urlContains) + " but full URL was : \n" + homePage.getWebDriver().getCurrentUrl(), homePage.getWebDriver().getCurrentUrl().contains(UrlBuilder.getMessage(urlContains)));
        }

        if (url.contains(UrlBuilder.getMessage("faqUrl.key")) || url.contains(UrlBuilder.getMessage("submitTicketUrl.key"))) {
            homePage.clickBrowserBackButton();
            homePage.cLickZonnicLogo();
        } else if (urlContains.equals("contactUsUrl.key") && UrlBuilder.LANGUAGE.equalsIgnoreCase("fr")) {
            homePage.clickBrowserBackButton();
            homePage.cLickZonnicLogo();
        }
    }

    @Given("^select over 18 age confirmation option with province '(.*)'$")
    public void selectsOver18AgeConfirmation2(String provinceFromFeature) {
        homePage.waitForPage();
        homePage.clickOver18(provinceFromFeature);
        scenarioContext.setContext(URL, homePage.getCurrentUrl());
        commonMethods.assertUrl("homePageLink.key");
    }

    public void userWaitHomePageToLoad() {
        homePage.waitForExpectedElement(HOME_PAGE_TO_LOAD, 20);
    }

    @And("^user navigates to PLP page and adds a product to basket$")
    public void userNavigatesToPLPPageAndAddsAProductToBasket() throws Throwable {
        userNavigatesToPLPPage();
        plp.addProductToBasket();
    }

    @And("^user navigates to PLP page$")
    public void userNavigatesToPLPPage() {
        switch (UrlBuilder.getSite()) {
            case "zonnic":
                paymentPage.navigateToPlp();
                commonMethods.assertUrl("plpLink.key");
                commonMethods.assertTitleContains("nicotinePouchesTitle");
                break;
        }
    }

    @Then("^click on language selector$")
    public void language_selector() {
        homePage.languageSelector();
    }

    @Then("^click on close in language selector$")
    public void language_selector_close() {
        homePage.languageSelectorClose();
    }

    @Then("^select other language$")
    public void select_OtherLanguage() {
        homePage.selectOtherLanguage();
    }

    @And("^user navigates to PLP page and adds a product to basket with quantity '(.*)'$")
    public void userNavigatesToPLPPageAndAddsAProductToBasketWithQuantity(String qty) throws InterruptedException {
        userNavigatesToPLPPage();
        plp.updateQuantityInPLP(qty);
        plp.addProductToBasket1();
    }

    @And("^user click on basket icon and open mini Basket$")
    public void clickOnBasketIcon() {
        homePage.clickOnBasketIcon();
    }

    @And("^user click on checkout button in mini basket$")
    public void clickOnProceedToCheckoutButtonInMini() {
        homePage.clickOnProceedToCheckoutButtonInMiniBasket();
    }

    @And("^user get subtotal in mini basket$")
    public void getSubTotalInMini() {
        homePage.getSubTotalInMini();
    }

    @And("^user accepts cookies and select over 18 age confirmation option with province '(.*)'$")
    public void acceptCookiesVuse(String provinceFromFeature) {
        homePage.selectCookiesAndAge(provinceFromFeature);
    }

    @And("^website should be redirected to appropriate province with '(.*)' province$")
    public void redirectedToAppropriateProvince(String provinceFromFeature) {
        homePage.redirectedToAppropriateProvince(provinceFromFeature);
    }

    @When("^accept terms and conditions in zonnic site and signin$")
    public void acceptTermsAndConditions() {
        homePage.acceptZonnicContent();
    }

    @When("^click on shopping cart link and navigate to Cart Page$")
    public void clickOnShoppingCartLink() {
        homePage.clickOnShoppingCartLink();
    }

    @Then("^assert URL contains text '(.*)'$")
    public void assertUrlText(String url){
        homePage.assertUrlText(url);
    }

    @And("^user validate all whyzonnic page$")
    public void validateAllWhyzonnicpage(){
        homePage.validateAllWhyzonnicpage();
    }

    @And("^assert faq text is displayed$")
    public void faqIsDisplayed(){
        homePage.faqIsDisplayed();
    }

    @And("^user clicks on the '(.*)' text and verify link '(.*)' with title '(.*)'$")
    public void navigateToRequiredHeaderLink(String linkName, String url, String title) {
        homePage.navigateToRequiredHeaderLink(linkName, url, title);
    }

    @Given("^refresh browser$")
    public void refreshBrowser() {
        homePage.refreshBrowser1();
    }

    @And("^confirm quantity '(.*)' added to minibasket$")
    public void confirmMiniBasketDisplayedAmountOf(String qty) {
        homePage.confirmMiniBasketDisplayedAmountOf(qty);
    }

    @And("^user clicks on zonnic logo$")
    public void clcikOnZonnicLogo() {
        homePage.cLickZonnicLogo();
        commonMethods.assertUrl("homePageLink.key");
    }

    @And("^user validate empty '(.*)' elements$")
    public void validateEmptyMiniBasket(String page) {
        homePage.validateEmptyMiniBasket(page);
    }

    @And("^click on start shopping button$")
    public void clickOnStartShoppingButton() {
        homePage.clickOnStartShoppingButton();
    }

    @Given("^browser back$")
    public void browserBack() {
        homePage.browserBack();
    }

    @And("^validate message of '(.*)'$")
    public void validateMssg(String text){
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            homePage.validateMssg(text);
        }
    }

    @And("^user click on start chat icon$")
    public void userclickOnChatIcon(){
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            homePage.userclickOnChatIcon();
        }
    }

    @And("^user click on call us button$")
    public void userclickOnCallusButton(){
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            homePage.userclickOnCallusButton();
        }
    }

    @And("^user click on log a ticket button$")
    public void logAticketButton(){
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            homePage.logAticketButton();
        }
    }

    @And("^user click on see all faq button$")
    public void seeAllFaqButton(){
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            homePage.seeAllFaqButton();
        }
    }

    @And("^assert url contain '(.*)'$")
    public void mssg(String url){
        if(!SessionInfo.scenarioName.equalsIgnoreCase("Footer - Contact Us") || !UrlBuilder.LANGUAGE.equalsIgnoreCase("FR")) {
            homePage.validateTheURL(url);
        }
    }

    @Given("^verify '(.*)' in age gate$")
    public void verifyElementsInAgeGatePopup(String element) {
        homePage.verifyElementsInAgeGatePopup(element);
    }

    @Given("^click on '(.*)' in age gate$")
    public void clickOnButtons(String button) {
        homePage.clickOnButton(button);
    }

    @Given("^select '(.*)'$")
    public void selectLanguageOrProvince(String languageOrProvince) {
        homePage.selectLanguageOrProvince(languageOrProvince);
    }

    @Given("^validate '(.*)' in Language Selector$")
    public void validateElementsInLanguageSelector(String element) {
        homePage.validateElementsInLanguageSelector(element);
    }

    @Given("^click on confirm button$")
    public void clickOnConfirmButton() {
        homePage.clickOnConfirmButton();
    }

    @And("^user click on the '(.*)' text$")
    public void userclicksOnTheExploreFlavours(String link){
        homePage.userclicksOnTheExploreFlavour(link);
    }

    @Then("^user naviagte back to what is zonnic page$")
    public void navigateBackTopage() {
        homePage.navigateBackTopage();
    }

    @And("^user click on second explore flavours link$")
    public void secondExploreFlavours(){
        homePage.secondExploreFlavours();
    }

    @And("^user click on learnmore button$")
    public void clickOnMoreLink(){
        homePage.clickOnMoreLink();
    }

    @And("^user clicks on learnmore button$")
    public void clickOnFirstLearnmoreButton(){
        homePage.clickOnFirstsLearnmoreButton();
    }

    @And("^verify url contains (.*)'$")
    public void verifyUrl(String url){
        homePage.verifyUrl(url);
    }

    @And("^assert faqs text is displayed$")
    public void assertFaqTextIsDisplayed(){
        homePage.assertFaqTextIsDisplayed();
    }
}