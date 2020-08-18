package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtContractMapper extends BaseDao<MchtContract, MchtContractExample> {
    int countByExample(MchtContractExample example);

    int deleteByExample(MchtContractExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtContract record);

    int insertSelective(MchtContract record);

    List<MchtContract> selectByExample(MchtContractExample example);

    MchtContract selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtContract record, @Param("example") MchtContractExample example);

    int updateByExample(@Param("record") MchtContract record, @Param("example") MchtContractExample example);

    int updateByPrimaryKeySelective(MchtContract record);

    int updateByPrimaryKey(MchtContract record);
}
