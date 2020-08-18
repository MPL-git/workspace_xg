package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.AreaExtMapper;
import com.jf.dao.AreaMapper;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.AreaExt;
import com.jf.entity.AreaExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaBiz extends BaseService<Area, AreaExample> {

	@Autowired
	private AreaMapper dao;
	@Autowired
	private AreaExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setAreaMapper(AreaMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public AreaExt findById(int id){
		return extDao.findById(id);
	}

	public AreaExt find(AreaExtExample example){
		return extDao.find(example.fill());
	}

	public int count(AreaExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, AreaExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, AreaExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, AreaExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(AreaExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<AreaExt> list(AreaExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<AreaExt> paginate(AreaExtExample example) {
		List<AreaExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
