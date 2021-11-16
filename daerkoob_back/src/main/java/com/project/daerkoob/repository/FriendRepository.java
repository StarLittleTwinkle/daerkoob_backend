package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Boolean existsByUserIndexAndFriendIndex(Long userIndex, Long friendIndex);
    List<Friend> findByUserIndex(Long userIndex);
}
