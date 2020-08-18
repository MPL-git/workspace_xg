package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberAddressExample;
@Repository
public interface MemberAddressCustomMapper{
    int countByExample(MemberAddressExample example);
    List<MemberAddressCustom> selectByExample(MemberAddressExample example);
    MemberAddressCustom selectByPrimaryKey(Integer id);
}