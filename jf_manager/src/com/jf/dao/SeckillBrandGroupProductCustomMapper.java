package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductCustom;
import com.jf.entity.SeckillBrandGroupProductCustomExample;
import com.jf.entity.SeckillBrandGroupProductExample;

@Repository
public interface SeckillBrandGroupProductCustomMapper {
    public int countByCustomExample(SeckillBrandGroupProductCustomExample example);

    public List<SeckillBrandGroupProductCustom> selectByCustomExample(SeckillBrandGroupProductCustomExample example);

    public SeckillBrandGroupProductCustom selectByPrimaryCustomKey(Integer id);
    
    public int updateByCustomExampleSelective(@Param("record") SeckillBrandGroupProduct record, @Param("example") SeckillBrandGroupProductCustomExample example);
    
}