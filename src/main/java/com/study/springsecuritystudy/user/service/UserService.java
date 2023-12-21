package com.study.springsecuritystudy.user.service;

import com.study.springsecuritystudy.security.JwtUtil;
import com.study.springsecuritystudy.user.dto.UserDto;
import com.study.springsecuritystudy.user.entity.User;
import com.study.springsecuritystudy.user.entity.UserRoleEnum;
import com.study.springsecuritystudy.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User createUser(UserDto userDto){
        User user = new User(userDto.getUsername(), userDto.getPassword(), UserRoleEnum.USER );

        return userRepository.save(user);
    }

    public String login(UserDto userDto){
        User user =  userRepository.findByUsername(userDto.getUsername()).orElseThrow(EntityNotFoundException::new);

        if(user.getPassword().equals(userDto.getPassword())){
            return jwtUtil.createToken(user.getUsername(), user.getRole());
        } else {
            return "돌아가";
        }
    }


}
