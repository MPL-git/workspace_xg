package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CommentExtMapper;
import com.jf.dao.CommentMapper;
import com.jf.entity.Comment;
import com.jf.entity.CommentExample;
import com.jf.entity.CommentExt;
import com.jf.entity.CommentExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentBiz extends BaseService<Comment, CommentExample> {

	@Autowired
	private CommentMapper dao;
	@Autowired
	private CommentExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCommentMapper(CommentMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CommentExt save(CommentExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Comment update(Comment model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Comment model = new Comment();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CommentExt findById(int id){
		return extDao.findById(id);
	}

	public CommentExt find(CommentExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CommentExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CommentExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CommentExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CommentExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CommentExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CommentExt> list(CommentExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CommentExt> paginate(CommentExtExample example) {
		List<CommentExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
