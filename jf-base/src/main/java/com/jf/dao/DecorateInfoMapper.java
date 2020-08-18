package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorateInfoMapper extends BaseDao<DecorateInfo, DecorateInfoExample> {
    int countByExample(DecorateInfoExample example);

    int deleteByExample(DecorateInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DecorateInfo record);

    int insertSelective(DecorateInfo record);

    List<DecorateInfo> selectByExample(DecorateInfoExample example);

    DecorateInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DecorateInfo record, @Param("example") DecorateInfoExample example);

    int updateByExample(@Param("record") DecorateInfo record, @Param("example") DecorateInfoExample example);

    int updateByPrimaryKeySelective(DecorateInfo record);

    int updateByPrimaryKey(DecorateInfo record);
}
