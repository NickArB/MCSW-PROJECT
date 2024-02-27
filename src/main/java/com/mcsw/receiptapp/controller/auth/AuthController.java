package com.mcsw.receiptapp.controller.auth;

import com.mcsw.receiptapp.exception.InvalidCredentialsException;
import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Calendar;
import java.util.Date;

public class AuthController {

    private final UserService userService = new UserService();


    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto )
    {
        User user = userService.findByEmail( loginDto.email );
        if (loginDto.password.equals(user.getPasswordHash()) )
        {
            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
        }
        else
        {
            throw new InvalidCredentialsException();
        }

    }
}
