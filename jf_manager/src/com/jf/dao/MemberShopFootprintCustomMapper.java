package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFootprintCustom;
import com.jf.entity.MemberFootprintExample;
import com.jf.entity.MemberShopFootprintCustom;
import com.jf.entity.MemberShopFootprintExample;
@Repository
public interface MemberShopFootprintCustomMapper{
    int countByExample(MemberShopFootprintExample example);
    List<MemberShopFootprintCustom> selectByExample(MemberShopFootprintExample example);
    MemberShopFootprintCustom selectByPrimaryKey(Integer id);
}