package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CutLinkClickLog;
import com.jf.entity.CutLinkClickLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutLinkClickLogMapper extends BaseDao<CutLinkClickLog, CutLinkClickLogExample> {
    int countByExample(CutLinkClickLogExample example);

    int deleteByExample(CutLinkClickLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutLinkClickLog record);

    int insertSelective(CutLinkClickLog record);

    List<CutLinkClickLog> selectByExample(CutLinkClickLogExample example);

    CutLinkClickLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutLinkClickLog record, @Param("example") CutLinkClickLogExample example);

    int updateByExample(@Param("record") CutLinkClickLog record, @Param("example") CutLinkClickLogExample example);

    int updateByPrimaryKeySelective(CutLinkClickLog record);

    int updateByPrimaryKey(CutLinkClickLog record);
}
