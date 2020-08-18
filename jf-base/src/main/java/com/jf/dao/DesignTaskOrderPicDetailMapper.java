package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DesignTaskOrderPicDetail;
import com.jf.entity.DesignTaskOrderPicDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignTaskOrderPicDetailMapper extends BaseDao<DesignTaskOrderPicDetail, DesignTaskOrderPicDetailExample> {
    int countByExample(DesignTaskOrderPicDetailExample example);

    int deleteByExample(DesignTaskOrderPicDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DesignTaskOrderPicDetail record);

    int insertSelective(DesignTaskOrderPicDetail record);

    List<DesignTaskOrderPicDetail> selectByExample(DesignTaskOrderPicDetailExample example);

    DesignTaskOrderPicDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DesignTaskOrderPicDetail record, @Param("example") DesignTaskOrderPicDetailExample example);

    int updateByExample(@Param("record") DesignTaskOrderPicDetail record, @Param("example") DesignTaskOrderPicDetailExample example);

    int updateByPrimaryKeySelective(DesignTaskOrderPicDetail record);

    int updateByPrimaryKey(DesignTaskOrderPicDetail record);
}