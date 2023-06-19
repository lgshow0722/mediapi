package com.medi.mediapi.project.store.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendProduct {
    private int pk_idx;
    private int fk_tpmm_idx;
    private int fk_cate_1_idx;
    private int fk_cate_2_idx;
//    private String fd_company_info;
    private String fd_product_name;
    private String fd_product_standard;
    private String fd_product_unit;
    private int fd_product_amount;
    private String fd_type_code;
    private int fd_rank_point;
    private String fd_del_yn;
    private String fd_reg_id;
    private Date fd_reg_date;
}
