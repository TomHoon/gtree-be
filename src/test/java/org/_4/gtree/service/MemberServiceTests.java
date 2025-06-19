package org._4.gtree.service;

import org._4.gtree.dto.MemberDTO;
import org._4.gtree.entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTests {

  @Autowired
  private MemberService memberService;

  @Test
  public void 추가() {
    MemberEntity e = MemberEntity.builder()
        .id("test")
        .pw("1234")
        .nickname("tomhoon")
        .build();

    memberService.registerMember(new MemberDTO(e));
  }

  @Test
  public void 조회() {
    Long mno = 2L;

    MemberDTO dto = memberService.getMember(mno);

    log.info("dto : {} ", dto);
  }

  @Test
  public void 삭제() {
    Long mno = 1L;

    memberService.deleteMember(mno);
  }

  @Test
  public void 수정() {
    Long 수정할아이디 = 1L;
    MemberDTO dto = memberService.getMember(수정할아이디);
    dto.setNickname("닉네임변경");

    memberService.updateMember(dto);
  }

}
