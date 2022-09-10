package com.example.authenusser.service.impl;

import com.example.authenusser.constant.SystemConstant;
import com.example.authenusser.dto.MyUser;

import com.example.authenusser.entity.RoleEntity;
import com.example.authenusser.entity.UserEntity;
import com.example.authenusser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; //////////

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        UserEntity userEntity = userRepository.findByUserNameAndStatus(username,
                SystemConstant.ACTIVE_STATUS);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(),
                true, true, true, true, authorities);
        myUser.setFullName(userEntity.getFullname());
        myUser.setAvatar(userEntity.getAvatar());
        myUser.setEmail(userEntity.getEmail());
        return myUser;
    }
}
