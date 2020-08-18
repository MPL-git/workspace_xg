package com.jf.dao;

import com.jf.entity.MchtBrandOtherPicChg;
import com.jf.entity.MchtBrandOtherPicChgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtBrandOtherPicChgMapper extends BaseDao<MchtBrandOtherPicChg, MchtBrandOtherPicChgExample>{
    int countByExample(MchtBrandOtherPicChgExample example);

    int deleteByExample(MchtBrandOtherPicChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandOtherPicChg record);

    int insertSelective(MchtBrandOtherPicChg record);

    List<MchtBrandOtherPicChg> selectByExample(MchtBrandOtherPicChgExample example);

    MchtBrandOtherPicChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandOtherPicChg record, @Param("example") MchtBrandOtherPicChgExample example);

    int updateByExample(@Param("record") MchtBrandOtherPicChg record, @Param("example") MchtBrandOtherPicChgExample example);

    int updateByPrimaryKeySelective(MchtBrandOtherPicChg record);

    int updateByPrimaryKey(MchtBrandOtherPicChg record);
}