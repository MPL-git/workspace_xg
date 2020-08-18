package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.CouponAddTaskConfig;
import com.jf.entity.CouponAddTaskConfigExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponAddTaskConfigMapper extends BaseDao<CouponAddTaskConfig, CouponAddTaskConfigExample> {
    int countByExample(CouponAddTaskConfigExample example);

    int deleteByExample(CouponAddTaskConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponAddTaskConfig record);

    int insertSelective(CouponAddTaskConfig record);

    List<CouponAddTaskConfig> selectByExample(CouponAddTaskConfigExample example);

    CouponAddTaskConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponAddTaskConfig record, @Param("example") CouponAddTaskConfigExample example);

    int updateByExample(@Param("record") CouponAddTaskConfig record, @Param("example") CouponAddTaskConfigExample example);

    int updateByPrimaryKeySelective(CouponAddTaskConfig record);

    int updateByPrimaryKey(CouponAddTaskConfig record);
}
