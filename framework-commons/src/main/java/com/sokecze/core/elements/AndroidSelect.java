package com.sokecze.core.elements;

public class AndroidSelect extends BaseSelect {

    public AndroidElement getOption(String option) {
        return new AndroidElement(resolveLocatorTemplate(option));
    }
}
