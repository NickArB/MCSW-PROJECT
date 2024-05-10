package com.mcsw.receiptapp.exception;

import com.mcsw.receiptapp.error.ErrorCodeEnum;
import com.mcsw.receiptapp.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class IncorrectCardInformationException extends InternalServerErrorException
{
    public IncorrectCardInformationException()
    {
        super( new ServerErrorResponseDto( "The information provided is not correct.", ErrorCodeEnum.INCORRECT_CARD_INFORMATION,
                HttpStatus.FORBIDDEN ), HttpStatus.FORBIDDEN );
    }
}