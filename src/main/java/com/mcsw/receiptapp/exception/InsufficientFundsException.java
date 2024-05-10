package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class InsufficientFundsException extends InternalServerErrorException
{
    public InsufficientFundsException()
    {
        super( new ServerErrorResponseDto( "Insuficient funds.", ErrorCodeEnum.INSUFFICIENT_FUNDS,
                HttpStatus.FORBIDDEN ), HttpStatus.FORBIDDEN );
    }
}