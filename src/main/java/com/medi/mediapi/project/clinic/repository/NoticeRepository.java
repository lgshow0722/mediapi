package com.medi.mediapi.project.clinic.repository;

import com.medi.mediapi.project.clinic.dto.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeRepository {

    List<Notice> getList();
}
