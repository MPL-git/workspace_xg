package com.jf.service;

import com.jf.dao.SvipOrderCustomMapper;
import com.jf.dao.SvipOrderMapper;
import com.jf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SvipOrderService extends BaseService<SvipOrder, SvipOrderExample> {

	@Autowired
	private SvipOrderMapper svipOrderMapper;
	
	@Autowired
	private SvipOrderCustomMapper svipOrderCustomMapper;
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	public void setSvipOrderMapper(SvipOrderMapper svipOrderMapper) {
		super.setDao(svipOrderMapper);
		this.svipOrderMapper = svipOrderMapper;
	}
	
	public int countByCustomExample(SvipOrderCustomExample example) {
		return svipOrderCustomMapper.countByCustomExample(example);
	}

	public List<SvipOrderCustom> selectByCustomExample(SvipOrderCustomExample example) {
		return svipOrderCustomMapper.selectByCustomExample(example);
	}

	public SvipOrder selectByCustomPrimaryKey(Integer id) {

		return svipOrderCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") SvipOrderCustom record, @Param("example") SvipOrderCustomExample example) {
		return svipOrderCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public List<Map<String, Object>> countSvipOrderList(Map<String, Object> paramMap) {
		return svipOrderCustomMapper.countSvipOrderList(paramMap);
	}

	public List<Map<String, Object>> refCountSvipOrderList(Map<String, Object> paramMap) {
		return svipOrderCustomMapper.refCountSvipOrderList(paramMap);
	}
	
	public void SvipOrderUpdateStatus(SvipOrder svipOrder,MemberInfo memberInfo) {
		memberInfoService.updateByPrimaryKeySelective(memberInfo);
		this.updateByPrimaryKeySelective(svipOrder);
	}
	
}
