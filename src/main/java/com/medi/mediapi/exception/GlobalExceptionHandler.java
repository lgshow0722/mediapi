package com.medi.mediapi.exception;

import com.medi.mediapi.constant.ResultMessageCode;
import com.medi.mediapi.util.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 예외처리 핸들러
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<JsonResponse> handle404(NoHandlerFoundException e) {
        log.error("[ERROR] page not found : {}", e.getRequestURL());
        JsonResponse jsonResponse = JsonResponse.builder()
                .message(ResultMessageCode.NOT_FOUND)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonResponse);
    }

    // 500 에러처리
    @ExceptionHandler({ApiException.class})
    public <T extends ApiException> ResponseEntity<JsonResponse> handleApiException(T e) {
        if(e.getJsonResponse().getCustom_message()!=null) {
            log.error("[Error] {} : {}", e.getJsonResponse().getCustom_message(), e.getLocalizedMessage());
        } else {
            log.error("[Error] {} : {}", e.getJsonResponse().getMessage().getMessage(), e.getLocalizedMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getJsonResponse());
    }

    // 데이터베이스오류
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<JsonResponse> handleDataAccessException(DataAccessException e) {
        log.error("[ERROR] Databalse : {}", e.getLocalizedMessage());
        JsonResponse jsonResponse = JsonResponse.builder()
                .message(ResultMessageCode.DB_ERROR)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonResponse);
    }

}
