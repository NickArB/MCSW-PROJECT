package com.mcsw.receiptapp.controller.user;

import com.mcsw.receiptapp.exception.InvalidCredentialsException;
import com.mcsw.receiptapp.exception.InvalidEmailException;
import com.mcsw.receiptapp.exception.UserNotFoundException;
import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> findByEmail( @PathVariable String email ) {
        try{
            User user = userService.findByEmail( email );
            return ResponseEntity.ok(user);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>("Error, usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto )
    {
        try {
            User user = userService.create( userDto );
            return ResponseEntity.ok( user );
        }catch (InvalidEmailException e){
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
        }
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
