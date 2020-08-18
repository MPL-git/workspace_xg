package com.jf.service.integrallottery;

import com.google.common.collect.Maps;
import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.LotterySettingsMapper;
import com.jf.entity.LotterySettings;
import com.jf.entity.LotterySettingsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
@Service
public class LotterySettingsService extends BaseService<LotterySettings, LotterySettingsExample> {

    @Autowired
    private LotterySettingsMapper lotterySettingsMapper;

    @Autowired
    public void setMapper() {
        super.setDao(lotterySettingsMapper);
    }

    public List<LotterySettings> findSettings() {
        LotterySettingsExample example = new LotterySettingsExample();
        example.createCriteria()
                .andDelFlagEqualTo(StateConst.FALSE);
        return this.selectByExample(example);
    }

    public Map<Integer, LotterySettings> getLotterySettingsMap() {
        List<LotterySettings> settingsList = findSettings();
        if (CollectionUtils.isEmpty(settingsList)) {
            return Collections.emptyMap();
        }
        Map<Integer, LotterySettings> map = Maps.newHashMap();
        for (LotterySettings settings : settingsList) {
            map.put(settings.getSeqNo(), settings);
        }
        return map;
    }
}
