package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberDynamicLikeMapper;
import com.jf.entity.MemberDynamicLike;
import com.jf.entity.MemberDynamicLikeExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberDynamicLikeService extends BaseService<MemberDynamicLike, MemberDynamicLikeExample> {
	@Autowired
	private MemberDynamicLikeMapper memberDynamicLikeMapper;
	@Autowired
	public void setMemberDynamicLikeMapper(MemberDynamicLikeMapper memberDynamicLikeMapper) {
		this.setDao(memberDynamicLikeMapper);
		this.memberDynamicLikeMapper = memberDynamicLikeMapper;
	}
	public List<MemberDynamicLike> findMemberDynamicFabulousModel(Integer memberId, Integer dynamicId, String type) {
		MemberDynamicLikeExample memberDynamicLikeExample = new MemberDynamicLikeExample();
		memberDynamicLikeExample.createCriteria().andMemberIdEqualTo(memberId).andDynamicIdEqualTo(dynamicId).andTypeEqualTo(type).andDelFlagEqualTo("0");
		return selectByExample(memberDynamicLikeExample);
	}
	
	public String updateMemberDynamicFabulous(Integer memberId, Integer dynamicId, String type) {
		String returnType = "0";//0未点赞 1已点赞
		MemberDynamicLikeExample memberDynamicLikeExample = new MemberDynamicLikeExample();
		memberDynamicLikeExample.createCriteria().andMemberIdEqualTo(memberId).andDynamicIdEqualTo(dynamicId).andTypeEqualTo(type).andDelFlagEqualTo("0");
		List<MemberDynamicLike> dynamicLikes = selectByExample(memberDynamicLikeExample);
		if(CollectionUtils.isNotEmpty(dynamicLikes)){
			MemberDynamicLike dynamicLike = dynamicLikes.get(0);
			if(dynamicLike.getStatus().equals("1")){
				dynamicLike.setStatus("0");
			}else{
				dynamicLike.setStatus("1");
				returnType = "1";
			}
			updateByPrimaryKeySelective(dynamicLike);
		}else{
			MemberDynamicLike dynamicLike = new MemberDynamicLike();
			dynamicLike.setMemberId(memberId);
			dynamicLike.setDynamicId(dynamicId);
			dynamicLike.setStatus("1");
			dynamicLike.setCreateDate(new Date());
			dynamicLike.setType(type);
			dynamicLike.setDelFlag("0");
			try {
				insertSelective(dynamicLike);
				returnType = "1";
			} catch (Exception e) {
				returnType = "0";
			}
		}
		return returnType;
	}
	
	
}
