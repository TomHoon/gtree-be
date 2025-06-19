package org._4.gtree.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org._4.gtree.dto.MemberDTO;
import org._4.gtree.entity.MemberEntity;
import org._4.gtree.repository.MemberRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberDTO getMember(Long mno) {
    Optional<MemberEntity> res = memberRepository.findById(mno);
    MemberEntity entity = res.orElseThrow(() -> new NoSuchElementException("entity가 없습니다."));

    return new MemberDTO(entity);
  }

  public MemberDTO registerMember(MemberDTO dto) {
    MemberEntity e = dto.toEntity();
    memberRepository.save(e);

    return new MemberDTO(e);
  }

  public void deleteMember(Long mno) {
    memberRepository.deleteById(mno);
  }

  public MemberDTO updateMember(MemberDTO dto) {
    MemberEntity e = dto.toEntity();
    MemberEntity res = memberRepository.save(e);

    return new MemberDTO(res);
  }
}
