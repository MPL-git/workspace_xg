package com.jf.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author luoyb
 * Created on 2019/9/16
 */
public final class ListHelper {

    public static <T> List<Integer> extractIds(List<T> list, IdExtractor<T> func) {
        if (CollectionUtils.isEmpty(list)) return Collections.emptyList();

        Set<Integer> idSet = Sets.newHashSet();
        for (T t : list) {
            Integer id = func.getId(t);
            if (id != null) {
                idSet.add(func.getId(t));
            }
        }
        return Lists.newArrayList(idSet);
    }

    public interface IdExtractor<S> {
        Integer getId(S source);
    }
}
