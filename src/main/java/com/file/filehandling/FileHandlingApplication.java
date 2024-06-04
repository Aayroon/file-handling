package com.file.filehandling;

import com.file.filehandling.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@SpringBootApplication
public class FileHandlingApplication {

    @Autowired
    private FileService fileService;


    @PostMapping("/file")
    public ResponseEntity<?> uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
        String uploadFile = fileService.uploadFile(file);

        return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] fileData = fileService.downloadFile(fileName);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.valueOf("image/png")).body(fileData);
    }


    public static void main(String[] args) {
        SpringApplication.run(FileHandlingApplication.class, args);
    }

}
