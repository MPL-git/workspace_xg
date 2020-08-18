package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtInfoHis;
import com.jf.entity.MchtInfoHisExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInfoHisMapper extends BaseDao<MchtInfoHis, MchtInfoHisExample> {
    int countByExample(MchtInfoHisExample example);

    int deleteByExample(MchtInfoHisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtInfoHis record);

    int insertSelective(MchtInfoHis record);

    List<MchtInfoHis> selectByExample(MchtInfoHisExample example);

    MchtInfoHis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtInfoHis record, @Param("example") MchtInfoHisExample example);

    int updateByExample(@Param("record") MchtInfoHis record, @Param("example") MchtInfoHisExample example);

    int updateByPrimaryKeySelective(MchtInfoHis record);

    int updateByPrimaryKey(MchtInfoHis record);
}
