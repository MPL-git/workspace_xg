package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtInfoChangeLogCustom;
import com.jf.entity.MchtInfoChangeLogExample;
@Repository
public interface MchtInfoChangeLogCustomMapper {
    int countByExample(MchtInfoChangeLogExample example);

    List<MchtInfoChangeLogCustom> selectByExample(MchtInfoChangeLogExample example);

    MchtInfoChangeLogCustom selectByPrimaryKey(Integer id);
    
    public List<Map<String, Object>> selectCreatorByListExample(MchtInfoChangeLogExample example);

	int rejectAuditCount(HashMap<String, Object> paramMap);
}