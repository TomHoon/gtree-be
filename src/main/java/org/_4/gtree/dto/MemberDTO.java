package org._4.gtree.dto;

import org._4.gtree.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {

  private Long mno;
  private String id;
  private String pw;
  private int degree;
  private String nickname;
  private Boolean isSocial;

  public MemberDTO(MemberEntity e) {
    this.mno = e.getMno();
    this.id = e.getId();
    this.degree = e.getDegree();
    this.nickname = e.getNickname();
    this.isSocial = e.getIsSocial();
  }

  public MemberEntity toEntity() {
    MemberEntity e = MemberEntity.builder()
        .id(this.id)
        .pw(this.pw)
        .nickname(this.nickname)
        .degree(this.degree)
        .isSocial(this.isSocial).build();

    return e;
  }
}
