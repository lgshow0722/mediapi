package com.medi.mediapi.project.clinic.repository;

import com.medi.mediapi.project.clinic.dto.AIReview;
import com.medi.mediapi.util.PagingUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AIRepository {

    int getReviewTotal();

    List<AIReview> getReviewList(PagingUtil pagingUtil);
}
