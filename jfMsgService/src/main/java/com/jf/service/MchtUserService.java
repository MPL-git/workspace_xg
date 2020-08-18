package com.jf.service;

import com.jf.biz.MchtUserBiz;
import com.jf.common.constant.Const;
import com.jf.entity.MchtUserExt;
import com.jf.entity.MchtUserExtExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtUserService extends MchtUserBiz {

	/**
	 * 获取商家主账号手机
	 */
	public String findMobileByMchtId(int mchtId){
		MchtUserExtExample example = new MchtUserExtExample();
		MchtUserExtExample.MchtUserExtCriteria criteria = example.createCriteria();
		criteria.andMchtIdEqualTo(mchtId);
		criteria.andIsPrimaryEqualTo("1");
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);

		MchtUserExt model = find(example);
		return model == null ? null : model.getMobile();
	}

}
