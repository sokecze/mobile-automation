package com.sokecze.unitconverter;

import com.sokecze.core.actions.DriverActions;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.sokecze.unitconverter.UnitConverterApp.*;

public class MenuTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        DriverActions.startMainActivity();

        areaPage.waitUntilOpened();

        areaPage.closeMenu();
    }

    @AfterMethod
    public void closeMenu() {
        areaPage.closeMenu();
    }

    @Test(description = "Check Menu Functionality")
    @Description("Full test description")
    public void openMenuByClickTest() {
        areaPage.openMenuByClick();
        areaPage.verifyMenuOpened();
        areaPage.closeMenu();
        areaPage.verifyMenuClosed();
    }

    @Test
    public void openMenuBySwipeTest() {
        areaPage.openMenuBySwipe();
        areaPage.verifyMenuOpened();
    }

    @Test(description = "Verify Menu Scrolling")
    public void menuScrollingTest() {
        areaPage.openMenuByClick();

        areaPage.verifyMenuScrolling();
    }
}
