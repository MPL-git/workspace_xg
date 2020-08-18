package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysMenuExtMapper;
import com.jf.dao.SysMenuMapper;
import com.jf.entity.SysMenu;
import com.jf.entity.SysMenuExample;
import com.jf.entity.SysMenuExt;
import com.jf.entity.SysMenuExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuBiz extends BaseService<SysMenu, SysMenuExample> {

	@Autowired
	private SysMenuMapper dao;
	@Autowired
	private SysMenuExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setSysMenuMapper(SysMenuMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public SysMenuExt findById(int id){
		return extDao.findById(id);
	}

	public SysMenuExt find(SysMenuExtExample example){
		return extDao.find(example.fill());
	}

	public int count(SysMenuExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, SysMenuExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, SysMenuExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, SysMenuExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(SysMenuExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<SysMenuExt> list(SysMenuExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<SysMenuExt> paginate(SysMenuExtExample example) {
		List<SysMenuExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
