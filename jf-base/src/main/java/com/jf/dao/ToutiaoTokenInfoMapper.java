package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ToutiaoTokenInfo;
import com.jf.entity.ToutiaoTokenInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoTokenInfoMapper extends BaseDao<ToutiaoTokenInfo, ToutiaoTokenInfoExample> {
    int countByExample(ToutiaoTokenInfoExample example);

    int deleteByExample(ToutiaoTokenInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToutiaoTokenInfo record);

    int insertSelective(ToutiaoTokenInfo record);

    List<ToutiaoTokenInfo> selectByExample(ToutiaoTokenInfoExample example);

    ToutiaoTokenInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToutiaoTokenInfo record, @Param("example") ToutiaoTokenInfoExample example);

    int updateByExample(@Param("record") ToutiaoTokenInfo record, @Param("example") ToutiaoTokenInfoExample example);

    int updateByPrimaryKeySelective(ToutiaoTokenInfo record);

    int updateByPrimaryKey(ToutiaoTokenInfo record);
}
