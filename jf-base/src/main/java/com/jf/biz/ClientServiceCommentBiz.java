package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.ClientServiceCommentExtMapper;
import com.jf.dao.ClientServiceCommentMapper;
import com.jf.entity.ClientServiceComment;
import com.jf.entity.ClientServiceCommentExample;
import com.jf.entity.ClientServiceCommentExt;
import com.jf.entity.ClientServiceCommentExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientServiceCommentBiz extends BaseService<ClientServiceComment, ClientServiceCommentExample> {

	@Autowired
	private ClientServiceCommentMapper dao;
	@Autowired
	private ClientServiceCommentExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setClientServiceCommentMapper(ClientServiceCommentMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public ClientServiceCommentExt save(ClientServiceCommentExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public ClientServiceComment update(ClientServiceComment model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		ClientServiceComment model = new ClientServiceComment();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public ClientServiceCommentExt findById(int id){
		return extDao.findById(id);
	}

	public ClientServiceCommentExt find(ClientServiceCommentExtExample example){
		return extDao.find(example.fill());
	}

	public int count(ClientServiceCommentExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, ClientServiceCommentExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, ClientServiceCommentExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, ClientServiceCommentExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(ClientServiceCommentExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<ClientServiceCommentExt> list(ClientServiceCommentExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<ClientServiceCommentExt> paginate(ClientServiceCommentExtExample example) {
		List<ClientServiceCommentExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
