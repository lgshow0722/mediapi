package com.medi.mediapi.project.clinic.controller;

import com.medi.mediapi.constant.ResultMessageCode;
import com.medi.mediapi.exception.ApiException;
import com.medi.mediapi.project.clinic.dto.AIReview;
import com.medi.mediapi.project.clinic.service.AIService;
import com.medi.mediapi.util.JsonResponse;
import com.medi.mediapi.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clinic/ai/list")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("")
    public ResponseEntity<JsonResponse> index(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "gap", required = false, defaultValue = "100") int pageGap
    ) {
        JsonResponse response = JsonResponse.builder().build();

        PagingUtil pagingUtil = new PagingUtil();
        pagingUtil.setPage(pageNum);
        pagingUtil.setRecordSize(pageGap);

        int reviewTotal = aiService.getReviewTotal();
        List<AIReview> reviewList = aiService.getReviewList(pagingUtil);

        if(reviewList.isEmpty()) {
            throw new ApiException(ResultMessageCode.DATA_EMPTY);
        }

        response.setSuccess(true);
        response.setData(reviewList);
        response.setTotal(reviewTotal);

        return ResponseEntity.ok(response);
    }
}
