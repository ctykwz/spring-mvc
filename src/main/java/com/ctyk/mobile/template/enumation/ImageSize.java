package com.ctyk.mobile.template.enumation;

/**
 * @author wei.yang on 2017/11/14
 */
public enum ImageSize {

    /**
     * 50*50
     */
    LOW_SQUARE(new int[]{50, 50}),

    /**
     * 100*100
     */
    MID_SQUARE(new int[]{100, 100}),

    /**
     * 300*300
     */
    HIGH_SQUARE(new int[]{300, 300}),

    /**
     * 500*500
     */
    X_SQUARE(new int[]{500, 500}),

    /**
     * 1000*1000
     */
    XX_SQUARE(new int[]{1000, 1000}),

    /**
     * 480*320
     */
    HVGA(new int[]{480, 320}),

    /**
     * 800*480
     */
    WVGA(new int[]{800, 480}),

    /**
     * 960*540
     */
    QHD(new int[]{960, 540}),

    /**
     * 1280*720
     */
    XHDPI(new int[]{1280, 720}),

    /**
     * 1920*1080
     */
    XXHDPI(new int[]{1920, 1080});

    private int[] value;

    ImageSize(int[] value) {
        this.value = value;
    }

    public int[] getValue() {
        return value;
    }
}
