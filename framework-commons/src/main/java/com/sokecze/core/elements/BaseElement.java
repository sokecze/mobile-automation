package com.sokecze.core.elements;

import com.sokecze.core.actions.DriverActions;
import com.sokecze.core.config.DriverSettings;
import com.sokecze.core.entities.Messages;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sokecze.core.config.DriverSettings.getDriver;
import static com.sokecze.core.logging.Log4j.logger;

public abstract class BaseElement implements IElement {
    protected By locator;
    protected String locatorString;

    protected BaseElement() { }

    BaseElement(By locator) {
        setLocator(locator);
    }

    public abstract void scrollIntoView() throws InvalidSelectorException;

    public abstract boolean isDisplayed();

    @Override
    public String toString() {
        return locator.toString();
    }

    protected By resolveLocatorTemplate(String argument) {
        try {
            return locator.getClass().getDeclaredConstructor(String.class).newInstance(String.format(locatorString, argument));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setLocator(By locator) {
        this.locator = locator;
        this.locatorString = locator.toString().replaceFirst("^By.*: ", "");
    }

    public String getText() {
        String text = getDriver().findElement(locator).getText();

        logger.info("Element's text: '" + text + "' (" + locator.toString() + ")");

        return text;
    }

    public Point getLocation() {
        Point location = getDriver().findElement(locator).getLocation();

        logger.info("Element's location: '" + location + "' (" + locator.toString() + ")");

        return location;
    }

    public Dimension getSize() {
        Dimension size = getDriver().findElement(locator).getSize();

        logger.info("Element's size: '" + size + "' (" + locator.toString() + ")");

        return size;
    }

    public void click() {
        logger.info("Clicking onto the element (" + locator.toString() + ")");

        getDriver().findElement(locator).click();
    }

    public void waitUntilDisplayed(int timeout) {
        logger.info("Waiting for the element to be displayed (" + locator.toString() + ")");

        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilDisplayed() {
        waitUntilDisplayed(DriverSettings.WAIT_TIMEOUT);
    }

    public void waitUntilHidden(int timeout) {
        logger.info("Waiting for the element to be hidden (" + locator.toString() + ")");

        for (int i = 0; i < timeout; i++) {
            if (!isDisplayed()) return;
            DriverActions.wait(1000);
        }

        throw new AssertionError(Messages.getElementVisibleErrorMessage(this.toString()));
    }

    public void waitUntilHidden() {
        waitUntilHidden(DriverSettings.WAIT_TIMEOUT);
    }
}
