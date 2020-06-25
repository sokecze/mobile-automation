package com.sokecze.iosapp;

import com.sokecze.core.TestListener;
import com.sokecze.core.actions.InitActions;
import com.sokecze.core.config.DriverSettings;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {

    @BeforeSuite
    public void suiteSetUp() {
        startAppium();

        InitActions.initPages(App.class);
    }

    @AfterSuite
    public void suiteTearDown() {
        DriverSettings.getDriver().quit();

        stopAppium();
    }

    private static AppiumDriverLocalService service;

    private void startAppium() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    private void stopAppium() {
        service.stop();
    }
}
