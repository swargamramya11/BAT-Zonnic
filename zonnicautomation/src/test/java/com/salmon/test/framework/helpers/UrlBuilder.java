package com.salmon.test.framework.helpers;

import com.salmon.test.enums.PermittedBrowserMode;
import com.salmon.test.enums.PermittedMobileMode;
import com.salmon.test.enums.PermittedSiteMode;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.WebDriverHelper.*;

public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static URL basePath;
    public static String ENVIRONMENT;
    public static String LANGUAGE;
    public static String PROVINCE;
    public static String LOCALE;
    public static String url;
    private static Properties profileProperties;
    private static String regionCode;
    public static String env;
    public static String storeCode;
    public static String SITE;
    public static String countryCode;

    static {
        try {
            profileProperties = Props.getProfileProps();
            env=getEnv();
            regionCode=getRegionCode();
            basePath = new URL(Props.getProp("site.url"));
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage());
        }
    }

    public static String getLocale(){
        return LOCALE;
    }

    public static boolean isDesktop(){
        return getSiteMode()==PermittedSiteMode.DESKTOP;
    }

    public static boolean isFirefox(){
        return getBrowserMode()== PermittedBrowserMode.FIREFOX;
    }

    public static boolean isEdge(){
        return getBrowserMode()== PermittedBrowserMode.EDGE;
    }

    public static boolean isSafari(){
        return getBrowserMode()== PermittedBrowserMode.SAFARI;
    }

    public static void setSite(final String site)
    {
        SITE = site;
    }

    public static String getSite()
    {
        return SITE;
    }

    public static void setEnvironment(final String env)
    {
        ENVIRONMENT = env;
    }

    public static void setLanguage(final String lang)
    {
        LANGUAGE = lang;
    }

    public static void setProvince(final String province)
    {
        PROVINCE = province;
    }

    public static void setLocale(final String newLocale)
    {
        LOCALE = newLocale;
    }

    public static void setStoreCode(final String newStoreCode)
    {
        storeCode = newStoreCode;
    }

    public static String getEnv()
    {
        try {
            return ENVIRONMENT;
        } catch (final Exception ignored) {
            return "";
        }
    }

    public static String getRegionCode()
    {
        try {
            return "region_" + countryCode.replaceAll("-", "");
        } catch (final Exception ignored) {
            return "";
        }
    }

    public static void navigateToUrl(String url) {
        WebDriverHelper.getWebDriver().get(url);
    }

    public static void navigateToRelativeUrl(String relativeUrl) {
        UrlBuilder.navigateToUrl(getWebDriver().getCurrentUrl() + "/" +
                (relativeUrl.startsWith("/")? relativeUrl.substring(1) : relativeUrl));
    }
    public static void urlBuilderForVuse() {
        String url;
        if(Props.getTestSuite().equalsIgnoreCase("Live")){
            url = "https://www.vuse.com/ca/en";
            navigateToUrl(url);
        } else {
            url = "https://bat-reviewer:bAT!+4sa7ya@uat.vuse.com/ca/en";
            try {
                navigateToUrl(url);
                navigateToUrl(getMessage("vuseUrl" + "-" + LANGUAGE));
            }catch (Exception e) {

            }
        }
    }

    public static void invokeBrowser() {
        startDriver(getSiteMode());
    }

    public static void urlBuilder(){
        switch (getLocale()) {
            case "zonnicca":
                if (!System.getProperty("override.url", Props.getProp("override.url")).equals("")) {
                    url = System.getProperty("override.url", Props.getProp("override.url")) + getEndPoints(LOCALE);
                    if(System.getProperty("override.url") .equals("https://www.zonnic.ca/")) {
                        navigateToUrl(url);
                    } else {
                        String url1 = "https://bat-reviewer:bAT!+4sa7ya@www-ca-"+ ENVIRONMENT  + "-canada-zonnic.non-prod.marketing.bat.net/"+ getEndPoints(LOCALE);
                        navigateToUrl(url1);
                        String url2 = "https://www-" + "ca-"+ ENVIRONMENT  + "-canada-" + SITE + ".non-prod.marketing.bat.net/" + getEndPoints(LOCALE);

                        try{
                            navigateToUrl(url2);
                        }catch (Exception e){

                        }
                    }
                }
                break;
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getEndPoints(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        }
        return ResourceBundle.getBundle("endpoints/locale").getString(key).trim();
    }

    public static String getMessage(String key) {
        String url = "";
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            switch (LOCALE.toLowerCase()) {
                case "zonnicca":
                    url = ResourceBundle.getBundle("locales/zonnicca").getString(key).trim();
                    break;
            }
        }
        return url;
    }

    public static String getProvince(){
        return PROVINCE;
    }

    public static String getProvinceCode() {
        String province = null;
        switch (getProvince()) {
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
