package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSupplierUserCustom;
import com.jf.entity.MchtSupplierUserExample;
@Repository
public interface MchtSupplierUserCustomMapper{
    int countByExample(MchtSupplierUserExample example);
    List<MchtSupplierUserCustom> selectByExample(MchtSupplierUserExample example);
}