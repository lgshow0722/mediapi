package com.medi.mediapi.exception;

import com.medi.mediapi.constant.ResultMessageCode;
import com.medi.mediapi.util.JsonResponse;
import lombok.Getter;
import org.json.simple.JSONObject;

@Getter
public class ApiException extends RuntimeException {

    private final JsonResponse jsonResponse;

    public ApiException(ResultMessageCode resultMessageCode) {
        super(resultMessageCode.getMessage());
        this.jsonResponse = JsonResponse.builder()
                .message(resultMessageCode)
                .build();
    }

    // 별도의 데이터를 등록한다.
    public ApiException(ResultMessageCode resultMessageCode, JSONObject data) {
        super(resultMessageCode.getMessage());
        this.jsonResponse = JsonResponse.builder()
                .message(resultMessageCode)
                .data(data)
                .build();
    }

    public ApiException(ResultMessageCode resultMessageCode, String customMessage) {
        super(resultMessageCode.getMessage());
        this.jsonResponse = JsonResponse.builder()
                .message(resultMessageCode)
                .custom_message(customMessage)
                .build();
    }


}
