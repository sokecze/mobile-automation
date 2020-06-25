package com.sokecze.core.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static com.sokecze.core.logging.Log4j.logger;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DriverSettings {
    public static final int WAIT_TIMEOUT = 10;

    private static final String RESOURCE_PATH = new File("src").getAbsolutePath() + File.separator + "main" + File.separator + "resources" + File.separator;

    private enum DriverType {ANDROID, IOS}

    private static AppiumDriver driver;
    private static DriverType driverType;

    public static synchronized AppiumDriver getDriver() {
        String devicePlatform = PropertyReader.getProperty("device.platform");

        if (driver == null) try {
            logger.info("Getting a new driver...");

            if (devicePlatform.toLowerCase().equals("android")) {
                driverType = DriverType.ANDROID;
                driver = new AndroidDriver<AndroidElement>(new URL(PropertyReader.getProperty("appium.hub")), getAndroidEmulatorCapabilities());
            }
            else if (devicePlatform.toLowerCase().equals("ios")) {
                driverType = DriverType.IOS;
                driver = new IOSDriver<IOSElement>(new URL(PropertyReader.getProperty("appium.hub")), getIOSEmulatorCapabilites());
            }
            else
                throw new SessionNotCreatedException("Invalid driver: '" + devicePlatform + "'. Acceptable driver types are 'Android' and 'iOS'.");
            getDriverSettings();

            logger.info("A driver is obtained");
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Invalid appium hub URL format: " + PropertyReader.getProperty("appium.hub"));
        }
        return driver;
    }

    public static String getAppPackage() {
        return PropertyReader.getProperty("app.package");
    }

    public static String getMainActivity() {
        return PropertyReader.getProperty("app.activity");
    }

    private static DesiredCapabilities getAndroidEmulatorCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, PropertyReader.getProperty("device.name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");

        if (PropertyReader.getProperty("app.installed").equals("true")) {
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getAppPackage());
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getMainActivity());
        } else {
            capabilities.setCapability(MobileCapabilityType.APP, RESOURCE_PATH + PropertyReader.getProperty("app.file"));
        }

        return capabilities;
    }

    public static String getBundleId() {
        return PropertyReader.getProperty("app.bundleId");
    }

    private static DesiredCapabilities getIOSEmulatorCapabilites() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, PropertyReader.getProperty("device.name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");

        if (PropertyReader.getProperty("app.installed").equals("true")) {
            capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getBundleId());
        } else {
            capabilities.setCapability(MobileCapabilityType.APP, RESOURCE_PATH + PropertyReader.getProperty("app.file"));
        }

        return capabilities;
    }

    public static void resetTimeout() {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, SECONDS);
    }

    private static void getDriverSettings() {
        resetTimeout();
    }

    public static boolean isDriverAndroid() {
        return driverType == DriverType.ANDROID;
    }

    public static boolean isDriverIOS() {
        return driverType == DriverType.IOS;
    }
}
