package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutPriceOrderMapper extends BaseDao<CutPriceOrder, CutPriceOrderExample> {
    int countByExample(CutPriceOrderExample example);

    int deleteByExample(CutPriceOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CutPriceOrder record);

    int insertSelective(CutPriceOrder record);

    List<CutPriceOrder> selectByExample(CutPriceOrderExample example);

    CutPriceOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CutPriceOrder record, @Param("example") CutPriceOrderExample example);

    int updateByExample(@Param("record") CutPriceOrder record, @Param("example") CutPriceOrderExample example);

    int updateByPrimaryKeySelective(CutPriceOrder record);

    int updateByPrimaryKey(CutPriceOrder record);
}
