package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberStatisticalInfoExtMapper;
import com.jf.dao.MemberStatisticalInfoMapper;
import com.jf.entity.MemberStatisticalInfo;
import com.jf.entity.MemberStatisticalInfoExample;
import com.jf.entity.MemberStatisticalInfoExt;
import com.jf.entity.MemberStatisticalInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberStatisticalInfoBiz extends BaseService<MemberStatisticalInfo, MemberStatisticalInfoExample> {

	@Autowired
	private MemberStatisticalInfoMapper dao;
	@Autowired
	private MemberStatisticalInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMemberStatisticalInfoMapper(MemberStatisticalInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MemberStatisticalInfoExt save(MemberStatisticalInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public MemberStatisticalInfo update(MemberStatisticalInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		MemberStatisticalInfo model = new MemberStatisticalInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MemberStatisticalInfoExt findById(int id){
		return extDao.findById(id);
	}

	public MemberStatisticalInfoExt find(MemberStatisticalInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MemberStatisticalInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MemberStatisticalInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MemberStatisticalInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MemberStatisticalInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MemberStatisticalInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MemberStatisticalInfoExt> list(MemberStatisticalInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MemberStatisticalInfoExt> paginate(MemberStatisticalInfoExtExample example) {
		List<MemberStatisticalInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
