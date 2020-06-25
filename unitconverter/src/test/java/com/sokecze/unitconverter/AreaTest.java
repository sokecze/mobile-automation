package com.sokecze.unitconverter;

import com.sokecze.core.actions.DriverActions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.sokecze.unitconverter.UnitConverterApp.areaPage;
import static org.assertj.core.api.Assertions.assertThat;

public class AreaTest extends BaseTest {

    @BeforeClass
    public void setUp() {
        DriverActions.startMainActivity();

        areaPage.waitUntilOpened();

        areaPage.closeMenu();
    }

    @Test
    public void testPicker() {
        List<String> valuesBeforeScroll = areaPage.getValues();

        assertThat(valuesBeforeScroll.get(0)).isEqualTo("1");

        areaPage.scrollPickerDown();

        List<String> valuesAfterOneScrollDown = areaPage.getValues();

        assertThat(valuesAfterOneScrollDown).isNotEqualTo(valuesBeforeScroll);
        assertThat(valuesAfterOneScrollDown.get(1)).isEqualTo("1");

        areaPage.scrollPickerDown();

        List<String> valuesAfterTwoScrollsDown = areaPage.getValues();

        assertThat(valuesAfterTwoScrollsDown).isNotEqualTo(valuesAfterOneScrollDown);
        assertThat(valuesAfterTwoScrollsDown.get(2)).isEqualTo("1");

        areaPage.scrollPickerUp();

        List<String> valuesAfterOneScrollUp = areaPage.getValues();

        assertThat(valuesAfterOneScrollUp).isEqualTo(valuesAfterOneScrollDown);
    }
}
