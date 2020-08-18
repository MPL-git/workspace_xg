package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoChgExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInfoChgMapper extends BaseDao<MchtInfoChg, MchtInfoChgExample> {
    int countByExample(MchtInfoChgExample example);

    int deleteByExample(MchtInfoChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtInfoChg record);

    int insertSelective(MchtInfoChg record);

    List<MchtInfoChg> selectByExample(MchtInfoChgExample example);

    MchtInfoChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtInfoChg record, @Param("example") MchtInfoChgExample example);

    int updateByExample(@Param("record") MchtInfoChg record, @Param("example") MchtInfoChgExample example);

    int updateByPrimaryKeySelective(MchtInfoChg record);

    int updateByPrimaryKey(MchtInfoChg record);
}
