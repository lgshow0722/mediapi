package com.medi.mediapi.project.store.repository;

import com.medi.mediapi.project.store.entity.RecommendProduct;
import com.medi.mediapi.project.store.entity.RecommendProductCriteria;
import com.medi.mediapi.project.store.entity.RecommendProductWeight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecommendRepository {
    List<RecommendProductCriteria> getRecommendProductCriteriaList(Map<String, Object> pMap);
    List<RecommendProductWeight> getRecommendProductWeightList(Map<String, Object> pMap);
    int insertRecommendProductList(Map<String, Object> pMap);

    //RT0002, RT0004, RT0005
    List<RecommendProduct> getRecommendTypeProductList(Map<String, Object> pMap);
    //RT0006
    List<RecommendProduct> getRecommendFrequentlyOrderProductList(Map<String, Object> pMap);
    //RT0007
    List<RecommendProduct> getRecommendBasketProductList(Map<String, Object> pMap);
    //RT0008
    List<RecommendProduct> getRecommendWishProductList(Map<String, Object> pMap);

}
