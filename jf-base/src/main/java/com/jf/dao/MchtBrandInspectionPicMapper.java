package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBrandInspectionPic;
import com.jf.entity.MchtBrandInspectionPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandInspectionPicMapper extends BaseDao<MchtBrandInspectionPic, MchtBrandInspectionPicExample> {
    int countByExample(MchtBrandInspectionPicExample example);

    int deleteByExample(MchtBrandInspectionPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandInspectionPic record);

    int insertSelective(MchtBrandInspectionPic record);

    List<MchtBrandInspectionPic> selectByExample(MchtBrandInspectionPicExample example);

    MchtBrandInspectionPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandInspectionPic record, @Param("example") MchtBrandInspectionPicExample example);

    int updateByExample(@Param("record") MchtBrandInspectionPic record, @Param("example") MchtBrandInspectionPicExample example);

    int updateByPrimaryKeySelective(MchtBrandInspectionPic record);

    int updateByPrimaryKey(MchtBrandInspectionPic record);
}
