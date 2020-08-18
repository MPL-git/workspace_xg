package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DesignTaskOrderPicMapper;
import com.jf.entity.DesignTaskOrderPic;
import com.jf.entity.DesignTaskOrderPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pengl
 * @create 2020-03-18 上午 11:02
 */
@Service
@Transactional
public class DesignTaskOrderPicService extends BaseService<DesignTaskOrderPic, DesignTaskOrderPicExample> {

	@Autowired
	private DesignTaskOrderPicMapper designTaskOrderPicMapper;

	@Autowired
	public void setDesignTaskOrderPicMapper(DesignTaskOrderPicMapper designTaskOrderPicMapper) {
		super.setDao(designTaskOrderPicMapper);
		this.dao = designTaskOrderPicMapper;
	}

}
