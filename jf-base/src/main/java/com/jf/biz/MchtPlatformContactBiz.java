package com.jf.biz;

import com.jf.common.base.BaseService;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtPlatformContactExtMapper;
import com.jf.dao.MchtPlatformContactMapper;
import com.jf.entity.MchtPlatformContact;
import com.jf.entity.MchtPlatformContactExample;
import com.jf.entity.MchtPlatformContactExt;
import com.jf.entity.MchtPlatformContactExtExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MchtPlatformContactBiz extends BaseService<MchtPlatformContact, MchtPlatformContactExample> {

	@Autowired
	private MchtPlatformContactMapper dao;
	@Autowired
	private MchtPlatformContactExtMapper extDao;

	// -----------------------------------------------------------------------------------------------------------------
	// 公用业务方法
	// -----------------------------------------------------------------------------------------------------------------





	// -----------------------------------------------------------------------------------------------------------------
	// 基本方法
	// -----------------------------------------------------------------------------------------------------------------

	@Autowired
	public void setMchtPlatformContactMapper(MchtPlatformContactMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}

	public MchtPlatformContactExt findById(int id){
		return extDao.findById(id);
	}

	public MchtPlatformContactExt find(MchtPlatformContactExtExample example){
		return extDao.find(example.fill());
	}

	public int count(MchtPlatformContactExtExample example) {
		return extDao.count(example);
	}

	public long sum(String field, MchtPlatformContactExtExample example) {
		return extDao.sum(field, example);
	}

	public int max(String field, MchtPlatformContactExtExample example) {
		return extDao.max(field, example);
	}

	public int min(String field, MchtPlatformContactExtExample example) {
		return extDao.min(field, example);
	}

	public List<Integer> listId(MchtPlatformContactExtExample example) {
		return extDao.listId(example.fill());
	}

	public List<MchtPlatformContactExt> list(MchtPlatformContactExtExample example) {
		return extDao.list(example.fill());
	}

	public Page<MchtPlatformContactExt> paginate(MchtPlatformContactExtExample example) {
		List<MchtPlatformContactExt> list = extDao.list(example.fillPage());
		int totalCount = extDao.count(example);

		QueryObject queryObject = example.getQueryObject();
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

}
