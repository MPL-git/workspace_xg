package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SpreadActivityGroupSetDtl;
import com.jf.entity.SpreadActivityGroupSetDtlCustom;
import com.jf.entity.SpreadActivityGroupSetDtlCustomExample;

@Repository
public interface SpreadActivityGroupSetDtlCustomMapper {
    
	public int countByCustomExample(SpreadActivityGroupSetDtlCustomExample example);

    public List<SpreadActivityGroupSetDtlCustom> selectByCustomExample(SpreadActivityGroupSetDtlCustomExample example);

    public SpreadActivityGroupSetDtlCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") SpreadActivityGroupSetDtl record, @Param("example") SpreadActivityGroupSetDtlCustomExample example);

}