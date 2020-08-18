package com.jf.vo.request;

import javax.validation.constraints.Min;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class PageRequest {

    @Min(value = 0, message = "页码不能小于0")
    private int currentPage = 0; //页码-1
    private int pageSize = 10; //分页大小

    //获取offset
    public int getOffset() {
        return currentPage * pageSize;
    }

    //重置页数为5
    public void resetPageSize5() {
        this.pageSize = 5;
    }

    public PageRequest() {
    }

    public PageRequest(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
