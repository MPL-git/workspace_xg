package com.jf.common.utils.page;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author luoyb
 * Created on 2019/12/4
 */
public class Pager<E> implements Iterable<Pager.PerPage<E>> {

    private int pageSize = 10;

    private final List<E> data;

    private int totalPage = 0;

    private Pager(List<E> data) {
        this.data = data;
    }

    public static <E> Pager<E> pager(List<E> data) {
        Pager<E> pager = new Pager<>(data);
        pager.restTotalPage();
        return pager;
    }

    public Pager<E> withPageSize(int pageSize) {
        Assert.isTrue(pageSize > 0, "每页至少一条");
        this.pageSize = pageSize;
        restTotalPage();
        return this;
    }

    private void restTotalPage() {
        if (null == data) {
            totalPage = 0;
            return;
        }
        int total = data.size();
        int mod = total % pageSize != 0 ? 1 : 0;
        totalPage = total / pageSize + mod;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<E> fetch(int pageNo) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        int from = Math.max(0, (pageNo - 1) * pageSize);
        int to = Math.min(data.size(), pageNo * pageSize);
        return data.subList(from, to);
    }

    @Override
    public Iterator<PerPage<E>> iterator() {
        return new PageIterator();
    }

    private class PageIterator implements Iterator<PerPage<E>> {
        int cursor;

        public boolean hasNext() {
            return cursor < totalPage;
        }

        public PerPage<E> next() {
            int i = cursor;
            if (i >= totalPage)
                throw new NoSuchElementException(String.format("页码超过最大页码数:%s", totalPage));
            int from = cursor * pageSize;
            int to = Math.min(data.size(), (cursor + 1) * pageSize);
            PerPage<E> perPage = new PerPage<>(data.subList(from, to));
            cursor = i + 1;
            return perPage;
        }

        public void remove() {
            throw new UnsupportedOperationException("不支持删除功能");
        }
    }

    public static class PerPage<E> {
        private final List<E> list;

        public PerPage(List<E> list) {
            this.list = list;
        }

        public List<E> getList() {
            return list;
        }
    }

}
