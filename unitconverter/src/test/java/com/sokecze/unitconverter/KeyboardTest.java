package com.sokecze.unitconverter;

import com.sokecze.core.actions.DriverActions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.sokecze.unitconverter.UnitConverterApp.areaPage;

public class KeyboardTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        DriverActions.startMainActivity();

        areaPage.waitUntilOpened();

        areaPage.closeMenu();
    }

    @AfterMethod
    public void closeMenu() {
        areaPage.closeKeyboard();
    }

    @Test
    public void openCloseTest() {
        areaPage.openKeyboard();
        areaPage.clickOk();
        areaPage.verifyKeyboardClosed();

        areaPage.clickOnInput();
        areaPage.clickOk();
        areaPage.verifyKeyboardClosed();

        areaPage.openKeyboard();
        DriverActions.goBack();
        areaPage.verifyKeyboardClosed();
    }

    @Test
    public void inputTest() {
        int inputInt = 153;
        float inputFloat = 234.421f;

        areaPage.openKeyboard();
        areaPage.enterNumber(inputInt);
        areaPage.verifyInputTextIs(String.valueOf(inputInt));

        areaPage.clickClear();
        areaPage.verifyInputTextIs("1");

        areaPage.enterNumber(inputFloat);
        areaPage.verifyInputTextIs(String.valueOf(inputFloat));
        areaPage.clickClear();
        areaPage.verifyInputTextIs("1");
    }
}
