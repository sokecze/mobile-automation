package com.sokecze.unitconverter;

import com.sokecze.core.actions.DriverActions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.sokecze.unitconverter.UnitConverterApp.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class TemperatureTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        DriverActions.startMainActivity();

        areaPage.waitUntilOpened();

        areaPage.closeMenu();

        temperaturePage.open();
        temperaturePage.openKeyboard();
    }

    @BeforeMethod
    public void clearNumber() {
        temperaturePage.clickClear();
    }

    @Test
    public void calculationsCelsius() {
        temperaturePage.scrollPickerUp();

        temperaturePage.enterNumber(0);

        assertThat(temperaturePage.getFahrenheit()).isEqualTo(32.00);
        assertThat(temperaturePage.getKelvin()).isEqualTo(273.15);

        temperaturePage.enterNumber(100);

        assertThat(temperaturePage.getFahrenheit()).isCloseTo(212.00, within(0.1));
        assertThat(temperaturePage.getKelvin()).isEqualTo(373.15);
    }

    @Test
    public void calculationsFahrenheit() {
        temperaturePage.scrollPickerDown();

        temperaturePage.enterNumber(32);

        assertThat(temperaturePage.getCelsius()).isEqualTo(0.00);
        assertThat(temperaturePage.getKelvin()).isEqualTo(273.15);

        temperaturePage.clickClear();

        temperaturePage.enterNumber(212);

        assertThat(temperaturePage.getCelsius()).isEqualTo(100.00);
        assertThat(temperaturePage.getKelvin()).isCloseTo(373.15, within(0.1));
    }
}
