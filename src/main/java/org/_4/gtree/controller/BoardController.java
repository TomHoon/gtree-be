package org._4.gtree.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org._4.gtree.dto.BoardDTO;
import org._4.gtree.dto.PageRequestDTO;
import org._4.gtree.dto.PageResponseDTO;
import org._4.gtree.entity.FileInfo;
import org._4.gtree.service.BoardService;
import org._4.gtree.utils.ApiResponse;
import org._4.gtree.utils.FileUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1/board/")
@CrossOrigin("*")
public class BoardController {

  private final BoardService boardService;
  private final FileUtil fileUtil;

  @GetMapping("/getBoardAll")
  public ResponseEntity<ApiResponse<List<BoardDTO>>> getBoardAll() {
    List<BoardDTO> list = boardService.getBoardAll();

    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(list));
  }

  @GetMapping("/{bno}")
  public ResponseEntity<ApiResponse<BoardDTO>> getBoardByBno(@PathVariable Long bno) {
    BoardDTO dto = boardService.getBoardByBno(bno);
    return ResponseEntity.ok(ApiResponse.success(dto));
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

  @PostMapping("/write")
  public ResponseEntity<ApiResponse<BoardDTO>> writeBoard(@RequestPart("board") BoardDTO dto,
      @RequestPart(value = "attachList", required = false) List<MultipartFile> attachList) throws JsonMappingException, JsonProcessingException {

    if (attachList != null && attachList.size() > 0) {
      List<FileInfo> infoList = attachList
          .stream()
          .map(item -> new FileInfo(fileUtil.saveFile(item), fileUtil.saveFile(item)))
          .collect(Collectors.toList());
          
      log.info(">>>> infoList  {} ", infoList);
      System.out.println(">>>> infoList " + infoList);
      dto.setFiles(infoList);
    }

    BoardDTO res = boardService.writeBoard(dto);
    return ResponseEntity.ok(ApiResponse.success(res));
  }

}
