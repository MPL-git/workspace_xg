package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleMapMapper extends BaseDao<ModuleMap,ModuleMapExample> {
    int countByExample(ModuleMapExample example);

    int deleteByExample(ModuleMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ModuleMap record);

    int insertSelective(ModuleMap record);

    List<ModuleMap> selectByExample(ModuleMapExample example);

    ModuleMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ModuleMap record, @Param("example") ModuleMapExample example);

    int updateByExample(@Param("record") ModuleMap record, @Param("example") ModuleMapExample example);

    int updateByPrimaryKeySelective(ModuleMap record);

    int updateByPrimaryKey(ModuleMap record);
}