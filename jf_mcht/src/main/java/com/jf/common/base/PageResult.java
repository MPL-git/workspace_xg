package com.jf.common.base;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/19
 */
public class PageResult<T> {

    private int totalCount;
    private List<T> list;

    public PageResult() {
    }

    public static <T> PageResult<T> empty() {
        return of(0, Collections.<T>emptyList());
    }

    public static <T> PageResult<T> of(int totalCount, List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.setTotalCount(totalCount);
        result.setList(list);
        return result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
