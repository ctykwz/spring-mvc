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
public interface CompressionFactory {

    /**
     * 压缩，保存为指定名称
     *
     * @param src  源文件
     * @param dest 目的文件
     * @param size 尺寸信息
     * @throws IOException          删除失败
     * @throws CompressionException 压缩异常
     */
    void compressionImage(File src, ImageSize size, File dest) throws CompressionException, IOException;

    /**
     * 压缩，保存为指定名称
     *
     * @param src  源文件
     * @param name 文件的命名方式
     * @param size 尺寸信息
     * @throws IOException          删除失败
     * @throws CompressionException 压缩异常
     */
    void compressionImage(File src, ImageSize size, String name) throws CompressionException, IOException;

    /**
     * 批量压缩
     *
     * @param srcList    源文件
     * @param suffix     保存的类型
     * @param size       尺寸
     * @param renameType 保存方式
     * @throws CompressionException 压缩异常
     */
    void compressionBatch(File[] srcList, ImageSuffix suffix, ImageSize size, Rename renameType)
            throws CompressionException;

    /**
     * 批量压缩
     *
     * @param folder     源文件目录
     * @param suffix     保存的类型
     * @param size       尺寸
     * @param renameType 保存方式
     * @throws CompressionException             压缩异常
     * @throws FolderNotExistValidFileException 文件夹不存在有效的图片
     */
    void compressionBatch(File folder, ImageSuffix suffix, ImageSize size, Rename renameType)
            throws CompressionException, FolderNotExistValidFileException;

    /**
     * 批量压缩
     *
     * @param folder     源文件目录
     * @param suffix     保存的类型
     * @param size       尺寸
     * @param filePath   保存路径
     * @param renameType 命名类型
     * @throws CompressionException             压缩异常
     * @throws FolderNotExistValidFileException 文件夹不存在有效的图片
     */
    void compressionBatch(File folder, ImageSuffix suffix, ImageSize size, String filePath, Rename renameType)
            throws CompressionException, FolderNotExistValidFileException;
}
