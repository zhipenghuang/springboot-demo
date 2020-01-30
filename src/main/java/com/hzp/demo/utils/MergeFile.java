package com.hzp.demo.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 * <p>
 * 这里在进行合并时，如果遇到失败或者异常情况，那么需要删除临时文件，并且不能删除之前上传的文件
 */
public class MergeFile {
    public static void main(String[] args) throws IOException {
        String mergeFilesDir = "/Users/mac/Desktop/shard_temp";
        File dirFile = new File(mergeFilesDir);
        if (!dirFile.exists()) {
            throw new RuntimeException("文件不存在！");
        }
        String targetFileName = "1-2.mov";
        //分片上传的文件已经位于同一个文件夹下，方便寻找和遍历
        String[] fileNames = dirFile.list();

        List<SequentialFile> files = new ArrayList<>();
        for (String fileName : fileNames) {
            int index = Integer.parseInt(fileName.substring(fileName.lastIndexOf(".part") + 5));
            files.add(new SequentialFile(index, fileName));
        }
        Collections.sort(files);

        File targetFile = new File(mergeFilesDir, targetFileName);
        RandomAccessFile writeFile = new RandomAccessFile(targetFile, "rw");

        int position = 0;
        for (SequentialFile fileName : files) {
            File sourceFile = new File(mergeFilesDir, fileName.getFileName());
            RandomAccessFile readFile = new RandomAccessFile(sourceFile, "rw");
            int chunksize = 100 * 1024;
            byte[] buf = new byte[chunksize];
            writeFile.seek(position);
            int byteCount = 0;
            while ((byteCount = readFile.read(buf)) != -1) {
                if (byteCount != chunksize) {
                    byte[] tempBytes = new byte[byteCount];
                    System.arraycopy(buf, 0, tempBytes, 0, byteCount);
                    buf = tempBytes;
                }
                writeFile.write(buf);
                position = position + byteCount;
            }
            readFile.close();
            FileUtils.deleteQuietly(sourceFile);
        }
        writeFile.close();
    }
}