package com.study.springsecuritystudy.user.controller;

import com.study.springsecuritystudy.security.JwtUtil;
import com.study.springsecuritystudy.security.UserDetailsImpl;
import com.study.springsecuritystudy.user.dto.CreateUserRequest;
import com.study.springsecuritystudy.user.dto.CreateUserResponse;
import com.study.springsecuritystudy.user.dto.GetInfoFromJwtResponse;
import com.study.springsecuritystudy.user.dto.LoginUserRequest;
import com.study.springsecuritystudy.user.entity.User;
import com.study.springsecuritystudy.user.entity.UserRoleEnum;
import com.study.springsecuritystudy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@Slf4j(topic = "로그인 및 JWT 생성")
public class UserController {

    final UserService userService;
    final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    //회원가입 (POST /user) JWT X
    //로그인 (Post /login) JWT x
    //JWT를 이용한 UserName 반환 GET 만들기 (GET /user/info) -> JWT O

    //request response는 마음대로 몇개를 만드셔도 상관 없습니다.

    @PostMapping("/jwt")
    public ResponseEntity<String> createJWT(){
        String username = "박성원";
        UserRoleEnum role = UserRoleEnum.USER;

        return new ResponseEntity<>(jwtUtil.createToken(username, role), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest){
        User user = userService.createUser(createUserRequest);

        return ResponseEntity.ok(new CreateUserResponse(user));
    }


    //Response도 DTO로 만들어 주시는것이 더 좋습니다. 지금은 시간 관계상 ㅠㅠ
    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserRequest loginUserRequest){
        return ResponseEntity.ok(userService.userLogin(loginUserRequest));
    }

    @GetMapping("/user/info")
    public ResponseEntity<GetInfoFromJwtResponse> getInfoFromJWT(@AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.ok(new GetInfoFromJwtResponse(userDetails.getUser()));
    }

    @GetMapping("/")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("잘 들어 옵니다?",HttpStatus.OK);
    }
}
