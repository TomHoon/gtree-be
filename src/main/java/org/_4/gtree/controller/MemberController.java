package org._4.gtree.controller;

import org._4.gtree.dto.MemberDTO;
import org._4.gtree.service.MemberService;
import org._4.gtree.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/v1/member")
public class MemberController {
  private final MemberService memberService;

  @GetMapping("/get/{mno}")
  public ResponseEntity<ApiResponse<MemberDTO>> getMember(@PathVariable Long mno) {
    MemberDTO dto = memberService.getMember(mno);

    return ResponseEntity.ok(ApiResponse.success(dto));
  }
}
