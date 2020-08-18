package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.TmpSubOrder;
import com.jf.entity.TmpSubOrderExample;
@Repository
public interface TmpSubOrderMapper extends BaseDao<TmpSubOrder, TmpSubOrderExample>{
    int countByExample(TmpSubOrderExample example);

    int deleteByExample(TmpSubOrderExample example);

    int insert(TmpSubOrder record);

    int insertSelective(TmpSubOrder record);

    List<TmpSubOrder> selectByExample(TmpSubOrderExample example);

    int updateByExampleSelective(@Param("record") TmpSubOrder record, @Param("example") TmpSubOrderExample example);

    int updateByExample(@Param("record") TmpSubOrder record, @Param("example") TmpSubOrderExample example);

	void updateBySubOrderId(TmpSubOrder ts);
}