package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFootprintCustom;
import com.jf.entity.MemberFootprintExample;
@Repository
public interface MemberFootprintCustomMapper{
    int countByExample(MemberFootprintExample example);
    List<MemberFootprintCustom> selectByExample(MemberFootprintExample example);
    MemberFootprintCustom selectByPrimaryKey(Integer id);
}