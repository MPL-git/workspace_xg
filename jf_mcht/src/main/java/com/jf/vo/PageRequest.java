package com.jf.vo;


/**
 * @author luoyb
 * Created on 2019/9/7
 */
public class PageRequest {

    private int page = 1; //页码
    private int pageSize = 10; //分页大小

    //获取offset
    public int getOffset() {
        return (page - 1) * pageSize;
    }

    //重置页数为5
    public void resetPageSize5() {
        this.pageSize = 5;
    }

    public PageRequest() {
    }

    public PageRequest(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
