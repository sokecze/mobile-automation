package com.sokecze.core.actions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static com.sokecze.core.config.DriverSettings.*;

public class DriverActions {

    public static String getCurrentActivity() {
        return ((AndroidDriver<AndroidElement>) getDriver()).currentActivity();
    }

    public static void startMainActivity() {
        ((AndroidDriver<AndroidElement>) getDriver()).startActivity(new Activity(getAppPackage(), getMainActivity()));
    }

    public static void clickOnPoint(double multiplierX, double multiplierY) {
        Dimension size = getDriver().manage().window().getSize();
        int x = (int) (size.width * multiplierX);
        int y = (int) (size.height * multiplierY);

        new TouchAction(getDriver())
                .tap(point(x, y))
                .perform();
    }

    public static void swipe(Point start, Point end) {
        new TouchAction(getDriver())
                .longPress(point(start))
                .moveTo(point(end))
                .release()
                .perform();
    }

    public static void swipeDown() {
        Dimension size = getDriver().manage().window().getSize();
        int startY = (int) (size.height * 0.80);
        int endY = (int) (size.height * 0.20);
        int startX = size.width / 2;

        new TouchAction(getDriver())
                .longPress(point(startX, startY))
                .moveTo(point(startX, endY))
                .release()
                .perform();
    }

    public static void swipeRight() {
        Dimension size = getDriver().manage().window().getSize();
        int startX = (int) (size.width * 0.10);
        int endX = (int) (size.width * 0.70);
        int startY = size.height / 2;

        new TouchAction(getDriver())
                .longPress(point(startX, startY))
                .moveTo(point(endX, startY))
                .release()
                .perform();
    }

    public static void goBack() {
        getDriver().navigate().back();

//        ((AndroidDriver<AndroidElement>) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) { }
    }
}
