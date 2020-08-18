package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.BgModuleCategory;
import com.jf.entity.BgModuleCategoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BgModuleCategoryMapper extends BaseDao<BgModuleCategory, BgModuleCategoryExample> {
    int countByExample(BgModuleCategoryExample example);

    int deleteByExample(BgModuleCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BgModuleCategory record);

    int insertSelective(BgModuleCategory record);

    List<BgModuleCategory> selectByExample(BgModuleCategoryExample example);

    BgModuleCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BgModuleCategory record, @Param("example") BgModuleCategoryExample example);

    int updateByExample(@Param("record") BgModuleCategory record, @Param("example") BgModuleCategoryExample example);

    int updateByPrimaryKeySelective(BgModuleCategory record);

    int updateByPrimaryKey(BgModuleCategory record);
}
