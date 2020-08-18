package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CashTransfer;
import com.jf.entity.CashTransferExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashTransferMapper extends BaseDao<CashTransfer, CashTransferExample> {
    int countByExample(CashTransferExample example);

    int deleteByExample(CashTransferExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CashTransfer record);

    int insertSelective(CashTransfer record);

    List<CashTransfer> selectByExample(CashTransferExample example);

    CashTransfer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CashTransfer record, @Param("example") CashTransferExample example);

    int updateByExample(@Param("record") CashTransfer record, @Param("example") CashTransferExample example);

    int updateByPrimaryKeySelective(CashTransfer record);

    int updateByPrimaryKey(CashTransfer record);
}
