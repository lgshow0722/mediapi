package com.medi.mediapi.project.store.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RecommendProductWeight {
    private int pk_idx;
    private String fd_group_type_code;
    private String fd_type_code;
    private int fd_weight_value;
    private Date fd_start_date;
    private Date fd_end_date;
    private String fd_del_yn;
    private String fd_reg_id;
    private Date fd_reg_date;
}
