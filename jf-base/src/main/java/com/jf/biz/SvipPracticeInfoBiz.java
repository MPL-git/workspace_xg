package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SvipPracticeInfoExtMapper;
import com.jf.dao.SvipPracticeInfoMapper;
import com.jf.entity.SvipPracticeInfo;
import com.jf.entity.SvipPracticeInfoExample;
import com.jf.entity.SvipPracticeInfoExt;
import com.jf.entity.SvipPracticeInfoExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SvipPracticeInfoBiz extends BaseService<SvipPracticeInfo, SvipPracticeInfoExample> {

	@Autowired
	private SvipPracticeInfoMapper dao;
	@Autowired
	private SvipPracticeInfoExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSvipPracticeInfoMapper(SvipPracticeInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SvipPracticeInfoExt save(SvipPracticeInfoExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SvipPracticeInfo update(SvipPracticeInfo model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SvipPracticeInfo model = new SvipPracticeInfo();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SvipPracticeInfoExt findById(int id){
		return extDao.findById(id);
	}

	public SvipPracticeInfoExt find(SvipPracticeInfoExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SvipPracticeInfoExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SvipPracticeInfoExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SvipPracticeInfoExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SvipPracticeInfoExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SvipPracticeInfoExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SvipPracticeInfoExt> list(SvipPracticeInfoExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SvipPracticeInfoExt> paginate(SvipPracticeInfoExtExample example) {
		List<SvipPracticeInfoExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
