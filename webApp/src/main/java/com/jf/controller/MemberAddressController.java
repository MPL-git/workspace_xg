package com.jf.controller;
/**
  * 收货地址 
  * @author  chenwf: 
  * @date 创建时间：2017年4月19日 下午6:00:27 
  * @version 1.0 
  * @parameter  
  * @return  
*/

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.MemberAddress;
import com.jf.service.AreaService;
import com.jf.service.MemberAddressService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberAddressController extends BaseController{
	@Resource
	private MemberAddressService memberAddressService;
	@Resource
	private AreaService areaService;
	/**
	 * 
	 * 方法描述 ：获取收货地址
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:16:16 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getMemberAddressList")
	@ResponseBody
	public ResponseMsg getMemberAddressList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pageSize","currentPage"};
			this.requiredStr(obj,request);
			String memberId = getMemberIdStr(request);//会员标识id
			Integer pageSize = Integer.valueOf(reqDataJson.getString("pageSize"));//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			if(currentPage == null || pageSize == null){
				currentPage = 0;
				pageSize = 5;
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("memberId", memberId);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			
			List<Map<String,Object>> returnData = memberAddressService.getMemberAddressList(params);
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
	 * 方法描述 ：新增收货地址
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:31:02 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addMemberAddress")
	@ResponseBody
	public ResponseMsg addMemberAddress(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"phone","recipient","address","province","city","county"};
			this.requiredStr(obj,request);
			String memberId = getMemberIdStr(request);//会员标识id
			MemberAddress memberAddress = memberAddressService.addMemberAddress(reqDataJson, memberId);

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, memberAddress.getId().toString());
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：编辑收货地址
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:31:02 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateMemberAddress")
	@ResponseBody
	public ResponseMsg updateMemberAddress(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"id","phone","recipient","address","province","city","county"};
			this.requiredStr(obj,request);
			String memberId = getMemberIdStr(request);//会员标识id
			memberAddressService.updateMemberAddress(reqDataJson,memberId);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：删除收货地址
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:31:02 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/deleteMemberAddress")
	@ResponseBody
	public ResponseMsg deleteMemberAddress(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"id"};
			this.requiredStr(obj,request);
			Integer memberId = getMemberId(request);//会员标识id
			memberAddressService.deleteMemberAddress(reqDataJson,memberId);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：修改收货默认主地址
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:31:02 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateDefaultAddress")
	@ResponseBody
	public ResponseMsg updateDefaultAddress(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"id"};
			this.requiredStr(obj,request);
			String id = reqDataJson.getString("id");//id
			String memberId = getMemberIdStr(request);//会员标识id
			//根据会员标识id把 默认地址全部设置为 N
			memberAddressService.updateDefaultAddressByMemberId(Integer.valueOf(memberId));
			//根据id把 默认地址 设置为 Y
			memberAddressService.updateDefaultAddressById(Integer.valueOf(id));
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,null);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：获取省市区三级联动
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:31:02 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getAreaByParentId")
	@ResponseBody
	public ResponseMsg getAreaByParentId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {};
			this.requiredStr(obj,request);
			
			Integer parentId = 0;
			if(!StringUtil.isBlank(reqDataJson.getString("parentId"))){
				parentId = Integer.valueOf(reqDataJson.getString("parentId"));
			}
			AreaExample areaExample = new AreaExample();
			areaExample.createCriteria().andParentIdEqualTo(parentId);
			List<Area> areaList = areaService.selectByExample(areaExample);
			
			List<Map<String,String>> returnData = new ArrayList<Map<String,String>>();
			if(areaList != null & areaList.size() > 0){
				for(Area area : areaList){
					Map<String,String> map = new HashMap<String,String>();
					map.put("areaId", area.getAreaId().toString());
					map.put("areaName", area.getAreaName());
					map.put("parentId", area.getParentId().toString());
					returnData.add(map);
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 * 获取用户的默认收货地址
	 */
	@ResponseBody
	@RequestMapping(value = "api/y/memberDefaultAddress", method = RequestMethod.POST)
	public ResponseMsg getMemberDefaultAddress() {
		Map<String, Object> addressMap = memberAddressService.getMemberDefaultAddress(getMemberId(), null);
		return ResponseMsg.success(addressMap);
	}
}
