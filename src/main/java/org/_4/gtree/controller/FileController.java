package org._4.gtree.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

  private static final String UPLOAD_DIR = "uploads/";

  @PostConstruct
  public void init() {
    File directory = new File(UPLOAD_DIR);
    if (!directory.exists()) {
      boolean created = directory.mkdirs();
      if (created) {
      } else {
      }
    } else {
    }
  }

  @PostMapping("/upload")
  public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file) {

    Path uploadPath = Paths.get(UPLOAD_DIR);

    String filename = file.getOriginalFilename();
    Path filePath = uploadPath.resolve(filename);

    try {
      file.transferTo(filePath);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok("okay");
  }

}
