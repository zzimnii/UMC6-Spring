package umc.spring.apiPayload.exception;

import umc.spring.apiPayload.code.BaseErrorCode;

public class MemberHandler  extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
