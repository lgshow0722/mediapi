package com.medi.mediapi.project.store.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendProductCriteria {
      private int fk_tpmm_idx;
      private String fd_product_name;
      private String fd_product_standard;
      private String fd_product_unit;
      private int fk_cate_1_idx;
      private int fk_cate_2_idx;
      private int order_product_amount;
      private int order_product_cnt;
      private int order_cnt;
      private int search_cnt;
      private int wish_cnt;
      private int cart_cnt;
      private int cart_use_cnt;
      private int popular_point;
      private int wish_point;
      private int fd_product_amount;
}
