package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CommentPicExtMapper;
import com.jf.dao.CommentPicMapper;
import com.jf.entity.CommentPic;
import com.jf.entity.CommentPicExample;
import com.jf.entity.CommentPicExt;
import com.jf.entity.CommentPicExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentPicBiz extends BaseService<CommentPic, CommentPicExample> {

	@Autowired
	private CommentPicMapper dao;
	@Autowired
	private CommentPicExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCommentPicMapper(CommentPicMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CommentPicExt save(CommentPicExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CommentPic update(CommentPic model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CommentPic model = new CommentPic();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CommentPicExt findById(int id){
		return extDao.findById(id);
	}

	public CommentPicExt find(CommentPicExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CommentPicExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CommentPicExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CommentPicExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CommentPicExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CommentPicExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CommentPicExt> list(CommentPicExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CommentPicExt> paginate(CommentPicExtExample example) {
		List<CommentPicExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
