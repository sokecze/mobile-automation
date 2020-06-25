package com.sokecze.unitconverter.entities;

public enum MenuOptions {
    AREA("Area"), TEMPERATURE("Temperature"), VOLUME("Volume"), TIME("Time"),
    DIGITAL_IMAGING("Digital Imaging");

    private String value;

    MenuOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
