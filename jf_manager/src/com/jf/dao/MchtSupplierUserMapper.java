package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSupplierUser;
import com.jf.entity.MchtSupplierUserExample;
@Repository
public interface MchtSupplierUserMapper extends BaseDao<MchtSupplierUser, MchtSupplierUserExample>{
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