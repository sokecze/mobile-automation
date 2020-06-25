package com.sokecze.unitconverter.pages;

import com.sokecze.core.annotations.Element;
import com.sokecze.core.elements.AndroidSelect;
import com.sokecze.unitconverter.entities.MenuOptions;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class VolumePage extends BasePage {

    @Element(uiAutomator = "new UiSelector().resourceId('com.unitconverter.freeunitconversioncalculator:id/ime_jedinice_text_view')" +
            ".text('%s')")
    private AndroidSelect listUnits;

    @Step
    public void open() {
        sectionMenu.openMenuByClick();

        sectionMenu.openItem(MenuOptions.VOLUME);
    }

    @Step
    public void verifyScrolling() {
        String firstUnit = "gal (US)";
        String lastUnit = "cu in";

        listUnits.scrollToOption(lastUnit);

        assertThat(listUnits.getOption(lastUnit).isDisplayed()).isTrue();
        assertThat(listUnits.getOption(firstUnit).isDisplayed()).isFalse();
    }
}
