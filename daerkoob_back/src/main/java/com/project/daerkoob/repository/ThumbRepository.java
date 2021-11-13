package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbRepository extends JpaRepository<Thumb, Long> {
}
