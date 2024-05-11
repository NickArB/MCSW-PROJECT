package com.mcsw.receiptapp.service;

import com.mcsw.receiptapp.controller.user.UserDto;
import com.mcsw.receiptapp.exception.InvalidEmailException;
import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.repository.UserRepository;
import com.mcsw.receiptapp.exception.UserNotFoundException;

import java.util.List;

public class UserService {

    UserRepository userRepository = new UserRepository();
    public List<User> all() {
        return userRepository.all();
    }

    public User findById(String id) throws UserNotFoundException{
        User user = userRepository.findById(id);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    public User create(UserDto userDto) throws InvalidEmailException {
        User user = userRepository.save(new User( userDto ));
        System.out.println(user);
        if (user != null) {
            return user;
        } else {
            throw new InvalidEmailException();
        }
    }

    public User update(User newUser, String email) {
        User user = userRepository.update(email, newUser);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    public Boolean deleteByEmail(String email) {
        if(userRepository.delete(email)){
            return true;
        }
        return false;
    }

    public User findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }
}
