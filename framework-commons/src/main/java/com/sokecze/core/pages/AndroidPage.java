package com.sokecze.core.pages;

import com.sokecze.core.actions.InitActions;
import com.sokecze.core.config.DriverSettings;
import io.qameta.allure.Step;
import com.sokecze.core.actions.DriverActions;

import static com.sokecze.core.entities.Messages.getActivityErrorMessage;
import static com.sokecze.core.logging.Log4j.logger;
import static org.assertj.core.api.Assertions.*;

public abstract class AndroidPage {
    private String activityName;

    public AndroidPage() {
        InitActions.initElements(this);
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Step
    public void waitUntilOpened(int timeoutInSeconds) {
        String currentActivity = "ACTIVITY_ERR";

        for (int i = 0; i < timeoutInSeconds; i++) {
            currentActivity = DriverActions.getCurrentActivity();

            if (currentActivity.equals(activityName)) return;
            DriverActions.wait(1000);
        }

        throw new AssertionError(getActivityErrorMessage(activityName, currentActivity));
    }

    @Step
    public void waitUntilOpened() {
        waitUntilOpened(DriverSettings.WAIT_TIMEOUT);
    }

    @Step("verifyPageIsOpened")
    public void verifyOpened() {
        logger.info("Verifying the page is opened (" + activityName + ")");

        assertThat(DriverActions.getCurrentActivity()).isEqualTo(activityName);
    }
}
