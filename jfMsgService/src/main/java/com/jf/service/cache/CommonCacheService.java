package com.jf.service.cache;

import com.google.common.collect.Maps;
import com.jf.common.utils.DateUtil;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.service.SysParamCfgService;
import com.jf.vo.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2020/1/15
 */
@Service
public class CommonCacheService {

    @Autowired
    private SysParamCfgService sysParamCfgService;

    @Cacheable(value = "task_suspend_cache")
    public Map<String, DateRange> findTaskSuspendTimeConfigMap() {
        SysParamCfgExample example = new SysParamCfgExample();
        example.createCriteria().andParamCodeEqualTo("TASK_SUSPEND_TIME");
        List<SysParamCfg> list = sysParamCfgService.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();

        Map<String, DateRange> map = Maps.newHashMap();
        for (SysParamCfg cfg : list) {
            String value = cfg.getParamValue();
            if (!StringUtils.hasText(value)) {
                continue;
            }
            try {
                String[] dataStrs = value.split(",");
                Date begin = DateUtil.getDate(dataStrs[0]);
                Date end = DateUtil.getDate(dataStrs[1]);
                map.put(cfg.getParamName(), DateRange.of(begin, end));
            } catch (Exception e) {

            }
        }
        return map;
    }

    @CacheEvict(value = "task_suspend_cache", allEntries = true)
    public void clearTaskSuspendTimeConfigMap() {

    }

    @Cacheable(value = "kdn_query_channel_cache")
    public Map<String, String> findWuliuQuerySuspend() {
        SysParamCfgExample example = new SysParamCfgExample();
        example.createCriteria().andParamCodeEqualTo("KDN_WULIU_QUERY_CHANNEL");
        List<SysParamCfg> list = sysParamCfgService.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();

        Map<String, String> map = Maps.newHashMap();
        for (SysParamCfg cfg : list) {
            String value = cfg.getParamValue();
            if (!StringUtils.hasText(value)) {
                continue;
            }
            map.put(cfg.getParamName(), cfg.getParamValue());
        }
        return map;
    }

    @CacheEvict(value = "kdn_query_channel_cache", allEntries = true)
    public void clearFindWuliuQuerySuspend() {

    }
}
