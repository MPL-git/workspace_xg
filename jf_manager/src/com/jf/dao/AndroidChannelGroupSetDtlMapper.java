package com.jf.dao;

import com.jf.entity.AndroidChannelGroupSetDtl;
import com.jf.entity.AndroidChannelGroupSetDtlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AndroidChannelGroupSetDtlMapper extends BaseDao<AndroidChannelGroupSetDtl, AndroidChannelGroupSetDtlExample> {
    int countByExample(AndroidChannelGroupSetDtlExample example);

    int deleteByExample(AndroidChannelGroupSetDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AndroidChannelGroupSetDtl record);

    int insertSelective(AndroidChannelGroupSetDtl record);

    List<AndroidChannelGroupSetDtl> selectByExample(AndroidChannelGroupSetDtlExample example);

    AndroidChannelGroupSetDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AndroidChannelGroupSetDtl record, @Param("example") AndroidChannelGroupSetDtlExample example);

    int updateByExample(@Param("record") AndroidChannelGroupSetDtl record, @Param("example") AndroidChannelGroupSetDtlExample example);

    int updateByPrimaryKeySelective(AndroidChannelGroupSetDtl record);

    int updateByPrimaryKey(AndroidChannelGroupSetDtl record);
}