package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class InvalidCardException extends InternalServerErrorException
{
    public InvalidCardException()
    {
        super( new ServerErrorResponseDto( "Card specified does not exist.", ErrorCodeEnum.INVALID_CARD,
                HttpStatus.NOT_FOUND ), HttpStatus.NOT_FOUND);
    }
}
