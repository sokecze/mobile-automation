package com.sokecze.unitconverter.pages;

import io.qameta.allure.Step;

import static com.sokecze.unitconverter.entities.MenuOptions.AREA;
import static com.sokecze.unitconverter.entities.MenuOptions.DIGITAL_IMAGING;
import static org.assertj.core.api.Assertions.assertThat;

public class AreaPage extends BasePage {
    @Step
    public void verifyMenuScrolling() {
        assertThat(sectionMenu.isMenuItemDisplayed(AREA)).isTrue();
        assertThat(sectionMenu.isMenuItemDisplayed(DIGITAL_IMAGING)).isFalse();

        sectionMenu.scrollMenuItemIntoView(DIGITAL_IMAGING);

        assertThat(sectionMenu.isMenuItemDisplayed(DIGITAL_IMAGING)).isTrue();

        sectionMenu.close();
        sectionMenu.openMenuByClick();

        assertThat(sectionMenu.isMenuItemDisplayed(DIGITAL_IMAGING)).isTrue();
    }

    @Step
    public void waitUntilOpened() {
        super.waitUntilOpened();

        sectionMenu.waitUntilOpened();
    }
}
