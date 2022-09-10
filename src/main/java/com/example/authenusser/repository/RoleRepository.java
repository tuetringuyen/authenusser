package com.example.authenusser.repository;

import com.example.authenusser.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCode(String code);
}
