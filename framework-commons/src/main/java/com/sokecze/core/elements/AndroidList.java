package com.sokecze.core.elements;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.sokecze.core.config.DriverSettings.getDriver;
import static com.sokecze.core.logging.Log4j.logger;

public class AndroidList implements IElement {
    private By locator;

    public void setLocator(By locator) {
        this.locator = locator;
    }

    public List<String> getText() {
        logger.info("Getting the elements' text (" + locator.toString() + ")");

        return ((AndroidDriver<AndroidElement>) getDriver()).findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getIndexOf(String text) {
        logger.info("Getting the index of the element with text '" + text + "' (" + locator.toString() + ")");

        return getText().indexOf(text);
    }

    public int size() {
        logger.info("Getting the number of elements (" + locator.toString() + ")");

        return getDriver().findElements(locator).size();
    }

    public void click(int index) {
        logger.info("Clicking onto the element with index " + index + " (" + locator.toString() + ")");

        ((AndroidDriver<AndroidElement>) getDriver()).findElements(locator).get(index).click();
    }
}
