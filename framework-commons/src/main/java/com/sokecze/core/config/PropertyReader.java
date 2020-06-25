package com.sokecze.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final String PROPERTIES_PATH = "src/main/resources/config.properties";

    public static String getProperty(String property) {
        try (InputStream input = new FileInputStream(PROPERTIES_PATH)) {

            Properties properties = new Properties();

            properties.load(input);

            return properties.getProperty(property);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        throw new RuntimeException("No property " + property + " found");
    }
}
