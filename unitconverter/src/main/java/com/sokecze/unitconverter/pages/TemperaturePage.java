package com.sokecze.unitconverter.pages;

import com.sokecze.unitconverter.entities.MenuOptions;
import io.qameta.allure.Step;

public class TemperaturePage extends BasePage {

    @Step
    public void open() {
        sectionMenu.openMenuByClick();

        sectionMenu.openItem(MenuOptions.TEMPERATURE);
    }

    @Step
    public double getCelsius() {
        return Double.parseDouble(getValues().get(0));
    }

    @Step
    public double getFahrenheit() {
        return Double.parseDouble(getValues().get(1));
    }

    @Step
    public double getKelvin() {
        return Double.parseDouble(getValues().get(2));
    }
}
