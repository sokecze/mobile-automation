package com.sokecze.unitconverter.pages;

import com.sokecze.core.actions.DriverActions;
import com.sokecze.core.annotations.Element;
import com.sokecze.core.config.DriverSettings;
import com.sokecze.core.elements.AndroidElement;
import com.sokecze.core.entities.Messages;
import com.sokecze.core.pages.AndroidPage;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class AdPage extends AndroidPage {
    @Element(xpath = "//android.widget.TextView[@text='Test Ad']")
    private AndroidElement labelTestAd;

    @Step
    public void close() {
        for (int i = 0; i < (DriverSettings.WAIT_TIMEOUT * 2); i++) {
            if (labelTestAd.isDisplayed()) DriverActions.clickOnPoint(0.95, 0.05);
            if (labelTestAd.isDisplayed()) DriverActions.clickOnPoint(0.05, 0.05);

            DriverActions.wait(500);

            if (!labelTestAd.isDisplayed()) return;
        }

        throw new AssertionError(Messages.getElementVisibleErrorMessage(labelTestAd.toString()));
    }

    @Step
    public void verifyLabelTestAdDisplayed() {
        assertThat(labelTestAd.isDisplayed()).isTrue();
    }

    @Override
    public void waitUntilOpened() {
        super.waitUntilOpened(20);
    }
}
