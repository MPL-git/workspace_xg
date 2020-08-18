package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.vo.CurrentMember;
import com.jf.dao.MemberInfoExtMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MemberInfoExt;
import com.jf.entity.MemberInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberInfoBiz extends BaseService<MemberInfo, MemberInfoExample> {

	@Autowired
	private MemberInfoMapper dao;
	@Autowired
	private MemberInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * 创建当前登录对象
	 */
	public CurrentMember createCurrentMember(int id, String token) throws BizException {
		MemberInfo model = findById(id);

		CurrentMember currentMember = new CurrentMember();
		currentMember.setId(model.getId());
		currentMember.setLoginCode(model.getLoginCode());
		currentMember.setMobile(model.getMobile());
		currentMember.setPic(model.getPic());
		currentMember.setWeixinHead(model.getWeixinHead());
		currentMember.setNick(model.getNick());
		currentMember.setToken(token);
		return currentMember;
	}



	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberInfoMapper(MemberInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberInfoExt save(MemberInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberInfo update(MemberInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberInfo model = new MemberInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberInfoExt findById(int id){
		return extDao.findById(id);
	}

	public MemberInfoExt find(MemberInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberInfoExt> list(MemberInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberInfoExt> paginate(MemberInfoExtExample example) {
		List<MemberInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
