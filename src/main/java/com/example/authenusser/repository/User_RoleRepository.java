package com.example.authenusser.repository;

import com.example.authenusser.entity.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface User_RoleRepository extends JpaRepository<User_Role, Long> {
    @Query(value = "select * from user_role ur where ur.user_id = ?1 ", nativeQuery = true)
    User_Role findByUser_Id(Long user_id);



    @Query(value = "select max(id) from user_role", nativeQuery = true)
    Long getMaxId();

    @Query(value = "select * from user_role ur where ur.user_id = ?1 and ur.role_id = ?2", nativeQuery = true)
    User_Role findByUser_IdAndRole_id(Long user_id, Long role_id);

    @Query(value = "select  * from user_role ur where ur.user_id = ?1 and ur.asset = ?2", nativeQuery = true)
    User_Role findByUserIdAndAsset(Long user_id, int asset);

}
