package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.WoRkCustom;
import com.jf.entity.WoRkCustomExample;

@Repository
public interface WoRkCustomMapper {
    int countByCustomExample(WoRkCustomExample example);

    List<WoRkCustom> selectByCustomExample(WoRkCustomExample example);

    WoRkCustom selectByPrimaryKeyCustom(Integer staffRoleId);
    
    public List<Map<String, Object>> getstaffidList();
    
    public List<Map<String, Object>> getstaffidListc();

}