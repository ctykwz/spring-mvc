package com.ctyk.mobile.template.module.img;

import com.ctyk.mobile.template.enumation.ImageSize;
import com.ctyk.mobile.template.enumation.ImageSuffix;

/**
 * @author wei.yang on 2017/11/14
 */
public class ImageCompressionBuilder {

    private ImageSize imageSize;

    private ImageSuffix imageSuffix;

    /**
     * 设置文件的保存尺寸
     *
     * @param imageSize 尺寸
     * @return builder
     */
    public ImageCompressionBuilder setImageSize(ImageSize imageSize) {
        this.imageSize = imageSize;
        return this;
    }

    /**
     * 设置压缩的文件保存后缀
     *
     * @param imageSuffix 文件后缀
     * @return builder
     */
    public ImageCompressionBuilder setImageSuffix(ImageSuffix imageSuffix) {
        this.imageSuffix = imageSuffix;
        return this;
    }

    /**
     * 构建压缩
     *
     * @return 压缩类
     */
    public CompressionWorker builder() {
        CompressionWorker worker = new CompressionWorker();
        worker.setFactoryIml(new CompressionFactoryIml());
        worker.setSaveSuffix(this.imageSuffix);
        worker.setSize(this.imageSize);
        return worker;
    }

}
