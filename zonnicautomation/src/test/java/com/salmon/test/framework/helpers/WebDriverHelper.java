package com.salmon.test.framework.helpers;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.salmon.test.enums.PermittedBrowserMode;
import com.salmon.test.enums.PermittedMobileMode;
import com.salmon.test.enums.PermittedSiteMode;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.percy.selenium.Percy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import static com.salmon.test.enums.PermittedSiteMode.MOBILE;

public class WebDriverHelper extends EventFiringWebDriver {
    private static final Logger LOG = LoggerFactory
            .getLogger(WebDriverHelper.class);
    private static RemoteWebDriver REAL_DRIVER = null;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            if (REAL_DRIVER != null) {
                REAL_DRIVER.quit();
            }
        }
    };

    public static String BROWSER;
    private static String PLATFORM;
    private static String DRIVER_PATH;
    private static String DRIVER_ROOT_DIR;
    private static String FILE_SEPARATOR;
    private static String SELENIUM_HOST;
    private static String SELENIUM_PORT;
    private static String SELENIUM_REMOTE_URL;
    private static final Dimension BROWSER_WINDOW_SIZE;
    private static final Dimension MOBILE_WINDOW_SIZE = new Dimension(414, 622);
    private static final Dimension TABLET_WINDOW_SIZE = new Dimension(768, 1024);
    public static Dimension MOBILE_BROWSER_WINDOW_SIZE = new Dimension(500, 1240);
    private static Integer BROWSER_WINDOW_WIDTH;
    private static Integer BROWSER_WINDOW_HEIGHT;
    // Motel base vars
    private static String siteMode;
    private static String MotelPlatform;
    private static String MotelBrowser;
    private static String MotelDevice;
    private static String MotelTestURL;
    private static String MotelAccessKey;
    private static String MotelUDID; // also named serial
    private static String MotelTimeOut;
    private static String VENDOR;
    private static PermittedSiteMode mode = null;
    private static PermittedBrowserMode browserMode = null;
    private static PermittedMobileMode mobileMode = null;
    private static Platform platform = null;
    private static HashMap<String, String> mobileUDIDArray = new HashMap<>();
    public static final String BROWSERSTACK_USERNAME;
    public static final String BROWSERSTACK_PASSWORD;
    public static final String BROWSERSTACK_OS_VERSION;
    public static final String BROWSERSTACK_DEVICE;
    public static final boolean BROWSERSTACK_REAL_DEVICE;
    public static final String BROWSERSTACK_BROWSER;
    public static final String BROWSERSTACK_BROWSER_VERSION;
    public static final String BROWSERSTACK_BROWSER_OS;
    public static final String BROWSERSTACK_BROWSER_OS_VERSION;
    public static final String BROWSERSTACK_BUILD_NAME;
    public static final String BROWSERSTACK_LOCAL;
    public static final String BROWSERSTACK_LOCAL_IDENTIFIER;

    @Getter
    public static Percy percy;
    @Getter @Setter
    private static BatchInfo batch;
    private static final String APP_NAME = "BAT";
    public static final int DESKTOP_VIEW_MIN_WIDTH = 1024;

    static {
        SELENIUM_HOST = System.getProperty("driverhost");
        SELENIUM_PORT = System.getProperty("driverport");
        FILE_SEPARATOR = System.getProperty("file.separator");
        PLATFORM = Props.getProp("platform");
        BROWSER = System.getProperty("browser", Props.getProp("browser"));
        BROWSER_WINDOW_WIDTH = Integer.parseInt(Props.getProp("browser.width"));
        BROWSER_WINDOW_HEIGHT = Integer.parseInt(Props.getProp("browser.height"));
        BROWSER_WINDOW_SIZE = new Dimension(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);
        siteMode = Props.getProp("siteMode");
        VENDOR = Props.getProp("vendor");
        MotelPlatform = Props.getProp("motel_platform");
        MotelBrowser = Props.getProp("motel_browser");
        MotelDevice = Props.getProp("motel_device");
        MotelTestURL = Props.getProp("motelTestURL");
        MotelAccessKey = Props.getProp("user_token");
        MotelUDID = Props.getProp("UDID");
        MotelTimeOut = Props.getProp("motelTimeOut");
        BROWSERSTACK_USERNAME = Props.getProp("browserstack.username");
        BROWSERSTACK_PASSWORD= Props.getProp("browserstack.password");
        BROWSERSTACK_OS_VERSION= System.getProperty("browserstack.os.version", Props.getProp("browserstack.os.version"));
        BROWSERSTACK_DEVICE= System.getProperty("browserstack.device", Props.getProp("browserstack.device"));
        BROWSERSTACK_REAL_DEVICE= Boolean.valueOf(System.getProperty("browserstack.real.device", Props.getProp("browserstack.real.device")));
        BROWSERSTACK_BROWSER= System.getProperty("browserstack.browser", Props.getProp("browserstack.browser"));
        BROWSERSTACK_BROWSER_VERSION= System.getProperty("browserstack.browser.version", Props.getProp("browserstack.browser.version"));
        BROWSERSTACK_BROWSER_OS= System.getProperty("browserstack.browser.os", Props.getProp("browserstack.browser.os"));
        BROWSERSTACK_BROWSER_OS_VERSION= System.getProperty("browserstack.browser.os.version", Props.getProp("browserstack.browser.os.version"));
        BROWSERSTACK_BUILD_NAME = System.getenv("BROWSERSTACK_BUILD_NAME");
        BROWSERSTACK_LOCAL = System.getProperty("BROWSERSTACK_LOCAL");
        BROWSERSTACK_LOCAL_IDENTIFIER = System.getProperty("BROWSERSTACK_LOCAL_IDENTIFIER");
    }

    public static void startDriver(PermittedSiteMode mode) {
        try {
            switch (BROWSER.toLowerCase()) {
                case ("chrome"):
                    if (mode.equals(PermittedSiteMode.DESKTOP)) {
                        startChromeDriver();
                    } else {
                        startChromeDriver();
                        LOG.info("Browser size is "+BROWSER_WINDOW_SIZE);
                        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
                    }
                    break;
                case ("chromemobile"):
                    startAppiumMoTelDriver();
                    break;
                case ("firefox"):
                    startFireFoxDriver();
                    break;
                case ("iexplore"):
                    startIEDriver();
                    break;
                case ("edge"):
                    startEdgeDriver();
                    break;
                case "safari":
                    startSafariDriver();
                    break;
                case ("sauce"):
                    startSauceDriver();
                    break;
                case ("browserstackdesktop"):
                    startBrowserStackDesktop();
                    break;
                case ("browserstackmobile"):
                    startBrowserStackMobile();
                    break;
                default:
                    throw new IllegalArgumentException("Browser " + BROWSER + " or Platform "
                            + PLATFORM + " type not supported");
            }
            switch (mode) {
                case DESKTOP:
                    REAL_DRIVER.manage().window().maximize();
                    break;
                case TABLET:
                    break;
                case MOBILE:
                    LOG.info("LAUNCHING MOBILE");
                    break;
                default:
                    break;
            }
        } catch(
                IllegalStateException e)
        {
            LOG.error("FIX path for driver.root.dir in pom.xml " + DRIVER_ROOT_DIR
                    + " Browser parameter " + BROWSER + " Platform parameter " + PLATFORM
                    + " type not supported");
        }
        REAL_DRIVER.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        openPercy();
    }

    private static void  buildUDIDMatrix(){
        //MOBILES
        //Android devices
        mobileUDIDArray.put("samsungs6","071607ed83000603");
        mobileUDIDArray.put("samsungs6edge","1215fca29a852904");
        mobileUDIDArray.put("samsungs7","ad0617020f2fcea9e3");
        mobileUDIDArray.put("samsungs8","ce02171251ba2f1e05");
        //Sony Devices
        mobileUDIDArray.put("xperiae1","b2b0cac5");
        //Apple
        mobileUDIDArray.put("iphone6plus","732ae6cc919b1fa9968d9b81900c5f95c8bca217");
        mobileUDIDArray.put("iphonex","6da9f498a0f8d0b327d82ebcc4f3ac57f6dd1c16");
        mobileUDIDArray.put("iphone8plus","e1c5cc54a439524098533a6d2e9594ee9f481f59");
        mobileUDIDArray.put("iphone7","458723a2e4ff293060cd73e1ffe6782bbe5e7ea8");
        mobileUDIDArray.put("iphone6","9715fbbb1b911ff38a8be0139ffef5be1b6e605c");
        //Tablets
        mobileUDIDArray.put("ipadmini2","79bbff82eaec8c87898b92d199b629d092065a1d");
        mobileUDIDArray.put("ipadair2","1372776b2fee00563896406ea4b2070ff6ff30a9");
    }

    private WebDriverHelper() {
        super(REAL_DRIVER);
    }

    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }

    private static void startIEDriver() {
        InternetExplorerOptions options = getInternetExplorerOptions();
        if (SELENIUM_HOST == null) {
            WebDriverManager.iedriver().setup();
            REAL_DRIVER = new InternetExplorerDriver(options);
        }
        else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
    }

    private static void startEdgeDriver() {
        EdgeOptions options = getEdgeOptions();
        if (SELENIUM_HOST == null) {
            WebDriverManager.edgedriver().setup();
            REAL_DRIVER = new EdgeDriver(options);
        }
        else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
    }

    private static void startSafariDriver() {
        SafariOptions options = new SafariOptions();
        options.setUseTechnologyPreview(false);
        if (SELENIUM_HOST == null) {
            LOG.info("\n Executing Tests Locally on Safari \n");
            REAL_DRIVER = new SafariDriver(options);
        }
        else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
    }

    private static void startFireFoxDriver() {
        FirefoxOptions options=getFireFoxOptions();
        if (SELENIUM_HOST == null) {
            LOG.info("starting driver");
            WebDriverManager.firefoxdriver().setup();
            REAL_DRIVER = new FirefoxDriver(options);
            LOG.info("after starting driver");
        } else {
            try {
                //     capabilities.setPlatform(Platform.WIN8);
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
    }

    private static void startSauceDriver() {
        DesiredCapabilities capabilities = getSauceCapabilities();
        try {
            REAL_DRIVER = new RemoteWebDriver(new URL("http://username-string:access-key-string@ondemand.saucelabs.com:80/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            LOG.error(" Error Sauce Url " + e.getMessage());
        }
    }

    private static void startChromeDriver() {
        ChromeOptions options = getChromeOptions();
        options.addArguments("--incognito");
        if (SELENIUM_HOST == null) {
            WebDriverManager.chromedriver().setup();
            REAL_DRIVER = new ChromeDriver(
                    ChromeDriverService.createDefaultService(), options);
        }
        else {
            try {
                REAL_DRIVER = getRemoteWebDriver(options);
            } catch (MalformedURLException e) {
                LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
            }
        }
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
    }

    private static void startBrowserStackDesktop() {
        try
        {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", BROWSERSTACK_BROWSER);
            caps.setCapability("browser_version", BROWSERSTACK_BROWSER_VERSION);
            caps.setCapability("os", BROWSERSTACK_BROWSER_OS);
            caps.setCapability("os_version", BROWSERSTACK_BROWSER_OS_VERSION);
            caps.setCapability("sessionName",  BROWSERSTACK_BUILD_NAME);
            caps.setCapability("local",  BROWSERSTACK_LOCAL);
            caps.setCapability("localIdentifier",  BROWSERSTACK_LOCAL_IDENTIFIER);
            caps.setCapability("build", BROWSERSTACK_BUILD_NAME);
            caps.setCapability("browserstack.local", "false");
            caps.setCapability("browserstack.console", "errors");
            caps.setCapability("browserstack.networkLogs", "true");

            if(BROWSERSTACK_BROWSER.toLowerCase().equals("safari")){
                caps.setCapability("resolution", "1920x1080");
            }
            else{
                caps.setCapability("resolution", "2048x1536");
            }
            caps.setCapability("browserstack.seleniumLogs", "false");
//            caps.setCapability("build","BAT" + " Test - " + Props.getTestSuite()+" " + UrlBuilder.getLocale()+" " + SessionInfo.startTime);
            caps.setCapability("name", SessionInfo.scenarioName);

            caps.setCapability("browserstack.geoLocation", "CA");
            LOG.info("browserstack.geoLocation: CA");

            caps.setCapability("project","BAT");
            caps.setCapability("recordScreenshots", false);
            caps.setCapability("extendedDebugging", false);
            LOG.info("BROWSERSTACK_USERNAME " +BROWSERSTACK_USERNAME);
            LOG.info("BROWSERSTACK_PASSWORD "+BROWSERSTACK_USERNAME);
            REAL_DRIVER = new RemoteWebDriver(new URL("https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_PASSWORD + "@hub-cloud.browserstack.com/wd/hub"),caps);
            REAL_DRIVER.manage().window().maximize();
        }
        catch(MalformedURLException e)
        {
            LOG.error(" Error browser stack " + e.getMessage());
        }
    }

    private static void setGeoLocation(ChromeOptions options) {
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();

        contentSettings.put("geolocation", 1);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);
    }

    private static void startBrowserStackMobile() {
        try
        {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("os_version", BROWSERSTACK_OS_VERSION);
            caps.setCapability("device", BROWSERSTACK_DEVICE);
            caps.setCapability("real_mobile", BROWSERSTACK_REAL_DEVICE);
            caps.setCapability("browserstack.local", "false");
            caps.setCapability("browserstack.debug","false");
            caps.setCapability("build","BAT " +" Test - "+BROWSERSTACK_DEVICE+" "+Props.getTestSuite()+" " + UrlBuilder.getLocale()+" "+ SessionInfo.startTime);
            caps.setCapability("name", SessionInfo.scenarioName);
            caps.setCapability("project","BAT");

            caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            REAL_DRIVER = new RemoteWebDriver(new URL("https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_PASSWORD + "@hub-cloud.browserstack.com/wd/hub"),caps);
        }
        catch(MalformedURLException e)
        {
            LOG.error(" Error browser stack " + e.getMessage());
        }
    }

    // ******************************** MoTeL methods ********************************* //
    private static void startAppiumMoTelDriver() {
        DesiredCapabilities capabilities = getMoTelDesiredCapabilities();
        if (VENDOR.equalsIgnoreCase("MoTel")) {
            if (REAL_DRIVER != null) {
                return;
            }
            try {
                REAL_DRIVER = new RemoteWebDriver(new URL(MotelTestURL), capabilities);
            } catch (MalformedURLException e) {
                LOG.info("\n ******************* ERROR ");
                e.printStackTrace();
            }
        }
    }

    public static PermittedSiteMode getSiteMode(){
        switch (siteMode.toLowerCase()) {
            case "mobile":
                mode = PermittedSiteMode.MOBILE;
                break;
            case "desktop":
                mode = PermittedSiteMode.DESKTOP;
                break;
            case "tablet":
                mode = PermittedSiteMode.TABLET;
                break;
        }
                return mode;
    }

    public static PermittedMobileMode getMobileMode(){
        if(BROWSERSTACK_DEVICE.toLowerCase().contains("samsung")){
            mobileMode = PermittedMobileMode.SAMSUNG;
        }
        else if(BROWSERSTACK_DEVICE.toLowerCase().contains("iphone")){
            mobileMode = PermittedMobileMode.IPHONE;
        }
        else if(BROWSERSTACK_DEVICE.toLowerCase().contains("ipad")){
            mobileMode = PermittedMobileMode.IPAD;
        }
        return mobileMode;
    }

    public static PermittedBrowserMode getBrowserMode(){
        String browser = BROWSER.toLowerCase().startsWith("browserstack") ? BROWSERSTACK_BROWSER : BROWSER;
        switch(browser.toUpperCase()) {
            case "FIREFOX":
                browserMode=PermittedBrowserMode.FIREFOX;
                break;
            case"SAFARI":
                browserMode=PermittedBrowserMode.SAFARI;
                break;
            case"EDGE":
                browserMode=PermittedBrowserMode.EDGE;
                break;
            default:
                browserMode=PermittedBrowserMode.CHROME;
        }
        return browserMode;
    }

    public static Platform getPlatform() {
        switch (MotelPlatform) {
            case "ANDROID":
                platform = Platform.ANDROID;
                break;
            case "IOS":
                // Site says to use MAC
                LOG.info("using IOS");
                platform = Platform.IOS;
                break;
        }
        return platform;
    }

    private static DesiredCapabilities getMoTelDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", MotelTimeOut);//Integer.valueOf(MotelTimeOut)); // 240
        capabilities.setPlatform(Platform.ANY); //getPlatform());
        capabilities.setCapability("browserName", MotelBrowser);
        capabilities.setCapability("udid", getDeviceUdid());//MotelUDID);
        capabilities.setCapability("token", MotelAccessKey);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        return capabilities;
    }

    public static String getDeviceUdid() {
        buildUDIDMatrix();
        LOG.info("UDID : " + mobileUDIDArray.get(MotelDevice));
        return mobileUDIDArray.get(MotelDevice);
    }


    private static ChromeOptions getChromeOptions() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("allow-running-insecure-content");
        setGeoLocation(chromeOptions);
        return chromeOptions;
    }

    private static FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        options.setCapability(FirefoxDriver.PROFILE, profile);
        profile.setPreference("browser.privatebrowsing.autostart", true);
        options.setCapability("acceptInsecureCerts", true);
        return options;
    }

    private static InternetExplorerOptions getInternetExplorerOptions() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("requireWindowFocus", false);
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        options.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        options.setCapability("unhandledPromptBehavior", "dismiss");
        options.setCapability("disable-popup-blocking", true);
        options.setCapability("ignoreProtectedModeSettings", true);
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.OFF);
        EdgeOptions options = new EdgeOptions();
        options.setCapability("browserName","MicrosoftEdge");
        options.setPageLoadStrategy("normal");
        return options;
    }

    private static DesiredCapabilities getAppiumDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability(MobileCapabilityType.APP, "Chrome");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "42f7ab1fb7b59fab");
        return capabilities;
    }

    private static DesiredCapabilities getSauceCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", BROWSER);
        capabilities.setCapability("platform", PLATFORM);
        capabilities.setCapability("sauce-advisor", false);
        capabilities.setCapability("record-video", false);
        capabilities.setCapability("record-screenshots", false);
        return capabilities;
    }

    private static RemoteWebDriver getRemoteWebDriver(Capabilities capabilities) throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (capabilities));
    }

    private static RemoteWebDriver getRemoteWebDriver(ChromeOptions options) throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }
    private static RemoteWebDriver getRemoteWebDriver(InternetExplorerOptions options) throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    private static RemoteWebDriver getRemoteWebDriver(FirefoxOptions options) throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    private static RemoteWebDriver getRemoteWebDriver(SafariOptions options) throws MalformedURLException {
        SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
        LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
        return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (options));
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    private static void openPercy() {
        if (!Props.PERCY_ON) {
            return;
        }
        LOG.info("Percy switch engaged - percy is opening");
        if (!Props.USE_PERCY_GRID) {
            percy = new Percy(getWebDriver());
        }
    }
}