package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DepositOrderMapper;
import com.jf.dao.DesignTaskOrderCustomMapper;
import com.jf.dao.DesignTaskOrderMapper;
import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskOrderCustom;
import com.jf.entity.DesignTaskOrderCustomExample;
import com.jf.entity.DesignTaskOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Pengl
 * @create 2020-03-16 下午 2:54
 */
@Service
@Transactional
public class DesignTaskOrderService extends BaseService<DesignTaskOrder, DesignTaskOrderExample> {

	@Autowired
	private DesignTaskOrderMapper designTaskOrderMapper;

	@Autowired
	private DesignTaskOrderCustomMapper designTaskOrderCustomMapper;

	@Autowired
	public void setDesignTaskOrderMapper(DesignTaskOrderMapper designTaskOrderMapper) {
		super.setDao(designTaskOrderMapper);
		this.dao = designTaskOrderMapper;
	}

	public int countByCustomExample(DesignTaskOrderCustomExample example) {
		return designTaskOrderCustomMapper.countByCustomExample(example);
	}

	public List<DesignTaskOrderCustom> selectByCustomExample(DesignTaskOrderCustomExample example) {
		return designTaskOrderCustomMapper.selectByCustomExample(example);
	}

	public DesignTaskOrderCustom selectByCustomPrimaryKey(Integer id) {
		return designTaskOrderCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") DesignTaskOrder record, @Param("example") DesignTaskOrderCustomExample example) {
		return designTaskOrderCustomMapper.updateByCustomExampleSelective(record, example);
	}



}
