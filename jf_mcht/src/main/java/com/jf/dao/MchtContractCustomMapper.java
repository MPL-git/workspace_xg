package com.jf.dao;

import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractCustomExample;
import com.jf.entity.MchtContractExample;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtContractCustomMapper {

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月9日 下午4:52:44
	 */
	List<MchtContract> selectByCustomExample(
			MchtContractCustomExample mchtContractCustomExample);
  
}
