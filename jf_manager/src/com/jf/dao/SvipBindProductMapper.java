package com.jf.dao;

import com.jf.entity.SvipBindProduct;
import com.jf.entity.SvipBindProductExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvipBindProductMapper extends BaseDao<SvipBindProduct,SvipBindProductExample>{
    int countByExample(SvipBindProductExample example);

    int deleteByExample(SvipBindProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipBindProduct record);

    int insertSelective(SvipBindProduct record);

    List<SvipBindProduct> selectByExample(SvipBindProductExample example);

    SvipBindProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipBindProduct record, @Param("example") SvipBindProductExample example);

    int updateByExample(@Param("record") SvipBindProduct record, @Param("example") SvipBindProductExample example);

    int updateByPrimaryKeySelective(SvipBindProduct record);

    int updateByPrimaryKey(SvipBindProduct record);
}