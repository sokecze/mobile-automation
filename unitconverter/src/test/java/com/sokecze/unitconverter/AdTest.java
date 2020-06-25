package com.sokecze.unitconverter;

import com.sokecze.core.actions.DriverActions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.sokecze.unitconverter.UnitConverterApp.adPage;
import static com.sokecze.unitconverter.UnitConverterApp.areaPage;

public class AdTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        DriverActions.startMainActivity();
    }

    @Test(description = "Verify Ad Page Displayed on Start and can be closed")
    public void adTest() {
        adPage.waitUntilOpened();

        adPage.verifyLabelTestAdDisplayed();
        adPage.close();

        areaPage.verifyOpened();
    }
}
