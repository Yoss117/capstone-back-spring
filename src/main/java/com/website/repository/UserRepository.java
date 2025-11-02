package com.website.repository;

import com.website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByNickname(String nickname);

    @Query("SELECT u FROM User u WHERE u.nickname = :nickname")
    User findByUserNickname(String nickname);

    Optional<User> findByOauthId(Long userCode);
}
