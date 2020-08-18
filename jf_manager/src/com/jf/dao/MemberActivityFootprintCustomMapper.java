package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberActivityFootprintCustom;
import com.jf.entity.MemberActivityFootprintExample;
import com.jf.entity.MemberFootprintCustom;
import com.jf.entity.MemberFootprintExample;
import com.jf.entity.MemberShopFootprintCustom;
import com.jf.entity.MemberShopFootprintExample;
@Repository
public interface MemberActivityFootprintCustomMapper{
    int countByExample(MemberActivityFootprintExample example);
    List<MemberActivityFootprintCustom> selectByExample(MemberActivityFootprintExample example);
    MemberActivityFootprintCustom selectByPrimaryKey(Integer id);
}