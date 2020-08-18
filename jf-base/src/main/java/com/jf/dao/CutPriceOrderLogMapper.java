package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CutPriceOrderLog;
import com.jf.entity.CutPriceOrderLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceOrderLogMapper extends BaseDao<CutPriceOrderLog, CutPriceOrderLogExample> {
    int countByExample(CutPriceOrderLogExample example);

    int deleteByExample(CutPriceOrderLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutPriceOrderLog record);

    int insertSelective(CutPriceOrderLog record);

    List<CutPriceOrderLog> selectByExample(CutPriceOrderLogExample example);

    CutPriceOrderLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutPriceOrderLog record, @Param("example") CutPriceOrderLogExample example);

    int updateByExample(@Param("record") CutPriceOrderLog record, @Param("example") CutPriceOrderLogExample example);

    int updateByPrimaryKeySelective(CutPriceOrderLog record);

    int updateByPrimaryKey(CutPriceOrderLog record);
}
