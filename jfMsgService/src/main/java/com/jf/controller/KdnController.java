package com.jf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.core.BaseController;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.skd.KdApi;
import com.jf.common.ext.skd.LogisticInfo;
import com.jf.common.ext.util.JsonKit;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.Express;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoCustom;
import com.jf.entity.dto.ZzySubscribeDTO;
import com.jf.service.CombineOrderService;
import com.jf.service.ExpressService;
import com.jf.service.KdnWuliuInfoService;
import com.jf.service.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 快递鸟
 */
@Controller
public class KdnController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(KdnController.class);

	@Resource
	private KdnWuliuInfoService kdnWuliuInfoService;
	@Resource
	private ExpressService expressService;
	@Resource
	private CombineOrderService combineOrderService;
	@Resource
	private CacheService cacheService;

	@ResponseBody
	@RequestMapping("api/kdn")
	public String index() {
		Map<String, Object> data = new HashMap<>();
		data.put("welcome", "欢迎来到物流中心");
		return json(data);
	}

	@ResponseBody
	@RequestMapping("api/kdn/subscribe")
	public String subscribe() {
		JSONObject reqData = getReqData();
		String orderNumber = reqData.getString("orderNumber");
		String shipperCode = reqData.getString("shipperCode");
		String logisticCode = reqData.getString("logisticCode");
		try {
			CombineOrderCustom combineOrderCustom = null;
			if(shipperCode.equals("YD") || shipperCode.equals("SF")){
				//韵达,顺丰
				Express express = expressService.findByKdnCode(shipperCode);
				KdnWuliuInfo model = kdnWuliuInfoService.findByLogisticCode(express.getId(), logisticCode);
				if(model != null && model.getSubOrderId() != null){
					combineOrderCustom = combineOrderService.getOrderAddressInfo(model.getSubOrderId());
				}
			}else if(shipperCode.equals("JD")){
				//京东
				Express express = expressService.findByKdnCode(shipperCode);
				KdnWuliuInfo wuliuInfo = kdnWuliuInfoService.findByLogisticCode(express.getId(), logisticCode.trim());
				combineOrderCustom.setMerchantCode(wuliuInfo.getMerchantCode());
			}
			KdApi.subscribe(orderNumber, shipperCode, logisticCode,combineOrderCustom);
		} catch (Exception e) {
			throw new BizException("订阅快递鸟物流信息出错");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("subscribeInfo", "订阅成功");
		return json(data);
	}

	@ResponseBody
	@RequestMapping("api/kdn/query")
	public String queryLogistic() {
		Map<String, Object> data = new HashMap<>();

		JSONObject reqData = getReqData();
		String shipperCode = reqData.getString("shipperCode");
		String logisticCode = reqData.getString("logisticCode");
		try {
			CombineOrderCustom combineOrderCustom = new CombineOrderCustom();
			if("SF".equals(shipperCode)){
				combineOrderCustom = combineOrderService.getCombineOrderCustomByLogisticCode(shipperCode, logisticCode);
			}else if("JD".equals(shipperCode)){
				Express express = expressService.findByKdnCode(shipperCode);
				KdnWuliuInfo wuliuInfo = kdnWuliuInfoService.findByLogisticCode(express.getId(), logisticCode.trim());
				combineOrderCustom.setMerchantCode(wuliuInfo.getMerchantCode());
			}
			String res = KdApi.queryTraces(shipperCode, logisticCode, combineOrderCustom);
			LogisticInfo logisticInfo = JSONObject.parseObject(res, LogisticInfo.class);
			data.put("logisticInfo", logisticInfo);

			// 更新物流信息
			KdnWuliuInfo kdnWuliuInfo = kdnWuliuInfoService.update(logisticInfo);
			data.put("kdnWuliuInfo", kdnWuliuInfo);
		} catch (Exception e) {
			throw new BizException("查询快递鸟物流信息出错");
		}

		return json(data);
	}
	
	@ResponseBody
	@RequestMapping("api/kdn/queryKdn")
	public ResponseMsg queryKdn() {
		JSONObject reqData = getReqData();
		Integer id = reqData.getInteger("id");
		try {
			KdnWuliuInfo kdnWuliuInfo = kdnWuliuInfoService.findById(id);
			Express express = expressService.findById(kdnWuliuInfo.getExpressId());
			CombineOrderCustom combineOrderCustom = new CombineOrderCustom();
			if("SF".equals(express.getKdnCode())){
				combineOrderCustom = combineOrderService.getCombineOrderCustomByLogisticCode(express.getKdnCode(), kdnWuliuInfo.getLogisticCode());
			}else if("JD".equals(express.getKdnCode())){
				combineOrderCustom.setMerchantCode(kdnWuliuInfo.getMerchantCode());
			}
			String res = KdApi.queryTraces(express.getKdnCode(), kdnWuliuInfo.getLogisticCode(), combineOrderCustom);
			LogisticInfo logisticInfo = JSONObject.parseObject(res, LogisticInfo.class);
			if(logisticInfo.getTraces().length() > 10){
				kdnWuliuInfo = kdnWuliuInfoService.updateKdn(logisticInfo,kdnWuliuInfo);
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
			}else{
				return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
			}
		} catch (Exception e) {
			throw new BizException("查询快递鸟物流信息出错");
		}
	}

	@ResponseBody
	@RequestMapping("kdn/callBack")
	public String callBack() {
		String requestType = getPara("RequestType");
		//1008的返回是101
		//8008的返回是102   目前是8008
		/*if(!requestType.equals("101") && !requestType.equals("102")){
			throw new BizException("不合法的回调");
		}*/
		JSONObject requestData = JSONObject.parseObject(getPara("RequestData"));
		if(requestData == null){
			throw new BizException("快递鸟回调参数为空");
		}
		String eBusinessID = requestData.getString("EBusinessID");
		if(!eBusinessID.equals(KdApi.EBusinessID)){
			throw new BizException("不合法的回调");
		}




		JSONArray logisticArray = requestData.getJSONArray("Data");
		LogisticInfo logisticInfo;
		for(int i=0; i < logisticArray.size(); i++){
			logisticInfo = JSONObject.parseObject(logisticArray.getString(i), LogisticInfo.class);
			// 更新物流信息
			if(logisticInfo != null && !StringUtil.isBlank(logisticInfo.getTraces())){
				if(logisticInfo.getTraces().length() > 10){
					kdnWuliuInfoService.update(logisticInfo);
				}
			}
		}
		//Map<String, Object> data = new HashMap<>();
		//return json(data);

		Map<String, Object> data = new HashMap<>();
		data.put("EBusinessID", KdApi.EBusinessID);
		data.put("Success", true);
		return JsonKit.toJson(data);

	}

	@ResponseBody
	@RequestMapping("api/kdn/queryLogisticByDate")
	public ResponseMsg queryLogisticByDate() {
	      try {
	  		JSONObject reqData = getReqData();
			String beginDate = reqData.getString("beginDate");
			String endDate = reqData.getString("endDate");
			
		    if(StringUtil.isEmpty(beginDate)||StringUtil.isEmpty(endDate)){
		    	throw new ArgException("时间不能为空");
		    }
			
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("beginDate", DateUtil.getDate(beginDate));
			paramMap.put("endDate", DateUtil.getDate(endDate));
	    	List<KdnWuliuInfoCustom> list = kdnWuliuInfoService.getKdnLogisticsInfoByDate(paramMap);
		      logger.info("扫描到的已订阅并且需要实时同步的运单数：{}", list.size());
		      for(KdnWuliuInfoCustom kdnWuliuInfo : list){
		    	  try {
					  System.out.println("更新物流信息:"+kdnWuliuInfo.getLogisticCode());
					  CombineOrderCustom combineOrderCustom = new CombineOrderCustom();
					  if("SF".equals(kdnWuliuInfo.getKdnCode())){
						  combineOrderCustom = combineOrderService.getCombineOrderCustomByLogisticCode(kdnWuliuInfo.getKdnCode(), kdnWuliuInfo.getLogisticCode());
					  }else if("JD".equals(kdnWuliuInfo.getKdnCode())){
						  combineOrderCustom.setMerchantCode(kdnWuliuInfo.getMerchantCode());
					  }
					  String res = KdApi.queryTraces(kdnWuliuInfo.getKdnCode(), kdnWuliuInfo.getLogisticCode(), combineOrderCustom);
					  System.out.println(kdnWuliuInfo.getLogisticCode()+"成功调用接口返回数据:"+res);
					  LogisticInfo logisticInfo = JSONObject.parseObject(res, LogisticInfo.class);
						if(logisticInfo.getTraces().length() > 10){
							kdnWuliuInfoService.update(logisticInfo);
							System.out.println("更新物流信息:"+kdnWuliuInfo.getLogisticCode()+"更新成功");
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
		      }
			} catch (Exception e) {
				return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
			}
	      return new ResponseMsg(ResponseMsg.SUCCESS,"查询成功");
	}

	/**
	 * @MethodName: zzySubscribe
	 * @Description: 订阅猪猪云物流信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	//@RequestMapping("/kdn/zzySubscribe")
	public ResponseMsg zzySubscribe() {
		try{
			logger.info("已发货的订单订阅猪猪云物流信息(快递100查询失败的物流信息):start");

			HashMap<String, Object> paramMap = new HashMap<>();
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-15);
			Date date=cal.getTime();
			paramMap.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			paramMap.put("tryTimes", "5");

			kdnWuliuInfoService.zzySubscribe(paramMap);

			logger.info("已发货的订单订阅猪猪云物流信息(快递100查询失败的物流信息):end");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

	/**
	 * @MethodName: zzyAgainQuert
	 * @Description: 查询猪猪云物流信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	//@RequestMapping("/kdn/zzyAgainQuert")
	public ResponseMsg zzyAgainQuert() {
		try{
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
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

	/**
	 * @MethodName: zzyMainSubscribe
	 * @Description: 订阅猪猪云物流信息(全部查询)
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/kdn/zzyMainSubscribe")
	public ResponseMsg zzyMainSubscribe() {
		if (!cacheService.wuliuQuerySuspend(Const.WULIU_QUERY_CHANNEL_ZZY)) {
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		try{
			logger.info("已发货的订单订阅猪猪云物流信息(全部查询):start");

			HashMap<String, Object> paramMap = new HashMap<>();
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-10);
			Date date=cal.getTime();
			paramMap.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

			kdnWuliuInfoService.zzyBatchSubscribe(paramMap);

			logger.info("已发货的订单订阅猪猪云物流信息(全部查询):end");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

	/**
	 * @MethodName: zzyMainAgainQuert
	 * @Description: 查询猪猪云物流信息(全部查询)
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/kdn/zzyMainAgainQuert")
	public ResponseMsg zzyMainAgainQuert() {
		if (!cacheService.wuliuQuerySuspend(Const.WULIU_QUERY_CHANNEL_ZZY)) {
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		try{
			HashMap<String, Object> paramMap = new HashMap<>();
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,-10);
			//cal.add(Calendar.HOUR,-1);
			Date date=cal.getTime();
			paramMap.put("createDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			paramMap.put("subscribeStatus", "1");
			kdnWuliuInfoService.zzyBatchAgainQuert(paramMap);

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
}
