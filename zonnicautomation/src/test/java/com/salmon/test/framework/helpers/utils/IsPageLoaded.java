package com.salmon.test.framework.helpers.utils;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IsPageLoaded extends PageObject {
  private static WebDriver jsWaitDriver;
  private static WebDriverWait jsWait;
  private static JavascriptExecutor jsExec;

  //Get the driver
  private static void setDriver ()
  {
    jsWaitDriver = WebDriverHelper.getWebDriver();
    jsWait = new WebDriverWait(jsWaitDriver,50);
    jsExec = (JavascriptExecutor) jsWaitDriver;
  }

  private static void ajaxComplete()
  {
    jsExec.executeScript("var callback = arguments[arguments.length - 1];"
        + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
        + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
        + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
  }

  private static void waitForJQueryLoad()
  {
    try {
      ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor)driver)
          .executeScript("return jQuery.active") == 0);

      boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

      if (!jqueryReady) {
        jsWait.until(jQueryLoad);
      }
    } catch (WebDriverException ignored) {
    }
  }

  private static void waitForAngularLoad()
  {
    String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
    angularLoads(angularReadyScript);
  }

  private static void waitUntilJSReady()
  {
    try {
      ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

      boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

      if (!jsReady) {
        jsWait.until(jsLoad);
      }
    } catch (WebDriverException ignored) {
      System.out.println("Some error occured in JS Ready "+ignored.getMessage());
    }
  }

  private static void waitUntilJQueryReady()
  {
    Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
    if (jQueryDefined)
    {
      poll(500);

      waitForJQueryLoad();

      poll(500);
    }
  }

  public static void waitUntilAngularReady()
  {   setDriver();
    try {
      Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
      //System.out.println("Is Angular Present "+angularUnDefined);
      if (!angularUnDefined)
      {
        Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
        //System.out.println("Value of angular injector "+angularInjectorUnDefined);
        if (!angularInjectorUnDefined)
        {
          LOG.info("angular is Present");
          poll(500);

          waitForAngularLoad();

          poll(500);
        }
      }
    } catch (WebDriverException ignored)
    {
      System.out.println("Method wait untill Angular ready throwing error-->>"+ignored.getMessage());
    }
  }

  public void waitUntilAngular5Ready() {
    try {
      Object angular5Check = jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
      if (angular5Check != null) {
        Boolean angularPageLoaded = (Boolean) jsExec.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
        if (!angularPageLoaded) {
          poll(500);

          waitForAngular5Load();

          poll(500);
        }
      }
    } catch (WebDriverException ignored)
    { }
  }

  private void waitForAngular5Load()
  {
    String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
    angularLoads(angularReadyScript);
  }

  private static void angularLoads(String angularReadyScript)
  {
    try {
      ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());

      boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

      if (!angularReady) {
        jsWait.until(angularLoad);
      }
    } catch (WebDriverException ignored) {
    }
  }

  public static void waitAllRequest()
  {
    setDriver();
    waitUntilAngularReady();
    waitUntilJQueryReady();
    waitUntilJSReady();
    ajaxComplete();
  }

  /**
   * Method to make sure a specific element has loaded on the page
   *
   * @param by
   * @param expected
   */
  public static void waitForElementAreComplete(By by, int expected)
  {
    setDriver();
    ExpectedCondition<Boolean> angularLoad = driver ->
    {
      int loadingElements = driver.findElements(by).size();
      return loadingElements >= expected;
    };
    jsWait.until(angularLoad);
  }

  /**
   * Waits for the elements animation to be completed
   * @param css
   */
  public void waitForAnimationToComplete(String css) {
    ExpectedCondition<Boolean> angularLoad = driver -> {
      int loadingElements = driver.findElements(By.cssSelector(css)).size();
      return loadingElements == 0;
    };
    jsWait.until(angularLoad);
  }

  private static void poll(long milis) {
    try {
      Thread.sleep(milis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}