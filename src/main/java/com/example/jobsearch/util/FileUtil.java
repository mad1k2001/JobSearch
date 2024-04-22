package com.example.jobsearch.util;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {

    private static final String UPLOAD_DIR = "data/";

    @SneakyThrows
    public static String saveFile(MultipartFile file, String subDir) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOAD_DIR + subDir);
        Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return resultFileName;
    }

    public ResponseEntity<InputStreamResource> getOutputFile(String fileName, String subDir) {
        try {
            Path path = Paths.get(UPLOAD_DIR + subDir + "/" + fileName);
            InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
            MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(path));
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(new InputStreamResource(resource.getInputStream()));
        } catch (IOException e) {
            log.error("No file found:", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> getOutputFile(String filename, String subDir, MediaType mediaType){
        try {
            byte[] image = Files.readAllBytes(Paths.get(UPLOAD_DIR+subDir+"/"+filename));
            Resource resource = new ByteArrayResource(image);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+filename+"\"")
                    .contentLength(resource.contentLength())
                    .contentType(mediaType)
                    .body(resource);
        }catch (IOException e){
            log.error("File not found: ",e );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image not found");
        }
    }
}