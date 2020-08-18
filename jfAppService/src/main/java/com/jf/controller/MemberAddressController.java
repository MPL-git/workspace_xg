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
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberAddressExample;
import com.jf.service.AreaService;
import com.jf.service.MemberAddressService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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
			
			Object[] obj = {"memberId","pageSize","currentPage"};
			this.requiredStr(obj,request);
			String memberId = reqDataJson.getString("memberId");//会员标识id
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
			
			Integer count = memberAddressService.getMemberAddressCount(params);
			Page page = new Page(currentPage, pageSize, count);
			
			List<MemberAddressCustom> MemberAddrList= memberAddressService.getMemberAddressList(params);
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			if(MemberAddrList !=null && MemberAddrList.size() > 0){
				for (MemberAddressCustom memberAddressCustom : MemberAddrList) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("id", this.setStrByNull(memberAddressCustom.getId()));
					map.put("phone", this.setStrByNull(memberAddressCustom.getPhone()));
					map.put("recipient", this.setStrByNull(memberAddressCustom.getRecipient()));
					map.put("address", this.setStrByNull(memberAddressCustom.getAddress()));
					map.put("isPrimary", this.setStrByNull(memberAddressCustom.getIsPrimary()));
					map.put("province", this.setStrByNull(memberAddressCustom.getProvince()));
					map.put("provinceName", this.setStrByNull(memberAddressCustom.getProvinceName()));
					map.put("city", this.setStrByNull(memberAddressCustom.getCity()));
					map.put("cityName", this.setStrByNull(memberAddressCustom.getCityName()));
					map.put("county", this.setStrByNull(memberAddressCustom.getCounty()));
					map.put("countyName", this.setStrByNull(memberAddressCustom.getCountyName()));
					map.put("totalPage", page.getTotalPage());
					returnData.add(map);
				}
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
			
			Object[] obj = {"memberId","phone","recipient","address","province","city","county"};
			this.requiredStr(obj,request);
			String isPrimary = "N";
			if(reqDataJson.containsKey("isPrimary") && !StringUtil.isBlank(reqDataJson.getString("isPrimary"))){
				isPrimary = reqDataJson.getString("isPrimary");
			}
			String memberId = reqDataJson.getString("memberId");//会员标识id
			String phone = reqDataJson.getString("phone");//用户联系电话
			String recipient = reqDataJson.getString("recipient");//用户联系人
			String address = reqDataJson.getString("address");//用户收货地址
			String province = reqDataJson.getString("province");//省
			String city = reqDataJson.getString("city");//市
			String county = reqDataJson.getString("county");//区
			if (recipient.length() > 60) {
				throw new ArgException("联系人长度不能超过60个字符");
			}
			if (address.length() > 200) {
				throw new ArgException("详细地址不能超过200个字符");
			}

			MemberAddress memberAddress = new MemberAddress();
			memberAddress.setMemberId(Integer.valueOf(memberId));
			memberAddress.setPhone(phone);
			memberAddress.setRecipient(recipient);
			memberAddress.setAddress(address);
			memberAddress.setCreateDate(new Date());
			memberAddress.setCreateBy(Integer.valueOf(memberId));
			memberAddress.setProvince(Integer.valueOf(province));
			memberAddress.setCity(Integer.valueOf(city));
			memberAddress.setCounty(Integer.valueOf(county));
			MemberAddressExample memberAddressExample = new MemberAddressExample();
			memberAddressExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
			.andIsPrimaryEqualTo("Y").andDelFlagEqualTo("0");
			List<MemberAddress> MemberAddrList= memberAddressService.selectByExample(memberAddressExample);
			if(MemberAddrList != null && MemberAddrList.size() > 0){//null 表示第一次增加收货地址 应设置为 主地址
				if("Y".equals(isPrimary)){
					MemberAddress mAddress = MemberAddrList.get(0);
					mAddress.setIsPrimary("N");
					memberAddressService.updateByExampleSelective(mAddress, memberAddressExample);
					memberAddress.setIsPrimary("Y");
				}else{
					memberAddress.setIsPrimary("N");
				}
			}else{
				memberAddress.setIsPrimary("Y");
			}
			memberAddressService.insertSelective(memberAddress);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,memberAddress.getId().toString());
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
			
			Object[] obj = {"id","memberId","phone","recipient","address","province","city","county"};
			this.requiredStr(obj,request);
			Integer id = reqDataJson.getInt("id");//id
			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			String phone = reqDataJson.getString("phone");//用户联系电话
			String recipient = reqDataJson.getString("recipient");//用户联系人
			String address = reqDataJson.getString("address");//用户收货地址
			String province = reqDataJson.getString("province");//省
			String city = reqDataJson.getString("city");//市
			String county = reqDataJson.getString("county");//区
			String isPrimary = "N";
			if(reqDataJson.containsKey("isPrimary") && !StringUtil.isBlank(reqDataJson.getString("isPrimary"))){
				isPrimary = reqDataJson.getString("isPrimary");
			}
			if (recipient.length() > 60) {
				throw new ArgException("联系人长度不能超过60个字符");
			}
			if (address.length() > 200) {
				throw new ArgException("详细地址不能超过200个字符");
			}

			MemberAddress memberAddress = new MemberAddress();
			memberAddress.setId(id);
			memberAddress.setMemberId(memberId);
			memberAddress.setPhone(phone);
			memberAddress.setRecipient(recipient);
			memberAddress.setAddress(address);
			memberAddress.setUpdateDate(new Date());
			memberAddress.setUpdateBy(Integer.valueOf(memberId));
			memberAddress.setProvince(Integer.valueOf(province));
			memberAddress.setCity(Integer.valueOf(city));
			memberAddress.setCounty(Integer.valueOf(county));
			MemberAddressExample memberAddressExample = new MemberAddressExample();
			memberAddressExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
			.andIsPrimaryEqualTo("Y").andDelFlagEqualTo("0");
			List<MemberAddress> MemberAddrList= memberAddressService.selectByExample(memberAddressExample);
			if(MemberAddrList != null && MemberAddrList.size() > 0){//null 表示第一次增加收货地址 应设置为 主地址
				if("Y".equals(isPrimary)){
					MemberAddress mAddress = MemberAddrList.get(0);
					mAddress.setIsPrimary("N");
					memberAddressService.updateByExampleSelective(mAddress, memberAddressExample);
					memberAddress.setIsPrimary("Y");
				}else{
					memberAddress.setIsPrimary("N");
				}
			}else{
				memberAddress.setIsPrimary("Y");
			}
			memberAddressService.updateByPrimaryKeySelective(memberAddress);
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
			
			Object[] obj = {"id","memberId"};
			this.requiredStr(obj,request);
			Integer id = reqDataJson.getInt("id");//id
			Integer memberId = reqDataJson.getInt("memberId");//会员标识id
			Date date = new Date();
			MemberAddress memberAddress = memberAddressService.selectByPrimaryKey(id);
			if(memberAddress != null){
				String isPrimary = memberAddress.getIsPrimary();
				memberAddress.setDelFlag("1");//删除标识
				memberAddress.setIsPrimary("N");//默认地址都改为 N
				memberAddress.setUpdateBy(memberId);
				memberAddress.setUpdateDate(date);
				memberAddressService.updateByPrimaryKeySelective(memberAddress);
				if(isPrimary.equals("Y")){
					QueryObject queryObject = new QueryObject();
					queryObject.addQuery("memberId", memberId);
					queryObject.addQuery("sort", "id desc");
					List<MemberAddress> memberAddresses = memberAddressService.findListQuery(queryObject);
					if(CollectionUtils.isNotEmpty(memberAddresses)){
						if(!memberAddresses.contains("Y")){
							MemberAddress address = memberAddresses.get(0);
							address.setIsPrimary("Y");
							address.setUpdateBy(memberId);
							address.setUpdateDate(date);
							memberAddressService.updateByPrimaryKeySelective(address);
						}
					}
				}
			}
			
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
			
			Object[] obj = {"id","memberId"};
			this.requiredStr(obj,request);
			String id = reqDataJson.getString("id");//id
			String memberId = reqDataJson.getString("memberId");//会员标识id
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
	 * 方法描述 ：获取省市区三级联动()
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月20日 上午10:31:02 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getAreaByParentId")
	@ResponseBody
	public ResponseMsg getAreaByParentId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"memberId"};
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
	 * 
	 * 方法描述 ：获取省市区三级联动
	 * @author  chenwf: 
	 * @date 创建时间：2018年8月15日 上午10:31:02 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getAreaByParentId")
	@ResponseBody
	public ResponseMsg getAreasByParentId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
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
}
