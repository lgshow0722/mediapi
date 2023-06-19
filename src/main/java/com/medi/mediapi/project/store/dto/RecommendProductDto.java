package com.medi.mediapi.project.store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RecommendProductDto {
    private int pk_idx;
    private int fk_tpmm_idx;
    private String fd_type_code;
    private int fd_rank_no;
    private String fd_del_yn;
    private String fd_reg_id;
    private Date fd_reg_date;
    private String fd_mod_id;
    private Date fd_mod_date;
}
