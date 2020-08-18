package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberAddressExtMapper;
import com.jf.dao.MemberAddressMapper;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressExample;
import com.jf.entity.MemberAddressExt;
import com.jf.entity.MemberAddressExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberAddressBiz extends BaseService<MemberAddress, MemberAddressExample> {

	@Autowired
	private MemberAddressMapper dao;
	@Autowired
	private MemberAddressExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberAddressMapper(MemberAddressMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberAddressExt save(MemberAddressExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberAddress update(MemberAddress model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberAddress model = new MemberAddress();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberAddressExt findById(int id){
		return extDao.findById(id);
	}

	public MemberAddressExt find(MemberAddressExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberAddressExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberAddressExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberAddressExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberAddressExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberAddressExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberAddressExt> list(MemberAddressExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberAddressExt> paginate(MemberAddressExtExample example) {
		List<MemberAddressExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
