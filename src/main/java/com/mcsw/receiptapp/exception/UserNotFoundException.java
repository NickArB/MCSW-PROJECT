package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException
        extends InternalServerErrorException
{
    public UserNotFoundException()
    {
        super( new ServerErrorResponseDto( "User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND ),
                HttpStatus.NOT_FOUND );
    }
}

