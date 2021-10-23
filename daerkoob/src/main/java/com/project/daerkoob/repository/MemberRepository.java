package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
