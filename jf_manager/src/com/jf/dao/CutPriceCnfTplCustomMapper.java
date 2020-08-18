package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CutPriceCnfTpl;
import com.jf.entity.CutPriceCnfTplCustom;
import com.jf.entity.CutPriceCnfTplCustomExample;

@Repository
public interface CutPriceCnfTplCustomMapper {
    
	public int countByCustomExample(CutPriceCnfTplCustomExample example);

    public List<CutPriceCnfTplCustom> selectByCustomExample(CutPriceCnfTplCustomExample example);

    public CutPriceCnfTpl selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") CutPriceCnfTpl record, @Param("example") CutPriceCnfTplCustomExample example);

}