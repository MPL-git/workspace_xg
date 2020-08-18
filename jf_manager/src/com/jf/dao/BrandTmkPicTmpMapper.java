package com.jf.dao;

import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandTmkPicTmpMapper extends BaseDao<BrandTmkPicTmp, BrandTmkPicTmpExample> {
    int countByExample(BrandTmkPicTmpExample example);

    int deleteByExample(BrandTmkPicTmpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrandTmkPicTmp record);

    int insertSelective(BrandTmkPicTmp record);

    List<BrandTmkPicTmp> selectByExample(BrandTmkPicTmpExample example);

    BrandTmkPicTmp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrandTmkPicTmp record, @Param("example") BrandTmkPicTmpExample example);

    int updateByExample(@Param("record") BrandTmkPicTmp record, @Param("example") BrandTmkPicTmpExample example);

    int updateByPrimaryKeySelective(BrandTmkPicTmp record);

    int updateByPrimaryKey(BrandTmkPicTmp record);
}