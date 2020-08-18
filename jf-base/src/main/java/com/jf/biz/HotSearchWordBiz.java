package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.HotSearchWordExtMapper;
import com.jf.dao.HotSearchWordMapper;
import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordExample;
import com.jf.entity.HotSearchWordExt;
import com.jf.entity.HotSearchWordExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HotSearchWordBiz extends BaseService<HotSearchWord, HotSearchWordExample> {

	@Autowired
	private HotSearchWordMapper dao;
	@Autowired
	private HotSearchWordExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setHotSearchWordMapper(HotSearchWordMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public HotSearchWordExt save(HotSearchWordExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public HotSearchWord update(HotSearchWord model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		HotSearchWord model = new HotSearchWord();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public HotSearchWordExt findById(int id){
		return extDao.findById(id);
	}

	public HotSearchWordExt find(HotSearchWordExtExample example){
		return extDao.find(example.fill());
	}

	public int count(HotSearchWordExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, HotSearchWordExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, HotSearchWordExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, HotSearchWordExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(HotSearchWordExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<HotSearchWordExt> list(HotSearchWordExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<HotSearchWordExt> paginate(HotSearchWordExtExample example) {
		List<HotSearchWordExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
