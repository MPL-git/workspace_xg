package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityProductMapper extends BaseDao<ActivityProduct, ActivityProductExample> {
    int countByExample(ActivityProductExample example);

    int deleteByExample(ActivityProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityProduct record);

    int insertSelective(ActivityProduct record);

    List<ActivityProduct> selectByExample(ActivityProductExample example);

    ActivityProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityProduct record, @Param("example") ActivityProductExample example);

    int updateByExample(@Param("record") ActivityProduct record, @Param("example") ActivityProductExample example);

    int updateByPrimaryKeySelective(ActivityProduct record);

    int updateByPrimaryKey(ActivityProduct record);
}
