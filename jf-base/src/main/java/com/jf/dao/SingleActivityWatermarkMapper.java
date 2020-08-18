package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SingleActivityWatermark;
import com.jf.entity.SingleActivityWatermarkExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleActivityWatermarkMapper extends BaseDao<SingleActivityWatermark, SingleActivityWatermarkExample> {
    int countByExample(SingleActivityWatermarkExample example);

    int deleteByExample(SingleActivityWatermarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleActivityWatermark record);

    int insertSelective(SingleActivityWatermark record);

    List<SingleActivityWatermark> selectByExample(SingleActivityWatermarkExample example);

    SingleActivityWatermark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleActivityWatermark record, @Param("example") SingleActivityWatermarkExample example);

    int updateByExample(@Param("record") SingleActivityWatermark record, @Param("example") SingleActivityWatermarkExample example);

    int updateByPrimaryKeySelective(SingleActivityWatermark record);

    int updateByPrimaryKey(SingleActivityWatermark record);
}
