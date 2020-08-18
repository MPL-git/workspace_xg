package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jf.common.base.ArgException;
import com.jf.common.utils.JsonUtils;
import com.jf.dao.MemberAttentionMapper;
import com.jf.entity.MemberAttention;
import com.jf.entity.MemberAttentionExample;

import net.sf.json.JSONObject;

@Repository
public class MemberAttentionService extends BaseService<MemberAttention, MemberAttentionExample> {
	@Autowired
	private MemberAttentionMapper memberAttentionMapper;
	@Autowired
	public void setMemberAttentionMapper(MemberAttentionMapper memberAttentionMapper) {
		this.setDao(memberAttentionMapper);
		this.memberAttentionMapper = memberAttentionMapper;
	}
	public Map<String, Object> updateMemberAttention(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer memberId = reqDataJson.getInt("memberId");
		Integer friendMemberId = reqDataJson.getInt("friendMemberId");
		String type = "";
		if(!JsonUtils.isEmpty(reqDataJson, "type")){
			type = reqDataJson.getString("type");
		}
		String followStatus = "";//1已关注2互相关注3未关注
		MemberAttention memberAttention = null;
		MemberAttentionExample memberAttentionExample = new MemberAttentionExample();
		memberAttentionExample.createCriteria().andMemberIdEqualTo(memberId).andAttentionMemberIdEqualTo(friendMemberId);
		List<MemberAttention> memberAttentions = selectByExample(memberAttentionExample);
		if(CollectionUtils.isNotEmpty(memberAttentions)){
			memberAttention = memberAttentions.get(0);
			if(memberAttention.getDelFlag().equals("0")){
				if(!"1".equals(type)){
					memberAttention.setDelFlag("1");
					memberAttention.setUpdateDate(new Date());
					followStatus = "3";
					updateByPrimaryKeySelective(memberAttention);
				}
			}else{
				memberAttention.setDelFlag("0");
				memberAttention.setUpdateDate(new Date());
				followStatus = "1";
				updateByPrimaryKeySelective(memberAttention);
			}
			
		}else{
			memberAttention = new MemberAttention();
			memberAttention.setMemberId(memberId);
			memberAttention.setAttentionMemberId(friendMemberId);
			memberAttention.setUpdateDate(new Date());
			memberAttention.setCreateDate(new Date());
			memberAttention.setDelFlag("0");
			try {
				insertSelective(memberAttention);
				followStatus = "1";
			} catch (Exception e) {
				throw new ArgException("您已关注该用户，请勿重复关注");
			}
		}
		if(followStatus.equals("1")){
			MemberAttentionExample memberAttentionExample1 = new MemberAttentionExample();
			memberAttentionExample1.createCriteria().andMemberIdEqualTo(friendMemberId).andAttentionMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
			Integer count = countByExample(memberAttentionExample1);
			if(count > 0){
				followStatus = "2";
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("followStatus", followStatus);
		return map;
	}
	
	public void addMemberMutualConcern(Integer memberId, Integer parentMemberId) {
		if(memberId != null && parentMemberId != null){
			addModel(memberId,parentMemberId);
			addModel(parentMemberId,memberId);
		}
	}
	private void addModel(Integer memberId1, Integer memberId2) {
		MemberAttention memberAttention = new MemberAttention();
		memberAttention.setMemberId(memberId1);
		memberAttention.setAttentionMemberId(memberId2);
		memberAttention.setUpdateDate(new Date());
		memberAttention.setCreateDate(new Date());
		memberAttention.setDelFlag("0");
		insertSelective(memberAttention);
		
	}
	
	
}
