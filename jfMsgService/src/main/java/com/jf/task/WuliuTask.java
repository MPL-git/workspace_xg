package com.jf.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.constant.Const;
import com.jf.common.ext.RegCondition;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.ext.skd.KdApi;
import com.jf.common.ext.skd.LogisticInfo;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoCustom;
import com.jf.entity.dto.ZzySubscribeDTO;
import com.jf.service.CombineOrderService;
import com.jf.service.KdnWuliuInfoService;
import com.jf.service.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 订阅物流任务
 *
 * @auther hdl
 */
@RegCondition
@Component
public class WuliuTask {

    private static Logger logger = LoggerFactory.getLogger(WuliuTask.class);

//    @Resource
//    private SubOrderService subOrderService;
    @Resource
    private KdnWuliuInfoService kdnWuliuInfoService;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private CombineOrderService combineOrderService;

    /**
     * 已发货的订单订阅物流信息
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public void subscribe(){
        if (!cacheService.wuliuQuerySuspend(Const.WULIU_QUERY_CHANNEL_KDN)) {
            return;
        }
        logger.info("已发货的订单订阅物流信息:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("subscribeStatus", Const.FLAG_FALSE);
        queryObject.addQuery("tryTimesLessThan", 5);
        //queryObject.addQuery("deliveryDateBeforMinutes", 30);
        List<KdnWuliuInfo> list = kdnWuliuInfoService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());

        //List<SubOrder> subOrderList;
        for(KdnWuliuInfo kdnWuliuInfo : list){
//            queryObject = new QueryObject();
//            queryObject.addQuery("status", Const.ORDER_STATUS_SHIPPED);
//            queryObject.addQuery("expressId", kdnWuliuInfo.getExpressId() + "");
//            queryObject.addQuery("expressNo", kdnWuliuInfo.getLogisticCode());
//            subOrderList = subOrderService.findList(queryObject);
            try {
				kdnWuliuInfoService.subscribe(null, kdnWuliuInfo.getExpressId(), kdnWuliuInfo.getLogisticCode());
			} catch (Exception e) {
				logger.info("物流订阅出错,物流单id为："+kdnWuliuInfo.getId());
				e.printStackTrace();
			}
        }

        logger.info("已发货的订单订阅物流信息:end");
    }
    
    /**
     * 实时查询运单信
     * 分别为：06：50、12：50、18：50、23：50执行一次
     */
      @Scheduled(cron="0 50 6,12,18,23 * * ?")
	  public void kdnAgainQuert(){
          if (!cacheService.wuliuQuerySuspend(Const.WULIU_QUERY_CHANNEL_KDN)) {
              return;
          }
	      logger.info("实时查询运单信息:start");
	      try {
	    	  List<KdnWuliuInfoCustom> list = kdnWuliuInfoService.getKdnLogisticsInfo();
		      logger.info("扫描到的已订阅并且需要实时同步的运单数：{}", list.size());
		      for(KdnWuliuInfoCustom kdnWuliuInfo : list){
                  CombineOrderCustom combineOrderCustom = new CombineOrderCustom();
                  if("SF".equals(kdnWuliuInfo.getKdnCode())){
                      combineOrderCustom = combineOrderService.getCombineOrderCustomByLogisticCode(kdnWuliuInfo.getKdnCode(), kdnWuliuInfo.getLogisticCode());
                      if(combineOrderCustom == null || combineOrderCustom.getId() == null){
                          continue;
                      }
                  }else if("JD".equals(kdnWuliuInfo.getKdnCode())){
                      combineOrderCustom.setMerchantCode(kdnWuliuInfo.getMerchantCode());
                  }
                  String res = KdApi.queryTraces(kdnWuliuInfo.getKdnCode(), kdnWuliuInfo.getLogisticCode(), combineOrderCustom);
		    	  LogisticInfo logisticInfo = JSONObject.parseObject(res, LogisticInfo.class);
					if(logisticInfo.getTraces().length() > 10){
						kdnWuliuInfoService.update(logisticInfo);
					}
		      }
			} catch (Exception e) {
				logger.info("推送异常",e.getMessage());
			}
	      logger.info("实时查询运单信息:end");
	  }

	/**
	 * 已发货的订单订阅猪猪云物流信息(快递100查询失败的物流信息)
	 *
	 * 分别为：05：50、11：50、17：50、22：50执行一次
	 */
    //@Scheduled(cron="0 50 5,11,17,22 * * ?")
    @Deprecated
	public void zzySubscribe(){
		logger.info("已发货的订单订阅猪猪云物流信息(快递100查询失败的物流信息):start");

		HashMap<String, Object> paramMap = new HashMap<>();
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-15);
		Date date=cal.getTime();
		paramMap.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		paramMap.put("tryTimes", "5");

		kdnWuliuInfoService.zzySubscribe(paramMap);

		logger.info("已发货的订单订阅猪猪云物流信息(快递100查询失败的物流信息):end");
	}

    /**
     * 猪猪云物流信息实时查询
     * 分别为：06：50、12：50、18：50、23：50执行一次
     */
    //@Scheduled(cron="0 50 6,12,18,23 * * ?")
    @Deprecated
    public void zzyAgainQuert(){
        logger.info("猪猪云物流信息实时查询:start");
        try {
            HashMap<String, Object> paramMap = new HashMap<>();
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.DATE,-15);
            cal.add(Calendar.HOUR,-1);
            Date date=cal.getTime();
            paramMap.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            paramMap.put("tryTimes", "6");
            paramMap.put("subscribeStatus", "1");
            List<ZzySubscribeDTO> list = kdnWuliuInfoService.findSelectByZZYList(paramMap);
            logger.info("扫描到的已订阅并且需要实时同步的运单数：{}", list.size());
            for (ZzySubscribeDTO zzySubscribeDTO : list) {
                String res = KdApi.zzyAgainQuert(zzySubscribeDTO.getExpressCompanyCode(), zzySubscribeDTO.getExpressCode());
                HashMap<String, Object> map = new HashMap<>();
                JSONObject jsonObject = JSON.parseObject(res);
                if(Objects.equals("1",jsonObject.getString("code"))){
                    JSONObject resJson = JSON.parseObject(jsonObject.getString("msg"));
                    String wuLiuZhuangTai = resJson.getString("wuliuzhuangtai");
                    String xiangXiWuLiu = resJson.getString("xiangxiwuliu");
                    if(Objects.equals("运输中",wuLiuZhuangTai)){
                        map.put("status","2");
                        String objJson = kdnWuliuInfoService.parseZzyResult(xiangXiWuLiu);
                        map.put("tractInfo",objJson);
                    }else if (Objects.equals("已签收",wuLiuZhuangTai) || Objects.equals("代收",wuLiuZhuangTai)){
                        map.put("status","3");
                        String objJson = kdnWuliuInfoService.parseZzyResult(xiangXiWuLiu);
                        map.put("tractInfo",objJson);
                    }else if (Objects.equals("无物流",wuLiuZhuangTai) || Objects.equals("疑似无物流",wuLiuZhuangTai)){
                        map.put("status","1");
                    }else if (Objects.equals("异常件",wuLiuZhuangTai)){
                        map.put("status","4");
                    }
                    map.put("kdnId",zzySubscribeDTO.getId());
                    map.put("updateDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    map.put("updateBy",1);
                    kdnWuliuInfoService.updateKdnByExample(map);
                }
            }
        } catch (Exception e) {
            logger.info("推送异常：{}",e.getMessage());
        }
        logger.info("猪猪云物流信息实时查询:end");
    }

	/**
	 * 已发货的订单订阅猪猪云物流信息(全部查询)
	 *
	 * 分别为：03:00、09:00、15:00、21:00执行一次
	 */
    @Scheduled(cron="0 00 3,9,15,21 * * ?")
	public void zzyMainSubscribe(){
        if (!cacheService.wuliuQuerySuspend(Const.WULIU_QUERY_CHANNEL_ZZY)) {
            return;
        }
        logger.info("已发货的订单订阅猪猪云物流信息(全部查询):start");

        HashMap<String, Object> paramMap = new HashMap<>();
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-10);
        Date date=cal.getTime();
        paramMap.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        kdnWuliuInfoService.zzyBatchSubscribe(paramMap);

        logger.info("已发货的订单订阅猪猪云物流信息(全部查询):end");
	}

    /**
     * 猪猪云物流信息实时查询(全部查询)
     * 分别为：04:00、10:00、16:00、22:00执行一次
     */
    @Scheduled(cron="0 00 4,10,16,22 * * ?")
    public void zzyMainAgainQuert(){
        if (!cacheService.wuliuQuerySuspend(Const.WULIU_QUERY_CHANNEL_ZZY)) {
            return;
        }
        logger.info("猪猪云物流信息实时查询:start");
        try {
            HashMap<String, Object> paramMap = new HashMap<>();
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.DATE,-10);
            //cal.add(Calendar.HOUR,-1);
            Date date=cal.getTime();
            paramMap.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            paramMap.put("subscribeStatus", "1");
            kdnWuliuInfoService.zzyBatchAgainQuert(paramMap);

        } catch (Exception e) {
            logger.info("推送异常：{}",e.getMessage());
        }
        logger.info("猪猪云物流信息实时查询:end");
    }
}
