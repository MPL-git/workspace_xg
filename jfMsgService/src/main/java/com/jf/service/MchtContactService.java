package com.jf.service;

import com.jf.biz.MchtContactBiz;
import com.jf.common.constant.Const;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactExtExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtContactService extends MchtContactBiz {

	// -----------------------------------------------------------------------------------------------------------------
	// 业务方法
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * 获取商家对接人的手机号
	 */
	public String findMobile(int mchtId, String contactType){
		MchtContact model = findByMchtId(mchtId, contactType);
		return model != null ? model.getMobile() : null;
	}

	public MchtContact findByMchtId(int mchtId, String contactType){
		MchtContactExtExample example = new MchtContactExtExample();
		MchtContactExtExample.MchtContactExtCriteria criteria = example.createCriteria();
		criteria.andMchtIdEqualTo(mchtId);
		criteria.andContactTypeEqualTo(contactType);
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		return find(example);
	}


}
