package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.BankBranch;
import com.jf.entity.BankBranchExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBranchMapper extends BaseDao<BankBranch, BankBranchExample> {
    int countByExample(BankBranchExample example);

    int deleteByExample(BankBranchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BankBranch record);

    int insertSelective(BankBranch record);

    List<BankBranch> selectByExample(BankBranchExample example);

    BankBranch selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BankBranch record, @Param("example") BankBranchExample example);

    int updateByExample(@Param("record") BankBranch record, @Param("example") BankBranchExample example);

    int updateByPrimaryKeySelective(BankBranch record);

    int updateByPrimaryKey(BankBranch record);
}
