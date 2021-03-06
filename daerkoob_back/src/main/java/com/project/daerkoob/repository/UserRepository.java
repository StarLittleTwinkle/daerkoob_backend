package com.project.daerkoob.repository;

import com.project.daerkoob.domain.Friend;
import com.project.daerkoob.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByNickName(String nickName);
    Boolean existsByUserId(String userId);
    Boolean existsByNickName(String nickName);
}
