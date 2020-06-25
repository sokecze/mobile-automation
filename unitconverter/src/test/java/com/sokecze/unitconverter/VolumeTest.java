package com.sokecze.unitconverter;

import com.sokecze.core.actions.DriverActions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.sokecze.unitconverter.UnitConverterApp.*;


public class VolumeTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        DriverActions.startMainActivity();

        areaPage.waitUntilOpened();

        areaPage.closeMenu();
    }

    @Test
    public void pageScrolling() {
        volumePage.open();

        volumePage.verifyScrolling();
    }
}
