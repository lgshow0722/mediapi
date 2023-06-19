package com.medi.mediapi.project.store.service;

import com.medi.mediapi.project.store.dto.RecommendProductDto;
import com.medi.mediapi.project.store.entity.RecommendProduct;
import com.medi.mediapi.project.store.entity.RecommendProductCriteria;
import com.medi.mediapi.project.store.entity.RecommendProductWeight;
import com.medi.mediapi.project.store.recommend.RecommendType;
import com.medi.mediapi.project.store.recommend.WeightTypeGroup;
import com.medi.mediapi.project.store.repository.RecommendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecommendService {

    private final RecommendRepository recommendRepository;

    @Autowired
    public RecommendService(RecommendRepository recommendRepository) {
        this.recommendRepository = recommendRepository;
    }



    //RT0004, RT0005 추천 제품 리스트
    public void refineLatestRecommendProductList() throws Exception {
        Map<String, Object> pMap = new HashMap<>();
        pMap.put("interval_value", 3);

        List<RecommendProductCriteria> latestRecommendProductCriteriaList = recommendRepository.getRecommendProductCriteriaList(pMap);

        List<RecommendProduct> popularList = new ArrayList<>();
        List<RecommendProduct> wishList = new ArrayList<>();

        for (RecommendProductCriteria recommendProductCriteria : latestRecommendProductCriteriaList) {
            RecommendProduct latestPopularProduct = RecommendProduct.builder()
                    .fk_tpmm_idx(recommendProductCriteria.getFk_tpmm_idx())
                    .fd_type_code(RecommendType.RT0004.name())
                    .fd_rank_point(recommendProductCriteria.getPopular_point())
                    .fd_del_yn("N")
                    .build();

            RecommendProduct latestWishProduct = RecommendProduct.builder()
                    .fk_tpmm_idx(recommendProductCriteria.getFk_tpmm_idx())
                    .fd_type_code(RecommendType.RT0005.name())
                    .fd_rank_point(recommendProductCriteria.getWish_point())
                    .fd_del_yn("N")
                    .build();

            popularList.add(latestPopularProduct);
            wishList.add(latestWishProduct);
        }

        pMap.clear();

        if (popularList.size() > 0) {
            pMap.put("list", popularList);
            recommendRepository.insertRecommendProductList(pMap);
        }

        if (wishList.size() > 0) {
            pMap.put("list", wishList);
            recommendRepository.insertRecommendProductList(pMap);
        }

    }

    //인기 상품 리스트 (기간 없이) - RT0004, RT0005 연관
    public void refineRecommendProductList() throws Exception {
        Map<String, Object> pMap = new HashMap<>();

        List<RecommendProductCriteria> latestRecommendProductCriteriaList = recommendRepository.getRecommendProductCriteriaList(pMap);

        List<RecommendProduct> popularList = new ArrayList<>();

        for (RecommendProductCriteria recommendProductCriteria : latestRecommendProductCriteriaList) {
            RecommendProduct latestPopularProduct = RecommendProduct.builder()
                    .fk_tpmm_idx(recommendProductCriteria.getFk_tpmm_idx())
                    .fd_type_code(RecommendType.RT9999.name())
                    .fd_rank_point(recommendProductCriteria.getPopular_point())
                    .fd_del_yn("N")
                    .build();

            popularList.add(latestPopularProduct);
        }

        if (popularList.size() > 0) {
            pMap.put("list", popularList);
            recommendRepository.insertRecommendProductList(pMap);
        }
    }

    //RT0002
    public void refineRecommendMVProductList() throws Exception {
        Map<String, Object> pMap = new HashMap<>();
        pMap.put("interval_value", 7);

        List<RecommendProductCriteria> recommendMVProductCriteriaList = recommendRepository.getRecommendProductCriteriaList(pMap);

        List<RecommendProduct> mvRecommendProductList = new ArrayList<>();

        for (RecommendProductCriteria recommendProductCriteria : recommendMVProductCriteriaList) {

            if (recommendProductCriteria.getPopular_point() > 0 && recommendProductCriteria.getWish_point() > 0) {
                RecommendProduct recommendProduct = RecommendProduct.builder()
                        .fk_tpmm_idx(recommendProductCriteria.getFk_tpmm_idx())
                        .fd_type_code(RecommendType.RT0002.name())
                        .fd_rank_point(recommendProductCriteria.getPopular_point() + recommendProductCriteria.getWish_point())
                        .fd_del_yn("N")
                        .build();

                mvRecommendProductList.add(recommendProduct);
            }

        }

        if (mvRecommendProductList.size() > 0) {
            pMap.put("list", mvRecommendProductList);
            recommendRepository.insertRecommendProductList(pMap);
        }
    }
}
