package com.sokecze.iosapp.pages;

import com.sokecze.core.annotations.Element;
import com.sokecze.core.elements.IOSElement;
import com.sokecze.core.pages.AndroidPage;

public class MainPage extends AndroidPage {
    @Element(accessibilityId = "Toolbars")
    private IOSElement buttonAlertViews;

    public void clickAlertViews() {
        buttonAlertViews.scrollIntoView();
        buttonAlertViews.click();
    }
}
