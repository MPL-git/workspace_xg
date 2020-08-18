package com.jf.dao;

import com.jf.entity.MchtCloseApply;
import com.jf.entity.MchtCloseApplyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtCloseApplyMapper extends BaseDao<MchtCloseApply, MchtCloseApplyExample> {
    int countByExample(MchtCloseApplyExample example);

    int deleteByExample(MchtCloseApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtCloseApply record);

    int insertSelective(MchtCloseApply record);

    List<MchtCloseApply> selectByExample(MchtCloseApplyExample example);

    MchtCloseApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtCloseApply record, @Param("example") MchtCloseApplyExample example);

    int updateByExample(@Param("record") MchtCloseApply record, @Param("example") MchtCloseApplyExample example);

    int updateByPrimaryKeySelective(MchtCloseApply record);

    int updateByPrimaryKey(MchtCloseApply record);
}
