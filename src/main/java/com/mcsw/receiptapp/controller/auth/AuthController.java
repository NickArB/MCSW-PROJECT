package com.mcsw.receiptapp.controller.auth;

import com.mcsw.receiptapp.exception.InvalidCredentialsException;
import com.mcsw.receiptapp.model.User;
import com.mcsw.receiptapp.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.Calendar;
import java.util.Date;
import org.jsoup.safety.Safelist;

@RestController
@RequestMapping( "/auth" )
public class AuthController {

    private final UserService userService = new UserService();


    @Value( "${app.secret}" )
    String secret;


    @PostMapping
    public TokenDto login( @RequestBody LoginDto loginDto )
    {
        loginDto = sanitize(loginDto);
        User user = userService.findByEmail( loginDto.email );
        if ( BCrypt.checkpw( loginDto.password, user.getPasswordHash() ) )
        {
            return generateTokenDto( user );
        }
        else
        {
            throw new InvalidCredentialsException();
        }
    }

    private String generateToken( User user, Date expirationDate )
    {
        return Jwts.builder()
                .setSubject( user.getId() )
                .claim( "Role", user.getRole() )
                .claim( "id", user.getId() )
                .claim( "email", user.getEmail() )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
    }

    private TokenDto generateTokenDto( User user )
    {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, 10 );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }

    private LoginDto sanitize(LoginDto input){
        input.setEmail(Jsoup.clean(input.getEmail(), Safelist.relaxed()));
        input.setPassword(Jsoup.clean(input.getPassword(), Safelist.relaxed()));
        return input;
    }
}
