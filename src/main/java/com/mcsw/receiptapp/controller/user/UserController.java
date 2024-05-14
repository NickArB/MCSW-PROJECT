package com.mcsw.receiptapp.controller.user;

import com.mcsw.receiptapp.exception.InvalidEmailException;
import com.mcsw.receiptapp.exception.UserNotFoundException;
import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.service.UserService;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping( "/users" )
public class UserController{

    private final UserService userService = new UserService();


    @GetMapping
    @RolesAllowed({"ADMIN", "AUDITOR"})
    public List<User> all(){
        return userService.all();
    }

    @GetMapping( "/{email}" )
    @RolesAllowed("ADMIN")
    public ResponseEntity<?> findByEmail( @PathVariable String email ){
        try{
            User user = userService.findByEmail( email );
            return ResponseEntity.ok(user);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>("Error, usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<?> create(@RequestBody UserDto userDto ){
        userDto = sanitize(userDto);
        try {
            User user = userService.create( userDto );
            return ResponseEntity.ok( user );
        }catch (InvalidEmailException e){
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{email}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<User> update(@RequestBody UserDto userDto, @PathVariable String email){
        userDto = sanitize(userDto);
        User existingUser = userService.findByEmail(email);
        if (existingUser != null) {
            existingUser.setName(userDto.getName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setPasswordHash(userDto.getPassword());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setRole(userDto.getRole());
            User updatedUser = userService.update(existingUser, email);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping( "/{email}" )
    @RolesAllowed("ADMIN")
    public ResponseEntity<Boolean> delete( @PathVariable String email ){
        return ResponseEntity.ok( userService.deleteByEmail( email ) );
    }

    private UserDto sanitize(UserDto input){
        input.setEmail(Jsoup.clean(input.getEmail(), Safelist.relaxed()));
        input.setPassword(Jsoup.clean(input.getPassword(), Safelist.relaxed()));
        input.setName(Jsoup.clean(input.getName(), Safelist.relaxed()));
        input.setLastName(Jsoup.clean(input.getLastName(), Safelist.relaxed()));
        input.setRole(Jsoup.clean(input.getRole(), Safelist.relaxed()));
        return input;
    }

}
