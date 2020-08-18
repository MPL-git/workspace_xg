package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.JgContentExtMapper;
import com.jf.dao.JgContentMapper;
import com.jf.entity.JgContent;
import com.jf.entity.JgContentExample;
import com.jf.entity.JgContentExt;
import com.jf.entity.JgContentExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JgContentBiz extends BaseService<JgContent, JgContentExample> {

	@Autowired
	private JgContentMapper dao;
	@Autowired
	private JgContentExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setJgContentMapper(JgContentMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public JgContentExt save(JgContentExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public JgContent update(JgContent model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		JgContent model = new JgContent();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public JgContentExt findById(int id){
		return extDao.findById(id);
	}

	public JgContentExt find(JgContentExtExample example){
		return extDao.find(example.fill());
	}

	public int count(JgContentExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, JgContentExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, JgContentExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, JgContentExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(JgContentExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<JgContentExt> list(JgContentExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<JgContentExt> paginate(JgContentExtExample example) {
		List<JgContentExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
