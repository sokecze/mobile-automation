package com.sokecze.unitconverter.sections;

import com.sokecze.core.actions.DriverActions;
import com.sokecze.core.annotations.Element;
import com.sokecze.core.elements.AndroidElement;
import com.sokecze.core.elements.AndroidSelect;
import com.sokecze.unitconverter.entities.MenuOptions;
import io.qameta.allure.Step;

import static com.sokecze.unitconverter.entities.MenuOptions.TIME;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuSection extends BaseSection {
    @Element(id = "android:id/up")
    private AndroidElement buttonMenu;

    @Element(uiAutomator = "new UiSelector().resourceId('com.unitconverter.freeunitconversioncalculator:id/vrednost_text_view')" +
            ".text('%s')")
    private AndroidSelect selectMenu;

    @Step
    public void openMenuByClick() {
        buttonMenu.click();
    }

    @Step
    public void close() {
        DriverActions.clickOnPoint(0.90, 0.50);
        selectMenu.getOption(TIME.getValue()).waitUntilHidden();
    }

    @Step
    public void openMenuBySwipe() {
        DriverActions.swipeRight();
    }

    @Step
    public void scrollMenuItemIntoView(MenuOptions item) {
        selectMenu.scrollToOption(item.getValue());
    }

    @Step
    public void openItem(MenuOptions item) {
        selectMenu.getOption(item.getValue()).click();
    }

    @Step
    public boolean isMenuItemDisplayed(MenuOptions item) {
        return selectMenu.getOption(item.getValue()).isDisplayed();
    }

    @Step
    public boolean isOpen() {
        return selectMenu.getOption(TIME.getValue()).isDisplayed(1000);
    }

    @Step
    public void waitUntilOpened() {
        selectMenu.getOption(TIME.getValue()).waitUntilDisplayed();
    }

    @Step
    public void verifyMenuOpened() {
        assertThat(Boolean.valueOf(isOpen())).isTrue();
    }

    @Step
    public void verifyMenuClosed() {
        assertThat(Boolean.valueOf(isOpen())).isFalse();
    }
}
