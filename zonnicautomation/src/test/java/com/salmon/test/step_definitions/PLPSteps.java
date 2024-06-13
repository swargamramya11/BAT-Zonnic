package com.salmon.test.step_definitions;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.PDP;
import com.salmon.test.page_objects.PLP;
import com.salmon.test.page_objects.PaymentPage;
import com.salmon.test.page_objects.PercyPage;
import com.salmon.test.page_objects.cucumberContext.CommonMethods;
import com.salmon.test.page_objects.cucumberContext.ScenarioContext;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class PLPSteps extends PageObject {

  private PLP plp;
  private ScenarioContext scenarioContext;
  private PDP pdp;
  private PaymentPage paymentPage;
  private HomePageSteps homePageSteps;
  public CommonMethods commonMethods;
  public PercyPage percyPage;
  private static final Logger LOG = LoggerFactory.getLogger(PLPSteps.class);

  public PLPSteps(PercyPage percyPage, CommonMethods commonMethods, PLP plp,
                  ScenarioContext scenarioContext, PDP pdp, PaymentPage paymentPage,
                  HomePageSteps homePageSteps) {
    this.scenarioContext  =scenarioContext;
    this.plp = plp;
    this.pdp = pdp;
    this.paymentPage = paymentPage;
    this.homePageSteps = homePageSteps;
    this.commonMethods = commonMethods;
    this.percyPage = percyPage;
  }

  @And("^user clicks on '(.*)' and verify url and title contains '(.*)' '(.*)'$")
  public void userHoverOnMenuAndClickOnFlyoutAndURLContains1(String headermenu,String UrlToContain,String titleToContain,DataTable dtList) {
    List<List<String>> lstLinks = dtList.raw();
    for (int i = 1; i < lstLinks.size(); i++) {
      plp.headerPageNavigation(lstLinks.get(i).get(0) +"-"+UrlBuilder.LANGUAGE, lstLinks.get(i).get(0));
      homePageSteps.urlContainsSubscriptions(lstLinks.get(i).get(1));
      LOG.info(lstLinks.get(i).get(0));

      if(lstLinks.get(i).get(0).equals("nicotinePouches")) {
        commonMethods.assertTitleContains(lstLinks.get(i).get(2));
      } else if(lstLinks.get(i).get(0).equals("contactUs") && UrlBuilder.LANGUAGE.equalsIgnoreCase("fr")) {
        commonMethods.assertTitle("HomePageTitle");
      } else {
        commonMethods.assertTitle(lstLinks.get(i).get(2));
      }

      String pageName = UrlBuilder.getMessage(lstLinks.get(i).get(0) +"-"+UrlBuilder.LANGUAGE);
      percyPage.takePercyScreenshot(pageName);
    }
  }

  @And("^user goes to PLP page and validate elements$")
  public void PLPPageElementsValidation(){
    plp.PLPZonnic();
  }

    @And("^add product quantity of '(.*)' from '(.*)' of index '(.*)'$")
    public void addProductToCart(String qty, String page, String index) {
        plp.addProductToCart(qty, page, index);
    }

    @And("^navigate to PDP of product with index '(.*)'$")
    public void navigateToPdp(String index) {
        pdp.navigateToPdp(index);
    }

  @And("^validate is '(.*)' error message is displayed in '(.*)'$")
  public void validateError(String message, String page) {
    plp.validateQuantityErrorMessages(message, page);
  }
}