package com.jf.service;

import com.jf.biz.MchtBrandChgBiz;
import com.jf.common.constant.Const;
import com.jf.entity.MchtBrandChgExt;
import com.jf.entity.MchtBrandChgExtExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtBrandChgService extends MchtBrandChgBiz {

	// -----------------------------------------------------------------------------------------------------------------
	// 业务方法
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * 获取商家品牌最新的一条更新记录
	 */
	public MchtBrandChgExt findLatest(int mchtProductBrandId){
		MchtBrandChgExtExample example = new MchtBrandChgExtExample();
		MchtBrandChgExtExample.MchtBrandChgExtCriteria criteria = example.createCriteria();
		criteria.andMchtProductBrandIdEqualTo(mchtProductBrandId);
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		example.setOrderByClause("a.id desc");
		return find(example);
	}

}
