package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtDepositDtlMapper extends BaseDao<MchtDepositDtl, MchtDepositDtlExample> {
    int countByExample(MchtDepositDtlExample example);

    int deleteByExample(MchtDepositDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtDepositDtl record);

    int insertSelective(MchtDepositDtl record);

    List<MchtDepositDtl> selectByExample(MchtDepositDtlExample example);

    MchtDepositDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtDepositDtl record, @Param("example") MchtDepositDtlExample example);

    int updateByExample(@Param("record") MchtDepositDtl record, @Param("example") MchtDepositDtlExample example);

    int updateByPrimaryKeySelective(MchtDepositDtl record);

    int updateByPrimaryKey(MchtDepositDtl record);
}
