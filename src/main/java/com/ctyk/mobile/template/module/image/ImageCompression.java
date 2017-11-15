package com.ctyk.mobile.template.module.image;

import com.ctyk.mobile.template.enumation.ImageSize;

import java.io.File;
import java.io.IOException;

/**
 * @author Ctyk on 2017/11/15
 */
public interface ImageCompression {

    /**
     * 图片压缩
     *
     * @param size     尺寸
     * @param src      源文件
     * @param saveFile 保存地址
     * @param hints    着色方式
     * @param quality  质量（压缩率）
     * @throws IOException 写异常
     */
    void compression(ImageSize size, File src, File saveFile, Object hints, float quality) throws IOException;
}
