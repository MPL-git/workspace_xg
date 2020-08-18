package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ViolatePlatformRemarksPic;
import com.jf.entity.ViolatePlatformRemarksPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolatePlatformRemarksPicMapper extends BaseDao<ViolatePlatformRemarksPic, ViolatePlatformRemarksPicExample> {
    int countByExample(ViolatePlatformRemarksPicExample example);

    int deleteByExample(ViolatePlatformRemarksPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ViolatePlatformRemarksPic record);

    int insertSelective(ViolatePlatformRemarksPic record);

    List<ViolatePlatformRemarksPic> selectByExample(ViolatePlatformRemarksPicExample example);

    ViolatePlatformRemarksPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ViolatePlatformRemarksPic record, @Param("example") ViolatePlatformRemarksPicExample example);

    int updateByExample(@Param("record") ViolatePlatformRemarksPic record, @Param("example") ViolatePlatformRemarksPicExample example);

    int updateByPrimaryKeySelective(ViolatePlatformRemarksPic record);

    int updateByPrimaryKey(ViolatePlatformRemarksPic record);
}
