package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SubOrderExtend;
import com.jf.entity.SubOrderExtendExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubOrderExtendMapper extends BaseDao<SubOrderExtend,SubOrderExtendExample> {
    int countByExample(SubOrderExtendExample example);

    int deleteByExample(SubOrderExtendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SubOrderExtend record);

    int insertSelective(SubOrderExtend record);

    List<SubOrderExtend> selectByExample(SubOrderExtendExample example);

    SubOrderExtend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SubOrderExtend record, @Param("example") SubOrderExtendExample example);

    int updateByExample(@Param("record") SubOrderExtend record, @Param("example") SubOrderExtendExample example);

    int updateByPrimaryKeySelective(SubOrderExtend record);

    int updateByPrimaryKey(SubOrderExtend record);
}