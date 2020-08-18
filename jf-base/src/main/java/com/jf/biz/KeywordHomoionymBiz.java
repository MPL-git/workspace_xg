package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.KeywordHomoionymExtMapper;
import com.jf.dao.KeywordHomoionymMapper;
import com.jf.entity.KeywordHomoionym;
import com.jf.entity.KeywordHomoionymExample;
import com.jf.entity.KeywordHomoionymExt;
import com.jf.entity.KeywordHomoionymExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KeywordHomoionymBiz extends BaseService<KeywordHomoionym, KeywordHomoionymExample> {

	@Autowired
	private KeywordHomoionymMapper dao;
	@Autowired
	private KeywordHomoionymExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setKeywordHomoionymMapper(KeywordHomoionymMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public KeywordHomoionymExt save(KeywordHomoionymExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public KeywordHomoionym update(KeywordHomoionym model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		KeywordHomoionym model = new KeywordHomoionym();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public KeywordHomoionymExt findById(int id){
		return extDao.findById(id);
	}

	public KeywordHomoionymExt find(KeywordHomoionymExtExample example){
		return extDao.find(example.fill());
	}

	public int count(KeywordHomoionymExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, KeywordHomoionymExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, KeywordHomoionymExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, KeywordHomoionymExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(KeywordHomoionymExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<KeywordHomoionymExt> list(KeywordHomoionymExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<KeywordHomoionymExt> paginate(KeywordHomoionymExtExample example) {
		List<KeywordHomoionymExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
