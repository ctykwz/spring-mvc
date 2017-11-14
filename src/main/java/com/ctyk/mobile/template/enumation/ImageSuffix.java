package com.ctyk.mobile.template.enumation;

/**
 * @author wei.yang on 2017/11/14
 */
public enum ImageSuffix {
    /**
     * jpg
     */
    JPG("jpg"),

    /**
     * png
     */
    PNG("png");

    private String value;

    ImageSuffix(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}