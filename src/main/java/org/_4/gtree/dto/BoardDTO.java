package org._4.gtree.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org._4.gtree.entity.BoardEntity;
import org._4.gtree.entity.FileInfo;
import org._4.gtree.entity.MemberEntity;

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

  // private List<String> files = new ArrayList<>();
  private List<FileInfo> files = new ArrayList<>();

  private String writer;

  public BoardDTO(BoardEntity e) {
    this.bno = e.getBno();
    this.title = e.getTitle();
    this.category = e.getCategory();
    this.content = e.getContent();
    this.createdAt = e.getCreatedAt();
    this.modifiedAt = e.getModifiedAt();
    this.isDel = e.getIsDel();

    if (e.getMemberEntity() != null) {
      this.writer = e.getMemberEntity().getUserId();
    }
    // this.files = e.getFiles().stream().map(item -> item.getFilePath()).collect(Collectors.toList());
  }

  public BoardEntity toEntity(MemberEntity me) {
    BoardEntity e = BoardEntity.builder()
        .bno(this.bno)
        .title(this.title)
        .content(this.content)
        .category(this.category)
        .memberEntity(me)
        .build();

    return e;
  }

}
