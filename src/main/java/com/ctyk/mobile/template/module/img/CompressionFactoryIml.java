package com.ctyk.mobile.template.module.img;

import com.ctyk.mobile.template.enumation.ImageSize;
import com.ctyk.mobile.template.enumation.ImageSuffix;
import com.ctyk.mobile.template.exception.CompressionException;
import com.ctyk.mobile.template.exception.FolderNotExistValidFileException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;

/**
 * @author wei.yang on 2017/11/14
 */
public class CompressionFactoryIml implements CompressionFactory {

    @Override
    public void compressionImage(File src, ImageSize size, File dest) throws CompressionException, IOException {
        checkExist(dest);
        try {
            if (src.isFile() && src.isFile() && src.canRead() && src.canWrite()) {
                Thumbnails.of(src)
                        .size(getHeight(size), getWidth(size))
                        .toFile(dest);
            }
        } catch (IOException e) {
            throw new CompressionException();
        }
    }

    @Override
    public void compressionImage(File src, ImageSize size, String name) throws CompressionException, IOException {
        checkExist(new File(name));
        try {
            if (src.isFile() && src.isFile() && src.canRead() && src.canWrite()) {
                Thumbnails.of(src)
                        .size(getHeight(size), getWidth(size))
                        .toFile(name);
            }
        } catch (IOException e) {
            throw new CompressionException();
        }
    }

    @Override
    public void compressionBatch(File[] srcList, ImageSuffix suffix, ImageSize size, Rename renameType)
            throws CompressionException {
        try {
            Thumbnails.of(srcList)
                    .size(getHeight(size), getWidth(size))
                    .outputFormat(suffix.getValue())
                    .toFiles(renameType);
        } catch (IOException e) {
            throw new CompressionException();
        }
    }

    @Override
    public void compressionBatch(File folder, ImageSuffix suffix, ImageSize size, Rename renameType)
            throws CompressionException, FolderNotExistValidFileException {
        try {
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    Thumbnails.of(files)
                            .size(getHeight(size), getWidth(size))
                            .outputFormat(suffix.getValue())
                            .toFiles(renameType);
                } else {
                    throw new NullPointerException();
                }
            }
        } catch (IOException e) {
            throw new CompressionException();
        } catch (NullPointerException e) {
            throw new FolderNotExistValidFileException();
        }
    }

    @Override
    public void compressionBatch(File folder, ImageSuffix suffix, ImageSize size, String filePath, Rename renameType)
            throws CompressionException, FolderNotExistValidFileException {
        try {
            File dest = getSaveFile(filePath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    Thumbnails.of(files)
                            .size(getHeight(size), getWidth(size))
                            .outputFormat(suffix.getValue())
                            .toFiles(dest, renameType);
                } else {
                    throw new NullPointerException();
                }
            }
        } catch (IOException e) {
            throw new CompressionException();
        } catch (NullPointerException e) {
            throw new FolderNotExistValidFileException();
        }
    }


    /**
     * 获取文件保存目录，并清空
     *
     * @param object 文件
     * @return 目录
     * @throws IOException io异常
     */
    private File getSaveFile(Object object) throws IOException {
        File dest;
        if (object instanceof File) {
            dest = (File) object;
            if (dest.exists() && dest.isDirectory()) {
                cleanChild(dest);
                return dest;
            } else {
                throw new IOException();
            }
        } else if (object instanceof String) {
            dest = new File((String) object);
            cleanChild(dest);
            return dest;
        } else {
            throw new IOException();
        }
    }

    /**
     * 删除文件夹
     *
     * @param file 文件夹
     * @throws IOException 删除失败
     */
    private void clean(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        clean(file1);
                    } else {
                        boolean success = file1.delete();
                        if (!success) {
                            throw new IOException();
                        }
                    }
                }
            }
        }
        boolean success = file.delete();
        if (!success) {
            throw new IOException();
        }
    }

    /**
     * 删除子文件
     *
     * @param parent 文件
     * @throws IOException 删除异常
     */
    private void cleanChild(File parent) throws IOException {
        File[] files = parent.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                clean(file);
            }
        }
    }

    /**
     * 如果存在，则先删除
     *
     * @param file 文件
     * @throws IOException 删除失败
     */
    private void checkExist(File file) throws IOException {
        if (file.exists()) {
            boolean success = file.delete();
            if (!success) {
                throw new IOException();
            }
        }
    }

    /**
     * 获取宽度
     *
     * @param size 尺寸信息
     * @return 宽度
     */
    @org.jetbrains.annotations.Contract(pure = true)
    private int getWidth(ImageSize size) {
        return size.getValue()[1];
    }

    /**
     * 获取长度信息
     *
     * @param size 尺寸信息
     * @return 长度
     */
    @org.jetbrains.annotations.Contract(pure = true)
    private int getHeight(ImageSize size) {
        return size.getValue()[0];
    }
}
