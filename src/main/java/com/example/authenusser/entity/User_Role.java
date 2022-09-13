package com.example.authenusser.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_role")
public class User_Role extends BaseEntity{

    @Column
    String module;

    @Column
    int asset;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
}
