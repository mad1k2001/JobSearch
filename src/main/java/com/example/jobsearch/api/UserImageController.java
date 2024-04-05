package com.example.jobsearch.api;

import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class UserImageController {
    private final UserImageService userImageService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file, ImageDto imageDto) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload an image file");
        }

        String fileExtension = getFileExtension(file.getOriginalFilename());

        List<String> allowedExtensions = Arrays.asList("jpeg", "jpg");
        if (!allowedExtensions.contains(fileExtension.toLowerCase())) {
            return ResponseEntity.badRequest().body("Only JPEG images are allowed");
        }

        if (file.getSize() > 3 * 1024 * 1024) {
            return ResponseEntity.badRequest().body("Image size should be less than 3 MB");
        }

        userImageService.upload(imageDto);
        return ResponseEntity.ok().build();
    }

    private String getFileExtension(String fileName) {
        return StringUtils.getFilenameExtension(fileName);
    }
}
