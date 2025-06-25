package org._4.gtree.dto;

import java.time.LocalDate;

import org._4.gtree.entity.BoardEntity;

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

  private LocalDate createdAt;

  private LocalDate modifiedAt;

  private Boolean isDel;

  public BoardDTO(BoardEntity e) {
    this.bno = e.getBno();
    this.content = e.getContent();
    this.createdAt = e.getCreatedAt();
    this.modifiedAt = e.getModifiedAt();
    this.isDel = e.getIsDel();
  }

  public BoardEntity toEntity() {
    BoardEntity e = BoardEntity.builder()
        .bno(this.bno)
        .title(this.title)
        .content(this.content)
        .build();

    return e;
  }

}
