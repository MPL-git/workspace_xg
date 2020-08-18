package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBlPicHis;
import com.jf.entity.MchtBlPicHisExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBlPicHisMapper extends BaseDao<MchtBlPicHis, MchtBlPicHisExample> {
    int countByExample(MchtBlPicHisExample example);

    int deleteByExample(MchtBlPicHisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBlPicHis record);

    int insertSelective(MchtBlPicHis record);

    List<MchtBlPicHis> selectByExample(MchtBlPicHisExample example);

    MchtBlPicHis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBlPicHis record, @Param("example") MchtBlPicHisExample example);

    int updateByExample(@Param("record") MchtBlPicHis record, @Param("example") MchtBlPicHisExample example);

    int updateByPrimaryKeySelective(MchtBlPicHis record);

    int updateByPrimaryKey(MchtBlPicHis record);
}
