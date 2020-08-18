package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealOrderMapper extends BaseDao<AppealOrder, AppealOrderExample> {
    int countByExample(AppealOrderExample example);

    int deleteByExample(AppealOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppealOrder record);

    int insertSelective(AppealOrder record);

    List<AppealOrder> selectByExample(AppealOrderExample example);

    AppealOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppealOrder record, @Param("example") AppealOrderExample example);

    int updateByExample(@Param("record") AppealOrder record, @Param("example") AppealOrderExample example);

    int updateByPrimaryKeySelective(AppealOrder record);

    int updateByPrimaryKey(AppealOrder record);
}
