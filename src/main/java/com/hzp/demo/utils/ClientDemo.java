package com.hzp.demo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ClientDemo {

    public static void main(String[] args) throws IOException {
        String TARGET_UPLOAD_URL = "http://localhost:8080/upload";
        String absoluteFilePath = "/Users/mac/Downloads/1-2.mov";
        int chunkSize = 100 * 1024;
        File sourceFile = new File("/Users/mac/Downloads/1-2.mov");
        long fileLength = sourceFile.length();
        System.out.println("文件大小为：" + fileLength);

        RandomAccessFile readFile = new RandomAccessFile(sourceFile, "rw");
        long chunkTotal = fileLength / chunkSize;
        if (fileLength % chunkSize != 0) {
            chunkTotal++;
        }
        byte[] buf = new byte[chunkSize];
        int chunkCount = 0;
        int currentChunkSize = -1;
        while ((currentChunkSize = readFile.read(buf)) != -1) {
            chunkCount++;
            String targetFile = absoluteFilePath + ".part" + chunkCount;
            RandomAccessFile writeFile = new RandomAccessFile(new File(targetFile), "rw");
            writeFile.write(buf, 0, currentChunkSize);
            writeFile.close();
            FileBody fileBody = new FileBody(new File(targetFile));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("file", fileBody);
            FormBodyPart formBodyPart = FormBodyPartBuilder.create().setName("chunkTotal").setBody(new StringBody(chunkTotal + "", ContentType.DEFAULT_BINARY)).build();
            builder.addPart(formBodyPart);
            HttpEntity reqEntity = builder.build();
            HttpPost httpPost = new HttpPost(TARGET_UPLOAD_URL);
            httpPost.setEntity(reqEntity);
            CloseableHttpClient httpClient = HttpClients.createDefault();//client实例初始化
            httpClient.execute(httpPost);
        }
    }
}
