package com.jf.common.constant;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ResourceConst {

    public static String msgServerUrl;
    public static String mUrl;

    /**
     * 初始化系统配置
     */
    public static void init(String properties) {
        try {
            Configuration configuration = new PropertiesConfiguration(properties);
            msgServerUrl = configuration.getString("msgServerUrl");
            mUrl = configuration.getString("mUrl");
        } catch (Exception e) {
            throw new RuntimeException("[加载配置文件失败]", e);
        }
    }
}
