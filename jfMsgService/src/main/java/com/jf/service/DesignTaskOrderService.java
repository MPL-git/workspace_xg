package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DesignTaskOrderMapper;
import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pengl
 * @create 2020-03-20 下午 2:01
 */
@Service
@Transactional
public class DesignTaskOrderService extends BaseService<DesignTaskOrder, DesignTaskOrderExample> {

	@Autowired
	private DesignTaskOrderMapper designTaskOrderMapper;

	@Autowired
	public void setDesignTaskOrderMapper(DesignTaskOrderMapper designTaskOrderMapper) {
		super.setDao(designTaskOrderMapper);
		this.designTaskOrderMapper = designTaskOrderMapper;
	}

}
