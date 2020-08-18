package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ToutiaoAdInfo;
import com.jf.entity.ToutiaoAdInfoExample;
import com.jf.entity.ToutiaoAdInfoWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoAdInfoMapper extends BaseDao<ToutiaoAdInfo, ToutiaoAdInfoExample> {
    int countByExample(ToutiaoAdInfoExample example);

    int deleteByExample(ToutiaoAdInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToutiaoAdInfoWithBLOBs record);

    int insertSelective(ToutiaoAdInfoWithBLOBs record);

    List<ToutiaoAdInfoWithBLOBs> selectByExampleWithBLOBs(ToutiaoAdInfoExample example);

    List<ToutiaoAdInfo> selectByExample(ToutiaoAdInfoExample example);

    ToutiaoAdInfoWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToutiaoAdInfoWithBLOBs record, @Param("example") ToutiaoAdInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ToutiaoAdInfoWithBLOBs record, @Param("example") ToutiaoAdInfoExample example);

    int updateByExample(@Param("record") ToutiaoAdInfo record, @Param("example") ToutiaoAdInfoExample example);

    int updateByPrimaryKeySelective(ToutiaoAdInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ToutiaoAdInfoWithBLOBs record);

    int updateByPrimaryKey(ToutiaoAdInfo record);
}
