package com.jf;

import com.jf.common.ext.EnvFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author luoyb
 * Created on 2019/11/18
 */
@Configuration
@EnableScheduling
@ComponentScan(value = "com.jf.task", useDefaultFilters = false, includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = EnvFilter.class)})
public class ScheduleConfig {

    /**
     * 线程池配置
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("schedule-");
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setAwaitTerminationSeconds(60);
        taskScheduler.initialize();
        return taskScheduler;
    }

}
