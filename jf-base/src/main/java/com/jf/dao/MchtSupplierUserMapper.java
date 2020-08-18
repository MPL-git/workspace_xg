package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtSupplierUser;
import com.jf.entity.MchtSupplierUserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSupplierUserMapper extends BaseDao<MchtSupplierUser, MchtSupplierUserExample> {
    int countByExample(MchtSupplierUserExample example);

    int deleteByExample(MchtSupplierUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSupplierUser record);

    int insertSelective(MchtSupplierUser record);

    List<MchtSupplierUser> selectByExample(MchtSupplierUserExample example);

    MchtSupplierUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSupplierUser record, @Param("example") MchtSupplierUserExample example);

    int updateByExample(@Param("record") MchtSupplierUser record, @Param("example") MchtSupplierUserExample example);

    int updateByPrimaryKeySelective(MchtSupplierUser record);

    int updateByPrimaryKey(MchtSupplierUser record);
}
