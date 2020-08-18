package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.InterventionProcessPic;
import com.jf.entity.InterventionProcessPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionProcessPicMapper extends BaseDao<InterventionProcessPic, InterventionProcessPicExample> {
    int countByExample(InterventionProcessPicExample example);

    int deleteByExample(InterventionProcessPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InterventionProcessPic record);

    int insertSelective(InterventionProcessPic record);

    List<InterventionProcessPic> selectByExample(InterventionProcessPicExample example);

    InterventionProcessPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InterventionProcessPic record, @Param("example") InterventionProcessPicExample example);

    int updateByExample(@Param("record") InterventionProcessPic record, @Param("example") InterventionProcessPicExample example);

    int updateByPrimaryKeySelective(InterventionProcessPic record);

    int updateByPrimaryKey(InterventionProcessPic record);
}
