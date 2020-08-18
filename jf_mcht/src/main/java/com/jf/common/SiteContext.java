package com.jf.common;

import com.jf.common.constant.BaseDefine;
import com.jf.entity.MchtInfoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luoyb
 * Created on 2019/9/18
 */
@Component
public class SiteContext {

    @Autowired
    private HttpServletRequest request;

    public MchtInfoCustom getSessionMchtInfo() {
        if (request.getSession().getAttribute(BaseDefine.MCHT_INFO) != null) {
            if (request.getSession().getAttribute(BaseDefine.MCHT_INFO) instanceof MchtInfoCustom) {
                return (MchtInfoCustom) request.getSession().getAttribute(BaseDefine.MCHT_INFO);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public int getMchtId() {
        MchtInfoCustom mchtInfoCustom = getSessionMchtInfo();
        return mchtInfoCustom == null ? 0 : mchtInfoCustom.getId();
    }

}
