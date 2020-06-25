package com.sokecze.core.elements;

import com.sokecze.core.config.DriverSettings;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;

import java.util.concurrent.TimeUnit;

import static com.sokecze.core.logging.Log4j.logger;

import static com.sokecze.core.config.DriverSettings.getDriver;

public class AndroidElement extends BaseElement implements IElement {

    protected AndroidElement() {
        super();
    }

    AndroidElement(By locator) {
        super(locator);
    }

    public boolean isDisplayed(int millis) {
        getDriver().manage().timeouts().implicitlyWait(millis, TimeUnit.MILLISECONDS);
        boolean result = getDriver().findElements(locator).size() != 0;
        DriverSettings.resetTimeout();

        return result;
    }

    public boolean isDisplayed() {
        return isDisplayed(0);
    }

    public void scrollIntoView() throws InvalidSelectorException {
        logger.info("Scrolling to the element (" + locator.toString() + ")");

        if (!locator.toString().contains("By.AndroidUIAutomator")) throw new InvalidSelectorException("Locator should've been of UI Automator type but was: " + locatorString);

        ((AndroidDriver<io.appium.java_client.android.AndroidElement>) getDriver()).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + locatorString + ");");
    }
}
