package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AssistanceDtl;
import com.jf.entity.AssistanceDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistanceDtlMapper extends BaseDao<AssistanceDtl, AssistanceDtlExample> {
    int countByExample(AssistanceDtlExample example);

    int deleteByExample(AssistanceDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssistanceDtl record);

    int insertSelective(AssistanceDtl record);

    List<AssistanceDtl> selectByExample(AssistanceDtlExample example);

    AssistanceDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssistanceDtl record, @Param("example") AssistanceDtlExample example);

    int updateByExample(@Param("record") AssistanceDtl record, @Param("example") AssistanceDtlExample example);

    int updateByPrimaryKeySelective(AssistanceDtl record);

    int updateByPrimaryKey(AssistanceDtl record);
}
