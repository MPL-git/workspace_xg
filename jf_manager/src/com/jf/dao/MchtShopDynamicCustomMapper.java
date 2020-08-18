package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtShopDynamicCustom;
import com.jf.entity.MchtShopDynamicExample;
@Repository
public interface MchtShopDynamicCustomMapper{
    int countByExample(MchtShopDynamicExample example);
    List<MchtShopDynamicCustom> selectByExample(MchtShopDynamicExample example);
}