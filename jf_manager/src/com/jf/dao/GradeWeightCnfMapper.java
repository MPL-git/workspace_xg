package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.GradeWeightCnf;
import com.jf.entity.GradeWeightCnfExample;
@Repository
public interface GradeWeightCnfMapper extends BaseDao<GradeWeightCnf, GradeWeightCnfExample>{
    int countByExample(GradeWeightCnfExample example);

    int deleteByExample(GradeWeightCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GradeWeightCnf record);

    int insertSelective(GradeWeightCnf record);

    List<GradeWeightCnf> selectByExample(GradeWeightCnfExample example);

    GradeWeightCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GradeWeightCnf record, @Param("example") GradeWeightCnfExample example);

    int updateByExample(@Param("record") GradeWeightCnf record, @Param("example") GradeWeightCnfExample example);

    int updateByPrimaryKeySelective(GradeWeightCnf record);

    int updateByPrimaryKey(GradeWeightCnf record);
}