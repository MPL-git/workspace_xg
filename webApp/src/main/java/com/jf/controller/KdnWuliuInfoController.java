package com.jf.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Express;
import com.jf.entity.ExpressExample;
import com.jf.entity.KdnWuliuInfoCustom;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.SubOrder;
import com.jf.entity.dto.TactInfoModel;
import com.jf.service.ExpressService;
import com.jf.service.KdnWuliuInfoService;
import com.jf.service.OrderDtlService;
import com.jf.service.SortClass;
import com.jf.service.SubOrderService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月5日 下午7:56:55 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class KdnWuliuInfoController extends BaseController{
	
	@Resource
	private KdnWuliuInfoService kdnWuliuInfoService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private ExpressService expressService;
	@Resource
	private OrderDtlService orderDtlService;
	
	
	/**
	 * 
	 * 方法描述 ：获取快递信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月5日 下午8:13:50 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getExpressInfo")
	@ResponseBody
	public ResponseMsg getExpressInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"expressNo","subOrderId"};
			this.requiredStr(obj,request);
			
			//快递id
			Integer expressId = null;
			if(!JsonUtils.isEmpty(reqDataJson, "expressId")){
				expressId = reqDataJson.getInt("expressId");
			}
			//快递单号

			String expressNo = reqDataJson.getString("expressNo");
			//子订单id
			Integer subOrderId = reqDataJson.getInt("subOrderId");
			String tractInfo = "";
			String status = "";
			String statusName = "";
			String expressCompany = "";
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("expressId", expressId);
			params.put("expressNo", expressNo);
			Map<String, Object> returnData = new HashMap<String, Object>();
			KdnWuliuInfoCustom kdnWuliuInfoCustom = kdnWuliuInfoService.getExpressInfo(params);
			if(kdnWuliuInfoCustom != null){
				SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
				List<TactInfoModel> listTacInfo = new ArrayList<TactInfoModel>();
				TactInfoModel tactInfoModel = new TactInfoModel();
				//TODO 数据问题 先设置为默认值，到时候改正过来
				String deliveryDate = DateUtil.getFormatDate(subOrder.getDeliveryDate(), "yyyy-MM-dd HH:mm:ss");
				if(kdnWuliuInfoCustom != null && kdnWuliuInfoCustom.getSubscribeStatus().equals("1")){
					tractInfo = kdnWuliuInfoCustom.getTractInfo() == null ? "" : kdnWuliuInfoCustom.getTractInfo();
					String kdnStatus = kdnWuliuInfoCustom.getStatus();
					if (kdnStatus.equals("0")) {
						//特定参数
						JSONObject param = new JSONObject();
						JSONObject reqData=new JSONObject();
						reqData.put("id", kdnWuliuInfoCustom.getId());
						param.put("reqData", reqData);
						//调用快鸟接口
						JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/kdn/queryKdn", CommonUtil.createReqData(param).toString()));
						if("0000".equals(result.getString("returnCode"))){
							kdnWuliuInfoCustom = kdnWuliuInfoService.getExpressInfo(params);
							tractInfo = kdnWuliuInfoCustom.getTractInfo();
						}
					}
					if(!StringUtil.isBlank(tractInfo)){
						Gson gson = new Gson();
						Type listType = new TypeToken<List<TactInfoModel>>(){}.getType();
						listTacInfo = gson.fromJson(tractInfo, listType);
					}
					tactInfoModel.setAcceptStation("您的订单已导入,商家正在通知快递公司进行取件");
					tactInfoModel.setAcceptTime(deliveryDate);
					listTacInfo.add(tactInfoModel);
					Collections.sort(listTacInfo, new SortClass());
					
					status = kdnWuliuInfoCustom.getStatus();
					if(status.equals("1")){
						statusName = "已揽收";
					}else if(status.equals("2")){
						statusName = "在途中";
					}else if(status.equals("201")){
						statusName = "到达派件城市";
					}else if(status.equals("3")){
						statusName = "签收";
					}else if(status.equals("4")){
						statusName = "问题件";
					}else if(status.equals("0")){
						statusName = "已发货";
					}
					expressCompany = kdnWuliuInfoCustom.getExpressCompany();
				}else{
					status = kdnWuliuInfoCustom.getStatus();
					if(status.equals("0")){
						statusName = "已发货";
					}
					expressCompany = kdnWuliuInfoCustom.getExpressCompany();
					tactInfoModel.setAcceptStation("您的订单已导入,商家正在通知快递公司进行取件");
					tactInfoModel.setAcceptTime(deliveryDate);
					listTacInfo.add(tactInfoModel);
				}
				
				
				returnData.put("tractInfo", listTacInfo);
				returnData.put("status", status);
				returnData.put("statusName", statusName);
				returnData.put("expressCompany", expressCompany);
				returnData.put("expressNo", expressNo);
			}else{
				throw new ArgException("查看物流失败");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取快递信息（根据子订单查询每个订单明细物流）
	 * @author  chenwf: 
	 * @date 创建时间：2019年4月9日 上午9:13:50 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getExpressInfoBySubOrderId")
	@ResponseBody
	public ResponseMsg getExpressInfoBySubOrderId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"subOrderId"};
			this.requiredStr(obj,request);
			Integer subOrderId = reqDataJson.getInt("subOrderId");//子订单id
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> dataList = new ArrayList<>();
			OrderDtlExample orderDtlExample = new OrderDtlExample();
			orderDtlExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andMarkedOutOfStockNotEqualTo("1").andDeliveryStatusNotEqualTo("0").andDelFlagEqualTo("0").andIsGiveEqualTo("0");
			List<OrderDtl> dtls = orderDtlService.selectByExample(orderDtlExample);
			if(CollectionUtils.isNotEmpty(dtls)){
				for (OrderDtl orderDtl : dtls) {
					Map<String, Object> dataMap = new HashMap<String, Object>();
					Integer expressId = orderDtl.getDtlExpressId();
					String expressNo = orderDtl.getDtlExpressNo();
					String skuPic = StringUtil.getPic(orderDtl.getSkuPic(), "S");
					String tractInfo = "";
					String status = "";
					String statusName = "";
					String expressCompany = "";
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("expressId", expressId);
					params.put("expressNo", expressNo);
					KdnWuliuInfoCustom kdnWuliuInfoCustom = kdnWuliuInfoService.getExpressInfo(params);
					if(kdnWuliuInfoCustom != null){
						SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
						List<TactInfoModel> listTacInfo = new ArrayList<TactInfoModel>();
						TactInfoModel tactInfoModel = new TactInfoModel();
						String deliveryDate = DateUtil.getFormatDate(subOrder.getDeliveryDate(), "yyyy-MM-dd HH:mm:ss");
						if(kdnWuliuInfoCustom != null && kdnWuliuInfoCustom.getSubscribeStatus().equals("1")){
							tractInfo = kdnWuliuInfoCustom.getTractInfo() == null ? "" : kdnWuliuInfoCustom.getTractInfo();
							String kdnStatus = kdnWuliuInfoCustom.getStatus();
							if (kdnStatus.equals("0")) {
								//特定参数
								JSONObject param = new JSONObject();
								JSONObject reqData=new JSONObject();
								reqData.put("id", kdnWuliuInfoCustom.getId());
								param.put("reqData", reqData);
								//调用快鸟接口
								JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(Config.getProperty("msgServerUrl")+"/api/kdn/queryKdn", CommonUtil.createReqData(param).toString()));
								if("0000".equals(result.getString("returnCode"))){
									kdnWuliuInfoCustom = kdnWuliuInfoService.getExpressInfo(params);
									tractInfo = kdnWuliuInfoCustom.getTractInfo();
								}
							}
							if(!StringUtil.isBlank(tractInfo)){
								Gson gson = new Gson();
								Type listType = new TypeToken<List<TactInfoModel>>(){}.getType();
								listTacInfo = gson.fromJson(tractInfo, listType);
							}
							tactInfoModel.setAcceptStation("您的订单已导入,商家正在通知快递公司进行取件");
							tactInfoModel.setAcceptTime(deliveryDate);
							listTacInfo.add(tactInfoModel);
							Collections.sort(listTacInfo, new SortClass());
							
							status = kdnWuliuInfoCustom.getStatus();
							if(status.equals("1")){
								statusName = "已揽收";
							}else if(status.equals("2")){
								statusName = "在途中";
							}else if(status.equals("201")){
								statusName = "到达派件城市";
							}else if(status.equals("3")){
								statusName = "签收";
							}else if(status.equals("4")){
								statusName = "问题件";
							}else if(status.equals("0")){
								statusName = "已发货";
							}
							expressCompany = kdnWuliuInfoCustom.getExpressCompany();
						}else{
							status = kdnWuliuInfoCustom.getStatus();
							if(status.equals("0")){
								statusName = "已发货";
							}
							expressCompany = kdnWuliuInfoCustom.getExpressCompany();
							tactInfoModel.setAcceptStation("您的订单已导入,商家正在通知快递公司进行取件");
							tactInfoModel.setAcceptTime(deliveryDate);
							listTacInfo.add(tactInfoModel);
						}
						dataMap.put("tractInfo", listTacInfo);
						dataMap.put("status", status);
						dataMap.put("statusName", statusName);
						dataMap.put("expressCompany", expressCompany);
						dataMap.put("expressNo", expressNo);
						dataMap.put("skuPic", skuPic);
						dataMap.put("orderDtlId", orderDtl.getId());
						dataMap.put("quantity", orderDtl.getQuantity());
						dataMap.put("productPropDesc", orderDtl.getProductPropDesc());
						dataMap.put("productName", orderDtl.getProductName());
						dataList.add(dataMap);
					}
				}
			}
			map.put("dataList", dataList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取快递公司名称
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月5日 下午8:13:50 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getExpressNames")
	@ResponseBody
	public ResponseMsg getExpressNames(HttpServletRequest request){
		try {
			ExpressExample expressExample = new ExpressExample();
			expressExample.createCriteria().andDelFlagEqualTo("0");
			List<Express> list = expressService.selectByExample(expressExample);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,list);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
