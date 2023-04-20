package com.medi.mediapi.exception;

import com.medi.mediapi.util.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public <T extends ApiException> ResponseEntity<JsonResponse> handleApiException(T e) {
        if(e.getJsonResponse().getCustom_message()!=null) {
            log.error("Error occurs > Message : {}", e.getJsonResponse().getCustom_message());
        } else {
            log.error("Error occurs > Type : {}", e.getJsonResponse().getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e.getJsonResponse());
    }
}
