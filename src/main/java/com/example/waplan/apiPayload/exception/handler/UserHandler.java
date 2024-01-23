package com.example.waplan.apiPayload.exception.handler;

import com.example.waplan.apiPayload.code.BaseErrorCode;
import com.example.waplan.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
