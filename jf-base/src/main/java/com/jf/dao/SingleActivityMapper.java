package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SingleActivity;
import com.jf.entity.SingleActivityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleActivityMapper extends BaseDao<SingleActivity, SingleActivityExample> {
    int countByExample(SingleActivityExample example);

    int deleteByExample(SingleActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleActivity record);

    int insertSelective(SingleActivity record);

    List<SingleActivity> selectByExample(SingleActivityExample example);

    SingleActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleActivity record, @Param("example") SingleActivityExample example);

    int updateByExample(@Param("record") SingleActivity record, @Param("example") SingleActivityExample example);

    int updateByPrimaryKeySelective(SingleActivity record);

    int updateByPrimaryKey(SingleActivity record);
}
