package com.jf.common;

import com.alibaba.fastjson.JSON;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * @author luoyb
 * Created on 2019/9/7
 */
@Component
public class AppContext {

    private static final String REQ_PRM = "reqPRM";
    private static final String VERSION = "version";
    private static final String SYSTEM = "system";
    private static final String REQ_DATA = "reqData";
    private static final String SYSTEM_VERSION = "systemVersion";

    @Autowired
    private HttpServletRequest httpServletRequest;

    public JSONObject reqPRM() {
        return (JSONObject) httpServletRequest.getAttribute(REQ_PRM);
    }

    public JSONObject reqData() {
        return reqPRM().getJSONObject(REQ_DATA);
    }

    public String systemVersion() {
        return reqPRM().has(SYSTEM_VERSION) ? reqPRM().getString(SYSTEM_VERSION) : null;
    }

    //版本号
    public String version() {
        return reqPRM().optString(VERSION);
    }

    public Integer intVersion() {
        String version = version();
        if (version == null) return 0;
        try {
            return Integer.parseInt(version);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    //系统类型
    public String system() {
        return reqPRM().optString(SYSTEM);
    }

    //低版本判断
    public boolean olderThan(int iosVersion, int androidVersion) {
        String system = system();
        Integer version = intVersion();
        return (Const.IOS.equals(system) && version < iosVersion) || (Const.ANDROID.equals(system) && version < androidVersion);
    }

    //请求参数 获取
    public <T> T getRequest(Class<T> clazz) {
        return request(clazz, false);
    }

    //请求参数 获取并校验
    public <T> T getRequestAndValidate(Class<T> clazz) {
        return request(clazz, true);
    }

    private <T> T request(Class<T> clazz, boolean valid) {
        JSONObject reqPRM = (JSONObject) httpServletRequest.getAttribute(REQ_PRM);
        T result = JSON.parseObject(reqPRM.optString(REQ_DATA, "{}"), clazz);
        if (valid) {
            validateRequest(result);
        }
        return result;
    }

    private <T> void validateRequest(T target) {
        Set<ConstraintViolation<T>> validateSet = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(target, new Class[0]);
        if (!CollectionUtils.isEmpty(validateSet)) {
            StringBuilder errorMsg = new StringBuilder();
            for (ConstraintViolation<T> violation : validateSet) {
                if (errorMsg.length() > 0) errorMsg.append(";");
                errorMsg.append(violation.getMessage());
            }
            if (errorMsg.length() == 0) {
                errorMsg.append("请求参数有误！");
            }
            throw new BizException(errorMsg.toString());
        }
    }

    public Integer optInteger(JSONObject reqData, String name) {
        if (reqData.has(name)) {
            return reqData.optInt(name);
        }
        return null;
    }

    public String getIpAddr() {
        return StringUtil.getIpAddr(httpServletRequest);
    }
}
