package com.jf.vo.video;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/16
 */
public class FindVideoResponse {

    private final List<VideoView> list = Lists.newArrayList();
    private int pageNumber;
    private int pageSize;
    private int totalPage;
    private int totalRow;

    public List<VideoView> getList() {
        return list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }
}
