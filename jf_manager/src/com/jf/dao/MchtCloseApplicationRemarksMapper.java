package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtCloseApplicationRemarks;
import com.jf.entity.MchtCloseApplicationRemarksExample;
@Repository
public interface MchtCloseApplicationRemarksMapper extends BaseDao<MchtCloseApplicationRemarks, MchtCloseApplicationRemarksExample>{
    int countByExample(MchtCloseApplicationRemarksExample example);

    int deleteByExample(MchtCloseApplicationRemarksExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtCloseApplicationRemarks record);

    int insertSelective(MchtCloseApplicationRemarks record);

    List<MchtCloseApplicationRemarks> selectByExample(MchtCloseApplicationRemarksExample example);

    MchtCloseApplicationRemarks selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtCloseApplicationRemarks record, @Param("example") MchtCloseApplicationRemarksExample example);

    int updateByExample(@Param("record") MchtCloseApplicationRemarks record, @Param("example") MchtCloseApplicationRemarksExample example);

    int updateByPrimaryKeySelective(MchtCloseApplicationRemarks record);

    int updateByPrimaryKey(MchtCloseApplicationRemarks record);
}