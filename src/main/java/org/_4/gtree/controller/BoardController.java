package org._4.gtree.controller;

import java.util.List;
import java.util.Map;

import org._4.gtree.dto.BoardDTO;
import org._4.gtree.dto.PageRequestDTO;
import org._4.gtree.dto.PageResponseDTO;
import org._4.gtree.service.BoardService;
import org._4.gtree.utils.ApiResponse;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@Log4j2
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/getBoardAll")
  public ResponseEntity<ApiResponse<List<BoardDTO>>> getBoardAll() {
    List<BoardDTO> list = boardService.getBoardAll();

    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(list));
  }

  @GetMapping("/getBoardByPage")
  public ResponseEntity<ApiResponse<PageResponseDTO>> getBoardByPage(@RequestParam Map<String, Object> param) {
    int size = (int) param.get("size");
    int page = (int) param.get("page");

    PageRequestDTO dto = PageRequestDTO.builder()
        .page(page)
        .size(size)
        .build();

    Pageable pageable = dto.getPageable(Sort.by("bno"));

    PageResponseDTO<BoardDTO> pageResponse = boardService.getBoardByPage(pageable);

    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(pageResponse));
  }

  @PutMapping("/updateBoard")
  public ResponseEntity<ApiResponse<BoardDTO>> updateBoard(@RequestBody BoardDTO dto) {
    BoardDTO updatedDTO = boardService.updateBoard(dto);
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(updatedDTO));
  }

  @PutMapping("/deleteBoard/{id}")
  public ResponseEntity<ApiResponse<String>> deleteBoard(@PathVariable Long bno) {
    try {
      boardService.deleteBoard(bno);
      return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Success Deleted"));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.error("Fail Deleted"));
    }
  }

}
