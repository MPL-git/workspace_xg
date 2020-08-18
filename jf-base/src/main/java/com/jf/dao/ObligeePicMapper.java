package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ObligeePic;
import com.jf.entity.ObligeePicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObligeePicMapper extends BaseDao<ObligeePic, ObligeePicExample> {
    int countByExample(ObligeePicExample example);

    int deleteByExample(ObligeePicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ObligeePic record);

    int insertSelective(ObligeePic record);

    List<ObligeePic> selectByExample(ObligeePicExample example);

    ObligeePic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ObligeePic record, @Param("example") ObligeePicExample example);

    int updateByExample(@Param("record") ObligeePic record, @Param("example") ObligeePicExample example);

    int updateByPrimaryKeySelective(ObligeePic record);

    int updateByPrimaryKey(ObligeePic record);
}
