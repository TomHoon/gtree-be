package org._4.gtree.service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org._4.gtree.dto.BoardDTO;
import org._4.gtree.dto.PageRequestDTO;
import org._4.gtree.dto.PageResponseDTO;
import org._4.gtree.entity.BoardEntity;
import org._4.gtree.entity.MemberEntity;
import org._4.gtree.repository.BoardRepository;
import org._4.gtree.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  public PageResponseDTO getBoardAll(Map<String, Object> map) {
    int page = (int) map.get("page");
    int size = (int) map.get("size");

    PageRequestDTO dto = PageRequestDTO.builder()
        .page(page)
        .size(size)
        .build();

    Pageable pageable = dto.getPageable(Sort.by("bno"));
    Page<BoardDTO> res = boardRepository.findAll(pageable).map(BoardDTO::new);

    PageResponseDTO resDTO = new PageResponseDTO<>(res);

    int currentGroup = page / size;
    int startPage = currentGroup * size + 1;
    int endPage = Math.min(startPage + size - 1, res.getTotalPages());

    boolean hasNextGroup = startPage > 1;
    boolean hasPrevGroup = endPage < res.getTotalPages();

    resDTO.setHasNextGroup(hasNextGroup);
    resDTO.setHasPreviousGroup(hasPrevGroup);

    return resDTO;
  }

  public PageResponseDTO getBoardByPage(Pageable pageable, String category) {
    Page<BoardEntity> res = boardRepository.findAllByCategory(category, pageable);
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

  public Map<String, Object> getCounts() {
    return boardRepository.getCounts();
  }

}
