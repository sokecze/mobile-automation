package com.sokecze.core.elements;

import com.sokecze.core.actions.DriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;

import static com.sokecze.core.config.DriverSettings.getDriver;
import static com.sokecze.core.logging.Log4j.logger;

public class IOSElement extends BaseElement {

    private IOSElement() {
        super();
    }

    IOSElement(By locator) {
        super(locator);
    }

    public boolean isDisplayed() {
        logger.info("Checking if the element is displayed (" + locator.toString() + ")");

        return getDriver().findElement(locator).isDisplayed();
    }

    @Override
    public void scrollIntoView() throws InvalidSelectorException {
        while (!this.isDisplayed())
            DriverActions.swipeDown();
    }
}
