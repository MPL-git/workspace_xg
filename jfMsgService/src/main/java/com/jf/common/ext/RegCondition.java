package com.jf.common.ext;

import com.jf.common.enumerate.RuntimeEnv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luoyb
 * Created on 2019/11/18
 * <p>
 * 用于控制不同运行环境下 定时任务Task 是否扫描注册：
 * 1 PROD 仅生产环境 下注册
 * 2 TEST 测试及生产环境 下注册
 * 3 LOCAL 所有环境下都会注册
 * --------------------------------------------
 * 注意：
 * 该注解仅支持到 类 控制(所以不同注册等级的Scheduled不要放在同一个task中)
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RegCondition {

    RuntimeEnv level() default RuntimeEnv.TEST;

}
