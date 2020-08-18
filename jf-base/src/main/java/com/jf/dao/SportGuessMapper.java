package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SportGuess;
import com.jf.entity.SportGuessExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportGuessMapper extends BaseDao<SportGuess, SportGuessExample> {
    int countByExample(SportGuessExample example);

    int deleteByExample(SportGuessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SportGuess record);

    int insertSelective(SportGuess record);

    List<SportGuess> selectByExample(SportGuessExample example);

    SportGuess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SportGuess record, @Param("example") SportGuessExample example);

    int updateByExample(@Param("record") SportGuess record, @Param("example") SportGuessExample example);

    int updateByPrimaryKeySelective(SportGuess record);

    int updateByPrimaryKey(SportGuess record);
}
