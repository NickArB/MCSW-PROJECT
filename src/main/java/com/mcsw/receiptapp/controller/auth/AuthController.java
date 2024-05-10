package com.mcsw.receiptapp.controller.auth;

import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.service.UserService;
import org.jsoup.Jsoup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jsoup.safety.Safelist;

@RestController
@RequestMapping( "/auth" )
public class AuthController {

    private final UserService userService = new UserService();


    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto ){
        loginDto = sanitize(loginDto);
        User user = userService.findByEmail(loginDto.getEmail());
        if (user == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        } else if (!loginDto.getPassword().equals(user.getPasswordHash())) {
            return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
        } else {
            if ("ADMIN".equals(user.getRole())) {
                System.out.println("ADMIN");
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/admin.xhtml");
                headers.add("Role", user.getRole());
                headers.add("Id", user.getId());
                return new ResponseEntity<>(headers, HttpStatus.OK);
            } else if("USER".equals(user.getRole())){
                System.out.println("USER");
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/main.xhtml");
                headers.add("Role", user.getRole());
                headers.add("Id", user.getId());
                return new ResponseEntity<>(headers, HttpStatus.OK);
            }else if("AUDITOR".equals(user.getRole())){
                System.out.println("AUDITOR");
                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/auditor.xhtml");
                headers.add("Role", user.getRole());
                headers.add("Id", user.getId());
                return new ResponseEntity<>(headers, HttpStatus.OK);
            }else {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Role", user.getRole());

                headers.add("Id", user.getId());

                return new ResponseEntity<>(headers, HttpStatus.OK);
            }
        }

    }

    private LoginDto sanitize(LoginDto input){
        input.setEmail(Jsoup.clean(input.getEmail(), Safelist.relaxed()));
        input.setPassword(Jsoup.clean(input.getPassword(), Safelist.relaxed()));
        return input;
    }
}
