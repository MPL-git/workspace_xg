package com.jf.common.utils;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2019/9/16
 */
public final class MapHelper {

    public static <K, V, T> Map<K, V> toMap(List<T> list, Function<T, K> keyFunction, Function<T, V> valueFunction) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<K, V> map = Maps.newHashMap();
        for (T t : list) {
            K key = keyFunction.apply(t);
            V value = valueFunction.apply(t);
            map.put(key, value);
        }
        return map;
    }

    public static <K, V> Map<K, V> toMap(List<V> list, MapKey<V, K> keyFunction) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<K, V> map = Maps.newHashMap();
        for (V t : list) {
            if (t == null) {
                continue;
            }
            K key = keyFunction.key(t);
            if (key == null) {
                continue;
            }
            map.put(key, t);
        }
        return map;
    }

    public static <K, V> Map<K, List<V>> toListMap(List<V> list, MapKey<V, K> keyFunction) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<K, List<V>> map = Maps.newHashMap();
        for (V t : list) {
            if (t == null) {
                continue;
            }
            K key = keyFunction.key(t);
            if (key == null) {
                continue;
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<V>());
            }
            map.get(key).add(t);
        }
        return map;
    }

    public interface MapKey<S, K> {
        K key(S source);
    }

}
