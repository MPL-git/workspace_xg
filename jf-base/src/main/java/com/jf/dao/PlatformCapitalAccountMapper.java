package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PlatformCapitalAccount;
import com.jf.entity.PlatformCapitalAccountExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformCapitalAccountMapper extends BaseDao<PlatformCapitalAccount, PlatformCapitalAccountExample> {
    int countByExample(PlatformCapitalAccountExample example);

    int deleteByExample(PlatformCapitalAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformCapitalAccount record);

    int insertSelective(PlatformCapitalAccount record);

    List<PlatformCapitalAccount> selectByExample(PlatformCapitalAccountExample example);

    PlatformCapitalAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformCapitalAccount record, @Param("example") PlatformCapitalAccountExample example);

    int updateByExample(@Param("record") PlatformCapitalAccount record, @Param("example") PlatformCapitalAccountExample example);

    int updateByPrimaryKeySelective(PlatformCapitalAccount record);

    int updateByPrimaryKey(PlatformCapitalAccount record);
}
