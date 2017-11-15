package com.ctyk.mobile.template.module.image;

import com.ctyk.mobile.template.enumation.ImageSize;

/**
 * @author Ctyk on 2017/11/15
 */
public class CompressionBuilder {

    private Object hints;

    private ImageSize imageSize;

    private float quality;

    public CompressionBuilder setHints(Object hints) {
        this.hints = hints;
        return this;
    }

    public CompressionBuilder setQuality(float quality) {
        this.quality = quality;
        return this;
    }

    public CompressionBuilder setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
        return this;
    }

    /**
     * builder
     *
     * @return worker
     */
    public ImageCompressionWorker builder() {
        ImageCompressionWorker worker = new ImageCompressionWorker();
        worker.setImageCompressionIml(new ImageCompressionIml());
        worker.setImageSize(this.imageSize);
        worker.setQuality(this.quality);
        worker.setHints(this.hints);
        return worker;
    }
}
