package com.sokecze.core.elements;

import org.openqa.selenium.By;

import static com.sokecze.core.config.DriverSettings.getDriver;
import static com.sokecze.core.logging.Log4j.logger;

public class Input extends AndroidElement {

    private Input() {
        super();
    }

    Input(By locator) {
        super(locator);
    }

    public void input(String text) {
        logger.info("Sending text '" + text + "' to the element (" + locator.toString() + ")");

        getDriver().findElement(locator).sendKeys(text);
        getDriver().hideKeyboard();
    }
}
