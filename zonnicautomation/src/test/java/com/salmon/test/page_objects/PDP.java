
package com.salmon.test.page_objects;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import java.util.List;

import static com.salmon.test.page_objects.constants.Context.SKU;
import static org.testng.Assert.*;

@Getter
@Setter
public class PDP extends PageObject {
	private ScenarioContext scenarioContext;
	private CommonMethods commonMethods;
	private HomePage homePage;
	private PercyPage percyPage;

	public PDP(PercyPage percyPage, HomePage homePage, ScenarioContext scenarioContext, CommonMethods commonMethods) {
		this.scenarioContext = scenarioContext;
		this.commonMethods = commonMethods;
		this.homePage = homePage;
		this.percyPage = percyPage;
	}

	public static final By PRODUCT_HEADING=By.cssSelector(".producthero-content .producthero-content-name h1");
	public static final By PRODUCT_DESCRIPTION_CA=By.cssSelector(".producthero-content .producthero-description");
	public static final By ADDTOBASKET=By.cssSelector(".producthero-content .bat-cta-style.button-dark.center");
	public static final By PRODUCT_PRICE=By.cssSelector(".producthero-content .productcard-text-price.producthero-price span");
	private static final By REMOVE_CART = By.cssSelector("button.basket__item__button");
	private static final By BACK_TO_HOMEPAGE_FROM_CART = By.cssSelector(".bat-header-back button");
	private static final By READ_MORE = By.cssSelector(".more-link");
	private static final By READ_LESS = By.cssSelector(".less-link");
	private static final By PLUS_BUTTON = By.xpath("//div[@class='producthero-content']//div[@class='bat-quantity']//i[@class='icon icon-plus']//parent::button");
	private static final By MINUS_BUTTON = By.xpath("//div[@class='producthero-content']//div[@class='bat-quantity']//i[@class='icon icon-minus']//parent::button");
	private static final By PLUS_BUTTON_CHANGED = By.xpath("//div[@class='producthero-content']//div[@class='bat-quantity changed']//i[@class='icon icon-plus']//parent::button");
	private static final By MINUS_BUTTON_CHANGED = By.xpath("//div[@class='producthero-content']//div[@class='bat-quantity changed']//i[@class='icon icon-minus']//parent::button");
	private static final By BANNER_IN_PDP = By.cssSelector(".responsivegrid.rootTemplateGrid.aem-GridColumn.aem-GridColumn--default--12 .aem-Grid.aem-Grid--12.aem-Grid--default--12 .aem-Grid.aem-Grid--12.aem-Grid--tablet--12.aem-Grid--default--12.aem-Grid--phone--12");
	private static final By EXPLORE_FLAVOURS_LINK = By.cssSelector(".bat-cta-style.arrow-link-dark.left");
	private static final By EXPLORE_FLAVOURS_LINK_TEXT = By.cssSelector(".bat-cta-style.arrow-link-dark.left span");
	private static final By RIGHT_ARROW = By.cssSelector(".slick-next.slick-arrow");
	private static final By LEFT_ARROW = By.cssSelector(".slick-prev.slick-arrow");
	private static final By NO_OF_IMAGES2 = By.cssSelector(".thumb");
	static final By QTY_PDP = By.xpath("//div[@class='producthero-content']//div[@class='bat-quantity']//input");
	static final By QTY_PDP_CHANGED = By.xpath("//div[@class='producthero-content']//div[@class='bat-quantity changed']//input");

	public void addProductToBasketFromPDPZonnic() throws InterruptedException {
		waitForExpectedElement(By.linkText(UrlBuilder.getMessage("nicotinePouches-"+ UrlBuilder.LANGUAGE)));
		clickByElementByQueryJSExecutor(By.linkText(UrlBuilder.getMessage("nicotinePouches-"+ UrlBuilder.LANGUAGE)));
		String ADDTOCART=UrlBuilder.getMessage("addToCart-"+ UrlBuilder.LANGUAGE);
		if(UrlBuilder.LANGUAGE.contains("fr")){
			ADDTOCART=ADDTOCART+" ";
		}
		By PRODUCT=By.xpath("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']");
		LOG.info("//span[text()='"+ADDTOCART+"']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']");
		Thread.sleep(2000);
		try {
			waitForExpectedElement(PRODUCT).click();
		}catch (Exception e){
			webDriver.findElements(PRODUCT).get(0).click();
		}
		Thread.sleep(2000);
			try {
				clickByElementByQueryJSExecutor(PRODUCT);
				waitForExpectedElement(PRODUCT_HEADING).isDisplayed();
			}catch (Exception e){
				try {
					clickUsingJS(PRODUCT);
					waitForExpectedElement(PRODUCT_HEADING).isDisplayed();
				}catch (Exception e1){
					try {
						waitForExpectedElement(PRODUCT).click();
						waitForExpectedElement(PRODUCT_HEADING).isDisplayed();
					}catch (Exception e2){

					}
				}
			}
		assertTrue(waitForExpectedElement(PRODUCT_HEADING).isDisplayed());
		assertTrue(waitForExpectedElement(PRODUCT_DESCRIPTION_CA).isDisplayed());
		scrollToElement(ADDTOBASKET);
		assertTrue(webDriver.findElements(PRODUCT_PRICE).get(1).isDisplayed());
		String price=webDriver.findElements(PRODUCT_PRICE).get(1).getText();
		assertTrueExpectedTextContainsActualText(".", price);
		assertTrueExpectedTextContainsActualText("$", price);

		percyPage.takePercyScreenshot("PDP");

		scrollToElement(ADDTOBASKET);
		waitForExpectedElement(ADDTOBASKET).click();
		Thread.sleep(3000);
		waitForExpectedElement(homePage.CART_CA).click();
		waitForItemToBeClickableAndClick(homePage.clickToCheckOutButton_ca, 10);
		clickByElementByQueryJSExecutor(REMOVE_CART);

		percyPage.takePercyScreenshot("Empty Cart");

		clickByElementByQueryJSExecutor(BACK_TO_HOMEPAGE_FROM_CART);
	}


	public void validateAllPdp() {
		String ADDTOCART = UrlBuilder.getMessage("addToCart-" + UrlBuilder.LANGUAGE);
		if (UrlBuilder.LANGUAGE.contains("fr")) {
			ADDTOCART = ADDTOCART + " ";
		}
		List<WebElement> PRODUCT1 = webDriver.findElements(By.xpath("(//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='productcard-text']//div[@class='productcard-text-name']//a)"));
		for (int i = 1; i <= 2; i++) {
			By PRODUCT = By.xpath("(//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='productcard-text']//div[@class='productcard-text-name']//a)[" + i + "]");
			String PRODUCT_NAME_PLP;
			try {
				PRODUCT_NAME_PLP = getTrimUpperText(PRODUCT);
			}catch (Exception e) {
				homePage.refreshBrowser();
				PRODUCT_NAME_PLP = getTrimUpperText(PRODUCT);
			}
			waitForExpectedElement(PRODUCT).click();
			try {
				clickByElementByQueryJSExecutor(PRODUCT);
				waitForExpectedElement(PRODUCT_HEADING).isDisplayed();
			}catch (Exception e){
				try {
					clickUsingJS(PRODUCT);
					waitForExpectedElement(PRODUCT_HEADING).isDisplayed();
				}catch (Exception e1){
					try {
						waitForExpectedElement(PRODUCT).click();
						waitForExpectedElement(PRODUCT_HEADING).isDisplayed();
					}catch (Exception e2){

					}
				}
			}
			String PRODUCT_NAME_PDP= getTrimUpperText(PRODUCT_HEADING);
			assertTrueExpectedTextEqualsActualText(PRODUCT_NAME_PLP, PRODUCT_NAME_PDP);
			LOG.info("Heading : "+PRODUCT_NAME_PDP);
			assertTrueExpectedElementDisplayed(waitForExpectedElement(PRODUCT_HEADING).isDisplayed());
			assertTrueExpectedElementDisplayed(waitForExpectedElement(PRODUCT_DESCRIPTION_CA).isDisplayed());
			assertTrueExpectedElementDisplayed(webDriver.findElements(BANNER_IN_PDP).get(1).isDisplayed());
			assertTrueExpectedElementDisplayed(webDriver.findElements(BANNER_IN_PDP).get(0).isDisplayed());

			List<WebElement> PLUS_IN_FAQ_1 = webDriver.findElements(By.xpath("(//button[@class='bat-cta-style bat-faq__button-container center icon-center  '])"));
			for (int j = 1; j <= PLUS_IN_FAQ_1.size(); j++) {
				By PLUS_IN_FAQ = By.xpath("(//button[@class='bat-cta-style bat-faq__button-container center icon-center  '])[" + j + "]");
				scrollToElement(PLUS_IN_FAQ);
				waitForExpectedElement(PLUS_IN_FAQ).click();
			}

			readMoreOrLess("readMore");
			readMoreOrLess("readLess");
			increaseOrDecreaseQuantity("increase",3);
			increaseOrDecreaseQuantity("decrease",3);

			navigateBetweenPicturesInProductImages();

			scrollToElement(EXPLORE_FLAVOURS_LINK);
			commonMethods.assertButtonText(getTextFor(EXPLORE_FLAVOURS_LINK_TEXT), "exploreFlavoursPDP");
			waitForExpectedElement(EXPLORE_FLAVOURS_LINK).click();
			threadSleep(3000);
//			commonMethods.assertUrl("plpLink.key");
			clickBrowserBackButton();
			clickBrowserBackButton();
		}
	}

	public void readMoreOrLess(String readMoreOrLess) {
		switch (readMoreOrLess) {
			case "readMore":
				scrollToElement(READ_MORE);
				waitForExpectedElement(READ_MORE).click();
				break;
			case "readLess":
				scrollToElement(READ_LESS);
				waitForExpectedElement(READ_LESS).click();
				break;
		}
	}

	public void increaseOrDecreaseQuantity(String increaseOrDecreaseQuantity, int quantity) {
		switch (increaseOrDecreaseQuantity) {
			case "increase":
				while (quantity-1 != 0) {
					try {
						scrollToElement(PLUS_BUTTON);
						waitForExpectedElement(PLUS_BUTTON).click();
					} catch (Exception e) {
						waitForExpectedElement(PLUS_BUTTON_CHANGED).click();
					}
					quantity--;
				}
				break;
			case "decrease":
				while (quantity-1 != 0) {
					try {
						scrollToElement(MINUS_BUTTON);
						waitForExpectedElement(MINUS_BUTTON).click();
					} catch (Exception e) {
						waitForExpectedElement(MINUS_BUTTON_CHANGED).click();
					}
					quantity--;
				}
				break;
		}
	}

	public void navigateBetweenPicturesInProductImages() {
		boolean displayed = false;
		try {
			waitForExpectedElement(NO_OF_IMAGES2).isDisplayed();
			displayed = true;
		} catch (Exception e) {
			displayed = false;
		}

		if(displayed) {
			List<WebElement> NO_OF_IMAGES = webDriver.findElements(By.cssSelector(".thumb"));
			int size = NO_OF_IMAGES.size();
			for (int j = 1; j <= size; j++) {
				By NO_OF_IMAGES1 = By.xpath("(//button[contains(@class,'thumb')])[" + j + "]");
				while (size != 0) {
					waitForExpectedElement(LEFT_ARROW).click();
					size--;
				}
				while (size != 0) {
					waitForExpectedElement(RIGHT_ARROW).click();
					size--;
				}
			}
		}
	}


	public void navigateToPdp(String index) {
		String ADDTOCART = UrlBuilder.getMessage("addToCart-" + UrlBuilder.LANGUAGE);
		if (UrlBuilder.LANGUAGE.contains("fr")) {
			ADDTOCART = ADDTOCART + " ";
		}
		By PRODUCT = By.xpath("(//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard']//div[@class='productcard-text']//div[@class='productcard-text-name']//a)[" + index + "]");
		By SKU_Number = By.xpath("(//span[text()='" + ADDTOCART + "']/parent::button/parent::div[@class='productcard-ctas']/parent::div[@class='productcard-controls']/parent::div[@class='productcard'])[" + index + "]");
		String SKU1=getAttribute(SKU_Number, "data-sku");
		scenarioContext.setContext(SKU, SKU1);
		waitForExpectedElement(PRODUCT).click();
	}
}