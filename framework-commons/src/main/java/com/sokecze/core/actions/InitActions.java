package com.sokecze.core.actions;

import com.sokecze.core.annotations.Element;
import com.sokecze.core.annotations.Page;
import com.sokecze.core.annotations.Section;
import com.sokecze.core.elements.IElement;
import com.sokecze.core.pages.AndroidPage;
import io.appium.java_client.MobileBy;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static com.sokecze.core.logging.Log4j.logger;

public class InitActions {
    public static void initPages(Class<?> appClass) {
        Field[] fields = appClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Page.class)) {
                field.setAccessible(true);
                try {
                    Page pageAnnotation = field.getAnnotation(Page.class);
                    Object page = field.getType().newInstance();

                    if (!pageAnnotation.activityName().isEmpty()) ((AndroidPage) page).setActivityName(pageAnnotation.activityName());

                    field.set(null, page);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void initElements(Class<?> appClass) {
        initElements(appClass, null);
    }

    public static void initElements(Object handler) {
        initElements(null, handler);
    }

    private static void initElements(Class<?> appClass, @Nullable Object handler) {
        Field[] fields;
        Class<?> clazz;

        if (handler != null) {
            clazz = handler.getClass();
            fields = clazz.getDeclaredFields();
        }
        else {
            fields = appClass.getDeclaredFields();
            clazz = appClass;
        }

        while (clazz != Object.class) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Element.class)) {
                    field.setAccessible(true);
                    try {
                        Element locator = field.getAnnotation(Element.class);
                        Constructor<?> constructor = field.getType().getDeclaredConstructor();
                        constructor.setAccessible(true);

                        IElement element = (IElement) constructor.newInstance();

                        if (!locator.xpath().isEmpty()) element.setLocator(MobileBy.xpath(locator.xpath()));
                        else if (!locator.id().isEmpty()) element.setLocator(MobileBy.id(locator.id()));
                        else if (!locator.uiAutomator().isEmpty())
                            element.setLocator(MobileBy.AndroidUIAutomator(locator.uiAutomator().replaceAll("'", "\"")));
                        else if (!locator.accessibilityId().isEmpty())
                            element.setLocator(MobileBy.AccessibilityId(locator.accessibilityId()));

                        field.set(handler, element);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                        throw new RuntimeException(e);
                    }
                }

                if (field.isAnnotationPresent(Section.class)) {
                    try {
                        field.setAccessible(true);
                        Object object = field.getType().newInstance();
                        field.set(handler, object);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
            fields = clazz.getDeclaredFields();
        }
    }
}
