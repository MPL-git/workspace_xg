package com.jf.service;

import com.jf.biz.MchtInfoChgBiz;
import com.jf.common.constant.Const;
import com.jf.entity.MchtInfoChgExt;
import com.jf.entity.MchtInfoChgExtExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtInfoChgService extends MchtInfoChgBiz {

	// -----------------------------------------------------------------------------------------------------------------
	// 业务方法
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * 获取商家最新的一条更新记录
	 */
	public MchtInfoChgExt findLatest(int mchtId){
		MchtInfoChgExtExample example = new MchtInfoChgExtExample();
		MchtInfoChgExtExample.MchtInfoChgExtCriteria criteria = example.createCriteria();
		criteria.andMchtIdEqualTo(mchtId);
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		example.setOrderByClause("a.id desc");
		return find(example);
	}

}
