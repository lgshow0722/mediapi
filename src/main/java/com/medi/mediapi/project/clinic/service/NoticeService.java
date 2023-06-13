package com.medi.mediapi.project.clinic.service;

import com.medi.mediapi.project.clinic.dto.Notice;
import com.medi.mediapi.project.clinic.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getList() {
        return noticeRepository.getList();
    }
}
