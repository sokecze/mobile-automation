package com.sokecze.core;

import com.sokecze.core.config.DriverSettings;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static com.sokecze.core.logging.Log4j.logger;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);

        logger.info("\n" +
                "  _______          _      _____  _                _             _ \n" +
                " |__   __|        | |    / ____|| |              | |           | |\n" +
                "    | |  ___  ___ | |_  | (___  | |_  __ _  _ __ | |_  ___   __| |\n" +
                "    | | / _ \\/ __|| __|  \\___ \\ | __|/ _` || '__|| __|/ _ \\ / _` |\n" +
                "    | ||  __/\\__ \\| |_   ____) || |_| (_| || |   | |_|  __/| (_| |\n" +
                "    |_| \\___||___/ \\__| |_____/  \\__|\\__,_||_|    \\__|\\___| \\__,_|");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);

        logger.info("\n" +
                "  _______          _     _____                            _ \n" +
                " |__   __|        | |   |  __ \\                          | |\n" +
                "    | |  ___  ___ | |_  | |__) |__ _  ___  ___   ___   __| |\n" +
                "    | | / _ \\/ __|| __| |  ___// _` |/ __|/ __| / _ \\ / _` |\n" +
                "    | ||  __/\\__ \\| |_  | |   | (_| |\\__ \\\\__ \\|  __/| (_| |\n" +
                "    |_| \\___||___/ \\__| |_|    \\__,_||___/|___/ \\___| \\__,_|");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);

        logger.info("\n" +
                "  _______          _     ______      _  _            _ \n" +
                " |__   __|        | |   |  ____|    (_)| |          | |\n" +
                "    | |  ___  ___ | |_  | |__  __ _  _ | |  ___   __| |\n" +
                "    | | / _ \\/ __|| __| |  __|/ _` || || | / _ \\ / _` |\n" +
                "    | ||  __/\\__ \\| |_  | |  | (_| || || ||  __/| (_| |\n" +
                "    |_| \\___||___/ \\__| |_|   \\__,_||_||_| \\___| \\__,_|");
        saveScreenshot(DriverSettings.getDriver().getScreenshotAs(OutputType.BYTES));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}
