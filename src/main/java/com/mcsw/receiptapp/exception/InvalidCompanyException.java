package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class InvalidCompanyException extends InternalServerErrorException
{
    public InvalidCompanyException()
    {
        super( new ServerErrorResponseDto( "Company must not be null.", ErrorCodeEnum.INVALID_COMPANY,
                HttpStatus.BAD_REQUEST ), HttpStatus.BAD_REQUEST );
    }
}
