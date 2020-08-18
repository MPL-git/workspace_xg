package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberGroupMapper;
import com.jf.entity.MemberGroup;
import com.jf.entity.MemberGroupExample;
import com.jf.entity.MemberInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月17日 下午6:10:01 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberGroupService extends BaseService<MemberGroup, MemberGroupExample> {
	@Autowired
	private MemberGroupMapper memberGroupMapper;
	
	@Resource
	private MemberInfoService memberInfoService;

	@Autowired
	public void setMemberGroupMapper(MemberGroupMapper memberGroupMapper) {
		this.setDao(memberGroupMapper);
		this.memberGroupMapper = memberGroupMapper;
	}
	/** 获取成长值*/
	public void updateMemberGroup(Integer gValue, Integer memberId) {
		List<MemberGroup> memberGroups = findList();
		MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
		Integer groupId = null;
		if(CollectionUtils.isNotEmpty(memberGroups)){
			for (MemberGroup memberGroup : memberGroups) {
				Integer bgnGValue = memberGroup.getBgnGValue();
				Integer endGValue = memberGroup.getEndGValue();
				//最小<=成长值<最大
				if(gValue >= bgnGValue && gValue <= endGValue){
					if(memberGroup.getId() != memberInfo.getGroupId()){
						groupId = memberGroup.getId();
						break;
					}
				}
			}
		}
		if(groupId != null){
			memberInfo.setGroupId(groupId);
			memberInfoService.update(memberInfo);
		}
		
	}
	
	public List<MemberGroup> findList() {
		MemberGroupExample memberGroupExample = new MemberGroupExample();
		return memberGroupMapper.selectByExample(memberGroupExample);
	}
	public MemberGroup findModel(Integer groupId) {
		
		return memberGroupMapper.selectByPrimaryKey(groupId);
	}
	
	
}
