package com.example.authenusser.controller;

import com.example.authenusser.dto.UserDTO;
import com.example.authenusser.entity.RoleEntity;
import com.example.authenusser.entity.UserEntity;
import com.example.authenusser.entity.User_Role;
import com.example.authenusser.repository.RoleRepository;
import com.example.authenusser.repository.UserRepository;
import com.example.authenusser.repository.User_RoleRepository;
import com.sun.deploy.net.HttpResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "homecontroller")
public class homecontroller {

    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    User_RoleRepository user_roleRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/user/get")
    public List<UserEntity> get() {
        List<UserEntity> list = userRepository.findAll();
        return list;
    }

    @GetMapping("/user/get/{id}")
    public UserEntity ds(@PathVariable("id") Long id){
        UserEntity user= userRepository.getById(id);
        return user;
    }

    @PutMapping("/user/update/{id}")
    public UserEntity update(@PathVariable("id") Long id, @RequestBody UserEntity user) {
        UserEntity oldUser = userRepository.getById(id);
        if(user.getFullname() != null){
            oldUser.setFullname(user.getFullname());
        }
        if(user.getPassword() != null){
            oldUser.setPassword(user.getPassword());
        }
        userRepository.save(oldUser);
        return user;
    }


    @DeleteMapping("/user/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }





    @GetMapping("/danhsach/{id}")
    public String getRoleTbByUserID(@PathVariable("id")Long id){
        UserEntity entity = userRepository.findByUserId(id);
        User_Role user_role = user_roleRepository.findByUser_Id(entity.getId());
        RoleEntity roleEntity = roleRepository.findByRoleId(user_role.getRole().getId());
        UserDTO userDTO = modelMapper.map(entity, UserDTO.class);
        userDTO.setChucnang(roleEntity.getCode());
        userDTO.setModule(user_role.getModule());
        userDTO.setAsset(user_role.getAsset());
        System.out.println(userDTO);
        return "/home.html";
    }


    @GetMapping(value = {"/trang-chu", "/", ""})
    public String homePage() {
        List<UserEntity> userEntityList = userRepository.findAll();
        System.out.println("adfadfadf");
        return "/home.html";
    }



    @GetMapping(value = {"/quan-tri/trang-chu"})
    public ModelAndView adminPage() {
        ModelAndView mav = new ModelAndView("Admin");

        return mav;
    }
}
