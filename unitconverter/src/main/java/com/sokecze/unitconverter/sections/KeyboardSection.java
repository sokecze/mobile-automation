package com.sokecze.unitconverter.sections;

import com.sokecze.core.annotations.Element;
import com.sokecze.core.elements.AndroidElement;
import com.sokecze.core.elements.AndroidSelect;
import io.qameta.allure.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyboardSection extends BaseSection {

    @Element(id = "com.unitconverter.freeunitconversioncalculator:id/imageView16")
    private AndroidElement buttonOpenKeyboard;

    @Element(xpath = "//android.widget.Button[@text='%s']")
    private AndroidSelect buttonsNumbers;

    @Element(id = "com.unitconverter.freeunitconversioncalculator:id/clear")
    private AndroidElement buttonClear;

    @Element(id = "com.unitconverter.freeunitconversioncalculator:id/spustitastaturu")
    private AndroidElement buttonOk;

    @Step
    public void open() {
        buttonOpenKeyboard.click();
    }

    @Step
    public void clickNumberButton(String number) {
        buttonsNumbers.getOption(number).click();
    }

    @Step
    public void clickComma() {
        buttonsNumbers.getOption(",").click();
    }

    @Step
    public void clickClear() {
        buttonClear.click();
    }

    @Step
    public void clickOk() {
        buttonOk.click();
    }

    @Step
    public void enterNumber(int number) {
        for (char c : String.valueOf(number).toCharArray()) clickNumberButton(String.valueOf(c));
    }

    @Step
    public void enterNumber(float number) {
        for (char c : String.valueOf(number).toCharArray()) {
            if (c == '.') clickComma();
            else clickNumberButton(String.valueOf(c));
        }
    }

    @Step
    public void waitUntilDisplayed() {
        buttonsNumbers.getOption("0").waitUntilDisplayed();
    }

    @Step
    public boolean isOpen() {
        return buttonsNumbers.getOption("0").isDisplayed(1000);
    }

    @Step
    public void verifyKeyboardClosed() {
        assertThat(Boolean.valueOf(isOpen())).isFalse();
    }
}
