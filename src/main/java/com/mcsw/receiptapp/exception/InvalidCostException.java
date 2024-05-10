package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class InvalidCostException extends InternalServerErrorException
{
    public InvalidCostException()
    {
        super( new ServerErrorResponseDto( "The cost must be a positive number.", ErrorCodeEnum.INVALID_COST,
                HttpStatus.BAD_REQUEST ), HttpStatus.BAD_REQUEST);
    }
}
