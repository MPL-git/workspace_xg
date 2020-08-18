package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopDecorateInfo;
import com.jf.entity.ShopDecorateInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateInfoMapper extends BaseDao<ShopDecorateInfo, ShopDecorateInfoExample> {
    int countByExample(ShopDecorateInfoExample example);

    int deleteByExample(ShopDecorateInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDecorateInfo record);

    int insertSelective(ShopDecorateInfo record);

    List<ShopDecorateInfo> selectByExample(ShopDecorateInfoExample example);

    ShopDecorateInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDecorateInfo record, @Param("example") ShopDecorateInfoExample example);

    int updateByExample(@Param("record") ShopDecorateInfo record, @Param("example") ShopDecorateInfoExample example);

    int updateByPrimaryKeySelective(ShopDecorateInfo record);

    int updateByPrimaryKey(ShopDecorateInfo record);
}
