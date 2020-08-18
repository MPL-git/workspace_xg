package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CloudProductAfterTempletCustom;
import com.jf.entity.CloudProductAfterTempletCustomExample;
@Repository
public interface CloudProductAfterTempletCustomMapper{
    int countByExample(CloudProductAfterTempletCustomExample example);
    List<CloudProductAfterTempletCustom> selectByExample(CloudProductAfterTempletCustomExample example);
    CloudProductAfterTempletCustom selectByPrimaryKey(Integer id);
}