package com.jf.service;

import com.google.common.collect.Sets;
import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.ConfigSpecialMchtMapper;
import com.jf.entity.ConfigSpecialMcht;
import com.jf.entity.ConfigSpecialMchtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author luoyb
 * Created on 2019/12/12
 */
@Service
public class ConfigSpecialMchtService extends BaseService<ConfigSpecialMcht, ConfigSpecialMchtExample> {

    @Autowired
    private ConfigSpecialMchtMapper configSpecialMchtMapper;

    @Autowired
    private void setDao() {
        this.setDao(configSpecialMchtMapper);
    }

    private List<ConfigSpecialMcht> findSpecialMcht(List<Integer> mchtIds) {
        ConfigSpecialMchtExample example = new ConfigSpecialMchtExample();
        example.createCriteria().andDelFlagEqualTo(StateConst.FALSE).andMchtIdIn(mchtIds);
        return this.selectByExample(example);
    }

    public Set<Integer> findSpecialMchtIds(List<Integer> mchtIds) {
        if (CollectionUtils.isEmpty(mchtIds)) return Collections.emptySet();

        List<ConfigSpecialMcht> specialMchtList = findSpecialMcht(mchtIds);
        if (CollectionUtils.isEmpty(specialMchtList)) return Collections.emptySet();

        Set<Integer> specialMchtIdSet = Sets.newHashSet();
        for (ConfigSpecialMcht mcht : specialMchtList) {
            specialMchtIdSet.add(mcht.getMchtId());
        }
        return specialMchtIdSet;
    }

}
