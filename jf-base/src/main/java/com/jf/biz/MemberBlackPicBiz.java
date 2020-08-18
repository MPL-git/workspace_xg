package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberBlackPicExtMapper;
import com.jf.dao.MemberBlackPicMapper;
import com.jf.entity.MemberBlackPic;
import com.jf.entity.MemberBlackPicExample;
import com.jf.entity.MemberBlackPicExt;
import com.jf.entity.MemberBlackPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberBlackPicBiz extends BaseService<MemberBlackPic, MemberBlackPicExample> {

	@Autowired
	private MemberBlackPicMapper dao;
	@Autowired
	private MemberBlackPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberBlackPicMapper(MemberBlackPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberBlackPicExt save(MemberBlackPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberBlackPic update(MemberBlackPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberBlackPic model = new MemberBlackPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberBlackPicExt findById(int id){
		return extDao.findById(id);
	}

	public MemberBlackPicExt find(MemberBlackPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberBlackPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberBlackPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberBlackPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberBlackPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberBlackPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberBlackPicExt> list(MemberBlackPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberBlackPicExt> paginate(MemberBlackPicExtExample example) {
		List<MemberBlackPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
