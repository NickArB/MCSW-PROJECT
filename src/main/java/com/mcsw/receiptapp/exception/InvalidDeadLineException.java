package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class InvalidDeadLineException extends InternalServerErrorException
{
    public InvalidDeadLineException()
    {
        super( new ServerErrorResponseDto( "Deadline must not be null or it must be greater than today´s date.", ErrorCodeEnum.INVALID_DEADLINE,
                HttpStatus.BAD_REQUEST ), HttpStatus.BAD_REQUEST);
    }
}