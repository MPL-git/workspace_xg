package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.BrandTmkPic;
import com.jf.entity.BrandTmkPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTmkPicMapper extends BaseDao<BrandTmkPic, BrandTmkPicExample> {
    int countByExample(BrandTmkPicExample example);

    int deleteByExample(BrandTmkPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrandTmkPic record);

    int insertSelective(BrandTmkPic record);

    List<BrandTmkPic> selectByExample(BrandTmkPicExample example);

    BrandTmkPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrandTmkPic record, @Param("example") BrandTmkPicExample example);

    int updateByExample(@Param("record") BrandTmkPic record, @Param("example") BrandTmkPicExample example);

    int updateByPrimaryKeySelective(BrandTmkPic record);

    int updateByPrimaryKey(BrandTmkPic record);
}
