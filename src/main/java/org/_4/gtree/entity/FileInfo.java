package org._4.gtree.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class FileInfo {
  private String fileName;
  private String filePath;

  public FileInfo(String fileName, String filePath) {
    this.fileName = fileName;
    this.filePath = filePath;
  }
}
