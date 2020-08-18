package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ShopStatusLog;
import com.jf.entity.ShopStatusLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopStatusLogMapper extends BaseDao<ShopStatusLog, ShopStatusLogExample> {
    int countByExample(ShopStatusLogExample example);

    int deleteByExample(ShopStatusLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopStatusLog record);

    int insertSelective(ShopStatusLog record);

    List<ShopStatusLog> selectByExample(ShopStatusLogExample example);

    ShopStatusLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopStatusLog record, @Param("example") ShopStatusLogExample example);

    int updateByExample(@Param("record") ShopStatusLog record, @Param("example") ShopStatusLogExample example);

    int updateByPrimaryKeySelective(ShopStatusLog record);

    int updateByPrimaryKey(ShopStatusLog record);
}
