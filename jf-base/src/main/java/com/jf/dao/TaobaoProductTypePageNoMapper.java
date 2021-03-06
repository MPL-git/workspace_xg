package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.TaobaoProductTypePageNo;
import com.jf.entity.TaobaoProductTypePageNoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaobaoProductTypePageNoMapper extends BaseDao<TaobaoProductTypePageNo, TaobaoProductTypePageNoExample> {
    int countByExample(TaobaoProductTypePageNoExample example);

    int deleteByExample(TaobaoProductTypePageNoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaobaoProductTypePageNo record);

    int insertSelective(TaobaoProductTypePageNo record);

    List<TaobaoProductTypePageNo> selectByExample(TaobaoProductTypePageNoExample example);

    TaobaoProductTypePageNo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaobaoProductTypePageNo record, @Param("example") TaobaoProductTypePageNoExample example);

    int updateByExample(@Param("record") TaobaoProductTypePageNo record, @Param("example") TaobaoProductTypePageNoExample example);

    int updateByPrimaryKeySelective(TaobaoProductTypePageNo record);

    int updateByPrimaryKey(TaobaoProductTypePageNo record);
}
