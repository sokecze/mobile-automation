package com.sokecze.core.elements;

import org.openqa.selenium.By;

import static com.sokecze.core.logging.Log4j.logger;

public abstract class BaseSelect implements IElement {
    private By locator;
    private String locatorString;

    @Override
    public void setLocator(By locator) {
        this.locator = locator;
        this.locatorString = locator.toString().replaceFirst("^By.*: ", "");
    }

    protected By resolveLocatorTemplate(String argument) {
        try {
            return locator.getClass().getDeclaredConstructor(String.class).newInstance(String.format(locatorString, argument));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public abstract BaseElement getOption(String option);

    public void scrollToOption(String option) {
        getOption(option).scrollIntoView();
    }

    public void selectOption(String option) {
        getOption(option).click();
    }
}
