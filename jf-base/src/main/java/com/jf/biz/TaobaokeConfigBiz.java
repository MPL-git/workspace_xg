package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.TaobaokeConfigExtMapper;
import com.jf.dao.TaobaokeConfigMapper;
import com.jf.entity.TaobaokeConfig;
import com.jf.entity.TaobaokeConfigExample;
import com.jf.entity.TaobaokeConfigExt;
import com.jf.entity.TaobaokeConfigExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaobaokeConfigBiz extends BaseService<TaobaokeConfig, TaobaokeConfigExample> {

	@Autowired
	private TaobaokeConfigMapper dao;
	@Autowired
	private TaobaokeConfigExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setTaobaokeConfigMapper(TaobaokeConfigMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public TaobaokeConfigExt save(TaobaokeConfigExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public TaobaokeConfig update(TaobaokeConfig model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		TaobaokeConfig model = new TaobaokeConfig();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public TaobaokeConfigExt findById(int id){
		return extDao.findById(id);
	}

	public TaobaokeConfigExt find(TaobaokeConfigExtExample example){
		return extDao.find(example.fill());
	}

	public int count(TaobaokeConfigExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, TaobaokeConfigExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, TaobaokeConfigExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, TaobaokeConfigExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(TaobaokeConfigExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<TaobaokeConfigExt> list(TaobaokeConfigExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<TaobaokeConfigExt> paginate(TaobaokeConfigExtExample example) {
		List<TaobaokeConfigExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
