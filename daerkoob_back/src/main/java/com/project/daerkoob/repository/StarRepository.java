package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star,Long> {
}
