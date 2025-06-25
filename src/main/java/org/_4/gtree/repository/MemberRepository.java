package org._4.gtree.repository;

import java.util.Optional;

import org._4.gtree.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  Optional<MemberEntity> findByUserId(String userId);
}
