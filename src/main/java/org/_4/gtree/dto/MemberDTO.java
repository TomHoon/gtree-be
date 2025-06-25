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
  private String userId;
  private String pw;
  private int degree;
  private String nickname;
  private Boolean isSocial;

  public MemberDTO(MemberEntity e) {
    this.mno = e.getMno();
    this.userId = e.getUserId();
    this.degree = e.getDegree();
    this.nickname = e.getNickname();
    this.isSocial = e.getIsSocial();
  }

  public MemberEntity toEntity() {
    MemberEntity e = MemberEntity.builder()
        .userId(this.userId)
        .pw(this.pw)
        .nickname(this.nickname)
        .degree(this.degree)
        .isSocial(this.isSocial).build();

    return e;
  }
}
