package org._4.gtree.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FileUtil {

  private static final String UPLOAD_DIR = "uploads/";

  public String saveFile(MultipartFile file) {
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

    log.info(">>>> filePath {} ", filePath.toString());

    return filePath.toString();
  }
}
