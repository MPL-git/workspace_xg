package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserCustom;
import com.jf.entity.MchtUserExample;
@Repository
public interface MchtUserCustomMapper{
    int countByExample(MchtUserExample example);
    List<MchtUserCustom> selectByExample(MchtUserExample example);
    MchtUser selectByPrimaryKey(Integer id);
    
    /**
     * 根据商家id查询商家主账号手机号码
     * 
     * @param mchtId
     * @return
     */
    String selectMobileByMchtId(Integer mchtId);

    /**
     * 根据商家id查询商家对接人手机号码
     * 
     * @param mchtId
     * @return
     */
    String selectMchtContactMobileByMchtId(Integer mchtId,Integer type);
}