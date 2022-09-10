package com.example.authenusser.controller;

import com.example.authenusser.entity.RoleEntity;
import com.example.authenusser.entity.UserEntity;
import com.example.authenusser.repository.RoleRepository;
import com.example.authenusser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "homecontroller")
public class homecontroller {

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

    @PostMapping("/user/create")
    public UserEntity post(@RequestBody UserEntity user ) {
        System.out.print("dsdgsdgsdsf");
        RoleEntity role = roleRepository.findByCode("ROLE_ADMIN");
        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(role);
        user.setRoles(roleEntities);
        userRepository.save(user);
        return user;
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
