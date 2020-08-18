package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.CommentDrawSettingExtMapper;
import com.jf.dao.CommentDrawSettingMapper;
import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingExample;
import com.jf.entity.CommentDrawSettingExt;
import com.jf.entity.CommentDrawSettingExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentDrawSettingBiz extends BaseService<CommentDrawSetting, CommentDrawSettingExample> {

	@Autowired
	private CommentDrawSettingMapper dao;
	@Autowired
	private CommentDrawSettingExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setCommentDrawSettingMapper(CommentDrawSettingMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public CommentDrawSettingExt save(CommentDrawSettingExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public CommentDrawSetting update(CommentDrawSetting model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		CommentDrawSetting model = new CommentDrawSetting();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public CommentDrawSettingExt findById(int id){
		return extDao.findById(id);
	}

	public CommentDrawSettingExt find(CommentDrawSettingExtExample example){
		return extDao.find(example.fill());
	}

	public int count(CommentDrawSettingExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, CommentDrawSettingExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, CommentDrawSettingExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, CommentDrawSettingExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(CommentDrawSettingExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<CommentDrawSettingExt> list(CommentDrawSettingExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<CommentDrawSettingExt> paginate(CommentDrawSettingExtExample example) {
		List<CommentDrawSettingExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
