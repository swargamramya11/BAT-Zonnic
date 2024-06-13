package com.salmon.test.framework.helpers.screenshot_helper;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.WebDriverHelper;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.constants.Locale;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;

public class ScreenshotHook {

    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotHook.class);

    @Before
    public void before(Scenario scenario) {
        SessionInfo.scenarioName = scenario.getName();
        SessionInfo.scenarioId = scenario.getId();
        if (!Props.EYES_ON) {
            return;
        }

        String envSiteLocale = "{env}_{site}_{locale}"
                .replace("{env}", UrlBuilder.getEnv())
                .replace("{site}", UrlBuilder.getSite())
                .replace("{locale}", UrlBuilder.getLocale());


        if (Props.USE_EYES_GRID) {
            SessionInfo.siteIdentifier = "[GRID][" + envSiteLocale.toUpperCase() + "]";
        } else {
            switch (WebDriverHelper.BROWSER.toLowerCase()) {
                case ("browserstackdesktop"):
                    SessionInfo.siteIdentifier = "[BS_Desktop][{envSiteLocale}][{os}{osVersion}][{browser}]"
                            .replace("{envSiteLocale}", envSiteLocale)
                            .replace("{os}", WebDriverHelper.BROWSERSTACK_BROWSER_OS)
                            .replace("{osVersion}", WebDriverHelper.BROWSERSTACK_BROWSER_OS_VERSION)
                            .replace("{browser}", WebDriverHelper.BROWSERSTACK_BROWSER)
                            .toUpperCase();
                    break;
                case ("browserstackmobile"):
                    SessionInfo.siteIdentifier = "[BS_Mobile][{envSiteLocale}][{device}][{browser}]"
                            .replace("{envSiteLocale}", envSiteLocale)
                            .replace("{device}", WebDriverHelper.BROWSERSTACK_DEVICE)
                            .replace("{browser}", WebDriverHelper.BROWSERSTACK_BROWSER)
                            .toUpperCase();
                    break;
                default: // local machine
                    SessionInfo.siteIdentifier = "[Local][{envSiteLocale}][{osName}][{browser}]"
                            .replace("{envSiteLocale}", envSiteLocale)
                            .replace("{osName}", System.getProperty("os.name"))
                            .replace("{browser}", WebDriverHelper.BROWSER)
                            .toUpperCase();
            }
        }

        String batchName = (WebDriverHelper.getBatch() != null) ? WebDriverHelper.getBatch().getName() : "";
        String featureName = Paths.get(scenario.getUri()).getFileName().toString().replace(".feature", "");
        String newBatchName = SessionInfo.siteIdentifier + " " + featureName;
    }
    @After
    public void embedScreenshot(final Scenario scenario)
    {
        final JavascriptExecutor jse = (JavascriptExecutor)getWebDriver();
        final Collection<String> tags = scenario.getSourceTagNames();
        if (tags.contains("@api"))
        {
            return;
        }
        if (tags.contains("@database"))
        {
            return;
        }
        try
        {
            final Map<String, Object> screenShots = ScreenshotHelper.getScreenShotsForCurrentTest();
            for (final Map.Entry<String, Object> screenShot : screenShots.entrySet())
            {
                scenario.write(screenShot.getKey());
                scenario.embed((byte[]) screenShot.getValue(), "image/png");
            }
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Test Passed\"}}");
            ScreenshotHelper.tidyUpAfterTestRun();

            if (scenario.isFailed())
            {
                scenario.write(WebDriverHelper.getWebDriver().getCurrentUrl());
                final byte[] screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenShot, "image/png");
                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Test Failed!\"}}");
            }

        }
        catch (final WebDriverException | ClassCastException wde)
        {
            LOG.error(wde.getMessage());
        }
        finally
        {
            getWebDriver().quit();
        }
    }
}
