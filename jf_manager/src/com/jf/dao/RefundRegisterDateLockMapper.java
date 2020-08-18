package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.RefundRegisterDateLock;
import com.jf.entity.RefundRegisterDateLockExample;
@Repository
public interface RefundRegisterDateLockMapper extends BaseDao<RefundRegisterDateLock, RefundRegisterDateLockExample>{
    int countByExample(RefundRegisterDateLockExample example);

    int deleteByExample(RefundRegisterDateLockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RefundRegisterDateLock record);

    int insertSelective(RefundRegisterDateLock record);

    List<RefundRegisterDateLock> selectByExample(RefundRegisterDateLockExample example);

    RefundRegisterDateLock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RefundRegisterDateLock record, @Param("example") RefundRegisterDateLockExample example);

    int updateByExample(@Param("record") RefundRegisterDateLock record, @Param("example") RefundRegisterDateLockExample example);

    int updateByPrimaryKeySelective(RefundRegisterDateLock record);

    int updateByPrimaryKey(RefundRegisterDateLock record);
}