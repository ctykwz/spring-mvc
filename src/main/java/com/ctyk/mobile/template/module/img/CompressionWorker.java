package com.ctyk.mobile.template.module.img;

import com.ctyk.mobile.template.enumation.ImageSize;
import com.ctyk.mobile.template.enumation.ImageSuffix;
import com.ctyk.mobile.template.exception.CompressionException;
import com.ctyk.mobile.template.exception.FolderNotExistValidFileException;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;

/**
 * @author wei.yang on 2017/11/14
 */
public class CompressionWorker {

    private CompressionFactoryIml factoryIml;

    private ImageSuffix saveSuffix;

    private ImageSize size;

    void setSaveSuffix(ImageSuffix saveSuffix) {
        this.saveSuffix = saveSuffix;
    }

    public void setSize(ImageSize size) {
        this.size = size;
    }

    void setFactoryIml(CompressionFactoryIml factoryIml) {
        this.factoryIml = factoryIml;
    }

    /**
     * 图片压缩
     *
     * @param src  源文件
     * @param dest 目的文件
     * @throws IOException          删除失败
     * @throws CompressionException 压缩异常
     */
    public void compressionImage(File src, File dest) throws CompressionException, IOException {
        factoryIml.compressionImage(src, size, dest);
    }

    /**
     * 图片压缩
     *
     * @param src  源文件
     * @param name 命名方式
     * @throws IOException          删除失败
     * @throws CompressionException 压缩异常
     */
    public void compressionImage(File src, String name) throws CompressionException, IOException {
        factoryIml.compressionImage(src, size, name);
    }

    /**
     * 批量压缩
     *
     * @param srcList    文件
     * @param renameType 命名方式
     */
    public void compressionBatch(File[] srcList, Rename renameType) throws CompressionException {
        factoryIml.compressionBatch(srcList, saveSuffix, size, renameType);
    }

    /**
     * 批量压缩
     *
     * @param folder     文件夹
     * @param renameType 命名方式
     */
    public void compressionBatch(File folder, Rename renameType) throws CompressionException, FolderNotExistValidFileException {
        factoryIml.compressionBatch(folder, saveSuffix, size, renameType);
    }

    /**
     * 批量压缩
     *
     * @param folder     文件夹
     * @param renameType 命名方式
     * @param filePath   保存路径
     */
    public void compressionBatch(File folder, String filePath, Rename renameType) throws CompressionException,
            FolderNotExistValidFileException {
        factoryIml.compressionBatch(folder, saveSuffix, size, filePath, renameType);
    }
}
