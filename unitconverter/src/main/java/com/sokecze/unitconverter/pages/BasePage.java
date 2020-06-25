package com.sokecze.unitconverter.pages;

import com.sokecze.core.actions.DriverActions;
import com.sokecze.core.annotations.Element;
import com.sokecze.core.annotations.Section;
import com.sokecze.core.elements.AndroidElement;
import com.sokecze.core.elements.AndroidList;
import com.sokecze.core.pages.AndroidPage;
import com.sokecze.unitconverter.sections.KeyboardSection;
import com.sokecze.unitconverter.sections.MenuSection;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class BasePage extends AndroidPage {

    private enum Direction {UP, DOWN};

    @Section
    protected MenuSection sectionMenu;

    @Section
    protected KeyboardSection sectionKeyboard;

    @Element(id = "com.unitconverter.freeunitconversioncalculator:id/unesena_vrednost")
    private AndroidElement input;

    @Element(id = "com.unitconverter.freeunitconversioncalculator:id/imageView1")
    private AndroidElement pickerUnits;

    @Element(id = "com.unitconverter.freeunitconversioncalculator:id/vrednost_text_view")
    private AndroidList listValues;

    @Step
    public void closeMenu() {
        if (sectionMenu.isOpen())
            sectionMenu.close();
    }

    @Step
    public void openMenuByClick() {
        sectionMenu.openMenuByClick();
    }

    @Step
    public void openMenuBySwipe() {
        sectionMenu.openMenuBySwipe();
    }

    @Step
    public void verifyMenuOpened() {
        sectionMenu.verifyMenuOpened();
    }

    @Step
    public void verifyMenuClosed() {
        sectionMenu.verifyMenuClosed();
    }

    @Step
    public void openKeyboard() {
        sectionKeyboard.open();
        sectionKeyboard.waitUntilDisplayed();
    }

    @Step
    public void closeKeyboard() {
        if (sectionKeyboard.isOpen())
            sectionKeyboard.clickOk();
    }

    @Step
    public void enterNumber(int number) {
        sectionKeyboard.enterNumber(number);
    }

    @Step
    public void enterNumber(float number) {
        sectionKeyboard.enterNumber(number);
    }

    @Step
    public void clickClear() {
        sectionKeyboard.clickClear();
    }

    @Step
    public void clickOk() {
        sectionKeyboard.clickOk();
    }

    @Step
    public void verifyKeyboardClosed() {
        sectionKeyboard.verifyKeyboardClosed();
    }

    @Step
    public void clickOnInput() {
        input.click();
    }

    @Step
    public void verifyInputTextIs(String text) {
        assertThat(input.getText()).isEqualTo(text);
    }

    private void scrollPicker(Direction direction) {
        Point pickerLocation = pickerUnits.getLocation();
        Dimension pickerSize = pickerUnits.getSize();

        Point pickerCenter = new Point(pickerLocation.getX() + pickerSize.getWidth() / 2,
                pickerLocation.getY() + pickerSize.getHeight() / 2);
        Point pickerTopCenter = new Point(pickerLocation.getX() + pickerSize.getWidth() / 2,
                pickerLocation.getY() + pickerSize.getHeight() / 4);

        if (direction == Direction.UP) DriverActions.swipe(pickerTopCenter, pickerCenter);
        else DriverActions.swipe(pickerCenter, pickerTopCenter);

        DriverActions.wait(500);
    }

    @Step
    public void scrollPickerUp() {
        scrollPicker(Direction.UP);
    }

    @Step
    public void scrollPickerDown() {
        scrollPicker(Direction.DOWN);
    }

    @Step
    public List<String> getValues() {
        return listValues.getText();
    }
}
