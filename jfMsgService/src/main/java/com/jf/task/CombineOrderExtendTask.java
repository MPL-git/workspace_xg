package com.jf.task;

import com.jf.common.constant.Const;
import com.jf.common.ext.RegCondition;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.skd.TrackingioApi;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExtendExt;
import com.jf.entity.CombineOrderExtendExtExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.SysParamCfg;
import com.jf.entity.TrackData;
import com.jf.service.CombineOrderExtendService;
import com.jf.service.CombineOrderService;
import com.jf.service.MemberInfoService;
import com.jf.service.SysParamCfgService;
import com.jf.service.SysPaymentService;
import com.jf.service.TrackDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扩展订单任务
 *
 * @auther hdl
 */
@RegCondition
@Component
public class CombineOrderExtendTask {

    private static Logger logger = LoggerFactory.getLogger(CombineOrderExtendTask.class);

    @Autowired
    private CombineOrderService combineOrderService;
    @Autowired
    private CombineOrderExtendService combineOrderExtendService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private TrackDataService trackDataService;
    @Autowired
    private SysParamCfgService sysParamCfgService;
    @Autowired
    private SysPaymentService sysPaymentService;



    /**
     * 总订单扩展表信息补全
     *
     * 每隔30分钟执行一次， 每次处理1万条数据
     */
    @Scheduled(cron="0 0/30 * * * ?")
    public void fillTrackData(){
        String task = "总订单扩展表补全推广活动名称、活动组、渠道名称";
        logger.info("任务{}:开始", task);
        int max = 10000;    //限制本次任务最多可处理的记录数

        String paramCode = "COMBINE_ORDER_FILL_TRACKDATA_ID";
        int lastCombineOrderId = findLastId(paramCode);

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("idGreaterThan", lastCombineOrderId);
        queryObject.addQuery("existsTrackData", true);
        queryObject.addSort("id", QueryObject.SORT_ASC);
        long count = combineOrderService.count(queryObject);
        if(count == 0){
            logger.info("需要处理的记录为0，退出任务。");
            return;
        }

        logger.info("需要处理的记录为{}", count);


        List<CombineOrder> list;
        int processedCount = 0;
        while (true){
            if(processedCount >= max)   break;

            queryObject.addQuery("idGreaterThan", lastCombineOrderId);
            queryObject.setLimitSize(1000);
            list = combineOrderService.findList(queryObject);
            if(list.size() == 0)    break;

            MemberInfo memberInfo;
            TrackData trackData;
            for(CombineOrder info : list){
                processedCount++;
                lastCombineOrderId = info.getId();

                memberInfo = memberInfoService.findMemberById(info.getMemberId());
                if(memberInfo == null || StrKit.isBlank(memberInfo.getReqImei())) continue;
                trackData = trackDataService.findByIdfa(memberInfo.getReqImei());
                if(trackData == null) {
                    trackData = trackDataService.findByImei(memberInfo.getReqImei());
                }
                if(trackData == null)   continue;

                combineOrderExtendService.saveTrackData(info.getId(), trackData);

                logger.info("处理第{}条id为[{}]的数据成功", processedCount, info.getId());
                if(processedCount >= max)   break;
            }

            sysParamCfgService.saveByCode(paramCode, "总订单表扩展活动信息按从小到大已处理到的ID", String.valueOf(lastCombineOrderId));
        }

        logger.info("任务{}:结束", task);
    }


    /**
     * 用户付款信息上报热云
     *
     * 每隔30分钟执行一次， 每次处理1万条数据
     * ps：已替换成{@link TrackingIOTask#pushDailyAddShoppingCartToTrackingIO()} 推送内容
     */
//    @Scheduled(cron="0 3/30 * * * ?")
    public void commitPayInfoToThackingio(){
        String task = "用户付款信息上报热云";
        logger.info("任务{}:开始", task);
        int max = 10000;    //限制本次任务最多可处理的记录数

        String paramCode = "COMBINE_ORDER_EXTEND_COMMIT_TO_RY_ID";
        int lastId = findLastId(paramCode);

        CombineOrderExtendExtExample example = new CombineOrderExtendExtExample();
        CombineOrderExtendExtExample.CombineOrderExtendExtCriteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(lastId);
        criteria.andChannelEqualTo("今日头条");
        criteria.andCombineOrderStatusEquals(Const.COMBINE_ORDER_STATUS_PAID);
        example.getQueryObject().addSort("id", QueryObject.SORT_ASC);
        long count = combineOrderExtendService.count(example);
        if(count == 0){
            logger.info("需要处理的记录为0，退出任务。");
            return;
        }

        logger.info("需要处理的记录为{}", count);


        List<CombineOrderExtendExt> list;
        int processedCount = 0;
        while (true){
            if(processedCount >= max)   break;

            criteria.andIdGreaterThan(lastId);
            example.getQueryObject().setLimitSize(1000);
            list = combineOrderExtendService.list(example);
            if(list.size() == 0)    break;

            MemberInfo memberInfo;
            CombineOrder combineOrder;
            for(CombineOrderExtendExt info : list){
                processedCount++;
                lastId = info.getId();

                combineOrder = combineOrderService.findById(info.getCombineOrderId());
                if(combineOrder.getPaymentId() == null) continue;

                memberInfo = memberInfoService.findMemberById(combineOrder.getMemberId());
                if(memberInfo == null || StrKit.isBlank(memberInfo.getReqImei())) continue;
//                if(StrKit.isBlank(memberInfo.getReqMobileBrand()))  continue;
//                if(!memberInfo.getReqMobileBrand().equals("苹果")) continue;

                Map<String, Object> context = new HashMap<>();
                context.put("_deviceid", memberInfo.getReqImei());	// 设备ID，最长128位，IOS：内容填写idfa的值。Android：内容填写imei的值，无则填写anroidid的
                context.put("_transactionid", combineOrder.getCombineOrderCode());	//交易流水号，请确保唯一
                context.put("_paymenttype", sysPaymentService.findById(combineOrder.getPaymentId()).getName());	//支付类型，最长64位，例如支付宝(alipay)，银联(unionpay)，微信支付（weixinpay）， FREE（FREE表示不统计付费）
                context.put("_currencytype", "CNY");	// 货币类型，按照国际标准组织ISO 4217中规范的3位字母，例如CNY人民币、USD
                //context.put("_currencyamount", combineOrder.getTotalPayAmount().floatValue());	//支付的真实货币金额，人民币单位：元
                // 目前写死1，不上报真实金额 2019-09-27
                context.put("_currencyamount", 1.0);	//支付的真实货币金额，人民币单位：元
                context.put("_ip", memberInfo.getRegIp());
                context.put("_ipv6", "");
                context.put("_tz", "+8");
                context.put("_rydevicetype", memberInfo.getReqMobileModel());	// 设备类型如iphone5s、sansung-GT9300

                // 目前所有数据都当成ios来上报
                context.put("_idfa", memberInfo.getReqImei());	// ios必填
                boolean b = TrackingioApi.commitPayInfo(TrackingioApi.APPKEY_IOS, String.valueOf(memberInfo.getId()), context);
//                boolean b;
//                if(memberInfo.getReqMobileBrand().equals("苹果")){
//                    context.put("_idfa", memberInfo.getReqImei());	// ios必填
//                    //logger.info("ios:memberId:{},content:{}", memberInfo.getId(), JSONObject.toJSONString(context));
//                    b = TrackingioApi.commitPayInfo(TrackingioApi.APPKEY_IOS, String.valueOf(memberInfo.getId()), context);
//                }else{
//                    context.put("_imei", memberInfo.getReqImei());	// android必填
//                    context.put("_androidid", "");	// android必填
//                    context.put("_mac", "");	// android必填
//                    //logger.info("android:memberId:{},content:{}", memberInfo.getId(), JSONObject.toJSONString(context));
//                    b = TrackingioApi.commitPayInfo(TrackingioApi.APPKEY_ANDROID, String.valueOf(memberInfo.getId()), context);
//                }

                combineOrderExtendService.saveTrackingio(info.getCombineOrderId(), b?"1":"2");

                logger.info("处理第{}条id为[{}]的数据成功", processedCount, info.getId());
                if(processedCount >= max)   break;
            }

            sysParamCfgService.saveByCode(paramCode, "总订单表扩展表上报热云按从小到大已处理到的ID", String.valueOf(lastId));
        }

        logger.info("任务{}:结束{}", task);
    }




    /**
     * 查找上次已处理的ID
     */
    private int findLastId(String paramCode){
        int lastId = 0;
        SysParamCfg sysParamCfg = sysParamCfgService.findByCode(paramCode);
        if(sysParamCfg != null && StrKit.notBlank(sysParamCfg.getParamValue())){
            lastId = Integer.valueOf(sysParamCfg.getParamValue());
        }
        return lastId;
    }


}
