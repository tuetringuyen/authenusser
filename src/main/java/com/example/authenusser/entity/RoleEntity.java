package com.example.authenusser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class RoleEntity extends BaseEntity{


    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserEntity> users = new ArrayList<>();



}
