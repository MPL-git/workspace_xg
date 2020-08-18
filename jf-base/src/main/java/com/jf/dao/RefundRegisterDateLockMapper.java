package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.RefundRegisterDateLock;
import com.jf.entity.RefundRegisterDateLockExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRegisterDateLockMapper extends BaseDao<RefundRegisterDateLock, RefundRegisterDateLockExample> {
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
