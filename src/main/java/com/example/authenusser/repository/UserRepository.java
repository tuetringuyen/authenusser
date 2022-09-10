package com.example.authenusser.repository;

import com.example.authenusser.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNameAndStatus(String username, int status);

    @Query(value = "select * from users where username = ?1", nativeQuery = true)
    UserEntity findByUserName(String username);
}
