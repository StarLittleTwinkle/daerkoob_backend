package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend , Long> {
    Boolean existsByUserAndFriendIndex(User user, Long friendIndex);
}
