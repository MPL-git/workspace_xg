package com.jf.dao;

import com.jf.entity.MchtBlPicChg;
import com.jf.entity.MchtBlPicChgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MchtBlPicChgMapper extends BaseDao<MchtBlPicChg, MchtBlPicChgExample>{
    int countByExample(MchtBlPicChgExample example);

    int deleteByExample(MchtBlPicChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBlPicChg record);

    int insertSelective(MchtBlPicChg record);

    List<MchtBlPicChg> selectByExample(MchtBlPicChgExample example);

    MchtBlPicChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBlPicChg record, @Param("example") MchtBlPicChgExample example);

    int updateByExample(@Param("record") MchtBlPicChg record, @Param("example") MchtBlPicChgExample example);

    int updateByPrimaryKeySelective(MchtBlPicChg record);

    int updateByPrimaryKey(MchtBlPicChg record);
}