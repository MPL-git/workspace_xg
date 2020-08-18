package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.BrandteamTypeadInfoCustom;
import com.jf.entity.BrandteamTypeadInfoCustomExample;

@Repository
public interface BrandteamTypeadInfoCustomMapper {
    int brandteamTypeadInfoCountByExample(BrandteamTypeadInfoCustomExample example);

    List<BrandteamTypeadInfoCustom> brandteamTypeadInfoCustomselectByExample(BrandteamTypeadInfoCustomExample example);

    BrandteamTypeadInfoCustom brandteamTypeadInfoCustomselectByPrimaryKey(Integer id);
}