package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.DesignTaskOrderPic;
import com.jf.entity.DesignTaskOrderPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignTaskOrderPicMapper extends BaseDao<DesignTaskOrderPic, DesignTaskOrderPicExample> {
    int countByExample(DesignTaskOrderPicExample example);

    int deleteByExample(DesignTaskOrderPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DesignTaskOrderPic record);

    int insertSelective(DesignTaskOrderPic record);

    List<DesignTaskOrderPic> selectByExample(DesignTaskOrderPicExample example);

    DesignTaskOrderPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DesignTaskOrderPic record, @Param("example") DesignTaskOrderPicExample example);

    int updateByExample(@Param("record") DesignTaskOrderPic record, @Param("example") DesignTaskOrderPicExample example);

    int updateByPrimaryKeySelective(DesignTaskOrderPic record);

    int updateByPrimaryKey(DesignTaskOrderPic record);
}