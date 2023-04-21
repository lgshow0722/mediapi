package com.medi.mediapi.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultMessageCode {

    DATA_EMPTY("데이터 없음"),
    RETURN_FAIL("API 리턴 실패"),
    RESCODE("API 응답 오류"),
    ETC("기타 오류"),
    NOT_FOUND("페이지 없음"),
    DB_ERROR("DB 오류");

    private final String message;

    ResultMessageCode(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
