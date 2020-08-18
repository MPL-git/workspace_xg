package com.jf.dao;

import com.jf.entity.HotSearchWord;
import com.jf.entity.HotSearchWordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotSearchWordMapper extends BaseDao<HotSearchWord, HotSearchWordExample>{
    int countByExample(HotSearchWordExample example);

    int deleteByExample(HotSearchWordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HotSearchWord record);

    int insertSelective(HotSearchWord record);

    List<HotSearchWord> selectByExample(HotSearchWordExample example);

    HotSearchWord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HotSearchWord record, @Param("example") HotSearchWordExample example);

    int updateByExample(@Param("record") HotSearchWord record, @Param("example") HotSearchWordExample example);

    int updateByPrimaryKeySelective(HotSearchWord record);

    int updateByPrimaryKey(HotSearchWord record);
}