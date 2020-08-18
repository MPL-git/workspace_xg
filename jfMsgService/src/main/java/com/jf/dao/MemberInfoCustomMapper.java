package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoCustom;
import com.jf.entity.MemberInfoCustomExample;
import com.jf.entity.MemberInfoExample;
@Repository
public interface MemberInfoCustomMapper extends BaseDao<MemberInfo, MemberInfoExample>{

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月18日 上午10:37:42
	 */
	List<Integer> selectByCustomExample();

	/**
	 * @MethodName updateNovaProjectDate
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月18日 上午11:04:43
	 */
	void updateNovaProjectDate(List<Integer> list);
	
	List<MemberInfoCustom> selectMemberInfoCustomExample(MemberInfoCustomExample memberInfoCustomExample);

}