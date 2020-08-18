package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.FreightTemplateCustom;
import com.jf.entity.FreightTemplateExample;
@Repository
public interface FreightTemplateCustomMapper{
	public List<FreightTemplateCustom>selectByExample(FreightTemplateExample freightTemplateExample);
	
}