package com.medi.mediapi.project.clinic.service;

import com.medi.mediapi.project.clinic.dto.AIReview;
import com.medi.mediapi.project.clinic.repository.AIRepository;
import com.medi.mediapi.util.PagingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIService {

    private final AIRepository aiRepository;

    public int getReviewTotal() {
        return aiRepository.getReviewTotal();
    }

    public List<AIReview> getReviewList(PagingUtil pagingUtil) {
        return aiRepository.getReviewList(pagingUtil);
    }
}
