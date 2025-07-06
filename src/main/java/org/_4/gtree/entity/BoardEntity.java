package org._4.gtree.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org._4.gtree.dto.BoardDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@EntityListeners(AuditingEntityListener.class)
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

  @ManyToOne
  @JoinColumn(name = "member_mno")  // Foreign key column in the Post table
  private MemberEntity memberEntity;

  @ElementCollection
  @CollectionTable(
    name = "tbl_board_file", 
    joinColumns = @JoinColumn(name = "board_bno")
  )
  private List<FileInfo> files = new ArrayList<>();

  public void setIsDel(Boolean isDel) {
    this.isDel = isDel;
  }

  public BoardDTO toDTO() {
    return new BoardDTO(this);
  }

}
