package umc.spring.apiPayload.exception;

import umc.spring.apiPayload.code.BaseErrorCode;

public class StoreHandler extends GeneralException {

    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
