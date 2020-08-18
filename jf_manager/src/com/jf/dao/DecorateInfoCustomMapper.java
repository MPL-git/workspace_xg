package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;
@Repository
public interface DecorateInfoCustomMapper extends BaseDao<DecorateInfo, DecorateInfoExample>{
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

	int insertSelective1(DecorateInfo dInfo);
}