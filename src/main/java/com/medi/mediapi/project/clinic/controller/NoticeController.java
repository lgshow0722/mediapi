package com.medi.mediapi.project.clinic.controller;

import com.medi.mediapi.constant.ResultMessageCode;
import com.medi.mediapi.exception.ApiException;
import com.medi.mediapi.project.clinic.dto.Notice;
import com.medi.mediapi.project.clinic.service.NoticeService;
import com.medi.mediapi.util.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clinic/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("")
    public ResponseEntity<JsonResponse> index() {
        JsonResponse response = JsonResponse.builder().build();
        List<Notice> noticeList = noticeService.getList();

        if(noticeList.isEmpty()) {
            throw new ApiException(ResultMessageCode.DATA_EMPTY);
        }

        response.setSuccess(true);
        response.setData(noticeList);
        response.setTotal(noticeList.size());

        return ResponseEntity.ok(response);
    }
}
