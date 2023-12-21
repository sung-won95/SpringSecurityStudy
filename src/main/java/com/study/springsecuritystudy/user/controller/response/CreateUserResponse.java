package com.study.springsecuritystudy.user.controller.response;

import com.study.springsecuritystudy.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {
    Long id;
    String username;
    String password;

    public CreateUserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}
