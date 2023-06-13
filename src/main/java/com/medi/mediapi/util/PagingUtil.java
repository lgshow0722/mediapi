package com.medi.mediapi.util;

public class PagingUtil {

    private int page; // 현재 페이지 번호
    private int recordSize; // 페이지당 출력할 데이터 갯수

    public PagingUtil() {
        this.page = 1;
        this.recordSize = 30;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public int getOffset() {
        return (page -1) * recordSize;
    }
}
