package com.jf.dao;

import com.jf.entity.MchtBrandInspectionPicChg;
import com.jf.entity.MchtBrandInspectionPicChgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtBrandInspectionPicChgMapper extends BaseDao<MchtBrandInspectionPicChg, MchtBrandInspectionPicChgExample>{
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