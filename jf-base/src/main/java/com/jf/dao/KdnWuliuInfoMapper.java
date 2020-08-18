package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KdnWuliuInfoMapper extends BaseDao<KdnWuliuInfo, KdnWuliuInfoExample> {
    int countByExample(KdnWuliuInfoExample example);

    int deleteByExample(KdnWuliuInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KdnWuliuInfo record);

    int insertSelective(KdnWuliuInfo record);

    List<KdnWuliuInfo> selectByExample(KdnWuliuInfoExample example);

    KdnWuliuInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KdnWuliuInfo record, @Param("example") KdnWuliuInfoExample example);

    int updateByExample(@Param("record") KdnWuliuInfo record, @Param("example") KdnWuliuInfoExample example);

    int updateByPrimaryKeySelective(KdnWuliuInfo record);

    int updateByPrimaryKey(KdnWuliuInfo record);
}
