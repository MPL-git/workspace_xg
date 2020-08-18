package com.jf.service;

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
public class TaskSuspendService {

    @Autowired
    private SysParamCfgService sysParamCfgService;

    /**
     * 定时任务是暂停：当前时间在指定配置的时间段内将返回true
     * paramName：
     * VIOLATION：违约单
     * AUTO_CONFIRM_RECEIVE:自动确认收货
     * AUTO_REFUND：退款、退货、换货
     */
    public boolean suspend(String paramName) {
        Map<String, DateRange> map = sysParamCfgService.findTaskSuspendTimeConfigMap();
        DateRange dataRange = map.get(paramName);
        if (dataRange == null || dataRange.getBegin() == null || dataRange.getEnd() == null) {
            return false;
        }
        Date now = new Date();
        return now.after(dataRange.getBegin()) && now.before(dataRange.getEnd());
    }

}
