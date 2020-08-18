package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBrandInspectionPicChg;
import com.jf.entity.MchtBrandInspectionPicChgExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandInspectionPicChgMapper extends BaseDao<MchtBrandInspectionPicChg, MchtBrandInspectionPicChgExample> {
    int countByExample(MchtBrandInspectionPicChgExample example);

    int deleteByExample(MchtBrandInspectionPicChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandInspectionPicChg record);

    int insertSelective(MchtBrandInspectionPicChg record);

    List<MchtBrandInspectionPicChg> selectByExample(MchtBrandInspectionPicChgExample example);

    MchtBrandInspectionPicChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandInspectionPicChg record, @Param("example") MchtBrandInspectionPicChgExample example);

    int updateByExample(@Param("record") MchtBrandInspectionPicChg record, @Param("example") MchtBrandInspectionPicChgExample example);

    int updateByPrimaryKeySelective(MchtBrandInspectionPicChg record);

    int updateByPrimaryKey(MchtBrandInspectionPicChg record);
}
