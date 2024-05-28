package umc.spring.apiPayload.exception;

import umc.spring.apiPayload.code.BaseErrorCode;

public class MissionHandler extends GeneralException {

    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
