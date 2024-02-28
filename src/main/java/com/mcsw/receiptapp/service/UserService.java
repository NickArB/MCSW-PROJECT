package com.mcsw.receiptapp.service;

import com.mcsw.receiptapp.controller.user.UserDto;
import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class UserService {

    UserRepository userRepository = new UserRepository();
    public List<User> all() {
        return null;
    }

    public User findById(String id) {
        try {
            return userRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User create(UserDto userDto) {
        return null;
    }

    public User update(UserDto userDto, String id) {
        return null;
    }

    public Boolean deleteById(String id) {
        return null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
