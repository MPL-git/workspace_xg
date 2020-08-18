package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BlackListCustomMapper;
import com.jf.dao.BlackListMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.entity.BlackList;
import com.jf.entity.BlackListCustom;
import com.jf.entity.BlackListCustomExample;
import com.jf.entity.BlackListExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;

@Service
@Transactional
public class BlackListService extends BaseService<BlackList, BlackListExample> {

	@Autowired
	private BlackListMapper blackListMapper;
	
	@Autowired
	private BlackListCustomMapper blackListCustomMapper;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	public void setBlackListMapper(BlackListMapper blackListMapper) {
		super.setDao(blackListMapper);
		this.blackListMapper = blackListMapper;
	}
	
	public int countByCustomExample(BlackListCustomExample example) {
		return blackListCustomMapper.countByCustomExample(example);
	}

	public List<BlackListCustom> selectByCustomExample(BlackListCustomExample example) {
		return blackListCustomMapper.selectByCustomExample(example);
	}

	public BlackListCustom selectByCustomPrimaryKey(Integer id) {
		return blackListCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(BlackList record, BlackListCustomExample example) {
		return blackListCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	/**
	 * 
	 * @Title updateBlackListIdListSelective   
	 * @Description TODO(批量修改)   
	 * @author pengl
	 * @date 2018年6月11日 下午5:32:35
	 */
	public void updateBlackListIdListSelective(List<BlackList> record) {
		blackListCustomMapper.updateBlackListIdListSelective(record);
	}
	
	public Map<String, Object> inBlack(Integer staffId, int excelRows, Set<String> set) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		int num = 0;
		for(String memberMobile : set ) {
			MemberInfoExample memberInfoExample = new MemberInfoExample();
			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(memberMobile);
			memberInfoExample.setLimitSize(1);
			List<MemberInfo> memberInfoList = memberInfoMapper.selectByExample(memberInfoExample);
			if(memberInfoList != null && memberInfoList.size() > 0 ) {
				num++;
				BlackList blackList = new BlackList();
				blackList.setMemberId(memberInfoList.get(0).getId());
				blackList.setBlackType("4");
				blackList.setCreateBy(staffId);
				blackList.setCreateDate(date);
				blackList.setDelFlag("0");
				blackListMapper.insertSelective(blackList);
			}
		}
		map.put("successNum", num);
		map.put("loserNum", excelRows-num);
		return map;
	}
	
}
