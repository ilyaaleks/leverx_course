package org.bstu.fit.controller;

import com.sun.mail.iap.Response;
import org.bstu.fit.dto.ImagePath;
import org.bstu.fit.service.ImageService;
import org.bstu.fit.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/image")
public class ImageController {
    private UserService userService;
    private ImageService imageService;

    public ImageController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<ImagePath> updatePhoto(@RequestParam(name = "file") MultipartFile file, @RequestParam("username") String username)
    {
        return ResponseEntity.ok(userService.updatePhoto(file,username));
    }
    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> getPhoto(@PathVariable() String name) throws IOException {
        return ResponseEntity.ok(imageService.getPhoto(name));
    }

}
