package com.example.authenusser.api;

import com.example.authenusser.entity.RoleEntity;
import com.example.authenusser.entity.UserEntity;
import com.example.authenusser.entity.User_Role;
import com.example.authenusser.repository.RoleRepository;
import com.example.authenusser.repository.UserRepository;
import com.example.authenusser.repository.User_RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController(value = "apiAuthor")
public class AuthorizationApi {
    @Autowired
    User_RoleRepository user_roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PutMapping("/api/author/{id}")
    public ResponseEntity<String> updateAsset(@PathVariable("id")Long id, @RequestParam("asset")int asset, @RequestParam("role")String role,
                              @RequestBody UserEntity userEntity){
        UserEntity userEntity1 = userRepository.findByUserName(userEntity.getUserName());
        User_Role user_rol = user_roleRepository.findByUserIdAndAsset(userEntity1.getId(),1);
        if(user_rol != null){
            RoleEntity roleEntity1 = roleRepository.findByRoleId(user_rol.getRole().getId());
            if(roleEntity1.getCode().equals("ROLE_PUT")) {
                RoleEntity roleEntity = roleRepository.findByCode(role);
                UserEntity user = userRepository.findByUserId(id);
                User_Role user_role = user_roleRepository.findByUser_IdAndRole_id(id, roleEntity.getId());
                user_role.setAsset(asset);
                user_roleRepository.save(user_role);
                return ResponseEntity.ok("ok");
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UnAuthorize");
            }
        }else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UnAuthorize");

    }

    @PostMapping("/user/create")
    public UserEntity post(@RequestBody UserEntity user ) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setId(userRepository.getMaxId()+1);
        userRepository.save(user);
        RoleEntity role = roleRepository.findByCode("ROLE_PUT");
        User_Role user_role = new User_Role();
        user_role.setRole(role);
        user_role.setUser(user);
        user_role.setId(user_roleRepository.getMaxId()+1);
        user_roleRepository.save(user_role);
//        List<RoleEntity> roleEntities = new ArrayList<>();
//        roleEntities.add(role);
//        user.setRoles(roleEntities);

        return user;
    }
}
