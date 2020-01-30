package com.hzp.demo.controller;

import com.hzp.demo.dto.UploadREQ;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController("")
public class UplodController {

    private static String fileUploadTempDir = "/Users/mac/Desktop/shard_temp";

    @PostMapping(value = "/upload")
    public String test(MultipartFile file, UploadREQ req) throws IOException {
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        System.out.println(req.getChunkTotal());
        File newFile = new File(fileUploadTempDir + originalFilename);
        file.transferTo(newFile);
        return "success";
    }
}

