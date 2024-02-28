package com.mcsw.receiptapp.controller.user;

import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/users" )
public class UserController
{

    private final UserService userService = new UserService();


    @GetMapping
    public List<User> all()
    {
        return userService.all();
    }

    @GetMapping( "/{email}" )
    public User findByEmail( @PathVariable String email )
    {
        return userService.findByEmail( email );
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto userDto )
    {
        return ResponseEntity.ok( userService.create( userDto ) );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id )
    {
        return ResponseEntity.ok( userService.update( userDto, id ) );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        return ResponseEntity.ok( userService.deleteById( id ) );
    }

}
