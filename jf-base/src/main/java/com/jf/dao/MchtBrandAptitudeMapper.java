package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtBrandAptitude;
import com.jf.entity.MchtBrandAptitudeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandAptitudeMapper extends BaseDao<MchtBrandAptitude, MchtBrandAptitudeExample> {
    int countByExample(MchtBrandAptitudeExample example);

    int deleteByExample(MchtBrandAptitudeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandAptitude record);

    int insertSelective(MchtBrandAptitude record);

    List<MchtBrandAptitude> selectByExample(MchtBrandAptitudeExample example);

    MchtBrandAptitude selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandAptitude record, @Param("example") MchtBrandAptitudeExample example);

    int updateByExample(@Param("record") MchtBrandAptitude record, @Param("example") MchtBrandAptitudeExample example);

    int updateByPrimaryKeySelective(MchtBrandAptitude record);

    int updateByPrimaryKey(MchtBrandAptitude record);
}
