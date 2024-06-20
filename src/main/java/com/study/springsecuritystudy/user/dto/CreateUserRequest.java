package com.study.springsecuritystudy.user.dto;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String info;
}
