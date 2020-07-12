package org.bstu.fit.service.impl;

import org.bstu.fit.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class    ImageServiceImpl implements ImageService {
    @Value("${upload.path}")
    private String baseDir;
    @Override
    public byte[] getPhoto(String name) {
        File imgPath= new File(baseDir+"/"+name);
        byte[] image=null;
        try {
             image= Files.readAllBytes(imgPath.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
