package com.jf.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.dao.SpreadActivityGroupSetCustomMapper;
import com.jf.dao.SpreadActivityGroupSetDtlMapper;
import com.jf.dao.SpreadActivityGroupSetMapper;
import com.jf.entity.SpreadActivityGroupSet;
import com.jf.entity.SpreadActivityGroupSetCustom;
import com.jf.entity.SpreadActivityGroupSetCustomExample;
import com.jf.entity.SpreadActivityGroupSetDtl;
import com.jf.entity.SpreadActivityGroupSetDtlExample;
import com.jf.entity.SpreadActivityGroupSetExample;

@Service
@Transactional
public class SpreadActivityGroupSetService extends BaseService<SpreadActivityGroupSet, SpreadActivityGroupSetExample> {

	@Autowired
	private SpreadActivityGroupSetMapper spreadActivityGroupSetMapper;
	
	@Autowired
	private SpreadActivityGroupSetCustomMapper spreadActivityGroupSetCustomMapper;
	
	@Autowired
	private SpreadActivityGroupSetDtlMapper spreadActivityGroupSetDtlMapper;
	
	@Autowired
	public void setSpreadActivityGroupSetMapper(SpreadActivityGroupSetMapper spreadActivityGroupSetMapper) {
		super.setDao(spreadActivityGroupSetMapper);
		this.dao = spreadActivityGroupSetMapper;
	}
	
	public int countByCustomExample(SpreadActivityGroupSetExample example) {
		return spreadActivityGroupSetCustomMapper.countByCustomExample(example);
	}

	public List<SpreadActivityGroupSetCustom> selectByCustomExample(SpreadActivityGroupSetCustomExample example) {
		return spreadActivityGroupSetCustomMapper.selectByCustomExample(example);
	}

	public SpreadActivityGroupSetCustom selectByCustomPrimaryKey(Integer id) {
		return spreadActivityGroupSetCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") SpreadActivityGroupSet record, @Param("example") SpreadActivityGroupSetCustomExample example) {
		return spreadActivityGroupSetCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public void editSpreadActivityGroupSet(SpreadActivityGroupSet spreadActivityGroupSet, String channelOld, String activityGroupIds) {
		String[] groupIds = null;
		if(!StringUtil.isEmpty(activityGroupIds) ) {
			groupIds = activityGroupIds.split(",");
		}
		if(spreadActivityGroupSet.getId() == null ) { //推广活动组集合新增
			spreadActivityGroupSetMapper.insertSelective(spreadActivityGroupSet);
			for(String groupId : groupIds ) {
				SpreadActivityGroupSetDtl spreadActivityGroupSetDtl = new SpreadActivityGroupSetDtl();
				spreadActivityGroupSetDtl.setSpreadActivityGroupSetId(spreadActivityGroupSet.getId());
				spreadActivityGroupSetDtl.setActivityGroupId(Integer.parseInt(groupId));
				spreadActivityGroupSetDtl.setCreateBy(spreadActivityGroupSet.getCreateBy());
				spreadActivityGroupSetDtl.setCreateDate(spreadActivityGroupSet.getCreateDate());
				spreadActivityGroupSetDtlMapper.insertSelective(spreadActivityGroupSetDtl);
			}
		}else { //推广活动组集合修改
			spreadActivityGroupSetMapper.updateByPrimaryKeySelective(spreadActivityGroupSet);
			SpreadActivityGroupSetDtlExample spreadActivityGroupSetDtlExample = new SpreadActivityGroupSetDtlExample();
			SpreadActivityGroupSetDtlExample.Criteria spreadActivityGroupSetDtlCriteria = spreadActivityGroupSetDtlExample.createCriteria();
			spreadActivityGroupSetDtlCriteria.andDelFlagEqualTo("0");
			SpreadActivityGroupSetDtl spreadActivityGroupSetDtl = new SpreadActivityGroupSetDtl();
			if(!channelOld.equals(spreadActivityGroupSet.getChannel()) ) { //推广渠道修改
				spreadActivityGroupSetDtlCriteria.andSpreadActivityGroupSetIdEqualTo(spreadActivityGroupSet.getId());
				spreadActivityGroupSetDtl.setUpdateBy(spreadActivityGroupSet.getUpdateBy());;
				spreadActivityGroupSetDtl.setUpdateDate(spreadActivityGroupSet.getUpdateDate());;
				spreadActivityGroupSetDtl.setDelFlag("1");
				spreadActivityGroupSetDtlMapper.updateByExampleSelective(spreadActivityGroupSetDtl, spreadActivityGroupSetDtlExample);
				for(String groupId : groupIds ) {
					SpreadActivityGroupSetDtl sActivityGroupSetDtl = new SpreadActivityGroupSetDtl();
					sActivityGroupSetDtl.setSpreadActivityGroupSetId(spreadActivityGroupSet.getId());
					sActivityGroupSetDtl.setActivityGroupId(Integer.parseInt(groupId));
					sActivityGroupSetDtl.setCreateBy(spreadActivityGroupSet.getUpdateBy());
					sActivityGroupSetDtl.setCreateDate(spreadActivityGroupSet.getUpdateDate());
					spreadActivityGroupSetDtlMapper.insertSelective(sActivityGroupSetDtl);
				}
			}else { //推广渠道未修改
				List<Integer> groupIdList = new ArrayList<Integer>();
				for(String groupId : groupIds ) {
					groupIdList.add(Integer.parseInt(groupId));
				}
				spreadActivityGroupSetDtlCriteria.andSpreadActivityGroupSetIdEqualTo(spreadActivityGroupSet.getId());
				spreadActivityGroupSetDtlCriteria.andActivityGroupIdNotIn(groupIdList);
				spreadActivityGroupSetDtl.setUpdateBy(spreadActivityGroupSet.getUpdateBy());;
				spreadActivityGroupSetDtl.setUpdateDate(spreadActivityGroupSet.getUpdateDate());;
				spreadActivityGroupSetDtl.setDelFlag("1");
				spreadActivityGroupSetDtlMapper.updateByExampleSelective(spreadActivityGroupSetDtl, spreadActivityGroupSetDtlExample);
				SpreadActivityGroupSetDtlExample sActivityGroupSetDtlExample = new SpreadActivityGroupSetDtlExample();
				SpreadActivityGroupSetDtlExample.Criteria sActivityGroupSetDtlCriteria = sActivityGroupSetDtlExample.createCriteria();
				sActivityGroupSetDtlCriteria.andDelFlagEqualTo("0")
					.andSpreadActivityGroupSetIdEqualTo(spreadActivityGroupSet.getId())
					.andActivityGroupIdIn(groupIdList);
				List<SpreadActivityGroupSetDtl> spreadActivityGroupSetDtlList = spreadActivityGroupSetDtlMapper.selectByExample(sActivityGroupSetDtlExample);
				for(SpreadActivityGroupSetDtl sGroupSetDtl : spreadActivityGroupSetDtlList ) {
					groupIdList.remove(sGroupSetDtl.getActivityGroupId());
				}
				for(Integer groupId : groupIdList ) {
					SpreadActivityGroupSetDtl sGroupSetDtl = new SpreadActivityGroupSetDtl();
					sGroupSetDtl.setSpreadActivityGroupSetId(spreadActivityGroupSet.getId());
					sGroupSetDtl.setActivityGroupId(groupId);
					sGroupSetDtl.setCreateBy(spreadActivityGroupSet.getUpdateBy());
					sGroupSetDtl.setCreateDate(spreadActivityGroupSet.getUpdateDate());
					spreadActivityGroupSetDtlMapper.insertSelective(sGroupSetDtl);
				}
			}
		}
	}
	
	
}
