package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CooperationChangeApply;
import com.jf.entity.CooperationChangeApplyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CooperationChangeApplyMapper extends BaseDao<CooperationChangeApply, CooperationChangeApplyExample> {
    int countByExample(CooperationChangeApplyExample example);

    int deleteByExample(CooperationChangeApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CooperationChangeApply record);

    int insertSelective(CooperationChangeApply record);

    List<CooperationChangeApply> selectByExample(CooperationChangeApplyExample example);

    CooperationChangeApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CooperationChangeApply record, @Param("example") CooperationChangeApplyExample example);

    int updateByExample(@Param("record") CooperationChangeApply record, @Param("example") CooperationChangeApplyExample example);

    int updateByPrimaryKeySelective(CooperationChangeApply record);

    int updateByPrimaryKey(CooperationChangeApply record);
}
