package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractCustom;
import com.jf.entity.MchtContractExample;

@Repository
public interface MchtContractCustomMapper{

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月8日 上午9:20:02
	 */
	List<MchtContractCustom> selectByCustomExample(
			MchtContractExample mchtContractExample);
 
}
