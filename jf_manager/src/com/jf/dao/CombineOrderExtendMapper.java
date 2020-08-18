package com.jf.dao;

import com.jf.entity.CombineOrderExtend;
import com.jf.entity.CombineOrderExtendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CombineOrderExtendMapper extends BaseDao<CombineOrderExtend,CombineOrderExtendExample>{
    int countByExample(CombineOrderExtendExample example);

    int deleteByExample(CombineOrderExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CombineOrderExtend record);

    int insertSelective(CombineOrderExtend record);

    List<CombineOrderExtend> selectByExample(CombineOrderExtendExample example);

    CombineOrderExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CombineOrderExtend record, @Param("example") CombineOrderExtendExample example);

    int updateByExample(@Param("record") CombineOrderExtend record, @Param("example") CombineOrderExtendExample example);

    int updateByPrimaryKeySelective(CombineOrderExtend record);

    int updateByPrimaryKey(CombineOrderExtend record);
}