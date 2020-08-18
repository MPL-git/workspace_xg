package com.jf.service.cache;

import com.jf.vo.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2020/1/15
 */
@Service
public class CacheService {

    @Autowired
    private CommonCacheService commonCacheService;

    /**
     * 定时任务是暂停：当前时间在指定配置的时间段内将返回true
     * paramName：
     * VIOLATION：违约单
     * AUTO_CONFIRM_RECEIVE:自动确认收货
     * AUTO_REFUND：退款、退货、换货
     */
    public boolean suspend(String paramName) {
        Map<String, DateRange> map = commonCacheService.findTaskSuspendTimeConfigMap();
        DateRange dataRange = map.get(paramName);
        if (dataRange == null || dataRange.getBegin() == null || dataRange.getEnd() == null) {
            return false;
        }
        Date now = new Date();
        return now.after(dataRange.getBegin()) && now.before(dataRange.getEnd());
    }

    /**
     * 获取定时任务暂停执行时间
     */
    public DateRange findTaskSuspendDateRange(String paramName) {
        Map<String, DateRange> map = commonCacheService.findTaskSuspendTimeConfigMap();
        return map.get(paramName);
    }

    /**
     * 定时任务是暂停：sys_param_cfg中配置物流渠道的是否为该渠道
     */
    public boolean wuliuQuerySuspend(String queryChannel) {
        Map<String, String> map = commonCacheService.findWuliuQuerySuspend();
        String queryChannelConfig = map.get("WULIU_QUERY_CHANNEL");
        Boolean flag = false;
        if(queryChannel.equals(queryChannelConfig)){
            flag = true;
        }
        return flag;
    }

}
