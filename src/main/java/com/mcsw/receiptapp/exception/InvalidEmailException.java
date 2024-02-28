package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class InvalidEmailException extends InternalServerErrorException
{
    public InvalidEmailException()
    {
        super( new ServerErrorResponseDto( "The email is already registered.", ErrorCodeEnum.USER_WITH_EMAIL_ALREADY_EXISTS,
                HttpStatus.CONFLICT ), HttpStatus.CONFLICT);
    }
}