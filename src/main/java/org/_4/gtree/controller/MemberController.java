package org._4.gtree.controller;

import java.util.Map;

import org._4.gtree.dto.MemberDTO;
import org._4.gtree.service.MemberService;
import org._4.gtree.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/v1/member")
public class MemberController {
  private final MemberService memberService;

  @PostMapping("/login")
  @CrossOrigin(origins = "http://127.0.0.1:5500")
  public ResponseEntity<ApiResponse<Map<String, Boolean>>> login(@RequestBody MemberDTO dto) {
    Boolean isMember = memberService.login(dto);
    return ResponseEntity.ok(ApiResponse.success(Map.of("로그인처리", isMember)));

  }

  @GetMapping("/get/{mno}")
  public ResponseEntity<ApiResponse<MemberDTO>> getMember(@PathVariable Long mno) {
    MemberDTO dto = memberService.getMember(mno);

    return ResponseEntity.ok(ApiResponse.success(dto));
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<MemberDTO>> register(@RequestBody MemberDTO dto) {
    MemberDTO res = memberService.registerMember(dto);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success(res));
  }

  @PutMapping("/update")
  public ResponseEntity<ApiResponse<MemberDTO>> update(@RequestBody MemberDTO dto) {
    MemberDTO res = memberService.updateMember(dto);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ApiResponse.success(res));
  }

  @DeleteMapping("/{mno}")
  public ResponseEntity<ApiResponse<String>> deleteMember(@PathVariable Long mno) {
    try {
      memberService.deleteMember(mno);

      return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("성공적으로 삭제되었습니다."));
    } catch (Exception e) {
      log.error("에러발생 {} ", e.getMessage());

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("에러발생"));
    }
  }

}
