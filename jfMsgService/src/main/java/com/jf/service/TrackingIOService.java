package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.constant.Const;
import com.jf.common.constant.KVConst;
import com.jf.common.ext.skd.TrackingioApi;
import com.jf.common.ext.util.StrKit;
import com.jf.dao.KeyValueMapper;
import com.jf.dao.ShoppingCartCustomMapper;
import com.jf.entity.KeyValue;
import com.jf.entity.MemberInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author luoyb
 * Created on 2019/11/15
 */
@Service
public class TrackingIOService {

    private static Logger logger = LoggerFactory.getLogger(TrackingIOService.class);

    @Autowired
    private ShoppingCartCustomMapper shoppingCartCustomMapper;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private KeyValueMapper keyValueMapper;

    public int countLatestAddShoppingCartHeadlineUser(String targetDate, Date searchBeginDate, Date searchEndDate, String regClient) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("targetDate", targetDate);
        params.put("searchBeginDate", searchBeginDate);
        params.put("searchEndDate", searchEndDate);
        if(Const.REG_CLIENT_IOS.equals(regClient)) {
            params.put("typeCode", KVConst.TYPE_PUSH_DAILY_ADD_SHOPPING_CART_TRACKING_IO);
        }else {
            params.put("typeCode", KVConst.TYPE_PUSH_DAILY_ADD_SHOPPING_CART_TRACKING_IO_ANDROID);
        }
        params.put("regClient", regClient);
        return shoppingCartCustomMapper.countLatestAddShoppingCartHeadlineUser(params);
    }

    public List<Integer> findLatestAddShoppingCartHeadlineUser(String targetDate, Date searchBeginDate, Date searchEndDate, Integer limit, String regClient) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("targetDate", targetDate);
        params.put("searchBeginDate", searchBeginDate);
        params.put("searchEndDate", searchEndDate);
        params.put("limit", limit);
        if(Const.REG_CLIENT_IOS.equals(regClient)) {
            params.put("typeCode", KVConst.TYPE_PUSH_DAILY_ADD_SHOPPING_CART_TRACKING_IO);
        }else {
            params.put("typeCode", KVConst.TYPE_PUSH_DAILY_ADD_SHOPPING_CART_TRACKING_IO_ANDROID);
        }
        params.put("regClient", regClient);
        return shoppingCartCustomMapper.findLatestAddShoppingCartHeadlineUser(params);
    }

    /**
     * 设备用户调用
     */
    @Transactional
    public boolean pushAddShoppingCartHeadlineUser(String targetDate, Integer memberId, String regClient) {
        MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
        if (memberInfo == null || StrKit.isBlank(memberInfo.getReqImei())) {
            logger.info("用户{}信息不全推送失败：", memberId);
            return false;
        }
        Map<String, Object> context = new HashMap<>();
        context.put("_deviceid", memberInfo.getReqImei()); // _deviceid为基本参数，是用来记录用户设备身份信息（IOS获取idfa，Android获取IMEI或者MAC地址或者AndroidID，但要保证一致）
        context.put("_transactionid", UUID.randomUUID().toString()); // 交易流水号，请确保唯一（随机生成）
        context.put("_paymenttype", "alipay"); // 支付类型（写死假数据 alipay）
        context.put("_currencytype", "CNY"); // 货币类型 （写死假数据 CNY）
        context.put("_currencyamount", 1.0); // 支付的真实货币金额，人民币单位：元 (写死假数据 1.0)
        context.put("_ip", memberInfo.getRegIp());
        context.put("_tz", "+8");
        context.put("_ipv6", "");
        context.put("_rydevicetype", memberInfo.getReqMobileModel()); // 设备类型如iphone5s、sansung-GT9300
        boolean success = false;
        if(Const.REG_CLIENT_IOS.equals(regClient)) {
            context.put("_idfa", memberInfo.getReqImei()); // ios必填
            success = TrackingioApi.commitPayInfo(TrackingioApi.APPKEY_IOS, String.valueOf(memberId), context);
        }else {
            context.put("_imei", memberInfo.getReqImei()); // android必填
            context.put("_androidid", memberInfo.getReqImei()); // android必填
            context.put("_oaid", memberInfo.getReqImei()); // android必填
            success = TrackingioApi.commitPayInfo(TrackingioApi.APPKEY_ANDROID, String.valueOf(memberId), context);
        }
        if (!success) {
            logger.info("用户{}推送失败", memberId);
            return false;
        }
        savePushAddShoppingCartHeadlineUser(targetDate, memberId, regClient);
        return true;
    }

    private void savePushAddShoppingCartHeadlineUser(String targetDate, Integer memberId, String regClient) {
        KeyValue keyValue = new KeyValue();
        if(Const.REG_CLIENT_IOS.equals(regClient)) {
            keyValue.setTypeCode(KVConst.TYPE_PUSH_DAILY_ADD_SHOPPING_CART_TRACKING_IO);
        }else {
            keyValue.setTypeCode(KVConst.TYPE_PUSH_DAILY_ADD_SHOPPING_CART_TRACKING_IO_ANDROID);
        }
        keyValue.setmKey(targetDate);
        keyValue.setmValue(String.valueOf(memberId));
        keyValue.setReservedInt(0);
        keyValue.setCreateDate(new Date());
        keyValue.setDelFlag(Const.FLAG_FALSE);
        keyValueMapper.insert(keyValue);
    }
}
