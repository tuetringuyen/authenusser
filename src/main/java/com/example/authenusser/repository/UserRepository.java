package com.example.authenusser.repository;

import com.example.authenusser.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserNameAndStatus(String username, int status);

    @Query(value = "select * from users where username = ?1", nativeQuery = true)
    UserEntity findByUserName(String username);



    @Query(value = "select max(id) from users", nativeQuery = true)
    Long getMaxId();

    @Query(value = "select * from users where id = ?1", nativeQuery = true)
    UserEntity findByUserId(Long id);




}
