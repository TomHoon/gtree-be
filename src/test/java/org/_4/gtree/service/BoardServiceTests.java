package org._4.gtree.service;

import org._4.gtree.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    
    @Autowired
    private BoardService boardService;

    @Test
    public void 단건조회() {
        BoardDTO dto = boardService.getBoardByBno(1L);
        log.info("dto >>>>> {} ", dto.getContent());
    }
}
