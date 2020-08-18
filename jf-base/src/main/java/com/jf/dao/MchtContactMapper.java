package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtContactExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtContactMapper extends BaseDao<MchtContact, MchtContactExample> {
    int countByExample(MchtContactExample example);

    int deleteByExample(MchtContactExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtContact record);

    int insertSelective(MchtContact record);

    List<MchtContact> selectByExample(MchtContactExample example);

    MchtContact selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtContact record, @Param("example") MchtContactExample example);

    int updateByExample(@Param("record") MchtContact record, @Param("example") MchtContactExample example);

    int updateByPrimaryKeySelective(MchtContact record);

    int updateByPrimaryKey(MchtContact record);
}
