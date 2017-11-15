package com.ctyk.mobile.template.module.image;

import com.ctyk.mobile.template.enumation.ImageSize;

import java.io.File;
import java.io.IOException;

/**
 * @author Ctyk on 2017/11/15
 */
public class ImageCompressionWorker {

    private ImageCompressionIml imageCompressionIml;

    private ImageSize imageSize;

    private Object hints;

    private float quality;

    void setImageCompressionIml(ImageCompressionIml imageCompressionIml) {
        this.imageCompressionIml = imageCompressionIml;
    }

    void setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
    }

    void setHints(Object hints) {
        this.hints = hints;
    }

    void setQuality(float quality) {
        this.quality = quality;
    }

    /**
     * 压缩
     *
     * @param src      源文件
     * @param saveFile 保存地址
     */
    public void compressionImage(File src, File saveFile) throws IOException {
        imageCompressionIml.compression(imageSize, src, saveFile, hints, quality);
    }
}
