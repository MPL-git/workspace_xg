package com.jf.common;

import com.jf.common.base.BaseService;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
public abstract class AppBaseService<T, E> extends BaseService<T, E> {

    public T selectOneByExample(E example) {
        List<T> list = super.dao.selectByExample(example);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
