package com.study.springsecuritystudy.user.service;

import com.study.springsecuritystudy.security.JwtUtil;
import com.study.springsecuritystudy.user.dto.CreateUserRequest;
import com.study.springsecuritystudy.user.dto.LoginUserRequest;
import com.study.springsecuritystudy.user.entity.User;
import com.study.springsecuritystudy.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;

    final PasswordEncoder passwordEncoder;

    final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User createUser(CreateUserRequest createUserRequest){
        String encodingPassword = passwordEncoder.encode(createUserRequest.getPassword());

        User user = new User(createUserRequest, encodingPassword);

        return userRepository.save(user);
    }

    public String userLogin(LoginUserRequest loginUserRequest){
        User user= userRepository.findByUsername(loginUserRequest.getUsername()).orElseThrow(EntityNotFoundException::new);

        String returnString;
        if(passwordEncoder.matches(loginUserRequest.getPassword(),user.getPassword())){
            returnString = jwtUtil.createToken(user.getUsername(), user.getRole());
        } else {
            returnString = "비밀번호 오류";
        }

        return returnString;
    }
}
