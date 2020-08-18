package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Constant;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MenuExtMapper;
import com.jf.dao.MenuMapper;
import com.jf.entity.Menu;
import com.jf.entity.MenuExample;
import com.jf.entity.MenuExt;
import com.jf.entity.MenuExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuBiz extends BaseService<Menu, MenuExample> {

	@Autowired
	private MenuMapper dao;
	@Autowired
	private MenuExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMenuMapper(MenuMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MenuExt save(MenuExt model){
		if (model.getId() != null && model.getId() > 0) {
			model.setUpdateDate(new Date());
			dao.updateByPrimaryKeySelective(model);
		} else {
			model.setCreateDate(new Date());
			dao.insertSelective(model);
		}

		return model;
	}

	public Menu update(Menu model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKey(model);
		return model;
	}

	public void delete(int id){
		Menu model = new Menu();
		model.setId(id);
		model.setDelFlag(Constant.FLAG_TRUE);
		dao.updateByPrimaryKeySelective(model);
	}

	public MenuExt findById(int id){
		return extDao.findById(id);
	}

	public MenuExt find(MenuExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MenuExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MenuExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MenuExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MenuExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MenuExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MenuExt> list(MenuExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MenuExt> paginate(MenuExtExample example) {
		List<MenuExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
