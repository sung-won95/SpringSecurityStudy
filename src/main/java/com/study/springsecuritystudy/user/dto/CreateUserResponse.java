package com.study.springsecuritystudy.user.dto;

import com.study.springsecuritystudy.user.entity.User;
import com.study.springsecuritystudy.user.entity.UserRoleEnum;
import lombok.Data;


@Data
public class CreateUserResponse {
    private String username;
    private String info;
    private UserRoleEnum userRoleEnum;

    public CreateUserResponse(User user){
        this.username = user.getUsername();
        this.info = user.getInfo();
        this.userRoleEnum = user.getRole();
    }
}
