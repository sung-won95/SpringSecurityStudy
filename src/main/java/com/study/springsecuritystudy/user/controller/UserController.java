package com.study.springsecuritystudy.user.controller;

import com.study.springsecuritystudy.security.JwtUtil;
import com.study.springsecuritystudy.user.controller.response.CreateUserResponse;
import com.study.springsecuritystudy.user.dto.UserDto;
import com.study.springsecuritystudy.user.entity.User;
import com.study.springsecuritystudy.user.entity.UserRoleEnum;
import com.study.springsecuritystudy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@Slf4j(topic = "로그인 및 JWT 생성")
public class UserController {

    final UserService userService;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;

    }

    @PostMapping("/login")
    public ResponseEntity<String> createJWT(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.login(userDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody UserDto userDto){
         User user = userService.createUser(userDto);

         return new ResponseEntity<>(new CreateUserResponse(user),HttpStatus.OK);
    }

}
