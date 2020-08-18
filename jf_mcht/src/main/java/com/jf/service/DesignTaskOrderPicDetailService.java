package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DesignTaskOrderPicDetailMapper;
import com.jf.entity.DesignTaskOrderPicDetail;
import com.jf.entity.DesignTaskOrderPicDetailExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pengl
 * @create 2020-03-16 下午 6:22
 */
@Service
@Transactional
public class DesignTaskOrderPicDetailService extends BaseService<DesignTaskOrderPicDetail, DesignTaskOrderPicDetailExample> {

	@Autowired
	private DesignTaskOrderPicDetailMapper designTaskOrderPicDetailMapper;

	@Autowired
	public void setDesignTaskOrderPicDetailMapper(DesignTaskOrderPicDetailMapper designTaskOrderPicDetailMapper) {
		super.setDao(designTaskOrderPicDetailMapper);
		this.dao = designTaskOrderPicDetailMapper;
	}

}
