package com.study.springsecuritystudy.user.service;

import com.study.springsecuritystudy.user.entity.User;
import com.study.springsecuritystudy.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
