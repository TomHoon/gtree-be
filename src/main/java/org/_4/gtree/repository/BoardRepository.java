package org._4.gtree.repository;

import java.util.Map;

import org._4.gtree.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  Page<BoardEntity> findAll(Pageable pageable);

  Page<BoardEntity> findAllByCategory(String category, Pageable pageable);

  @Query(value = """
      SELECT
        SUM(CASE WHEN category = '자유게시판' THEN 1 ELSE 0 END) as 자유게시판,
        SUM(CASE WHEN category = '공지사항' THEN 1 ELSE 0 END) as 공지사항,
        SUM(CASE WHEN category = '구인정보' THEN 1 ELSE 0 END) as 구인정보,
        SUM(CASE WHEN category = '언론보도' THEN 1 ELSE 0 END) as 언론보도,
        SUM(CASE WHEN category = '갤러리' THEN 1 ELSE 0 END) as 갤러리
      FROM
        tbl_board;
      """, nativeQuery = true)
  Map<String, Object> getCounts();

}
