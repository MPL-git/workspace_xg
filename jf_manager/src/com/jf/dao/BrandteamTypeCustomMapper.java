package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.BrandteamTypeCustom;
import com.jf.entity.BrandteamTypeCustomExample;

@Repository
public interface BrandteamTypeCustomMapper {
    int brandteamTypeCountByExample(BrandteamTypeCustomExample example);

    List<BrandteamTypeCustom> brandteamTypeCustomselectByExample(BrandteamTypeCustomExample example);

    BrandteamTypeCustom brandteamTypeCustomselectByPrimaryKey(Integer id);
}