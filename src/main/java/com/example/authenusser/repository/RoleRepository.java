package com.example.authenusser.repository;

import com.example.authenusser.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCode(String code);

    @Query(value = "select * from role r where r.id= ?1", nativeQuery = true)
    RoleEntity findByRoleId(Long id);

    @Query(value = "select * from role r where r.code= ?1", nativeQuery = true)
    RoleEntity findByCode(Long id);


}
