package com.ctyk.mobile.template.compression;

import com.ctyk.mobile.template.enumation.ImageSize;
import com.ctyk.mobile.template.enumation.ImageSuffix;
import com.ctyk.mobile.template.exception.CompressionException;
import com.ctyk.mobile.template.exception.FolderNotExistValidFileException;
import com.ctyk.mobile.template.module.image.CompressionBuilder;
import com.ctyk.mobile.template.module.image.ImageCompressionWorker;
import com.ctyk.mobile.template.module.img.CompressionWorker;
import com.ctyk.mobile.template.module.img.ImageCompressionBuilder;
import net.coobird.thumbnailator.name.Rename;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author wei.yang on 2017/11/14
 */
public class CompressionTest {

    @Test
    public void test() throws CompressionException, IOException, FolderNotExistValidFileException {
        CompressionWorker worker = new ImageCompressionBuilder()
                .setImageSize(ImageSize.HVGA)
                .setImageSuffix(ImageSuffix.JPG)
                .builder();
        worker.compressionImage(new File("E:\\图片\\壁纸\\03205_lowereastforkfalls_3840x2400.jpg"),
                "1.jpg");
        worker.compressionImage(new File("E:\\图片\\壁纸\\03205_lowereastforkfalls_3840x2400.jpg"),
                new File("2.jpg"));
        worker.compressionBatch(new File("E:\\图片\\test"), Rename.PREFIX_DOT_THUMBNAIL);
        worker.compressionBatch(new File("E:\\图片\\test"), "E:\\图片\\test1",
                Rename.PREFIX_DOT_THUMBNAIL);
    }

    @Test
    public void compressWithJDK() throws IOException {
        ImageCompressionWorker worker = new CompressionBuilder()
                .setHints(RenderingHints.VALUE_INTERPOLATION_BILINEAR)
                .setImageSize(ImageSize.X_SQUARE)
                .setQuality(0.85f)
                .builder();
        worker.compressionImage(new File("E:\\图片\\壁纸\\03205_lowereastforkfalls_3840x2400.jpg"),
                new File("E:\\图片\\壁纸\\03205_lowereastforkfalls_3840x24001.jpg"));
    }
}
