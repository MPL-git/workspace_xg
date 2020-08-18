package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SportGuessExtMapper;
import com.jf.dao.SportGuessMapper;
import com.jf.entity.SportGuess;
import com.jf.entity.SportGuessExample;
import com.jf.entity.SportGuessExt;
import com.jf.entity.SportGuessExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SportGuessBiz extends BaseService<SportGuess, SportGuessExample> {

	@Autowired
	private SportGuessMapper dao;
	@Autowired
	private SportGuessExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSportGuessMapper(SportGuessMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SportGuessExt save(SportGuessExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public SportGuess update(SportGuess model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		SportGuess model = new SportGuess();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public SportGuessExt findById(int id){
		return extDao.findById(id);
	}

	public SportGuessExt find(SportGuessExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SportGuessExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SportGuessExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SportGuessExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SportGuessExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SportGuessExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SportGuessExt> list(SportGuessExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SportGuessExt> paginate(SportGuessExtExample example) {
		List<SportGuessExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
