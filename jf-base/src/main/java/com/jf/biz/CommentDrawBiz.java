package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CommentDrawExtMapper;
import com.jf.dao.CommentDrawMapper;
import com.jf.entity.CommentDraw;
import com.jf.entity.CommentDrawExample;
import com.jf.entity.CommentDrawExt;
import com.jf.entity.CommentDrawExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentDrawBiz extends BaseService<CommentDraw, CommentDrawExample> {

	@Autowired
	private CommentDrawMapper dao;
	@Autowired
	private CommentDrawExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCommentDrawMapper(CommentDrawMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CommentDrawExt save(CommentDrawExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CommentDraw update(CommentDraw model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CommentDraw model = new CommentDraw();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CommentDrawExt findById(int id){
		return extDao.findById(id);
	}

	public CommentDrawExt find(CommentDrawExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CommentDrawExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CommentDrawExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CommentDrawExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CommentDrawExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CommentDrawExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CommentDrawExt> list(CommentDrawExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CommentDrawExt> paginate(CommentDrawExtExample example) {
		List<CommentDrawExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
