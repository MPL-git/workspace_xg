package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoChgCustom;
import com.jf.entity.MchtInfoChgExample;
@Repository
public interface MchtInfoChgCustomMapper{
    int countByExample(MchtInfoChgExample example);
    List<MchtInfoChgCustom> selectByExample(MchtInfoChgExample example);
    MchtInfoChgCustom selectByPrimaryKey(Integer id);
	/**
	 * @MethodName updateByPrimaryKeyCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月30日 下午1:50:22
	 */
	void updateByPrimaryKeyCustom(@Param("mchtInfoChg") MchtInfoChg mchtInfoChg);
	/**
	 * @MethodName updateByPrimaryKeyCustoms
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月14日 上午10:12:59
	 */
	void updateByPrimaryKeyCustoms(@Param("mchtInfoChg") MchtInfoChg mchtInfoChg);

}