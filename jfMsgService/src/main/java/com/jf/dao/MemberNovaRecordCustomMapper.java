package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberNovaRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberNovaRecordCustomMapper extends BaseDao<MemberNovaRecord, MemberNovaRecordExample>{

	/**
	 * @MethodName selectGroupByExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月18日 上午11:35:03
	 */
	List<MemberNovaRecord> selectGroupByExample(
			MemberNovaRecordExample memberNovaRecordExample);
	
}