package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ViolateComplainOrderExtMapper;
import com.jf.dao.ViolateComplainOrderMapper;
import com.jf.entity.ViolateComplainOrder;
import com.jf.entity.ViolateComplainOrderExample;
import com.jf.entity.ViolateComplainOrderExt;
import com.jf.entity.ViolateComplainOrderExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ViolateComplainOrderBiz extends BaseService<ViolateComplainOrder, ViolateComplainOrderExample> {

	@Autowired
	private ViolateComplainOrderMapper dao;
	@Autowired
	private ViolateComplainOrderExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setViolateComplainOrderMapper(ViolateComplainOrderMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ViolateComplainOrderExt save(ViolateComplainOrderExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ViolateComplainOrder update(ViolateComplainOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ViolateComplainOrder model = new ViolateComplainOrder();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ViolateComplainOrderExt findById(int id){
		return extDao.findById(id);
	}

	public ViolateComplainOrderExt find(ViolateComplainOrderExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ViolateComplainOrderExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ViolateComplainOrderExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ViolateComplainOrderExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ViolateComplainOrderExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ViolateComplainOrderExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ViolateComplainOrderExt> list(ViolateComplainOrderExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ViolateComplainOrderExt> paginate(ViolateComplainOrderExtExample example) {
		List<ViolateComplainOrderExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
