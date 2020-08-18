package com.jf.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.SysParamCfg;
import com.jf.service.WeiTaoService;
import com.jf.service.WetaoPageAdInfoService;
import com.jf.service.WetaoPageService;
/**
 * 微淘
 * @author chenwf
 *
 */
@Controller
public class WeiTaoController extends BaseController{
	@Resource
	private WeiTaoService weiTaoService;
	@Resource
	private WetaoPageService wetaoPageService;
	@Resource
	private WetaoPageAdInfoService wetaoPageAdInfoService;
	
	/**
	 * 
	 * 方法描述 ：微淘首页类目
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月6日 上午11:23:56 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getWeiTaoTopCategory")
	@ResponseBody
	public ResponseMsg getWeiTaoTopCategory(HttpServletRequest request){
		try {
			Map<String,Object> map = wetaoPageService.getWeiTaoTopCategory(null);
			String pic = "";
			List<SysParamCfg> list = DataDicUtil.getSysParamCfg("APP_WETAO_GUIDE_PIC_1");
			if(CollectionUtils.isNotEmpty(list)){
				pic = StringUtil.getPic(list.get(0).getParamValue(), "");
			}
			map.put("pic", pic);
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
	 * 方法描述 ：微淘首页广告+装修
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月6日 上午11:23:56 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getWeiTaoAdInfo")
	@ResponseBody
	public ResponseMsg getWeiTaoAdInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"wetaoPageId"};
			this.requiredStr(obj,request);
			Map<String,Object> map = wetaoPageAdInfoService.getWeiTaoAdInfo(reqPRM,reqDataJson);
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
	 * 方法描述 ：淘宝客商品列表
	 * @author  chenwf: 
	 * @date 创建时间：2019年03月05日 下午5:03:20 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getTaoBaoProductList")
	@ResponseBody
	public ResponseMsg getTaoBaoProductList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = weiTaoService.getWeiTaoProductList(reqDataJson, pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：第三方平台商品详情页面(淘宝客商品商品详情)
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月6日 上午11:23:56 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getThirdPartyProductBaseInfo")
	@ResponseBody
	public ResponseMsg getThirdPartyProductBaseInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Map<String,Object> map = weiTaoService.getThirdPartyProductBaseInfo(reqPRM,reqDataJson);
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
	 * @MethodName: getTaoBaoProductSearchList
	 * @Description: (淘宝客-推广者-物料搜索 )
	 * @author Pengl
	 * @date 2019年7月20日 下午2:19:05
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/getTaoBaoProductSearchList")
	public ResponseMsg getTaoBaoProductSearchList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData"); //获取请求参数
			Object[] obj = {"pageNo"};
			this.requiredStr(obj, request);
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String, Object> map = weiTaoService.getWeiTaoProductSearchList(reqPRM, reqDataJson, pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, pageSize, map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}
	
	
}
