package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdInfoMapper extends BaseDao<AdInfo, AdInfoExample> {
    int countByExample(AdInfoExample example);

    int deleteByExample(AdInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdInfo record);

    int insertSelective(AdInfo record);

    List<AdInfo> selectByExample(AdInfoExample example);

    AdInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdInfo record, @Param("example") AdInfoExample example);

    int updateByExample(@Param("record") AdInfo record, @Param("example") AdInfoExample example);

    int updateByPrimaryKeySelective(AdInfo record);

    int updateByPrimaryKey(AdInfo record);
}
