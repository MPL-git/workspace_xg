package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DouyinSprDtl;
import com.jf.entity.DouyinSprDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DouyinSprDtlMapper extends BaseDao<DouyinSprDtl, DouyinSprDtlExample> {
    int countByExample(DouyinSprDtlExample example);

    int deleteByExample(DouyinSprDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DouyinSprDtl record);

    int insertSelective(DouyinSprDtl record);

    List<DouyinSprDtl> selectByExample(DouyinSprDtlExample example);

    DouyinSprDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DouyinSprDtl record, @Param("example") DouyinSprDtlExample example);

    int updateByExample(@Param("record") DouyinSprDtl record, @Param("example") DouyinSprDtlExample example);

    int updateByPrimaryKeySelective(DouyinSprDtl record);

    int updateByPrimaryKey(DouyinSprDtl record);
}
