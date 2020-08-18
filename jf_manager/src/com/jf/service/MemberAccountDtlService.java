package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberAccountDtlCustomMapper;
import com.jf.dao.MemberAccountDtlMapper;
import com.jf.entity.MemberAccountDtl;
import com.jf.entity.MemberAccountDtlCustom;
import com.jf.entity.MemberAccountDtlCustomExample;
import com.jf.entity.MemberAccountDtlExample;

@Service
@Transactional
public class MemberAccountDtlService extends BaseService<MemberAccountDtl, MemberAccountDtlExample> {

	@Autowired
	private MemberAccountDtlMapper memberAccountDtlMapper;
	
	@Autowired
	private MemberAccountDtlCustomMapper memberAccountDtlCustomMapper;
	
	@Autowired
	public void setmemberAccountDtlMapper(MemberAccountDtlMapper memberAccountDtlMapper) {
		super.setDao(memberAccountDtlMapper);
		this.memberAccountDtlMapper = memberAccountDtlMapper;
	}
	
	public int countByCustomExample(MemberAccountDtlCustomExample example) {
		return memberAccountDtlCustomMapper.countByCustomExample(example);
	}

	public List<MemberAccountDtlCustom> selectByCustomExample(MemberAccountDtlCustomExample example) {
		return memberAccountDtlCustomMapper.selectByCustomExample(example);
	}

	public MemberAccountDtlCustom selectByCustomPrimaryKey(Integer id) {
		return memberAccountDtlCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(MemberAccountDtl record, MemberAccountDtlCustomExample example) {
		return memberAccountDtlCustomMapper.updateByCustomExampleSelective(record, example);
	}

	//分润记录总数
	public Integer countByCustomExampleFenRun(MemberAccountDtlCustomExample memberAccountDtlCustomExample) {
		// TODO Auto-generated method stub
		return memberAccountDtlCustomMapper.countByCustomExampleFenRun(memberAccountDtlCustomExample);
	}
	
	//分润记录列表
	public List<MemberAccountDtlCustom> selectByCustomExampleFenRun(MemberAccountDtlCustomExample memberAccountDtlCustomExample) {
		// TODO Auto-generated method stub
		return memberAccountDtlCustomMapper.selectByCustomExampleFenRun(memberAccountDtlCustomExample);
	}
	
	//新星余额每日汇总
	public List<MemberAccountDtlCustom> selectMemberAccountDtlCountEachDayList(
			HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<MemberAccountDtlCustom> list = memberAccountDtlCustomMapper.selectMemberAccountDtlCountEachDayList(paramMap);
		return list;
	}

	
}
