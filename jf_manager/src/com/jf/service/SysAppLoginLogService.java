package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.SysAppLoginLogCustomMapper;
import com.jf.dao.SysAppLoginLogMapper;
import com.jf.entity.SysAppLoginLog;
import com.jf.entity.SysAppLoginLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysAppLoginLogService  extends BaseService<SysAppLoginLog, SysAppLoginLogExample> {
	@Autowired
	private SysAppLoginLogMapper sysAppLoginLogMapper;
	@Autowired
	private SysAppLoginLogCustomMapper customDao;



	@Autowired
	public void setProductPropMapper(SysAppLoginLogMapper sysAppLoginLogMapper) {
		super.setDao(sysAppLoginLogMapper);
		this.sysAppLoginLogMapper = sysAppLoginLogMapper;
	}




	public int countMember(QueryObject queryObject) {
		return customDao.countByExampleGroupByMemberId(builderQuery(queryObject));
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<SysAppLoginLog> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private SysAppLoginLogExample builderQuery(QueryObject queryObject) {
		SysAppLoginLogExample example = new SysAppLoginLogExample();
		SysAppLoginLogExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("createDateAfter") != null){
			criteria.andCreateDateGreaterThan(queryObject.getQueryToDate("createDateAfter"));
		}
		if(queryObject.getQuery("createDateBefore") != null){
			criteria.andCreateDateLessThan(queryObject.getQueryToDate("createDateBefore"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}
}
