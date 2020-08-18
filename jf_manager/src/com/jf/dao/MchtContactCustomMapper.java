package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchContactCustom;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactExample;
@Repository
public interface MchtContactCustomMapper{
	 int countByCustomExample(MchtContactExample example);
	 List<MchContactCustom> selectByCustomExample(MchtContactExample example);
	 MchContactCustom selectByCustomPrimaryKey(Integer id);
	List<MchtContact> selectByExampleGroupBy(@Param("ids") List<Integer> mchtIdList);
}