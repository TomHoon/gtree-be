package org._4.gtree.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org._4.gtree.entity.BoardEntity;
import org._4.gtree.entity.FileInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

  private Long bno;

  private String title;
  private String content;
  private String category;

  private LocalDate createdAt;

  private LocalDate modifiedAt;

  private Boolean isDel;

  private List<String> files = new ArrayList<>();
  private List<FileInfo> files = new ArrayList<>();

  public BoardDTO(BoardEntity e) {
    this.bno = e.getBno();
    this.title = e.getTitle();
    this.category = e.getCategory();
    this.content = e.getContent();
    this.createdAt = e.getCreatedAt();
    this.modifiedAt = e.getModifiedAt();
    this.isDel = e.getIsDel();
    this.files = e.getFiles().stream().map(item -> item.getFilePath()).collect(Collectors.toList());
  }

  public BoardEntity toEntity() {
    BoardEntity e = BoardEntity.builder()
        .bno(this.bno)
        .title(this.title)
        .content(this.content)
        .category(this.category)
        .build();

    return e;
  }

}
