package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProvinceFreight;
import com.jf.entity.ProvinceFreightExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceFreightMapper extends BaseDao<ProvinceFreight, ProvinceFreightExample> {
    int countByExample(ProvinceFreightExample example);

    int deleteByExample(ProvinceFreightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProvinceFreight record);

    int insertSelective(ProvinceFreight record);

    List<ProvinceFreight> selectByExample(ProvinceFreightExample example);

    ProvinceFreight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProvinceFreight record, @Param("example") ProvinceFreightExample example);

    int updateByExample(@Param("record") ProvinceFreight record, @Param("example") ProvinceFreightExample example);

    int updateByPrimaryKeySelective(ProvinceFreight record);

    int updateByPrimaryKey(ProvinceFreight record);
}
