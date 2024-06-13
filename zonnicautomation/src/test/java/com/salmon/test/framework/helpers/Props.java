package com.salmon.test.framework.helpers;

import com.salmon.test.enums.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import static java.lang.System.out;

public class Props {
    private static final Logger LOG = LoggerFactory.getLogger(Props.class);
    public static Properties environmentProps;
    private static Properties properties;
    public static String storeCode;
    public static String lang;
    public static String province;
    private static String env;
    private static String site;
    private static String testSuite;
    private static String overrideUrl;
    private static final String DEFAULT_ENV = "uat2";
    private static final String DEFAULT_LANG = "en";
    private static final String DEFAULT_PROVINCE = "Alberta";
    private static final String DEFAULT_SITE = "zonnic";
    private static final String DEFAULT_LOCAL = "CA";
    private static final String DEFAULT_TEST_SUITE = "Automation";
    private static final String DEFAULT_STORECODE = "vuse_uk_en_gb";
    public static boolean EYES_ON = false;
    public static boolean USE_EYES_GRID = false;
    public static boolean PERCY_ON = false;
    public static boolean USE_PERCY_GRID = false;

    static {
        loadRunConfigProps("/environment.properties");
    }

    /**
     * Gets the key from messages.properties for a Site
     *
     * @param key
     **/
    public static String getMessage(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return ResourceBundle.getBundle("props/messages").getString(key);

        }
    }

    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return properties.getProperty(key);
        }
    }

    public static void loadRunConfigProps(String configPropertyFileLocation) {
        environmentProps = new Properties();
        try (InputStream inputStream = Props.class.getResourceAsStream(configPropertyFileLocation)) {
            environmentProps.load(inputStream);
            environmentProps.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        properties = new Properties();
        try (InputStream inputStream = Props.class.getResourceAsStream(environmentProps.getProperty("profile.path"))) {
            properties.load(inputStream);
            properties.list(out);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        try {
            testSuite = environmentProps.getProperty("test-suite");

            // If test-suite is unset, set it to default.
            if ("${test-suite}".equalsIgnoreCase(testSuite)) {
                testSuite = DEFAULT_TEST_SUITE;
            }
        } catch (final Exception ignored) {
            testSuite = DEFAULT_TEST_SUITE;
        }
        EYES_ON = Boolean.parseBoolean(environmentProps.getProperty("eyesOn"));
        USE_EYES_GRID = Boolean.parseBoolean(environmentProps.getProperty("useEyesGrid"));
        PERCY_ON = Boolean.parseBoolean(environmentProps.getProperty("percyOn"));
        USE_PERCY_GRID = Boolean.parseBoolean(environmentProps.getProperty("usePercyGrid"));
        try {
            overrideUrl = environmentProps.getProperty("override.url");
            System.setProperty("override.url", overrideUrl);

            // If test-suite is unset, set it to default.
            if ("${override.url}".equalsIgnoreCase(overrideUrl)) {
                overrideUrl = null;
                System.setProperty("override.url", "");
            }
        } catch (final Exception ignored) {
            System.setProperty("override.url", "");
        }

        try {
            env = environmentProps.getProperty("env");

            // If env is unset, set it to default.
            if ("${env}".equalsIgnoreCase(env)) {
                UrlBuilder.setEnvironment(DEFAULT_ENV);
            } else {
                UrlBuilder.setEnvironment(env);
            }

        } catch (final Exception ignored) {
            UrlBuilder.setEnvironment(env);
        }

        try {
            final String localeCode = environmentProps.getProperty("defaultLocale");

            if ("${defaultLocale}".equalsIgnoreCase(localeCode)) {
                // If localeCode is unset, set it to default.
                UrlBuilder.setLocale(DEFAULT_LOCAL);
                UrlBuilder.setSite(DEFAULT_SITE);
            } else {
                UrlBuilder.setLocale(localeCode);
                final String SITE = environmentProps.getProperty("site");
                UrlBuilder.setSite(SITE);
            }
        } catch (final Exception ignored) {
            UrlBuilder.setLocale(DEFAULT_LOCAL);
            UrlBuilder.setSite(DEFAULT_SITE);
        }
        try {
            storeCode = environmentProps.getProperty("storeCode");
            if ("${storeCode}".equalsIgnoreCase(storeCode)) {
                storeCode = getStoreCode();
            }
            UrlBuilder.setStoreCode(storeCode);
            UrlBuilder.setLocale(Store.valueOf(UrlBuilder.storeCode).getLocale());
        } catch (final Exception ignored) {
            storeCode = DEFAULT_STORECODE;
            UrlBuilder.setStoreCode(storeCode);
        }

        try {
            lang = environmentProps.getProperty("lang");

            // If env is unset, set it to default.
            if ("${lang}".equalsIgnoreCase(lang)) {
                UrlBuilder.setLanguage(DEFAULT_LANG);
            } else {
                UrlBuilder.setLanguage(lang);
            }
        } catch (final Exception ignored) {
            UrlBuilder.setLanguage(lang);
        }

        try {
            province = environmentProps.getProperty("province");

            // If env is unset, set it to default.
            if ("${province}".equalsIgnoreCase(province)) {
                UrlBuilder.setProvince(DEFAULT_PROVINCE);
            } else {
                UrlBuilder.setProvince(province);
            }

        } catch (final Exception ignored) {
            UrlBuilder.setLanguage(province);
        }
    }

    public static String getTestSuite() {
        return testSuite;
    }

    public static Properties getProfileProps() {
        return properties;
    }

    public static String getStoreCode() {
        site=UrlBuilder.getSite().toLowerCase();
        String newLocale=UrlBuilder.getLocale().toLowerCase();
        String tmp="";
        if (newLocale.contains("zonnicca")){
            tmp=site+"_ca_en_"+newLocale.replace("zonnic","");
        } else {
            tmp=site+"_"+newLocale;
        }
        Class clz = Store.class;
        for (Object obj: clz.getEnumConstants()) {
            if(obj.toString().contains(tmp)){
                return obj.toString();
            }
        }
        return DEFAULT_STORECODE;
    }
}