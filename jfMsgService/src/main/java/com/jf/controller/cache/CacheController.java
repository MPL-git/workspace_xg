package com.jf.controller.cache;

import com.jf.service.cache.CacheService;
import com.jf.service.cache.CommonCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author luoyb
 * Created on 2020/1/15
 */
@Controller
public class CacheController {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private CommonCacheService commonCacheService;

    /**
     * 清除 定时任务暂停(task_suspend_cache)的缓存
     */
    @ResponseBody
    @RequestMapping(value = "/msg/cache/taskSuspendTime/clear", method = RequestMethod.POST)
    public String updateTaskSuspendTime() {
        commonCacheService.clearTaskSuspendTimeConfigMap();
        return "success";
    }

    /**
     * 清除 定时任务暂停(kdn_query_channel_cache)的缓存
     */
    @ResponseBody
    @RequestMapping(value = "/msg/cache/kdnQueryChannelCache/clear", method = RequestMethod.POST)
    public String updateKdnQueryChannelCache() {
        commonCacheService.clearFindWuliuQuerySuspend();
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/msg/cache/taskSuspendTime/{paramsName}", method = RequestMethod.POST)
    public boolean taskSuspendTime(@PathVariable("paramsName") String paramsName) {
        return cacheService.suspend(paramsName);
    }

}
