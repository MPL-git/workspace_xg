package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ViolatePunishStandard;
import com.jf.entity.ViolatePunishStandardExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolatePunishStandardMapper extends BaseDao<ViolatePunishStandard, ViolatePunishStandardExample> {
    int countByExample(ViolatePunishStandardExample example);

    int deleteByExample(ViolatePunishStandardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ViolatePunishStandard record);

    int insertSelective(ViolatePunishStandard record);

    List<ViolatePunishStandard> selectByExample(ViolatePunishStandardExample example);

    ViolatePunishStandard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ViolatePunishStandard record, @Param("example") ViolatePunishStandardExample example);

    int updateByExample(@Param("record") ViolatePunishStandard record, @Param("example") ViolatePunishStandardExample example);

    int updateByPrimaryKeySelective(ViolatePunishStandard record);

    int updateByPrimaryKey(ViolatePunishStandard record);
}
