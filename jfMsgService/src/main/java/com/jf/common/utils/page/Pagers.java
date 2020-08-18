package com.jf.common.utils.page;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/12/4
 */
public final class Pagers {

    private Pagers() {
    }

    public static <T> void executeAllPage(List<T> list, int pageSize, PageFunction<T> function) {
        Pager<T> pager = Pager.pager(list).withPageSize(pageSize);
        for (Pager.PerPage<T> perPage : pager) {
            function.execute(perPage.getList());
        }
    }

    public interface PageFunction<T> {
        void execute(List<T> subList);
    }
}
