package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.BusinessCirclesAppealOrderCustom;
import com.jf.entity.BusinessCirclesAppealOrderCustomExample;

@Repository
public interface BusinessCirclesAppealOrderCustomMapper {
    int countByCustomExample(BusinessCirclesAppealOrderCustomExample example);

    List<BusinessCirclesAppealOrderCustom> selectByCustomExample(BusinessCirclesAppealOrderCustomExample example);

    BusinessCirclesAppealOrderCustom selectByPrimaryKeyCustom(Integer id);
    
    public List<Map<String, Object>> getstaffidList();
    
    public List<Map<String, Object>> getcreatebyList();
    
    public int customerServiceOrdercount(String id);
    
    public int interventionOrdercount(String id);
    
    public int appealOrdercount(String id);
    
    public int subOrderCustomscuont(String id);
    
    public List<Map<String, Object>> getproStatus(String id);
    
    public List<Map<String, Object>> getStatus(String id);
    
    public List<Map<String, Object>> getappealOrderstatus(String id);
    
}