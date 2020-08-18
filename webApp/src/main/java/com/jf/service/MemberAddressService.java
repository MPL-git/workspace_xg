package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberAddressCustomMapper;
import com.jf.dao.MemberAddressMapper;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberAddressExample;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月19日 下午6:51:03 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberAddressService extends BaseService<MemberAddress, MemberAddressExample> {
	@Autowired
	private MemberAddressMapper memberAddressMapper;
	
	@Autowired
	private MemberAddressCustomMapper memberAddressCustomMapper;
	
	@Autowired
	public void setMemberAddressMapper(MemberAddressMapper memberAddressMapper) {
		super.setDao(memberAddressMapper);
		this.memberAddressMapper = memberAddressMapper;
	}

	public void updateDefaultAddressByMemberId(Integer memberId) {
		
		memberAddressCustomMapper.updateDefaultAddressByMemberId(memberId);
	}

	public void updateDefaultAddressById(Integer id) {
		
		memberAddressCustomMapper.updateDefaultAddressById(id);
	}

	public Integer getMemberAddressCount(Map<String, Object> params) {
		
		return memberAddressCustomMapper.getMemberAddressCount(params);
	}

	public List<Map<String, Object>> getMemberAddressList(Map<String, Object> params) {
		List<MemberAddressCustom> MemberAddrList= memberAddressCustomMapper.getMemberAddressList(params);
		List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
		if(MemberAddrList !=null && MemberAddrList.size() > 0){
			for (MemberAddressCustom memberAddressCustom : MemberAddrList) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", memberAddressCustom.getId());
				map.put("phone", memberAddressCustom.getPhone());
				map.put("recipient", memberAddressCustom.getRecipient());
				map.put("address", memberAddressCustom.getAddress());
				map.put("isPrimary", memberAddressCustom.getIsPrimary());
				map.put("province", memberAddressCustom.getProvince());
				map.put("provinceName", memberAddressCustom.getProvinceName());
				map.put("city", memberAddressCustom.getCity());
				map.put("cityName", memberAddressCustom.getCityName());
				map.put("county", memberAddressCustom.getCounty());
				map.put("countyName", memberAddressCustom.getCountyName());
				map.put("totalPage", "");
				returnData.add(map);
			}
		}
		return returnData;
	}

	public MemberAddressCustom getAddressById(Integer memberId, Integer addressId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberId);
		params.put("addressId", addressId);
		return memberAddressCustomMapper.getAddressById(params);
	}

	public MemberAddressCustom getAddressById(Map<String, Object> addressParamsMap) {
		
		return memberAddressCustomMapper.getAddressById(addressParamsMap);
	}
	
	/**
	 * 有地址获取用户选择的地址，没有地址获取用户的默认地址
	 */
	public MemberAddressCustom getAddressByMemberId(Map<String, Object> addressParamsMap) {
		
		return memberAddressCustomMapper.getAddressByMemberId(addressParamsMap);
	}
	
	public List<MemberAddress> findListQuery(QueryObject queryObject) {
		
		return memberAddressMapper.selectByExample(builderQuery(queryObject));
	}

	private MemberAddressExample builderQuery(QueryObject queryObject) {
		MemberAddressExample example = new MemberAddressExample();
		MemberAddressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("memberId") != null){
			criteria.andMemberIdEqualTo(queryObject.getQueryToInt("memberId"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}

	public MemberAddress addMemberAddress(JSONObject reqDataJson, String memberId) {
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
		.andIsPrimaryEqualTo("Y");
		List<MemberAddress> MemberAddrList= memberAddressMapper.selectByExample(memberAddressExample);
		if(MemberAddrList != null && MemberAddrList.size() > 0){//null 表示第一次增加收货地址 应设置为 主地址
			if("Y".equals(isPrimary)){
				MemberAddress mAddress = MemberAddrList.get(0);
				mAddress.setIsPrimary("N");
				memberAddressMapper.updateByExampleSelective(mAddress, memberAddressExample);
				memberAddress.setIsPrimary("Y");
			}else{
				memberAddress.setIsPrimary("N");
			}
		}else{
			memberAddress.setIsPrimary("Y");
		}
		memberAddressMapper.insertSelective(memberAddress);
		return memberAddress;
	}

	public void updateMemberAddress(JSONObject reqDataJson, String memberId) {
		String id = reqDataJson.getString("id");//id
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
		
		memberAddress.setId(Integer.valueOf(id));
		memberAddress.setMemberId(Integer.valueOf(memberId));
		memberAddress.setPhone(phone);
		memberAddress.setRecipient(recipient);
		memberAddress.setAddress(address);
		memberAddress.setUpdateDate(new Date());
		memberAddress.setUpdateBy(Integer.valueOf(memberId));
		memberAddress.setProvince(Integer.valueOf(province));
		memberAddress.setCity(Integer.valueOf(city));
		memberAddress.setCounty(Integer.valueOf(county));
		memberAddressMapper.updateByPrimaryKeySelective(memberAddress);
		
	}

	public void deleteMemberAddress(JSONObject reqDataJson, Integer memberId) {
		Integer id = reqDataJson.getInt("id");//id
		Date date = new Date();
		MemberAddress memberAddress = memberAddressMapper.selectByPrimaryKey(id);
		if(memberAddress != null){
			String isPrimary = memberAddress.getIsPrimary();
			memberAddress.setId(Integer.valueOf(id));
			memberAddress.setDelFlag("1");//删除标识
			memberAddress.setIsPrimary("N");//默认地址都改为 N
			memberAddress.setUpdateBy(Integer.valueOf(memberId));
			memberAddress.setUpdateDate(date);
			memberAddressMapper.updateByPrimaryKeySelective(memberAddress);
			if(isPrimary.equals("Y")){
				QueryObject queryObject = new QueryObject();
				queryObject.addQuery("memberId", memberId);
				queryObject.addQuery("sort", "id desc");
				List<MemberAddress> memberAddresses = findListQuery(queryObject);
				if(CollectionUtils.isNotEmpty(memberAddresses)){
					if(!memberAddresses.contains("Y")){
						MemberAddress address = memberAddresses.get(0);
						address.setIsPrimary("Y");
						address.setUpdateBy(memberId);
						address.setUpdateDate(date);
						memberAddressMapper.updateByPrimaryKeySelective(address);
					}
				}
			}
		}
		
	}

	public Map<String, Object> getMemberDefaultAddress(Integer memberId, Integer addressId) {
		Map<String, Object> addressMap = new HashMap<String, Object>();
		Integer provinceId = null;
		Map<String, Object> addressParamsMap = new HashMap<String, Object>();
		addressParamsMap.put("memberId", memberId);
		addressParamsMap.put("addressId", addressId);
		MemberAddressCustom memberAddressCustom = getAddressByMemberId(addressParamsMap);
		String recipientAddressFullName = "";
		String recipientName = "";
		String recipientPhone = "";
		String recipientAddress = "";
		if(memberAddressCustom != null){
			recipientAddressFullName = memberAddressCustom.getProvinceName() + " "
			+ memberAddressCustom.getCityName() + " " + memberAddressCustom.getCountyName() + " "
			+ memberAddressCustom.getAddress();
			recipientName = memberAddressCustom.getRecipient();
			recipientPhone = memberAddressCustom.getPhone();
			recipientAddress = memberAddressCustom.getAddress();
			addressId = memberAddressCustom.getId();
			provinceId = memberAddressCustom.getProvince();
		}
		addressMap.put("recipientName", recipientName);
		addressMap.put("recipientPhone", recipientPhone);
		addressMap.put("recipientAddress", recipientAddress);
		addressMap.put("addressId", addressId);
		addressMap.put("recipientAddressFullName", recipientAddressFullName);
		addressMap.put("provinceId", provinceId);
		return addressMap;
	}
	
}
