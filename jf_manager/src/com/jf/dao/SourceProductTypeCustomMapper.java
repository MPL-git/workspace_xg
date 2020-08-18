package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SourceProductTypeCustom;
import com.jf.entity.SourceProductTypeExample;

@Repository
public interface SourceProductTypeCustomMapper {
    int sourceProductTypeCountByExample(SourceProductTypeExample example);

    List<SourceProductTypeCustom> sourceProductTypeCustomselectByExample(SourceProductTypeExample example);

    SourceProductTypeCustom sourceProductTypeCustomselectByPrimaryKey(Integer id);
}