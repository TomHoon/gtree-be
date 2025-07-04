package org._4.gtree.entity;

import java.time.LocalDate;

import org._4.gtree.dto.BoardDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "tbl_board")
public class BoardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  private String title;

  @Lob
  private String content;
  private String category;

  @CreatedDate
  private LocalDate createdAt;

  @LastModifiedDate
  private LocalDate modifiedAt;

  @Builder.Default
  private Boolean isDel = false;

  public void setIsDel(Boolean isDel) {
    this.isDel = isDel;
  }

  public BoardDTO toDTO() {
    return new BoardDTO(this);
  }

}
