package com.jf.dao;

import com.jf.entity.InformationCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InformationCustomMapper {

    List<InformationCustom> selectByExampleCustom(Map<String,Object> map);

    Integer selectCountCustom(Map<String, Object> map);
}