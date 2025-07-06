package org._4.gtree.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org._4.gtree.dto.BoardDTO;
import org._4.gtree.dto.PageResponseDTO;
import org._4.gtree.entity.BoardEntity;
import org._4.gtree.entity.MemberEntity;
import org._4.gtree.repository.BoardRepository;
import org._4.gtree.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final MemberRepository memberRepository;

  public BoardDTO getBoardByBno(Long bno) {
    BoardEntity e = boardRepository.findById(bno).orElseThrow();
    return new BoardDTO(e);
  }

  public List<BoardDTO> getBoardAll() {
    List<BoardDTO> list = boardRepository.findAll()
        .stream()
        .map(e -> new BoardDTO(e))
        .collect(Collectors.toList());

    return list;
  }

  public PageResponseDTO getBoardByPage(Pageable pageable) {
    Page<BoardEntity> res = boardRepository.findAll(pageable);
    Page<BoardDTO> page = res.map(e -> new BoardDTO(e));

    return new PageResponseDTO<>(page);
  }

  public BoardDTO updateBoard(BoardDTO dto) {
    String writer = dto.getWriter();
    MemberEntity me = memberRepository.findByUserId(writer).orElseThrow();
    
    BoardEntity e = dto.toEntity(me);
    BoardEntity updatedEntity = boardRepository.save(e);
    return new BoardDTO(updatedEntity);
  }

  public void deleteBoard(Long bno) {
    Optional<BoardEntity> res = boardRepository.findById(bno);
    BoardEntity e = res.orElseThrow(() -> new NoSuchElementException("멤버가 없습니다."));
    e.setIsDel(true);

    boardRepository.save(e);
  }

  public BoardDTO writeBoard(BoardDTO dto) {
    String writer = dto.getWriter();
    MemberEntity me = memberRepository.findByUserId(writer).orElseThrow();

    BoardEntity e = dto.toEntity(me);
    
    BoardEntity b = boardRepository.save(e);
    return b.toDTO();
  }

}
