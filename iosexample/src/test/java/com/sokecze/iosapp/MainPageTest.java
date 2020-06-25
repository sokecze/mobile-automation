package com.sokecze.iosapp;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.sokecze.iosapp.App.mainPage;

@Test(description = "Main Page")
public class MainPageTest extends BaseTest {

    @Test(description = "Check Empty Name Toast")
    @Description("Full test description")
    public void emptyNameToastTest() {
        mainPage.clickAlertViews();
    }
}
