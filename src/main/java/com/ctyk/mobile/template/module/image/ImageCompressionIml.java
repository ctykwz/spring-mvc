package com.ctyk.mobile.template.module.image;

import com.ctyk.mobile.template.enumation.ImageSize;
import com.ctyk.mobile.template.enumation.ImageSuffix;
import org.jetbrains.annotations.Contract;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Ctyk on 2017/11/15
 */
public class ImageCompressionIml implements ImageCompression {

    @Override
    public void compression(ImageSize size, File src, File saveFile, Object hints, float quality)
            throws IOException {
        BufferedImage srcImage = ImageIO.read(src);
        BufferedImage scale = getScale(srcImage, size, hints);
        writeImage(scale, saveFile, quality);
    }

    /**
     * @param src       源文件
     * @param imageSize 尺寸
     * @param hints     着色方式
     * @return 图片
     */
    private BufferedImage getScale(BufferedImage src, ImageSize imageSize, Object hints) {
        int type = src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = src;
        int height = getHeight(imageSize);
        int width = getWidth(imageSize);
        BufferedImage temp = new BufferedImage(width, height, type);
        Graphics2D graphics2D = temp.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hints);
        graphics2D.drawImage(ret, 0, 0, width, height, null);
        graphics2D.dispose();
        ret = temp;
        return ret;
    }

    /**
     * 写、压缩文件
     *
     * @param bufferedImage 图片
     * @param saveFile      输出流
     * @param quality       压缩率
     */
    private void writeImage(BufferedImage bufferedImage, File saveFile, float quality)
            throws IOException {
        Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(ImageSuffix.JPG.getValue());
        ImageWriter imageWriter = iterator.next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(quality);
        ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(new FileOutputStream(saveFile));
        imageWriter.setOutput(imageOutputStream);
        IIOImage iioimage = new IIOImage(bufferedImage, null, null);
        imageWriter.write(null, iioimage, imageWriteParam);
        imageOutputStream.flush();
    }

    /**
     * 获取长度
     *
     * @param imageSize 尺寸信息
     * @return 长度
     */
    @Contract(pure = true)
    private int getHeight(ImageSize imageSize) {
        return imageSize.getValue()[0];
    }

    /**
     * 获取宽度信息
     *
     * @param imageSize 尺寸信息
     * @return 宽度
     */
    @Contract(pure = true)
    private int getWidth(ImageSize imageSize) {
        return imageSize.getValue()[1];
    }
}
